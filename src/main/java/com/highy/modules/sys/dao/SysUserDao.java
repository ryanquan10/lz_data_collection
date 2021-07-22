/**
 * Copyright (c) 2018 high哥工作室 All rights reserved.
 *
 * https://www.jchaoy.io
 *
 * 版权所有，侵权必究！
 */

package com.highy.modules.sys.dao;

import com.highy.common.dao.BaseDao;
import com.highy.modules.sys.entity.SysUserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 系统用户
 * 
 * @author jchaoy 453428948@qq.com
 */
@Mapper
public interface SysUserDao extends BaseDao<SysUserEntity> {

	List<SysUserEntity> getList(Map<String, Object> params);

	SysUserEntity getById(Long id);

	SysUserEntity getByUsername(String username);

	int updatePassword(@Param("id") Long id, @Param("newPassword") String newPassword);

	int updatePasswordByEmail(@Param("email")String email,@Param("newPassword")String newPassword);

	/**
	 * 根据部门ID，查询用户数
	 */
	int getCountByDeptId(Long deptId);
	
	SysUserEntity getUser(String username);

}