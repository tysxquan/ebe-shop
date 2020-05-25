package com.sxquan.manage.business.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sxquan.manage.business.pojo.Sku;

/**
 * <p>
 * sku表 Mapper 接口
 * </p>
 *
 * @author sxquan
 * @since 2020-03-12
 */
public interface SkuMapper extends BaseMapper<Sku> {

    /**
     * 查询sku列表
     * @param page 分页条件
     * @param sku 查询条件
     * @return
     */
    Page<Sku> selectSkuList(Page<Sku> page, Sku sku);
}
