/**
 * Copyright (c) 2018 high哥工作室 All rights reserved.
 *
 * https://www.jchaoy.io
 *
 * 版权所有，侵权必究！
 */

package com.highy.modules.security.service;

import com.highy.modules.security.user.UserDetail;
import com.highy.modules.sys.entity.SysUserEntity;

import java.util.List;
import java.util.Set;

/**
 * shiro相关接口
 *
 * @author jchaoy 453428948@qq.com
 */
public interface ShiroService {
    /**
     * 获取用户权限列表
     */
    Set<String> getUserPermissions(UserDetail user);

    /**
     * 根据用户ID，查询用户
     * @param userId
     */
    SysUserEntity getUser(Long userId);

    /**
     * 根据用户名，查询用户
     * @param username
     */
    SysUserEntity getByUsername(String username);

    /**
     * 获取用户对应的部门数据权限
     * @param userId  用户ID
     * @return        返回部门ID列表
     */
    List<Long> getDataScopeList(Long userId);
    
    /**
     * 根据用户名、邮箱、手机号，查询用户
     * @param username
     * @return
     * @time 20200922
     */
    SysUserEntity getUser(String username);
}
