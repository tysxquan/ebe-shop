package com.sxquan.manage.spec.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sxquan.core.entity.RequestPage;
import com.sxquan.core.pojo.spec.SpecParam;
import com.sxquan.manage.spec.mapper.SpecParamMapper;
import com.sxquan.manage.spec.service.ISpecParamService;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 规格参数组下的参数表 服务实现类
 * </p>
 *
 * @author sxquan
 * @since 2020-03-10
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class SpecParamServiceImpl extends ServiceImpl<SpecParamMapper, SpecParam> implements ISpecParamService {


    @Override
    public IPage<SpecParam> listSpecParam(SpecParam specParam, RequestPage requestPage) {
        IPage<SpecParam> page = new Page<>(requestPage.getPageNum(),requestPage.getPageSize());
        return baseMapper.selectPage(page,new LambdaQueryWrapper<SpecParam>()
                .like(StringUtils.isNotBlank(specParam.getParamName()),SpecParam::getParamName,specParam.getParamName())
                .eq(SpecParam::getSpecGroupId,specParam.getSpecGroupId()));
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void saveSpecParam(SpecParam specParam) {
        //先保存规格参数生成返回主键
        this.save(specParam);
    }

    @Override
    public void deleteSpecParam(String specParamIds) {
        String[] split = StringUtils.split(specParamIds, StringPool.COMMA);
        Long[] convert = (Long[]) ConvertUtils.convert(split, Long.class);
        List<Long> ids = Arrays.asList(convert);
        this.removeByIds(ids);
    }

    @Override
    public SpecParam findSpecParamById(Long specParamId) {
        return baseMapper.selectById(specParamId);
    }

    @Override
    public List<SpecParam> findSpecParamByGroupId(Long groupId) {
        return baseMapper.selectList(new LambdaQueryWrapper<SpecParam>()
                .eq(SpecParam::getSpecGroupId,groupId));

    }


}
