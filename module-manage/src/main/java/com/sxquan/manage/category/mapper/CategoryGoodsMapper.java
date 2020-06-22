package com.sxquan.manage.category.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sxquan.core.pojo.category.CategoryGoods;

import java.util.List;

/**
 * <p>
 * 商品分类表 Mapper 接口
 * </p>
 *
 * @author sxquan
 * @since 2020-03-08
 */
public interface CategoryGoodsMapper extends BaseMapper<CategoryGoods> {

    /**
     * 根据父id查找子项是否还有子项的父id
     * @param parentId 父id
     * @return 含有子项的父id集合
     */
    List<Long> findParentIdsByParentId(Long parentId);


}
