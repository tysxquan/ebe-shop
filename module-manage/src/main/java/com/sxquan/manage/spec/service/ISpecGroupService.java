package com.sxquan.manage.spec.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sxquan.core.entity.RequestPage;
import com.sxquan.core.pojo.spec.SpecGroup;

import java.util.List;

/**
 * <p>
 * 规格组表 服务类
 * </p>
 *
 * @author sxquan
 * @since 2020-03-10
 */
public interface ISpecGroupService extends IService<SpecGroup> {

    /**
     *  分页查询规格组
     * @param specGroup 查询条件
     * @param requestPage 分页参数
     * @return
     */
    IPage<SpecGroup> ListSpecGroup(SpecGroup specGroup, RequestPage requestPage);

    /**
     * 通过id查询详情
     * @param groupId 主键
     * @return
     */
    SpecGroup findSpecGroupById(Long groupId);

    /**
     * 查询使用数据（用于加载表单）
     * @return
     */
    List<SpecGroup> findSpecGroupFormAll();

    /**
     * 通过spuId查询规格组
     * @param spuId spu主键
     * @return
     */
    List<SpecGroup> findSpecGroupBySpuId(Long spuId);
}
