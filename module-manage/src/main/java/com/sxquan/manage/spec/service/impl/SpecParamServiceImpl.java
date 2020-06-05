package com.sxquan.manage.spec.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sxquan.core.entity.RequestPage;
import com.sxquan.core.pojo.spec.SpecGroupParam;
import com.sxquan.core.pojo.spec.SpecParam;
import com.sxquan.manage.spec.mapper.SpecParamMapper;
import com.sxquan.manage.spec.service.ISpecGroupParamService;
import com.sxquan.manage.spec.service.ISpecParamService;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    ISpecGroupParamService specGroupParamService;

    @Override
    public IPage<SpecParam> ListSpecParam(SpecParam specParam, RequestPage requestPage) {
        List<SpecGroupParam> paramList = specGroupParamService.findParamIdByGroupId(specParam.getSpecGroupId());
        Page<SpecParam> page = new Page(requestPage.getPageNum(),requestPage.getPageSize());
        if (CollectionUtils.isNotEmpty(paramList)) {
            List<Long> collect = paramList.stream().map(SpecGroupParam::getSpecParamId).collect(Collectors.toList());
            return baseMapper.selectPage(page,new LambdaQueryWrapper<SpecParam>()
                    .like(StringUtils.isNotBlank(specParam.getParamName()),SpecParam::getParamName,specParam.getParamName())
                    .in(SpecParam::getSpecParamId,collect));
        }
        page.setTotal(0L);
        return page;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void saveSpecParam(SpecParam specParam) {
        //先保存规格参数生成返回主键
        this.save(specParam);
        SpecGroupParam groupParam = new SpecGroupParam();
        groupParam.setSpecGroupId(specParam.getSpecGroupId());
        groupParam.setSpecParamId(specParam.getSpecParamId());
        specGroupParamService.save(groupParam);
    }

    @Override
    public void deleteSpecParam(String specParamIds) {
        String[] split = StringUtils.split(specParamIds, StringPool.COMMA);
        Long[] convert = (Long[]) ConvertUtils.convert(split, Long.class);
        List<Long> ids = Arrays.asList(convert);
        this.removeByIds(ids);
        specGroupParamService.remove(new LambdaQueryWrapper<SpecGroupParam>()
                .in(SpecGroupParam::getSpecParamId,ids));
    }

    @Override
    public SpecParam findSpecParamById(Long specParamId) {
        return baseMapper.selectById(specParamId);
    }

    @Override
    public List<SpecParam> findSpecParamByGroupId(Long groupId) {
        return baseMapper.selectParamByGroupId(groupId);
    }


}
