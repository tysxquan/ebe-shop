package com.sxquan.manage.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sxquan.core.entity.RequestPage;
import com.sxquan.manage.business.mapper.SkuMapper;
import com.sxquan.manage.business.pojo.Sku;
import com.sxquan.manage.business.service.ISkuService;
import com.sxquan.manage.common.properties.EbeProperties;
import com.sxquan.manage.middle.pojo.SpecParamSku;
import com.sxquan.manage.middle.service.ISpecParamSkuService;
import com.sxquan.manage.spec.pojo.SpecGroupParam;
import com.sxquan.manage.spec.service.ISpecGroupParamService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * sku表 服务实现类
 * </p>
 *
 * @author sxquan
 * @since 2020-03-12
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
public class SkuServiceImpl extends ServiceImpl<SkuMapper, Sku> implements ISkuService {

    @Autowired
    ISpecParamSkuService specParamSkuService;

    @Autowired
    ISpecGroupParamService specGroupParamService;

    @Autowired
    EbeProperties properties;

    @Override
    public IPage<Sku> ListSku(Sku sku, RequestPage requestPage) {
        Page<Sku> page = new Page<>(requestPage.getPageNum(),requestPage.getPageSize());
        Page<Sku> selectPage = baseMapper.selectSkuList(page,sku);
        selectPage.getRecords().forEach( x -> {
            if (StringUtils.isNotBlank(x.getImage())){
                x.setImage(properties.getFileServer()+Sku.IMAGE_SUB_PATH+x.getImage());
            }

        });
        return selectPage;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void addSku(Sku sku) {
        this.save(sku);
        saveSpecParamSku(sku);
    }

    @Override
    public Sku findSkuBySkuId(Long skuId) {
        Sku sku = baseMapper.selectById(skuId);
        List<SpecGroupParam> paramIdByGroup = specGroupParamService.findSpecGroupParamBySkuId(skuId);
        sku.setGroupParam(paramIdByGroup);
        if (StringUtils.isNotBlank(sku.getImage())){
            sku.setImage(properties.getFileServer()+Sku.IMAGE_SUB_PATH+sku.getImage());
        }
        return sku;
    }

    @Override
    public void deleteSku(List<String> skuIds) {
        this.removeByIds(skuIds);
        specParamSkuService.deleteSpecParamSkuBySkuIds(skuIds);
    }

    @Override
    public void updateSku(Sku sku) {
        this.updateById(sku);
        List<String> skuIdList = new ArrayList<>();
        skuIdList.add(String.valueOf(sku.getSkuId()));
        specParamSkuService.deleteSpecParamSkuBySkuIds(skuIdList);
        saveSpecParamSku(sku);
    }

    @Override
    public void updateRemoveImageValue(String imageName) {
        Sku sku = new Sku();
        sku.setImage("");
        baseMapper.update(sku,new LambdaQueryWrapper<Sku>()
                .eq(Sku::getImage,imageName));
    }

    private void saveSpecParamSku(Sku sku){
        String paramIds = sku.getParamIds();
        if (StringUtils.isNotBlank(paramIds)) {
            String[] split = StringUtils.split(paramIds, StringPool.COMMA);
            List<SpecParamSku> list = new ArrayList<>();
            Arrays.stream(split).forEach( x -> {
                SpecParamSku specParamSku = new SpecParamSku();
                specParamSku.setSkuId(sku.getSkuId());
                specParamSku.setSpecParamId(Long.valueOf(x));
                list.add(specParamSku);
            });
            specParamSkuService.saveBatch(list);
        }
    }
}
