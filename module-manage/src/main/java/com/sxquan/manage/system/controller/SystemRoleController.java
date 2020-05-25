package com.sxquan.manage.system.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.sxquan.core.entity.LayuiPage;
import com.sxquan.core.entity.RequestPage;
import com.sxquan.core.entity.ServerResponse;
import com.sxquan.manage.common.annotation.ControllerEndpoint;
import com.sxquan.manage.system.pojo.SystemRole;
import com.sxquan.manage.system.service.ISystemRoleService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 系统角色表 前端控制器
 * </p>
 *
 * @author sxquan
 * @since 2019-12-20
 */
@Validated
@RestController
@RequestMapping("role")
public class SystemRoleController {

    @Autowired
    private ISystemRoleService systemRoleService;



    @GetMapping("all")
    @RequiresPermissions("role:view")
    public ServerResponse getRoleAll(){
        return ServerResponse.success(systemRoleService.findRoleAll());
    }

    @GetMapping("list")
    @RequiresPermissions("role:view")
    public ServerResponse roleList(String roleName, Integer status, RequestPage requestPage) {
        IPage<SystemRole> rolePage = systemRoleService.listRolePage(roleName, status, requestPage);
        return ServerResponse.success(new LayuiPage<>(rolePage.getRecords(),rolePage.getTotal()));
    }

    @PostMapping()
    @RequiresPermissions("role:add")
    @ControllerEndpoint(operation = "新增角色",exceptionMessage = "新增角色失败")
    public ServerResponse addRole(@Validated SystemRole systemRole) {
        systemRoleService.addRole(systemRole);
        return ServerResponse.success();
    }

    @PutMapping()
    @RequiresPermissions("role:update")
    @ControllerEndpoint(operation = "修改角色",exceptionMessage = "修改角色失败")
    public ServerResponse updateRole(@Validated SystemRole systemRole) {
        systemRoleService.updateByRole(systemRole);
        return ServerResponse.success();
    }

    @DeleteMapping("{roleIds}")
    @RequiresPermissions("role:delete")
    @ControllerEndpoint(operation = "删除角色",exceptionMessage = "删掉角色失败")
    public ServerResponse deleteRole(@PathVariable String roleIds) {
        String[] split = StringUtils.split(roleIds, StringPool.COMMA);
        List<String> list = Arrays.asList(split);
        boolean isExist = list.stream().anyMatch( x -> Long.parseLong(x) < SystemRole.NOT_DELETE_RANGE);
        if (isExist) {
           return ServerResponse.error(HttpStatus.BAD_REQUEST.value(),"含有不可删除的数据");
        }
        systemRoleService.deleteRole(list);
        return ServerResponse.success();
    }


}

