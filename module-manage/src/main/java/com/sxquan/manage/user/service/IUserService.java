package com.sxquan.manage.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sxquan.core.entity.RequestPage;
import com.sxquan.core.pojo.user.User;


/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author sxquan
 * @since 2020-05-12
 */
public interface IUserService extends IService<User> {

    /**
     * 分页查询列表
     * @param user 筛选条件
     * @param requestPage 分页条件
     * @return
     */
    IPage<User> listVip(User user, RequestPage requestPage);
}
