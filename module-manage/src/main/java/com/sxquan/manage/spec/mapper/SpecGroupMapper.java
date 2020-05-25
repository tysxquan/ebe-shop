package com.sxquan.manage.spec.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sxquan.manage.spec.pojo.SpecGroup;

import java.util.List;

/**
 * <p>
 * 规格参数组表 Mapper 接口
 * </p>
 *
 * @author sxquan
 * @since 2020-03-10
 */
public interface SpecGroupMapper extends BaseMapper<SpecGroup> {

    /**
     * 通过spuId查询规格组
     * @param spuId spu主键
     * @return
     */
    List<SpecGroup> selectSpecGroupBySpuId(Long spuId);
}
