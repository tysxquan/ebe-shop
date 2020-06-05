package com.sxquan.manage.spec.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sxquan.core.entity.RequestPage;
import com.sxquan.core.pojo.spec.SpecGroup;
import com.sxquan.manage.spec.mapper.SpecGroupMapper;
import com.sxquan.manage.spec.service.ISpecGroupService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 规格参数组表 服务实现类
 * </p>
 *
 * @author sxquan
 * @since 2020-03-10
 */
@Service
public class SpecGroupServiceImpl extends ServiceImpl<SpecGroupMapper, SpecGroup> implements ISpecGroupService {

    @Override
    public IPage<SpecGroup> ListSpecGroup(SpecGroup specGroup, RequestPage requestPage) {
        Page<SpecGroup> page = new Page<>(requestPage.getPageNum(),requestPage.getPageSize());
        return baseMapper.selectPage(page,new LambdaQueryWrapper<SpecGroup>()
                .like(StringUtils.isNotBlank(specGroup.getGroupName()),SpecGroup::getGroupName,specGroup.getGroupName())
                .eq(ObjectUtils.isNotEmpty(specGroup.getStandard()),SpecGroup::getStandard,specGroup.getStandard()));
    }

    @Override
    public SpecGroup findSpecGroupById(Long groupId) {
        return baseMapper.selectById(groupId);
    }

    @Override
    public List<SpecGroup> findSpecGroupFormAll() {
        return baseMapper.selectList(new LambdaQueryWrapper<SpecGroup>()
                .select(SpecGroup::getSpecGroupId,SpecGroup::getGroupName));
    }

    @Override
    public List<SpecGroup> findSpecGroupBySpuId(Long spuId) {
        return baseMapper.selectSpecGroupBySpuId(spuId);
    }
}
