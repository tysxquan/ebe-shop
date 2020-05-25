package com.sxquan.manage.spec.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sxquan.manage.spec.mapper.SpecGroupParamMapper;
import com.sxquan.manage.spec.pojo.SpecGroupParam;
import com.sxquan.manage.spec.service.ISpecGroupParamService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 规格组与规格参数中间表 服务实现类
 * </p>
 *
 * @author sxquan
 * @since 2020-03-10
 */
@Service
public class SpecGroupParamServiceImpl extends ServiceImpl<SpecGroupParamMapper, SpecGroupParam> implements ISpecGroupParamService {

    @Override
    public List<SpecGroupParam> findParamIdByGroupId(Long specGroupId) {
        return baseMapper.selectList(new LambdaQueryWrapper<SpecGroupParam>()
                .select(SpecGroupParam::getSpecParamId)
                .eq(SpecGroupParam::getSpecGroupId, specGroupId));
    }

    @Override
    public List<SpecGroupParam> findSpecGroupParamBySkuId(Long skuId) {
        return baseMapper.selectSpecGroupParamBySkuId(skuId);
    }


}
