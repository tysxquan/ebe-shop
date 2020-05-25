package com.sxquan.manage.system.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.sxquan.core.constant.SystemConstant;
import com.sxquan.core.entity.LayuiPage;
import com.sxquan.core.entity.RequestPage;
import com.sxquan.core.entity.ServerResponse;
import com.sxquan.core.exception.ShopException;
import com.sxquan.manage.common.annotation.ControllerEndpoint;
import com.sxquan.manage.system.pojo.SystemUser;
import com.sxquan.manage.system.service.ISystemUserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 系统用户表 前端控制器
 * </p>
 *
 * @author sxquan
 * @since 2019-12-20
 */
@RestController
@Validated
@RequestMapping("/system-user")
public class SystemUserController {

    @Autowired
    ISystemUserService systemUserService;

    @GetMapping("list")
    @RequiresPermissions("user:view")
    public ServerResponse userList(SystemUser systemUser, RequestPage requestPage){
        String createTimeRange = systemUser.getCreateTimeRange();
        if (StringUtils.isNotBlank(createTimeRange)){
            String[] split = StringUtils.splitByWholeSeparator(createTimeRange, SystemConstant.SEPARATOR_MINUS);
            systemUser.setCreateTimeStart(split[0]);
            systemUser.setCreateTimeEnd(split[1]+" 23:59:59");
        }
        IPage<SystemUser> page = systemUserService.listUser(systemUser, requestPage);
        return ServerResponse.success(new LayuiPage<>(page.getRecords(),page.getTotal()));
    }

    @GetMapping("check/{username}")
    public boolean checkUserName(@NotBlank(message = "{required}") @PathVariable("username")String username,Long userId){
        SystemUser systemUser = systemUserService.findUserByUsername(username);
        if (userId == null) {
            return systemUser != null;
        }
        if (systemUser != null) {
            return !systemUser.getSystemUserId().equals(userId);
        }
        return  false;
    }

    @PutMapping("avatar")
    @ControllerEndpoint(operation = "修改头像",exceptionMessage = "修改头像失败")
    public ServerResponse updateUserAvatar(@NotNull(message = "{required}") String avatar){
        SystemUser systemUser = (SystemUser) SecurityUtils.getSubject().getPrincipal();
        systemUserService.updateAvatar(systemUser.getSystemUserId(),avatar);
        return ServerResponse.success();
    }

    @PutMapping("profile/update")
    @ControllerEndpoint(operation = "更新个人信息",exceptionMessage = "更新个人信息失败")
    public ServerResponse updateProfile(SystemUser systemUser){
        systemUser.setPassword(null);
        systemUser.setRoleIds(null);
        systemUser.setUsername(null);
        systemUser.setStatus(null);
        systemUserService.updateById(systemUser);
        return ServerResponse.success();
    }

    @PutMapping("password/update")
    @ControllerEndpoint(exceptionMessage = "修改密码失败")
    public ServerResponse updatePassword(
            @NotBlank(message = "{required}") String oldPassword,
            @NotBlank(message = "{required}")  @Size(min = 6, max = 18, message = "{range}") String newPassword) {
        SystemUser systemUser = (SystemUser) SecurityUtils.getSubject().getPrincipal();
        if (!StringUtils.equals(systemUser.getPassword(),new Md5Hash( oldPassword,systemUser.getUsername(),2).toString())) {
            throw new ShopException("原密码不正确");
        }
        systemUserService.updatePassword(systemUser.getUsername(), newPassword);
        return ServerResponse.success();
    }

    @PutMapping()
    @RequiresPermissions("user:update")
    @ControllerEndpoint(operation = "修改用户",exceptionMessage = "修改用户失败")
    public ServerResponse updateUser(@Validated SystemUser systemUser) {
        systemUserService.updateUser(systemUser);
        return ServerResponse.success();
    }

    @PostMapping()
    @RequiresPermissions("user:add")
    @ControllerEndpoint(operation = "添加用户", exceptionMessage = "添加用户失败")
    public ServerResponse addUser(@Validated SystemUser systemUser) {
        systemUser.setPassword(new Md5Hash(SystemUser.DEFAULT_PASSWORD,systemUser.getUsername(),2).toString());
        systemUser.setAvatar(SystemUser.DEFAULT_AVATAR);
        SystemUser user = systemUserService.findUserByUsername(systemUser.getUsername());
        if (user != null) {
            return ServerResponse.error(HttpStatus.BAD_REQUEST.value(),"用户已存在");
        }
        systemUserService.addUser(systemUser);
        return ServerResponse.success();
    }

    @DeleteMapping("{userIds}")
    @RequiresPermissions("user:delete")
    @ControllerEndpoint(exceptionMessage = "删除用户失败")
    public ServerResponse deleteUser(@PathVariable("userIds") String userIds){
        String[] split = StringUtils.split(userIds, StringPool.COMMA);
        List<String> list = Arrays.asList(split);
        systemUserService.deleteUser(list);
        return ServerResponse.success();
    }

    @PutMapping("password/reset/{username}")
    @RequiresPermissions("user:password:reset")
    @ControllerEndpoint(exceptionMessage = "重置用户密码失败")
    public ServerResponse resetPassword(@PathVariable("username")String username){
        String[] split = StringUtils.split(username, StringPool.COMMA);
        systemUserService.resetPasswordByIds(split);
        return ServerResponse.success();
    }
}

