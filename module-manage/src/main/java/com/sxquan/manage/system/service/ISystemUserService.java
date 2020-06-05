package com.sxquan.manage.system.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sxquan.core.entity.RequestPage;
import com.sxquan.core.pojo.system.SystemUser;

import java.util.List;

/**
 * <p>
 * 系统用户表 服务类
 * </p>
 *
 * @author sxquan
 * @since 2019-12-20
 */
public interface ISystemUserService extends IService<SystemUser> {

    /**
     * 通过用户名查找系统用户
     *
     * @param username 用户名
     * @return SystemUser 用户对象
     */
    SystemUser findUserByUsername(String username);
    /**
     * 分页查询用户列表
     *
     * @param systemUser 用户对象
     * @param request 分页数据对象
     * @return 用户集合
     */
    IPage<SystemUser> listUser(SystemUser systemUser, RequestPage request);
    /**
     * 注册
     *
     * @param username 用户名
     * @param password 密码
     */
    void register(String username, String password);

    /**
     * 通过id查找系统用户
     *
     * @param systemUserId 用户id
     * @return SystemUser 用户对象
     */
    SystemUser findUserByUserId(Long systemUserId);

    /**
     * 批量重置密码
     *
     * @param usernames 用户名组
     */
    void resetPasswordByIds(String[] usernames);

    /**
     * 添加用户（按钮）
     *
     * @param systemUser 用户对象
     */
    void addUser(SystemUser systemUser);

    /**
     * 更新用户（按钮）
     *
     * @param systemUser 用户对象
     */
    void updateUser(SystemUser systemUser);

    /**
     * 删除用户（按钮）
     *
     * @param userIds 用户 id
     */
    void deleteUser(List<String> userIds);

    /**
     *更新用户登录时间
     * @param username 用户名
     */
    void updateLoginTime(String username);

    /**
     *更新用户头像
     * @param avatar 头像名
     * @param systemUserId 主键
     */
    void updateAvatar(Long systemUserId,String avatar);

    /**
     * 更新用户密码
     * @param username 用户名
     * @param newPassword 新的密码
     */
    void updatePassword(String username, String newPassword);
}
