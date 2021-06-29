/**
 * Copyright (c) 2018 high哥工作室 All rights reserved.
 *
 * https://www.jchaoy.io
 *
 * 版权所有，侵权必究！
 */

package com.highy.modules.sys.service.impl;

import com.highy.common.constant.Constant;
import com.highy.common.page.PageData;
import com.highy.common.service.impl.BaseServiceImpl;
import com.highy.common.utils.ConvertUtils;
import com.highy.modules.security.user.SecurityUser;
import com.highy.modules.security.user.UserDetail;
import com.highy.modules.sys.dao.SysDictDao;
import com.highy.modules.sys.dto.SysDictDTO;
import com.highy.modules.sys.entity.SysDictEntity;
import com.highy.modules.sys.service.SysDictService;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 数据字典
 *
 * @author jchaoy 453428948@qq.com
 * @since 1.0.0
 */
@Service
public class SysDictServiceImpl extends BaseServiceImpl<SysDictDao, SysDictEntity> implements SysDictService {

    @Override
    public PageData<SysDictDTO> page(Map<String, Object> params) {
        QueryWrapper<SysDictEntity> wrapper = getWrapper(params);
        wrapper.eq("pid", Constant.DICT_ROOT);

        IPage<SysDictEntity> page = baseDao.selectPage(
            getPage(params, "sort", true),
            wrapper
        );

        return getPageData(page, SysDictDTO.class);
    }

    @Override
    public List<SysDictDTO> list(Map<String, Object> params) {
        List<SysDictEntity> entityList = baseDao.selectList(getWrapper(params));

        return ConvertUtils.sourceToTarget(entityList, SysDictDTO.class);
    }

    private QueryWrapper<SysDictEntity> getWrapper(Map<String, Object> params){
        String pid = (String) params.get("pid");
        String dictType = (String) params.get("dictType");
        String dictName = (String) params.get("dictName");
        String dictValue = (String) params.get("dictValue");
        String NE_DICT_ROOT = (String) params.get("neDictRoot");
        List valueList = (List) params.get("valueList");

        QueryWrapper<SysDictEntity> wrapper = new QueryWrapper<>();
        if(StringUtils.isNotBlank(pid)){
        	wrapper.eq(pid != null, "pid", Long.parseLong(pid));
        }
        if(StringUtils.isNotBlank(NE_DICT_ROOT)){
        	wrapper.ne("pid", Constant.DICT_ROOT);
        }
        wrapper.eq(StringUtils.isNotBlank(dictType), "dict_type", dictType);
        wrapper.like(StringUtils.isNotBlank(dictName), "dict_name", dictName);
        wrapper.like(StringUtils.isNotBlank(dictValue), "dict_value", dictValue);
        wrapper.in(null!=valueList && valueList.size()>0, "dict_value", valueList);

        return wrapper;
    }

    @Override
    public SysDictDTO get(Long id) {
        SysDictEntity entity = baseDao.selectById(id);

        return ConvertUtils.sourceToTarget(entity, SysDictDTO.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(SysDictDTO dto) {
        SysDictEntity entity = ConvertUtils.sourceToTarget(dto, SysDictEntity.class);

        insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SysDictDTO dto) {
        SysDictEntity entity = ConvertUtils.sourceToTarget(dto, SysDictEntity.class);

        updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long[] ids) {
        //删除
        deleteBatchIds(Arrays.asList(ids));
    }

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void add(SysDictDTO dto) {
		// 先判断是否存在该类型的，如果不存在先新增大类，再新增小类
		if(StringUtils.isNotBlank(dto.getDictType())){
			UserDetail user = SecurityUser.getUser();
			
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("dictType", dto.getDictType());
			params.put("pid", "0");
			
			List<SysDictEntity> entityList = baseDao.selectList(getWrapper(params));
			if(null!=entityList && !entityList.isEmpty() && entityList.size()>0){
				SysDictEntity pentity = entityList.get(0);
				SysDictEntity entity = ConvertUtils.sourceToTarget(dto, SysDictEntity.class);
				entity.setDictName(dto.getDictValue());
				entity.setCreator(user.getId());
				entity.setUpdateDate(new Date());
				entity.setUpdater(user.getId());
				entity.setPid(pentity.getId());
				
				insert(entity);
			}else{// 不存在时先新增
				SysDictEntity pentity = new SysDictEntity();
				pentity.setCreateDate(new Date());
				pentity.setCreator(user.getId());
				pentity.setDictName(dto.getDictName());
				pentity.setDictType(dto.getDictType());
				pentity.setPid(new Long(0));
				pentity.setUpdateDate(new Date());
				pentity.setUpdater(user.getId());
				pentity.setSort(0);
				
				insert(pentity);
				
				SysDictEntity entity = ConvertUtils.sourceToTarget(dto, SysDictEntity.class);
				entity.setDictName(dto.getDictValue());
				entity.setCreator(user.getId());
				entity.setUpdateDate(new Date());
				entity.setUpdater(user.getId());
				entity.setPid(pentity.getId());
				
				insert(entity);
			}
		}
	}

	@Override
	public Map<String, List<SysDictDTO>> paramList(Map<String, String> params) {
		Map<String, List<SysDictDTO>> resultMap = new HashMap<String, List<SysDictDTO>>();
		
		if(null!=params && !params.isEmpty()){
			for(Map.Entry<String,String> entry : params.entrySet()){
				String key = entry.getKey();
				String dictType = params.get(key);
				
				QueryWrapper<SysDictEntity> wrapper = new QueryWrapper<>();
				wrapper.eq(StringUtils.isNotBlank(dictType), "dict_type", dictType);
				wrapper.ne("pid", Constant.DICT_ROOT);
				
				List<SysDictEntity> entityList = baseDao.selectList(wrapper);
				resultMap.put(key, ConvertUtils.sourceToTarget(entityList, SysDictDTO.class));
			}
		}
		return resultMap;
	}

}