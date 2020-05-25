package com.sxquan.manage.common.shiro;


import com.sxquan.manage.common.annotation.Helper;
import org.apache.shiro.authz.AuthorizationInfo;

/**
 * @Description
 * @Author sxquan
 * @Date 2020/2/1 16:06
 */
@Helper
public class ShiroHelper extends ShiroRealm {

    /**
     * 获取当前用户的角色和权限集合
     *
     * @return AuthorizationInfo
     */
    public AuthorizationInfo getCurrentuserAuthorizationInfo() {
        return super.doGetAuthorizationInfo(null);
    }
}