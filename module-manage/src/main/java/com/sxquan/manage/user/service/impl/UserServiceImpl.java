package com.sxquan.manage.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sxquan.core.entity.RequestPage;
import com.sxquan.core.pojo.user.User;
import com.sxquan.manage.user.mapper.UserMapper;
import com.sxquan.manage.user.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author sxquan
 * @since 2020-05-12
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public IPage<User> listVip(User user, RequestPage requestPage) {
        IPage<User> page = new Page<>(requestPage.getPageNum(),requestPage.getPageSize());

        return baseMapper.selectPage(page,new LambdaQueryWrapper<User>()
                        .like(StringUtils.isNotBlank(user.getUsername()),User::getUsername,user.getUsername()));
    }
}
