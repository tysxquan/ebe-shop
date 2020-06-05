package com.sxquan.manage.system.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sxquan.core.pojo.system.SystemRole;

import java.util.List;

/**
 * <p>
 * 系统角色表 Mapper 接口
 * </p>
 *
 * @author sxquan
 * @since 2019-12-20
 */
public interface SystemRoleMapper extends BaseMapper<SystemRole> {

    /**
     * 通过用户名ID查找用户角色
     *
     * @param userId 用户名标识
     * @return 用户角色集合
     */
    List<SystemRole> findRoleByUserId(Long userId);


}
