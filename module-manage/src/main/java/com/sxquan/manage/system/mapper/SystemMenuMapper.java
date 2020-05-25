package com.sxquan.manage.system.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sxquan.manage.system.pojo.SystemMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 系统菜单表 Mapper 接口
 * </p>
 *
 * @author sxquan
 * @since 2019-12-20
 */
public interface SystemMenuMapper extends BaseMapper<SystemMenu> {
    /**
     *  根据角色id查询菜单权限
     *
     * @param roleIds 角色id集
     * @return 角色菜单集
     */
    List<SystemMenu> findMenuByRoleIds(@Param("roleIds") List<Long> roleIds);

    /**
     *  根据用户id查找用户菜单集合
     *
     * @param userId 用户Id
     * @param type 排除的类型
     * @return 用户菜单集合
     */
    List<SystemMenu> findUserMenuByUserId(Long userId, Integer type);

    /**
     * 根据父id查找子项是否还有子项的父id
     * @param parentId 父id
     * @return
     */
    List<Long> findParentIdsByParentId(Long parentId);

}
