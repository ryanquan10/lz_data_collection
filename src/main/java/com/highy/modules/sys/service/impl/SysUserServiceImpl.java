/**
 * Copyright (c) 2018 high哥工作室 All rights reserved.
 *
 * https://www.jchaoy.io
 *
 * 版权所有，侵权必究！
 */

package com.highy.modules.sys.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.highy.common.constant.Constant;
import com.highy.common.page.PageData;
import com.highy.common.service.impl.BaseServiceImpl;
import com.highy.common.utils.ConvertUtils;
import com.highy.modules.message.email.EmailConfig;
import com.highy.modules.message.email.EmailUtils;
import com.highy.modules.security.password.PasswordUtils;
import com.highy.modules.security.user.SecurityUser;
import com.highy.modules.security.user.UserDetail;
import com.highy.modules.sys.dao.SysUserDao;
import com.highy.modules.sys.dto.SysUserDTO;
import com.highy.modules.sys.entity.CaptchaRecordEntity;
import com.highy.modules.sys.entity.SysUserEntity;
import com.highy.modules.sys.enums.SuperAdminEnum;
import com.highy.modules.sys.service.CaptchaRecordService;
import com.highy.modules.sys.service.SysDeptService;
import com.highy.modules.sys.service.SysParamsService;
import com.highy.modules.sys.service.SysRoleUserService;
import com.highy.modules.sys.service.SysUserService;


/**
 * 系统用户
 * 
 * @author jchaoy 453428948@qq.com
 */
@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUserDao, SysUserEntity> implements SysUserService {
	@Autowired
	private SysRoleUserService sysRoleUserService;
	@Autowired
	@Lazy
	private SysDeptService sysDeptService;
	@Autowired
    private SysParamsService sysParamsService;
	@Autowired
	private CaptchaRecordService captchaRecordService;
	@Autowired
	private EmailUtils emailUtils;
	
	@Override
	public PageData<SysUserDTO> page(Map<String, Object> params) {
		//转换成like
		paramsToLike(params, "username");

		//分页
		IPage<SysUserEntity> page = getPage(params, Constant.CREATE_DATE, false);

		//普通管理员，只能查询所属部门及子部门的数据
		UserDetail user = SecurityUser.getUser();
		if(user.getSuperAdmin() == SuperAdminEnum.NO.value()) {
			params.put("deptIdList", sysDeptService.getSubDeptIdList(user.getDeptId()));
		}

		//查询
		List<SysUserEntity> list = baseDao.getList(params);

		return getPageData(list, page.getTotal(), SysUserDTO.class);
	}

	@Override
	public List<SysUserDTO> list(Map<String, Object> params) {
		//普通管理员，只能查询所属部门及子部门的数据
		UserDetail user = SecurityUser.getUser();
		if(user.getSuperAdmin() == SuperAdminEnum.NO.value()) {
			params.put("deptIdList", sysDeptService.getSubDeptIdList(user.getDeptId()));
		}

		List<SysUserEntity> entityList = baseDao.getList(params);

		return ConvertUtils.sourceToTarget(entityList, SysUserDTO.class);
	}

	@Override
	public SysUserDTO get(Long id) {
		SysUserEntity entity = baseDao.getById(id);

		return ConvertUtils.sourceToTarget(entity, SysUserDTO.class);
	}

	@Override
	public SysUserDTO getByUsername(String username) {
		SysUserEntity entity = baseDao.getUser(username);
		return ConvertUtils.sourceToTarget(entity, SysUserDTO.class);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void save(SysUserDTO dto) {
		SysUserEntity entity = ConvertUtils.sourceToTarget(dto, SysUserEntity.class);

		//密码加密
		String password = PasswordUtils.encode(entity.getPassword());
		entity.setPassword(password);

		//保存用户
		entity.setSuperAdmin(SuperAdminEnum.NO.value());
		insert(entity);

		//保存角色用户关系
		sysRoleUserService.saveOrUpdate(entity.getId(), dto.getRoleIdList());
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void update(SysUserDTO dto) {
		SysUserEntity entity = ConvertUtils.sourceToTarget(dto, SysUserEntity.class);

		//密码加密
		if(StringUtils.isBlank(dto.getPassword())){
			entity.setPassword(null);
		}else{
			String password = PasswordUtils.encode(entity.getPassword());
			entity.setPassword(password);
		}

		//更新用户
		updateById(entity);

		//更新角色用户关系
		sysRoleUserService.saveOrUpdate(entity.getId(), dto.getRoleIdList());
	}

	@Override
	public void delete(Long[] ids) {
		//删除用户
		baseDao.deleteBatchIds(Arrays.asList(ids));

		//删除角色用户关系
		sysRoleUserService.deleteByUserIds(ids);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updatePassword(Long id, String newPassword) {
		newPassword = PasswordUtils.encode(newPassword);

		baseDao.updatePassword(id, newPassword);
	}


	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updatePasswordByEmail(String email, String newPassword) {
		newPassword = PasswordUtils.encode(newPassword);

		baseDao.updatePasswordByEmail(email, newPassword);
	}


	@Override
	public int getCountByDeptId(Long deptId) {
		return baseDao.getCountByDeptId(deptId);
	}

	/**
	 * 发送验证码
	 * @throws Exception 
	 */
	@Override
	public void sendCaptcha(String email, String captcha) throws Exception {
		if(StringUtils.isNotBlank(email) && StringUtils.isNotBlank(captcha)){
			EmailConfig config = sysParamsService.getValueObject(Constant.MAIL_CONFIG_KEY, EmailConfig.class);
			// 查找是否发送验证码到该邮箱，若已发送直接更新
			CaptchaRecordEntity entity = this.captchaRecordService.findByRecipient(email);
			if(null==entity){
				entity = new CaptchaRecordEntity();
				entity.setCreateDate(new Date());
			}
			entity.setRecipientEmail(email);
			if(null!=config){
				entity.setSenderEmail(config.getUsername());
			}else{
				entity.setSenderEmail("464552119@qq.com");
			}
			// 记录验证码
			entity.setCaptcha(captcha);
			entity.setUpdateTime(new Date());
        	//保存发送记录
			if(entity.getId()!=null){
				this.captchaRecordService.updateById(entity);
			}else{
				this.captchaRecordService.insert(entity);
			}
        	
        	String[] to = new String[]{entity.getRecipientEmail()};
            String[] cc = null;
        	// 发送邮件
        	emailUtils.userSendMail(to, cc, "账号注册验证码", "您注册账号"+email+"的验证码为：" + captcha + "。", null);
		}
		
	}

	@Override
	public void register(SysUserDTO dto) throws Exception {
		// 校验邮箱是否已注册
        SysUserDTO user = this.getByUsername(dto.getEmail());
        if(null!=user){
        	throw new Exception("邮箱已注册，请直接登录！");
        }
        // 校验验证码是否正确
        CaptchaRecordEntity captchaEntity = this.captchaRecordService.findByRecipient(dto.getEmail());
        if(null==captchaEntity || !captchaEntity.getCaptcha().equalsIgnoreCase(dto.getCaptcha())){
        	throw new Exception("验证码输入不正确或者失效，请重新输入！");
        }

        if(StringUtils.isBlank(dto.getUsername())){
        	dto.setUsername(dto.getEmail());
        }
        dto.setPassword(dto.getPassword());
        dto.setCreateDate(new Date());
        dto.setStatus(1);
        
        this.save(dto);
	}
}
