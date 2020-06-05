package com.sxquan.manage.system.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sxquan.core.entity.RequestPage;
import com.sxquan.core.pojo.system.SystemRole;
import com.sxquan.core.pojo.system.SystemUser;
import com.sxquan.core.pojo.system.SystemUserRole;
import com.sxquan.manage.common.constant.ManageConstant;
import com.sxquan.manage.system.mapper.SystemUserMapper;
import com.sxquan.manage.system.service.ISystemRoleService;
import com.sxquan.manage.system.service.ISystemUserRoleService;
import com.sxquan.manage.system.service.ISystemUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 系统用户表 服务实现类
 * </p>
 *
 * @author sxquan
 * @since 2019-12-20
 */
@Slf4j
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class SystemUserServiceImpl extends ServiceImpl<SystemUserMapper, SystemUser> implements ISystemUserService {

    @Autowired
    ISystemUserRoleService systemUserRoleService;

    @Autowired
    ISystemRoleService systemRoleService;

    @Override
    public SystemUser findUserByUsername(String username) {
        return this.baseMapper.findUserByUsername(username);
    }

    @Override
    public IPage<SystemUser> listUser(SystemUser systemUser, RequestPage request) {
        System.out.println(request);
        Page<SystemUser> page = new Page<>(request.getPageNum(), request.getPageSize());
        return baseMapper.selectPage(page, new LambdaQueryWrapper<SystemUser>()
                .like(StringUtils.isNotBlank(systemUser.getUsername()), SystemUser::getUsername, systemUser.getUsername())
                .like(StringUtils.isNotBlank(systemUser.getTrueName()), SystemUser::getTrueName, systemUser.getTrueName())
                .like(StringUtils.isNotBlank(systemUser.getMobile()), SystemUser::getMobile, systemUser.getMobile())
                .eq(ObjectUtils.isNotEmpty(systemUser.getStatus()), SystemUser::getStatus, systemUser.getStatus())
                .eq(ObjectUtils.isNotEmpty(systemUser.getSex()), SystemUser::getSex, systemUser.getSex())
                .between(StringUtils.isNotBlank(systemUser.getCreateTimeRange()), SystemUser::getCreateTime, systemUser.getCreateTimeStart(), systemUser.getCreateTimeEnd()));

    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void register(String username, String password) {
        SystemUser systemUser = new SystemUser();
        systemUser.setUsername(username);
        // 盐是username，散列2次
        systemUser.setPassword(new Md5Hash(password, username, 2).toString());
        systemUser.setSex(SystemUser.SEX_SECRECY);
        systemUser.setStatus(SystemUser.STATUS_VALID);
        systemUser.setAvatar(SystemUser.DEFAULT_AVATAR);
        this.save(systemUser);

        SystemUserRole sur = new SystemUserRole();
        sur.setSystemUserId(systemUser.getSystemUserId());
        sur.setRoleId(ManageConstant.REGISTER_ROLE_ID);
        systemUserRoleService.save(sur);
    }

    @Override
    public SystemUser findUserByUserId(Long systemUserId) {
        SystemUser systemUser = baseMapper.selectOne(new LambdaQueryWrapper<SystemUser>().eq(SystemUser::getSystemUserId, systemUserId));
        List<SystemRole> roleList = systemRoleService.findRoleByUserId(systemUserId);
        String roleName = roleList.stream().map(SystemRole::getRoleName).collect(Collectors.joining(StringPool.COMMA));
        systemUser.setRoleName(roleName);
        return systemUser;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void resetPasswordByIds(String[] usernames) {
        Arrays.stream(usernames).forEach(username -> {
            SystemUser systemUser = new SystemUser();
            systemUser.setPassword(new Md5Hash(SystemUser.DEFAULT_PASSWORD, username, 2).toString());
            this.baseMapper.update(systemUser, new LambdaUpdateWrapper<SystemUser>().eq(SystemUser::getUsername, username));
        });
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void addUser(SystemUser systemUser) {
        //保存用户数据
        this.baseMapper.insert(systemUser);
        //保存用户的角色数据
        saveUserRole(systemUser);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void updateUser(SystemUser systemUser) {
        //更新用户数据
        this.baseMapper.updateById(systemUser);
        Long userId = systemUser.getSystemUserId();
        List<String> userRoleList = new ArrayList<>();
        userRoleList.add(String.valueOf(userId));
        //删除该用户对应的旧角色绑定
        systemUserRoleService.deleteUserRoleByUserIds(userRoleList);
        //保存新添加的角色绑定
        saveUserRole(systemUser);
    }

    @Override
    public void deleteUser(List<String> userIds) {
        //删除用户
        this.removeByIds(userIds);
        //删除用户对应的角色数据
        systemUserRoleService.deleteUserRoleByUserIds(userIds);
    }

    @Override
    public void updateLoginTime(String username) {
        SystemUser systemUser = new SystemUser();
        systemUser.setLastLoginTime(LocalDateTime.now());
        this.update(systemUser,new LambdaQueryWrapper<SystemUser>()
                .eq(SystemUser::getUsername,username));
    }

    @Override
    public void updateAvatar(Long systemUserId, String avatar) {
        SystemUser systemUser = new SystemUser();
        systemUser.setAvatar(avatar);
        systemUser.setSystemUserId(systemUserId);
        this.updateById(systemUser);
    }

    @Override
    public void updatePassword(String username, String newPassword) {
        SystemUser systemUser = new SystemUser();
        systemUser.setPassword(new Md5Hash(newPassword,username,2).toString());
        baseMapper.update(systemUser,new LambdaQueryWrapper<SystemUser>()
                .eq(SystemUser::getUsername,username));
    }

    private void saveUserRole(SystemUser systemUser) {
        String roleIds = systemUser.getRoleIds();
        if (StringUtils.isNotBlank(roleIds)) {
            String[] split = StringUtils.split(roleIds, StringPool.COMMA);
            List<SystemUserRole> userRoleList = new ArrayList<>();
            Arrays.stream(split).forEach(roleId -> {
                SystemUserRole userRole = new SystemUserRole();
                userRole.setRoleId(Long.valueOf(roleId));
                userRole.setSystemUserId(systemUser.getSystemUserId());
                userRoleList.add(userRole);
            });
            systemUserRoleService.saveBatch(userRoleList);
        }
    }

}
