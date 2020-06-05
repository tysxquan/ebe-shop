package com.sxquan.manage.system.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sxquan.core.pojo.system.SystemRoleMenu;
import com.sxquan.manage.system.mapper.SystemRoleMenuMapper;
import com.sxquan.manage.system.service.ISystemRoleMenuService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 角色菜单关联表 服务实现类
 * </p>
 *
 * @author sxquan
 * @since 2020-02-03
 */
@Service
public class SystemRoleMenuServiceImpl extends ServiceImpl<SystemRoleMenuMapper, SystemRoleMenu> implements ISystemRoleMenuService {

    @Override
    public void deleteRoleMenusByRoleId(List<String> roleIds) {
        this.baseMapper.delete(new LambdaQueryWrapper<SystemRoleMenu>().in(SystemRoleMenu::getRoleId,roleIds));
    }

    @Override
    public void deleteRoleMenusByMenuId(List<String> menuIds) {
        this.baseMapper.delete(new LambdaQueryWrapper<SystemRoleMenu>().in(SystemRoleMenu::getMenuId,menuIds));
    }

    @Override
    public List<SystemRoleMenu> findRoleMenuByRoleId(Long roleId) {
        return this.baseMapper.selectList(new LambdaQueryWrapper<SystemRoleMenu>().eq(SystemRoleMenu::getRoleId,roleId));
    }
}
