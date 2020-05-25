package com.sxquan.manage.common.util;


import com.sxquan.manage.system.pojo.SystemUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;

/**
 * @author sxquan
 * @since 2020/3/27 19:51
 */
@Slf4j
public class ShiroUtil {



    /**
     * 获取当前登录用户
     *
     * @return SystemUser
     */
    public static SystemUser getCurrentUser() {
        return (SystemUser) SecurityUtils.getSubject().getPrincipal();
    }



}
