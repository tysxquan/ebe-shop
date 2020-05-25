package com.sxquan.manage.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sxquan.core.entity.RequestPage;
import com.sxquan.manage.business.mapper.SpuMapper;
import com.sxquan.manage.business.pojo.Spu;
import com.sxquan.manage.business.service.ISpuService;
import com.sxquan.manage.common.enums.SpuImageSrcEnum;
import com.sxquan.manage.common.properties.EbeProperties;
import com.sxquan.manage.middle.pojo.CategoryProductSpu;
import com.sxquan.manage.middle.pojo.SpecGroupSpu;
import com.sxquan.manage.middle.service.ICategoryProductSpuService;
import com.sxquan.manage.middle.service.ISpecGroupSpuService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * SPU信息表 服务实现类
 * </p>
 *
 * @author sxquan
 * @since 2020-03-12
 */
@Service
@Slf4j
@Transactional(propagation = Propagation.SUPPORTS,readOnly = true,rollbackFor = {Exception.class})
public class SpuServiceImpl extends ServiceImpl<SpuMapper, Spu> implements ISpuService {

    @Autowired
    private EbeProperties dispatching;

    @Autowired
    private ICategoryProductSpuService categoryProductSpuService;

    @Autowired
    private ISpecGroupSpuService specGroupSpuService;


    @Override
    public IPage<Spu> ListSpu(Spu spu, RequestPage requestPage) {
        Page<Spu> page = new Page<>(requestPage.getPageNum(),requestPage.getPageSize());
        IPage<Spu> selectSpuList = baseMapper.selectSpuList(page, spu);
        selectSpuList.getRecords().forEach( x -> {
            if (StringUtils.isNotBlank(x.getCover())) {
                x.setCover(dispatching.getFileServer() + SpuImageSrcEnum.COVER.getSrc() + x.getCover());
            }
        });
        return selectSpuList;
    }

    @Override
    public void updateRemoveImageValue(String imageName, String imageParamName) {
        Spu spu = new Spu();
        LambdaQueryWrapper<Spu> wrapper = new LambdaQueryWrapper<>();
        if (StringUtils.equals(imageParamName, SpuImageSrcEnum.COVER.getParamName())) {
            spu.setCover("");
            wrapper.eq(Spu::getCover,imageName);

        } else if (StringUtils.equals(imageParamName, SpuImageSrcEnum.DETAIL_IMG.getParamName())) {
            spu.setDetailImg("");
            wrapper.eq(Spu::getCover,imageName);
        // 多图片
        } else if (StringUtils.equals(imageParamName, SpuImageSrcEnum.BANNER_IMAGES.getParamName())) {
            Spu selectOne = baseMapper.selectOne(new LambdaQueryWrapper<Spu>()
                    .select(Spu::getBannerImages,Spu::getSpuId)
                    .like(Spu::getBannerImages, imageName));
            if (ObjectUtils.isNotEmpty(selectOne)) {
                String[] split = StringUtils.split(selectOne.getBannerImages(), StringPool.COMMA);
                String collect = Arrays.stream(split).filter(x -> !StringUtils.equals(x, imageName)).collect(Collectors.joining(StringPool.COMMA));
                spu.setBannerImages(collect);
                wrapper.eq(Spu::getSpuId,selectOne.getSpuId());
            }
        }
        if (wrapper.isEmptyOfWhere()) {
            return;
        }
        baseMapper.update(spu,wrapper);

    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void addSpu(Spu spu) {
        this.save(spu);
        CategoryProductSpu spuCategoryProduct = saveSpuCategoryProduct(spu);
        //分类关联
        categoryProductSpuService.save(spuCategoryProduct);
        //规格组关联
        saveSpuSpecGroup(spu);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void updateSpu(Spu spu) {
        this.updateById(spu);
        CategoryProductSpu spuCategoryProduct = saveSpuCategoryProduct(spu);
        //更新分类中间表数据
        categoryProductSpuService.updateCategoryProductBySpuId(spuCategoryProduct);
        //删除规格组中间表数据
        specGroupSpuService.deleteSpecGroupSpuBySpuId(spu.getSpuId());
        //新增规格组中间表数据
        saveSpuSpecGroup(spu);
    }

    @Override
    public Spu findSpuBySpuId(Long spuId) {
        Spu spu = baseMapper.selectById(spuId);
        if (StringUtils.isNotBlank(spu.getCover())) {
            spu.setCover(dispatching.getFileServer() + SpuImageSrcEnum.COVER.getSrc() + spu.getCover());
        }
        if (StringUtils.isNotBlank(spu.getBannerImages())) {
            String[] split = StringUtils.split(spu.getBannerImages(), StringPool.COMMA);
            String newBannerImages = Arrays.stream(split).map(x -> dispatching.getFileServer() + SpuImageSrcEnum.BANNER_IMAGES.getSrc() + x).collect(Collectors.joining(StringPool.COMMA));
            spu.setBannerImages(newBannerImages);
        }
        if (StringUtils.isNotBlank(spu.getDetailImg())) {
            spu.setDetailImg(dispatching.getFileServer() + SpuImageSrcEnum.DETAIL_IMG.getSrc() + spu.getDetailImg());
        }
        CategoryProductSpu categoryProductSpu = categoryProductSpuService.findCategoryProductSpuBySpuId(spuId);
        spu.setMergerCategoryId(categoryProductSpu.getMergerCategoryId());
        spu.setCategoryId(categoryProductSpu.getCategoryId());
        List<SpecGroupSpu> groupSpuList = specGroupSpuService.findSpecGroupSpuBySpuId(spuId);
        List<Long> collect = groupSpuList.stream().map(SpecGroupSpu::getSpecGroupId).collect(Collectors.toList());
        spu.setSpecGroup(StringUtils.join(collect,StringPool.COMMA));
        return spu;
    }

    @Override
    public void deleteSpuBySpuIds(List<String> spuIds) {
        Spu spu = new Spu();
        spu.setDeleted(true);
        baseMapper.update(spu,new LambdaQueryWrapper<Spu>()
                .in(Spu::getSpuId,spuIds));
    }

    @Override
    public List<Spu> findSpuFormAll(String title) {
        return baseMapper.selectList(new LambdaQueryWrapper<Spu>()
                .select(Spu::getSpuId,Spu::getTitle)
                .like(StringUtils.isNotBlank(title),Spu::getTitle,title)
                .eq(Spu::getDeleted,false));
    }

    private CategoryProductSpu saveSpuCategoryProduct(Spu spu) {
        CategoryProductSpu spuCategory = new CategoryProductSpu();
        spuCategory.setCategoryId(spu.getCategoryId());
        spuCategory.setMergerCategoryId(spu.getMergerCategoryId());
        spuCategory.setSpuId(spu.getSpuId());
        return spuCategory;
    }

    private void saveSpuSpecGroup(Spu spu) {
        if (StringUtils.isNotBlank(spu.getSpecGroup())) {
            String[] split = StringUtils.split(spu.getSpecGroup(), StringPool.COMMA);
            List<SpecGroupSpu> spuGroups = new ArrayList<>();
            Arrays.stream(split).forEach( x -> {
                SpecGroupSpu spuGroup = new SpecGroupSpu();
                spuGroup.setSpuId(spu.getSpuId());
                spuGroup.setSpecGroupId(Long.valueOf(x));
                spuGroups.add(spuGroup);
            });
            specGroupSpuService.saveBatch(spuGroups);
        }
    }
}
