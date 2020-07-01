package com.sxquan.manage.common.shiro;


import com.sxquan.core.pojo.system.SystemMenu;
import com.sxquan.core.pojo.system.SystemRole;
import com.sxquan.core.pojo.system.SystemUser;
import com.sxquan.manage.system.service.ISystemMenuService;
import com.sxquan.manage.system.service.ISystemRoleService;
import com.sxquan.manage.system.service.ISystemUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.crazycake.shiro.RedisCacheManager;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Title ShiroRealm
 * @Description
 * @Author sxquan
 * @Date 2019/12/12 19:36
 */
@Slf4j
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private ISystemUserService systemUserService;

    @Autowired
    private ISystemRoleService systemRoleService;

    @Autowired
    private ISystemMenuService systemMenuService;

    @Autowired
    private RedisCacheManager redisCacheManager;



    /**
     * 授权模块，获取用户角色和权限
     *
     * @param principalCollection
     * @return org.apache.shiro.authz.AuthorizationInfo
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("执行权限.........................");
        Subject subject = SecurityUtils.getSubject();
        SystemUser systemUser = (SystemUser) subject.getPrincipal();

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //设置角色集
        List<SystemRole> roleList = systemRoleService.findRoleByUserId(systemUser.getSystemUserId());
        Set<String> roleSet = roleList.stream().map(SystemRole::getRoleName).collect(Collectors.toSet());
        simpleAuthorizationInfo.setRoles(roleSet);

        // 给shiro设置权限
        List<Long> roleIds = roleList.stream().map(SystemRole::getRoleId).collect(Collectors.toList());
        List<SystemMenu> menuList = systemMenuService.findMenuByRoleIds(roleIds);
        Set<String> menuSet = menuList.stream().map(SystemMenu::getPerms).collect(Collectors.toSet());
        simpleAuthorizationInfo.setStringPermissions(menuSet);

        return simpleAuthorizationInfo;
    }
    /**
     * 认证
     *
     * @param token
     * @return org.apache.shiro.authc.AuthenticationInfo
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws IncorrectCredentialsException {
        String username = (String) token.getPrincipal();
        SystemUser systemUser = systemUserService.findUserByUsername(username);
        if (systemUser == null) {
            throw new UnknownAccountException("账户不存在");
        }
        if (SystemUser.STATUS_LOCK.equals(systemUser.getStatus())) {
            throw new LockedAccountException("账号已被锁定");
        }
        //创建盐
        ByteSource credentialsSalt = ByteSource.Util.bytes(systemUser.getUsername());
        //返回密码
        return new SimpleAuthenticationInfo(systemUser,systemUser.getPassword(),credentialsSalt,this.getName());
    }

    /**
     * 清除当前用户权限缓存
     * 使用方法：在需要清除用户权限的地方注入 ShiroRealm,
     * 然后调用其 clearCache方法。
     */
    public void clearCache() {
        log.debug("clear the currentUser authorizationInfo");
        PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
        super.clearCache(principals);
    }

    /**
     * 自定义方法：清除所有 授权缓存
     */
    public void clearAllCachedAuthorizationInfo() {
        getAuthorizationCache().clear();
    }

    /**
     * 清除用户权限缓存
     */
    public void clearCache(Long systemUserId) {
        log.debug("clear the user:"+systemUserId+" authorizationInfo");
        redisCacheManager.getCache("s");

    }
}
