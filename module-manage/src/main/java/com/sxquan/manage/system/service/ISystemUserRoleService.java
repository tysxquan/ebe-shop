package com.sxquan.manage.system.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.sxquan.core.pojo.system.SystemUserRole;

import java.util.List;

/**
 * <p>
 * 用户角色关联表 服务类
 * </p>
 *
 * @author sxquan
 * @since 2020-02-03
 */
public interface ISystemUserRoleService extends IService<SystemUserRole> {

    /**
     * 通过用户 id 删除
     * @param userIds 用户 id
     */
    void deleteUserRoleByUserIds(List<String> userIds);

    /**
     * 通过角色 id 删除
     * @param roleIds 角色 id
     */
    void deleteUserRoleByRoleIds(List<String> roleIds);
}
