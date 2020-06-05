package com.sxquan.manage.middle.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sxquan.core.pojo.middle.SpecGroupSpu;
import com.sxquan.manage.middle.mapper.SpecGroupSpuMapper;
import com.sxquan.manage.middle.service.ISpecGroupSpuService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * SPU与规格组中间表 服务实现类
 * </p>
 *
 * @author sxquan
 * @since 2020-03-14
 */
@Service
public class SpecGroupSpuServiceImpl extends ServiceImpl<SpecGroupSpuMapper, SpecGroupSpu> implements ISpecGroupSpuService {

    @Override
    public List<SpecGroupSpu> findSpecGroupSpuBySpuId(Long spuId) {
        return baseMapper.selectList(new LambdaQueryWrapper<SpecGroupSpu>()
                .eq(SpecGroupSpu::getSpuId,spuId));
    }

    @Override
    public void deleteSpecGroupSpuBySpuId(Long spuId) {
        baseMapper.delete(new LambdaQueryWrapper<SpecGroupSpu>()
                .eq(SpecGroupSpu::getSpuId,spuId));
    }
}
