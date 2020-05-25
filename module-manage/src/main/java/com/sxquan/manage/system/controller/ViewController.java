package com.sxquan.manage.system.controller;


import com.sxquan.core.util.ShopUtil;
import com.sxquan.core.util.LocalDateUtil;
import com.sxquan.manage.common.shiro.ShiroHelper;
import com.sxquan.manage.common.util.ShiroUtil;
import com.sxquan.manage.monitor.pojo.OperationLog;
import com.sxquan.manage.monitor.service.IOperationLogService;
import com.sxquan.manage.system.pojo.SystemMenu;
import com.sxquan.manage.system.pojo.SystemRole;
import com.sxquan.manage.system.pojo.SystemUser;
import com.sxquan.manage.system.service.ISystemMenuService;
import com.sxquan.manage.system.service.ISystemRoleService;
import com.sxquan.manage.system.service.ISystemUserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.ExpiredSessionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author sxquan
 * @Date 2020/2/1 16:06
 */
@Controller("/system-view")
public class ViewController {

    @Autowired
    private ShiroHelper shiroHelper;

    @Autowired
    private ISystemUserService systemUserService;

    @Autowired
    private ISystemRoleService systemRoleService;

    @Autowired
    private ISystemMenuService systemMenuService;

    @Autowired
    private IOperationLogService operationLogService;

    @GetMapping("index")
    public String index(Model model) {
        AuthorizationInfo authorizationInfo = shiroHelper.getCurrentuserAuthorizationInfo();
        SystemUser user = (SystemUser) SecurityUtils.getSubject().getPrincipal();
        SystemUser systemUser = systemUserService.findUserByUserId(user.getSystemUserId());
        model.addAttribute("user", systemUser);
        model.addAttribute("permissions", authorizationInfo.getStringPermissions());
        model.addAttribute("roles", authorizationInfo.getRoles());
        return "index";
    }

    @GetMapping("unauthorised")
    public String unauthorised(){return "error/403";}

    @GetMapping("login")
    public Object login(HttpServletRequest request) {
        if (ShopUtil.isAjaxRequest(request)) {
            throw new ExpiredSessionException();
        } else {
            ModelAndView mav = new ModelAndView();
            mav.setViewName("/login");
            return mav;
        }
    }

    @GetMapping("welcome")
    public String welcome() {return "welcome";}

    @GetMapping("system/user/userCentre/profile")
    public String systemUserCentreProfile(){
        return "system/user/userCentre/profile";
    }

    @GetMapping("system/user/userCentre/action")
    public String systemUserCentreAction(Model model) {
        SystemUser currentUser = ShiroUtil.getCurrentUser();
        List<OperationLog> userOperationLog = operationLogService.findUserOperationLog(currentUser.getSystemUserId());
        model.addAttribute("userOperationLogList",userOperationLog);
        return "system/user/userCentre/action";
    }

    @GetMapping("system/user/userCentre/avatar")
    public String systemUserCentreAvatar(){
        return "system/user/userCentre/avatar";
    }

    @GetMapping("system/user/userCentre/passwordUpdate")
    public String systemUserCentrePasswordUpdate(){
        return "system/user/userCentre/passwordUpdate";
    }

    @GetMapping("system/user")
    @RequiresPermissions("user:view")
    public String systemUser(){
        return "system/user/user";
    }

    @RequiresPermissions("user:view")
    @GetMapping("system/user/userInfo/{systemUserId}")
    public String systemUserInfo(@PathVariable("systemUserId") Long systemUserId,Model model){
        SystemUser systemUser = systemUserService.findUserByUserId(systemUserId);

        Integer sex = systemUser.getSex();
        if (SystemUser.SEX_MALE.equals(sex)) {
            systemUser.setSexValue("男");
        } else if (SystemUser.SEX_FEMALE.equals(sex)){
            systemUser.setSexValue("女");
        } else {
            systemUser.setSexValue("保密");
        }
        model.addAttribute("user",systemUser);
        if(systemUser.getLastLoginTime() != null) {
            model.addAttribute("lastLoginTime", LocalDateUtil.localDateTimeToString(systemUser.getLastLoginTime()));
        }
        return "system/user/userInfo";
    }


    @GetMapping("system/user/update/{systemUserId}")
    @RequiresPermissions("user:update")
    public String systemUserUpdate(@PathVariable("systemUserId") Long systemUserId,Model model){
        SystemUser systemUser = systemUserService.findUserByUserId(systemUserId);
        List<SystemRole> list = systemRoleService.findRoleByUserId(systemUserId);
        List<Long> roleIdList = list.stream().map(SystemRole::getRoleId).collect(Collectors.toList());
        systemUser.setRoleIds(StringUtils.join(roleIdList,","));
        model.addAttribute("user", systemUser);
        return "system/user/userUpdate";
    }

    @GetMapping("system/user/userCentre")
    public String systemUserCentre() {
        return "system/user/userCentre";
    }

    @GetMapping("403")
    public String error403(){
        return "error/403";
    }

    @GetMapping("404")
    public String error404(){
        return "error/404";
    }

    @GetMapping("500")
    public String Error500(){
        return "error/500";
    }

    @GetMapping("system/user/add")
    @RequiresPermissions("user:add")
    public String systemUserAdd() {return "system/user/userAdd";}

    @GetMapping("system/menu")
    @RequiresPermissions("menu:view")
    public String systemMenu() {return "system/menu/menu";}

    @RequiresPermissions("menu:add")
    @GetMapping("system/menu/add")
    public String systemMenuAdd() {return "system/menu/menuAdd";}

    @RequiresPermissions("menu:update")
    @GetMapping("system/menu/update/{menuId}")
    public String systemMenuUpdate(@PathVariable Long menuId,Model model) {
        SystemMenu menu = systemMenuService.findMenuById(menuId);
        model.addAttribute("menu",menu);
        return "system/menu/menuUpdate";
    }

    @RequiresPermissions("role:view")
    @GetMapping("system/role")
    public String systemRole() {return "system/role/role";}

    @RequiresPermissions("role:add")
    @GetMapping("system/role/add")
    public String systemRoleAdd() {return "system/role/roleAdd";}

    @RequiresPermissions("role:update")
    @GetMapping("system/role/update/{roleId}")
    public String systemRoleUpdate(@PathVariable Long roleId,Model model) {
        SystemRole role = systemRoleService.findRoleById(roleId);
        model.addAttribute("role",role);
        return "system/role/roleUpdate";
    }


}
