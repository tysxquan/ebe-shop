package com.sxquan.manage.middle.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.sxquan.core.pojo.middle.SpecParamSku;
import com.sxquan.core.pojo.spec.SpecGroupParam;

import java.util.List;

/**
 * <p>
 * SKU与规格参数中间表 服务类
 * </p>
 *
 * @author sxquan
 * @since 2020-03-17
 */
public interface ISpecParamSkuService extends IService<SpecParamSku> {

    /**
     * 通过skuId查询参数集
     * @param skuId
     * @return
     */
    List<SpecParamSku> findSpecParamSkuBySkuId(Long skuId);

    /**
     * 删除该id集合数据
     * @param skuIds
     */
    void deleteSpecParamSkuBySkuIds(List<String> skuIds);

    /**
     * 通过skuId查询
     * @param skuId
     * @return
     */
    List<SpecGroupParam> findSpecGroupParamBySkuId(Long skuId);

}
