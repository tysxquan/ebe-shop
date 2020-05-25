package com.sxquan.manage.system.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.sxquan.manage.system.pojo.SystemRoleMenu;

import java.util.List;

/**
 * <p>
 * 角色菜单关联表 服务类
 * </p>
 *
 * @author sxquan
 * @since 2020-02-03
 */
public interface ISystemRoleMenuService extends IService<SystemRoleMenu> {

    /**
     * 通过角色 id 删除
     *
     * @param roleIds 角色 id
     */
    void deleteRoleMenusByRoleId(List<String> roleIds);

    /**
     * 通过菜单（按钮）id 删除
     *
     * @param menuIds 菜单（按钮）id
     */
    void deleteRoleMenusByMenuId(List<String> menuIds);

    /**
     * 通过roleId查找权限菜单
     *
     * @param roleId 角色id
     * @return 权限集
     */
    List<SystemRoleMenu> findRoleMenuByRoleId(Long roleId);

}
