package com.sxquan.manage.system.controller;


import com.sxquan.core.entity.ServerResponse;
import com.sxquan.core.exception.ShopException;
import com.sxquan.core.pojo.system.SystemMenu;
import com.sxquan.core.pojo.system.SystemUser;
import com.sxquan.manage.common.annotation.ControllerEndpoint;
import com.sxquan.manage.system.pojo.bo.MenuTreeBO;
import com.sxquan.manage.system.service.ISystemMenuService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * <p>
 * 系统菜单表 前端控制器
 * </p>
 *
 * @author sxquan
 * @since 2019-12-20
 */
@Validated
@RestController
@RequestMapping("menu")
public class SystemMenuController {

    @Autowired
    ISystemMenuService systemMenuService;

    @GetMapping("{username}")
    public ServerResponse getUserMenus(@NotBlank(message = "{required}") @PathVariable String username) {
        SystemUser systemUser = (SystemUser) SecurityUtils.getSubject().getPrincipal();
        if (!StringUtils.equalsIgnoreCase(systemUser.getUsername(),username)) {
            throw new ShopException("你无权获取他人的菜单");
        }
        List<MenuTreeBO> userMenu = systemMenuService.findUserMenu(username);
        return ServerResponse.success(userMenu);
    }

    @GetMapping("list")
    @RequiresPermissions("menu:view")
    public ServerResponse menuList(Long parentId) {
        if (ObjectUtils.isEmpty(parentId)) {
            parentId = SystemMenu.PARENT_ROOT;
        }
        return ServerResponse.success(systemMenuService.listMenu(parentId));
    }

    @GetMapping("loadTree")
    @RequiresPermissions("menu:view")
    public ServerResponse getMenuTree() {
        return ServerResponse.success(systemMenuService.findMenuTree());
    }

    @GetMapping("load")
    @RequiresPermissions("menu:view")
    public ServerResponse getLoadMenu(){
        return ServerResponse.success(systemMenuService.findMenuExcludeByTypeBtn());
    }

    @PostMapping()
    @RequiresPermissions("menu:add")
    @ControllerEndpoint(operation = "新增菜单",exceptionMessage = "新增菜单失败")
    public ServerResponse addMenu(@Validated SystemMenu systemMenu) {
        if (systemMenu.getParentId() == null) {
            systemMenu.setParentId(SystemMenu.PARENT_ROOT);
        }
        return ServerResponse.success(systemMenuService.save(systemMenu));
    }

    @PutMapping()
    @RequiresPermissions("menu:update")
    @ControllerEndpoint(operation = "修改菜单",exceptionMessage = "修改菜单失败")
    public ServerResponse updateMenu(@Validated SystemMenu systemMenu) {
        if (systemMenu.getMenuId().longValue() == systemMenu.getParentId().longValue()) {
            return ServerResponse.error(HttpStatus.BAD_REQUEST.value(),"上级菜单不能是自己！");
        }
        return ServerResponse.success(systemMenuService.updateById(systemMenu));
    }

    @DeleteMapping("{menuId}")
    @RequiresPermissions("menu:delete")
    @ControllerEndpoint(operation = "删除菜单",exceptionMessage = "删除菜单失败")
    public ServerResponse deleteMenu(@PathVariable Long menuId) {
        systemMenuService.delMenuByMenuIdAndParentId(menuId);
        return ServerResponse.success();
    }
}

