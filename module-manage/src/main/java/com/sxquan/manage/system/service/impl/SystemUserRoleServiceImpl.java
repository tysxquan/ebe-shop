package com.sxquan.manage.system.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sxquan.core.pojo.system.SystemUserRole;
import com.sxquan.manage.system.mapper.SystemUserRoleMapper;
import com.sxquan.manage.system.service.ISystemUserRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户角色关联表 服务实现类
 * </p>
 *
 * @author sxquan
 * @since 2020-02-03
 */
@Service
public class SystemUserRoleServiceImpl extends ServiceImpl<SystemUserRoleMapper, SystemUserRole> implements ISystemUserRoleService {

    @Override
    public void deleteUserRoleByUserIds(List<String> userIds) {
        this.baseMapper.delete(new LambdaQueryWrapper<SystemUserRole>().in(SystemUserRole::getSystemUserId,userIds));
    }

    @Override
    public void deleteUserRoleByRoleIds(List<String> roleIds) {
        this.baseMapper.delete(new LambdaQueryWrapper<SystemUserRole>().in(SystemUserRole::getRoleId,roleIds));
    }
}
