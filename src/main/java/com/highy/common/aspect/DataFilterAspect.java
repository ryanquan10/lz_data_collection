/**
 * Copyright (c) 2018 high哥工作室 All rights reserved.
 *
 * https://www.jchaoy.io
 *
 * 版权所有，侵权必究！
 */

package com.highy.common.aspect;

import cn.hutool.core.collection.CollUtil;
import com.highy.common.annotation.DataFilter;
import com.highy.common.constant.Constant;
import com.highy.common.exception.ErrorCode;
import com.highy.common.exception.RenException;
import com.highy.common.interceptor.DataScope;
import com.highy.modules.security.user.SecurityUser;
import com.highy.modules.security.user.UserDetail;
import com.highy.modules.sys.enums.SuperAdminEnum;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 数据过滤，切面处理类
 *
 * @author jchaoy 453428948@qq.com
 */
@Aspect
@Component
public class DataFilterAspect {

    @Pointcut("@annotation(com.highy.common.annotation.DataFilter)")
    public void dataFilterCut() {

    }

    @Before("dataFilterCut()")
    public void dataFilter(JoinPoint point) {
        Object params = point.getArgs()[0];
        if(params != null && params instanceof Map){
            UserDetail user = SecurityUser.getUser();

            //如果不是超级管理员，则进行数据过滤
            if(user.getSuperAdmin() == SuperAdminEnum.NO.value()) {
                Map map = (Map)params;
                String sqlFilter = getSqlFilter(user, point);
                map.put(Constant.SQL_FILTER, new DataScope(sqlFilter));
            }

            return ;
        }

        throw new RenException(ErrorCode.DATA_SCOPE_PARAMS_ERROR);
    }

    /**
     * 获取数据过滤的SQL
     */
    private String getSqlFilter(UserDetail user, JoinPoint point){
        MethodSignature signature = (MethodSignature) point.getSignature();
        DataFilter dataFilter = signature.getMethod().getAnnotation(DataFilter.class);
        //获取表的别名
        String tableAlias = dataFilter.tableAlias();
        if(StringUtils.isNotBlank(tableAlias)){
            tableAlias +=  ".";
        }

        StringBuilder sqlFilter = new StringBuilder();

        //查询条件前缀
        String prefix = dataFilter.prefix();
        if(StringUtils.isNotBlank(prefix)){
            sqlFilter.append(" ").append(prefix);
        }

        sqlFilter.append(" (");

        //部门ID列表
        List<Long> deptIdList = user.getDeptIdList();
        if(CollUtil.isNotEmpty(deptIdList)){
            sqlFilter.append(tableAlias).append(dataFilter.deptId());

            sqlFilter.append(" in(").append(StringUtils.join(deptIdList, ",")).append(")");
        }

        //查询本人数据
        if(CollUtil.isNotEmpty(deptIdList)){
            sqlFilter.append(" or ");
        }
        sqlFilter.append(tableAlias).append(dataFilter.userId()).append("=").append(user.getId());

        sqlFilter.append(")");

        return sqlFilter.toString();
    }
}