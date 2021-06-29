/**
 * Copyright (c) 2018 high哥工作室 All rights reserved.
 *
 * https://www.jchaoy.io
 *
 * 版权所有，侵权必究！
 */

package com.highy.modules.security.service.impl;

import com.highy.modules.security.user.UserDetail;
import com.highy.modules.sys.dao.SysMenuDao;
import com.highy.modules.sys.dao.SysRoleDataScopeDao;
import com.highy.modules.sys.dao.SysUserDao;
import com.highy.modules.sys.entity.SysUserEntity;
import com.highy.modules.security.service.ShiroService;
import com.highy.modules.sys.enums.SuperAdminEnum;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ShiroServiceImpl implements ShiroService {
    @Autowired
    private SysMenuDao sysMenuDao;
    @Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private SysRoleDataScopeDao sysRoleDataScopeDao;

    @Override
    public Set<String> getUserPermissions(UserDetail user) {
        //系统管理员，拥有最高权限
        List<String> permissionsList;
        if(user.getSuperAdmin() == SuperAdminEnum.YES.value()) {
            permissionsList = sysMenuDao.getPermissionsList();
        }else{
            permissionsList = sysMenuDao.getUserPermissionsList(user.getId());
        }

        //用户权限列表
        Set<String> permsSet = new HashSet<>();
        for(String permissions : permissionsList){
            if(StringUtils.isBlank(permissions)){
                continue;
            }
            permsSet.addAll(Arrays.asList(permissions.trim().split(",")));
        }

        return permsSet;
    }

    @Override
    public SysUserEntity getUser(Long userId) {
        return sysUserDao.selectById(userId);
    }

    @Override
    public SysUserEntity getByUsername(String username) {
        return sysUserDao.getByUsername(username);
    }

    @Override
    public List<Long> getDataScopeList(Long userId) {
        return sysRoleDataScopeDao.getDataScopeList(userId);
    }

	@Override
	public SysUserEntity getUser(String username) {
		return sysUserDao.getUser(username);
	}
}