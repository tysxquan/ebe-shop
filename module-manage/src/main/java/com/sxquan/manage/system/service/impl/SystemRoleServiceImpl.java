package com.sxquan.manage.system.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sxquan.core.entity.RequestPage;
import com.sxquan.core.pojo.system.SystemRole;
import com.sxquan.core.pojo.system.SystemRoleMenu;
import com.sxquan.manage.common.shiro.ShiroRealm;
import com.sxquan.manage.system.mapper.SystemRoleMapper;
import com.sxquan.manage.system.service.ISystemRoleMenuService;
import com.sxquan.manage.system.service.ISystemRoleService;
import com.sxquan.manage.system.service.ISystemUserRoleService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 系统角色表 服务实现类
 * </p>
 *
 * @author sxquan
 * @since 2019-12-20
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class SystemRoleServiceImpl extends ServiceImpl<SystemRoleMapper, SystemRole> implements ISystemRoleService {

    @Autowired
    private ISystemRoleMenuService systemRoleMenuService;

    @Autowired
    private ISystemUserRoleService systemUserRoleService;

    @Autowired
    private ShiroRealm shiroRealm;

    @Override
    public List<SystemRole> findRoleByUserId(Long userId) {
        return this.baseMapper.findRoleByUserId(userId);
    }

    @Override
    public List<SystemRole> findRoleAll() {
        return this.baseMapper.selectList(new LambdaQueryWrapper<SystemRole>().select(SystemRole::getRoleId,SystemRole::getRoleName,SystemRole::getStatus,SystemRole::getCreateTime));
    }

    @Override
    public IPage<SystemRole> listRolePage(String roleName, Integer status, RequestPage requestPage) {
        Page<SystemRole> page = new Page<>(requestPage.getPageNum(),requestPage.getPageSize());
        return this.baseMapper.selectPage(page, new LambdaQueryWrapper<SystemRole>()
                .like(StringUtils.isNotBlank(roleName),SystemRole::getRoleName, roleName)
                .eq(ObjectUtils.isNotEmpty(status),SystemRole::getStatus, status));
    }

    @Override
    public SystemRole findRoleById(Long roleId) {
        SystemRole systemRole = this.baseMapper.selectById(roleId);
        //查询权限集
        List<SystemRoleMenu> roleMenuList = systemRoleMenuService.findRoleMenuByRoleId(roleId);
        List<Long> collect = roleMenuList.stream().map(SystemRoleMenu::getMenuId).collect(Collectors.toList());
        systemRole.setMenuIds(StringUtils.join(collect, StringPool.COMMA));
        return systemRole;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void updateByRole(SystemRole systemRole) {
        //更新role表数据
        this.updateById(systemRole);
        List<String> roleIds = new ArrayList<>();
        roleIds.add(String.valueOf(systemRole.getRoleId()));
        //删除当前roleId的所有菜单
        systemRoleMenuService.deleteRoleMenusByRoleId(roleIds);
        //保存当前修改的菜单
        saveRoleMenus(systemRole);
        //清除所有权限缓存
        shiroRealm.clearAllCachedAuthorizationInfo();
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void addRole(SystemRole systemRole) {
        //更新role表数据
        this.save(systemRole);
        saveRoleMenus(systemRole);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void deleteRole(List<String> roleIds) {
        //删除role表数据
        this.baseMapper.deleteBatchIds(roleIds);
        //删除与menu中间表的数据
        systemRoleMenuService.deleteRoleMenusByRoleId(roleIds);
        //删除与user中间表的数据
        systemUserRoleService.deleteUserRoleByRoleIds(roleIds);
    }

    private void saveRoleMenus(SystemRole systemRole) {
        if (StringUtils.isNotBlank(systemRole.getMenuIds())) {
            String[] menuIds = systemRole.getMenuIds().split(StringPool.COMMA);
            List<SystemRoleMenu> roleMenus = new ArrayList<>();
            Arrays.stream(menuIds).forEach(menuId -> {
                SystemRoleMenu roleMenu = new SystemRoleMenu();
                roleMenu.setMenuId(Long.valueOf(menuId));
                roleMenu.setRoleId(systemRole.getRoleId());
                roleMenus.add(roleMenu);
            });
            systemRoleMenuService.saveBatch(roleMenus);
        }
    }
}
