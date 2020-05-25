package com.sxquan.manage.system.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sxquan.manage.system.pojo.SystemUser;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 系统用户表 Mapper 接口
 * </p>
 *
 * @author sxquan
 * @since 2019-12-20
 */
public interface SystemUserMapper extends BaseMapper<SystemUser> {

    /**
     * 通过用户名查找系统用户
     *
     * @param username 用户名
     * @return SystemUser 用户对象
     */
    SystemUser findUserByUsername(String username);
    
    /**
     * 分页查找用户列表
     *
     * @param page 分页对象
     * @param systemUser 用户对象，用于查询条件
     * @return IPage
     */
    IPage<SystemUser> findUserListPage(Page page, @Param("systemUser") SystemUser systemUser);

}
