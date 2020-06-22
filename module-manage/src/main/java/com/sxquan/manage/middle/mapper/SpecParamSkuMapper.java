package com.sxquan.manage.middle.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sxquan.core.pojo.middle.SpecParamSku;
import com.sxquan.core.pojo.spec.SpecGroupParam;

import java.util.List;


/**
 * <p>
 * SKU与规格参数中间表 Mapper 接口
 * </p>
 *
 * @author sxquan
 * @since 2020-03-17
 */
public interface SpecParamSkuMapper extends BaseMapper<SpecParamSku> {

    /**
     * 通过skuId查询
     * @param skuId
     * @return
     */
    List<SpecGroupParam> selectSpecGroupParamBySkuId(Long skuId);

}
