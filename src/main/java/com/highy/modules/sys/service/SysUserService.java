/**
 * Copyright (c) 2018 high哥工作室 All rights reserved.
 *
 * https://www.jchaoy.io
 *
 * 版权所有，侵权必究！
 */

package com.highy.modules.sys.service;

import com.highy.common.page.PageData;
import com.highy.common.service.BaseService;
import com.highy.modules.sys.dto.SysUserDTO;
import com.highy.modules.sys.entity.SysUserEntity;

import java.util.List;
import java.util.Map;


/**
 * 系统用户
 * 
 * @author jchaoy 453428948@qq.com
 */
public interface SysUserService extends BaseService<SysUserEntity> {

	PageData<SysUserDTO> page(Map<String, Object> params);

	List<SysUserDTO> list(Map<String, Object> params);

	SysUserDTO get(Long id);

	SysUserDTO getByUsername(String username);

	void save(SysUserDTO dto);

	void update(SysUserDTO dto);

	void delete(Long[] ids);

	/**
	 * 修改密码
	 * @param id           用户ID
	 * @param newPassword  新密码
	 */
	void updatePassword(Long id, String newPassword);


	void updatePasswordByEmail(String email,String newPassowrd);

	/**
	 * 根据部门ID，查询用户数
	 */
	int getCountByDeptId(Long deptId);
	
	void sendCaptcha(String email, String captcha) throws Exception ;
	
	/**
	 * 注册
	 * @param dto
	 */
	void register(SysUserDTO dto) throws Exception ;
}
