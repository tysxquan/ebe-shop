package com.sxquan.manage.system.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sxquan.core.pojo.system.SystemMenu;
import com.sxquan.core.pojo.system.SystemUser;
import com.sxquan.core.util.TreeUtils;
import com.sxquan.manage.system.mapper.SystemMenuMapper;
import com.sxquan.manage.system.mapper.SystemUserMapper;
import com.sxquan.manage.system.pojo.bo.MenuTreeBO;
import com.sxquan.manage.system.service.ISystemMenuService;
import com.sxquan.manage.system.service.ISystemRoleMenuService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 系统菜单表 服务实现类
 * </p>
 *
 * @author sxquan
 * @since 2019-12-20
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class SystemMenuServiceImpl extends ServiceImpl<SystemMenuMapper, SystemMenu> implements ISystemMenuService {

    @Resource
    SystemUserMapper systemUserMapper;

    @Autowired
    ISystemRoleMenuService systemRoleMenuService;

    @Override
    public List<SystemMenu> findMenuByRoleIds(List<Long> roleIds) {
        return this.baseMapper.findMenuByRoleIds(roleIds);
    }

    @Override
    public List<MenuTreeBO> findUserMenu(String username) {
        // 通过用户名查找用户
        SystemUser user = systemUserMapper.findUserByUsername(username);

        // 根据用户id查找菜单
        List<SystemMenu> userMenu = baseMapper.findUserMenuByUserId(user.getSystemUserId(),SystemMenu.TYPE_BTN);

        List<MenuTreeBO> menuTreeBoList = this.convertMenus(userMenu);

        return TreeUtils.getTree(menuTreeBoList);
    }

    @Override
    public List<SystemMenu> listMenu(Long parentId) {
        List<SystemMenu> list = baseMapper.selectList(new LambdaQueryWrapper<SystemMenu>()
                .eq(ObjectUtils.isNotEmpty(parentId),SystemMenu::getParentId, parentId)
                .orderByAsc(SystemMenu::getSortOrder));
        List<Long> parentIds = baseMapper.findParentIdsByParentId(parentId);
        list.forEach(x -> {
            boolean isExits = parentIds.stream().anyMatch(p -> p.longValue() == x.getMenuId().longValue());
            if (isExits) {
                x.setHaveChild(true);
            }
        });
        return list;
    }

    @Override
    public List<SystemMenu> findMenuExcludeByTypeBtn() {
        return this.baseMapper.selectList(new LambdaQueryWrapper<SystemMenu>().lt(SystemMenu::getType, SystemMenu.TYPE_BTN));
    }

    @Override
    public SystemMenu findMenuById(Long menuId) {
        return baseMapper.selectById(menuId);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void delMenuByMenuIdAndParentId(Long menuId) {
        //删除当前菜单
        baseMapper.deleteById(menuId);
        LambdaQueryWrapper<SystemMenu> queryWrapper = new LambdaQueryWrapper<SystemMenu>().eq(SystemMenu::getParentId, menuId);
        //获取需要删除的子项
        List<SystemMenu> systemMenus = baseMapper.selectList(queryWrapper);
        if (CollectionUtils.isNotEmpty(systemMenus)) {
            List<String> menuIdList = new ArrayList<>();
            //添加需要删除的当前项
            menuIdList.add(String.valueOf(menuId));
            //添加需要删除的子项
            systemMenus.stream().forEach(menu -> menuIdList.add(String.valueOf(menu.getMenuId())));
            //删除中间表的绑定
            systemRoleMenuService.deleteRoleMenusByMenuId(menuIdList);
            //删除子项菜单
            baseMapper.delete(queryWrapper);
        }

    }

    @Override
    public List<MenuTreeBO> findMenuTree() {
        //查询所有菜单数据
        List<SystemMenu> menus = this.baseMapper.selectList(new LambdaQueryWrapper<SystemMenu>().orderByAsc(SystemMenu::getSortOrder));
        //转换为MenuTreeBO
        List<MenuTreeBO> menuTreeList = this.convertMenusOne(menus);
        return TreeUtils.getTree(menuTreeList);
    }

    /**
     *  转换实体
     *
     * @param menuList
     * @return List<MenuTreeBO>
     */
    private List<MenuTreeBO> convertMenus(List<SystemMenu> menuList ){
        List<MenuTreeBO> menuTreeBOList = new ArrayList<>();
        menuList.forEach(menu -> {
            MenuTreeBO menuTreeBO = new MenuTreeBO();
            menuTreeBO.setNodeId(menu.getMenuId());
            menuTreeBO.setParentId(menu.getParentId());
            menuTreeBO.setMenuName(menu.getMenuName());
            menuTreeBO.setUrl(menu.getUrl());
            menuTreeBO.setIcon(menu.getIcon());
            menuTreeBOList.add(menuTreeBO);
        });
        return menuTreeBOList;
    }

    /**
     *  转换实体
     *
     * @param menuList
     * @return List<MenuTreeBO>
     */
    private List<MenuTreeBO> convertMenusOne(List<SystemMenu> menuList ){
        List<MenuTreeBO> menuTreeBOList = new ArrayList<>();
        menuList.forEach(menu -> {
            MenuTreeBO menuTreeBO = new MenuTreeBO();
            menuTreeBO.setNodeId(menu.getMenuId());
            menuTreeBO.setParentId(menu.getParentId());
            menuTreeBO.setMenuName(menu.getMenuName());
            menuTreeBOList.add(menuTreeBO);
        });
        return menuTreeBOList;
    }

}
