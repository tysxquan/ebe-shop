package com.sxquan.manage.system.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.sxquan.core.pojo.system.SystemMenu;
import com.sxquan.manage.system.pojo.bo.MenuTreeBO;

import java.util.List;

/**
 * <p>
 * 系统菜单表 服务类
 * </p>
 *
 * @author sxquan
 * @since 2019-12-20
 */
public interface ISystemMenuService extends IService<SystemMenu> {

    /**
     *  根据角色id查询菜单权限
     *
     * @param roleIds 角色id集
     * @return 角色菜单集
     */
    List<SystemMenu> findMenuByRoleIds(List<Long> roleIds);

    /**
     *  查找用户菜单集合
     *
     * @param username
     * @return 用户菜单集合
     */
    List<MenuTreeBO> findUserMenu(String username);

    /**
     *  查找菜单
     *
     * @param parentId 筛选条件
     * @return 菜单集合
     */
    List<SystemMenu> listMenu(Long parentId);

    /**
     *  查找目录以及菜单数据（排除type：按钮）
     *
     * @return
     */
    List<SystemMenu> findMenuExcludeByTypeBtn();

    /**
     *  通过d查询菜单详情
     * @param menuId
     * @return
     */
    SystemMenu findMenuById(Long menuId);

    /**
     *  删除菜单（按钮）
     * @param menuId 主键
     */
    void delMenuByMenuIdAndParentId(Long menuId);

    /**
     *  查询菜单树 （树形结构）
     * @return 菜单树
     */
    List<MenuTreeBO> findMenuTree();
}
