/**
 * Copyright (c) 2018 high哥工作室 All rights reserved.
 *
 * https://www.jchaoy.io
 *
 * 版权所有，侵权必究！
 */

package com.highy.modules.security.user;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * 用户
 *
 * @author jchaoy 453428948@qq.com
 */
public class SecurityUser {

    public static Subject getSubject() {
        try {
            return SecurityUtils.getSubject();
        }catch (Exception e){
            return null;
        }
    }

    /**
     * 获取用户信息
     */
    public static UserDetail getUser() {
        Subject subject = getSubject();
        if(subject == null){
            return new UserDetail();
        }

        UserDetail user = (UserDetail)subject.getPrincipal();
        if(user == null){
            return new UserDetail();
        }

        return user;
    }

    /**
     * 获取用户ID
     */
    public static Long getUserId() {
        return getUser().getId();
    }

    /**
     * 获取部门ID
     */
    public static Long getDeptId() {
        return getUser().getDeptId();
    }
}