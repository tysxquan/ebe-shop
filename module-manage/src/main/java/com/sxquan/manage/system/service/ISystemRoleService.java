package com.sxquan.manage.system.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sxquan.core.entity.RequestPage;
import com.sxquan.manage.system.pojo.SystemRole;

import java.util.List;

/**
 * <p>
 * 系统角色表 服务类
 * </p>
 *
 * @author sxquan
 * @since 2019-12-20
 */
public interface ISystemRoleService extends IService<SystemRole> {

    /**
     * 通过用户名查找用户角色
     *
     * @param userId 用户名
     * @return 用户角色集合
     */
    List<SystemRole> findRoleByUserId(Long userId);

    /**
     * 查询所有的角色
     *
     * @return 角色集
     */
    List<SystemRole> findRoleAll();


    /**
     *  分页查询角色列表
     * @param roleName 角色名
     * @param status 状态
     * @param requestPage 分页参数
     * @return 角色集
     */
    IPage<SystemRole> listRolePage(String roleName, Integer status, RequestPage requestPage);

    /**
     *  通过roleId查询角色详情
     * @param roleId 角色id
     * @return
     */
    SystemRole findRoleById(Long roleId);

    /**
     * 更新角色(按钮)
     * @param systemRole 角色对象
     */
    void updateByRole(SystemRole systemRole);

    /**
     * 添加角色(按钮)
     * @param systemRole 角色对象
     */
    void addRole(SystemRole systemRole);

    /**
     * 删除角色（按钮）
     * @param roleIds 角色对象
     */
    void deleteRole(List<String> roleIds);
}
