package com.sxquan.manage.spec.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sxquan.core.pojo.spec.SpecGroupParam;

import java.util.List;

/**
 * <p>
 * 规格组与规格参数中间表 Mapper 接口
 * </p>
 *
 * @author sxquan
 * @since 2020-03-10
 */
public interface SpecGroupParamMapper extends BaseMapper<SpecGroupParam> {

    /**
     * 通过skuId查询
     * @param skuId
     * @return
     */
    List<SpecGroupParam> selectSpecGroupParamBySkuId(Long skuId);

}
