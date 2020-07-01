package com.sxquan.manage.content.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sxquan.core.entity.RequestPage;
import com.sxquan.core.enums.ImagePathEnum;
import com.sxquan.core.pojo.content.BannerItem;
import com.sxquan.manage.common.properties.EbeProperties;
import com.sxquan.manage.content.mapper.BannerItemMapper;
import com.sxquan.manage.content.service.IBannerItemService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * banner_item内容管理表 Service实现
 *
 * @author sxquan
 * @since 2020-05-28 14:40:36
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class BannerItemServiceImpl extends ServiceImpl<BannerItemMapper, BannerItem> implements IBannerItemService {

    @Autowired
    private BannerItemMapper bannerItemMapper;

    @Autowired
    private EbeProperties properties;

    @Override
    public IPage<BannerItem> findBannerItemList(Long bannerId,BannerItem bannerItem, RequestPage request) {
        LambdaQueryWrapper<BannerItem> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(BannerItem::getBannerId,bannerId);
        Page<BannerItem> page = new Page<>(request.getPageNum(), request.getPageSize());
        Page<BannerItem> bannerItemPage = this.page(page, queryWrapper);
        bannerItemPage.getRecords().forEach( x -> {
            if (StringUtils.isNotBlank(x.getImg())) {
                x.setImg(properties.getFileServer() + ImagePathEnum.BANNER_ITEM_IMG.getPath() + x.getImg());
            }
        });
        return bannerItemPage;
    }

    @Override
    public List<BannerItem> findBannerItemAll(BannerItem bannerItem) {
	    LambdaQueryWrapper<BannerItem> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    public BannerItem findBannerItemById(Long id) {
        BannerItem bannerItem = this.bannerItemMapper.selectById(id);
        if (StringUtils.isNotBlank(bannerItem.getImg())) {
            bannerItem.setImg(properties.getFileServer() + ImagePathEnum.BANNER_ITEM_IMG.getPath() + bannerItem.getImg());
        }
        return bannerItem;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void addBannerItem(BannerItem bannerItem) {
        this.save(bannerItem);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void updateBannerItem(BannerItem bannerItem) {
        this.updateById(bannerItem);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void deleteBannerItem(List<String> idList) {
        this.removeByIds(idList);
	}

    @Override
    public void updateRemoveImageValue(String imageName) {
        BannerItem bannerItem = new BannerItem();
        bannerItem.setImg("");
        baseMapper.update(bannerItem,new LambdaQueryWrapper<BannerItem>()
                .eq(BannerItem::getImg,imageName));
    }
}
