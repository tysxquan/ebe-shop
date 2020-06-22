package com.sxquan.manage.middle.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sxquan.core.pojo.middle.SpecParamSku;
import com.sxquan.core.pojo.spec.SpecGroupParam;
import com.sxquan.manage.middle.mapper.SpecParamSkuMapper;
import com.sxquan.manage.middle.service.ISpecParamSkuService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * SKU与规格参数中间表 服务实现类
 * </p>
 *
 * @author sxquan
 * @since 2020-03-17
 */
@Service
public class SpecParamSkuServiceImpl extends ServiceImpl<SpecParamSkuMapper, SpecParamSku> implements ISpecParamSkuService {

    @Override
    public List<SpecParamSku> findSpecParamSkuBySkuId(Long skuId) {
        return  baseMapper.selectList(new LambdaQueryWrapper<SpecParamSku>()
                .select(SpecParamSku::getSpecParamId)
                .eq(SpecParamSku::getSkuId, skuId));
    }

    @Override
    public void deleteSpecParamSkuBySkuIds(List<String> skuIds) {
        baseMapper.delete(new LambdaQueryWrapper<SpecParamSku>()
                .in(SpecParamSku::getSkuId,skuIds));
    }

    @Override
    public List<SpecGroupParam> findSpecGroupParamBySkuId(Long skuId) {
        return baseMapper.selectSpecGroupParamBySkuId(skuId);
    }
}
