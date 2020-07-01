package com.sxquan.manage.content.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sxquan.core.entity.RequestPage;
import com.sxquan.core.enums.ImagePathEnum;
import com.sxquan.core.pojo.content.Banner;
import com.sxquan.manage.common.properties.EbeProperties;
import com.sxquan.manage.content.mapper.BannerMapper;
import com.sxquan.manage.content.service.IBannerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * banner内容管理表 Service实现
 *
 * @author sxquan
 * @since 2020-05-28 14:40:02
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class BannerServiceImpl extends ServiceImpl<BannerMapper, Banner> implements IBannerService {

    @Autowired
    private BannerMapper bannerMapper;

    @Autowired
    private EbeProperties properties;

    @Override
    public IPage<Banner> findBannerList(Banner banner, RequestPage request) {
        LambdaQueryWrapper<Banner> queryWrapper = new LambdaQueryWrapper<>();
        // TODO 设置查询条件
        Page<Banner> page = new Page<>(request.getPageNum(), request.getPageSize());
        Page<Banner> bannerPage = this.page(page, queryWrapper);
        bannerPage.getRecords().forEach( x -> {
            if (StringUtils.isNotBlank(x.getImg())) {
                x.setImg(properties.getFileServer() + ImagePathEnum.BANNER_IMG.getPath() + x.getImg());
            }
        });
        return bannerPage;
    }

    @Override
    public List<Banner> findBannerAll(Banner banner) {
	    LambdaQueryWrapper<Banner> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    public Banner findBannerById(Long id) {
        Banner banner = this.bannerMapper.selectById(id);
        if (StringUtils.isNotBlank(banner.getImg())) {
            banner.setImg( properties.getFileServer() + ImagePathEnum.BANNER_IMG.getPath() + banner.getImg());
        }
        return banner;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void addBanner(Banner banner) {
        this.save(banner);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void updateBanner(Banner banner) {
        this.updateById(banner);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void deleteBanner(List<String> idList) {
        this.removeByIds(idList);
	}

    @Override
    public void updateRemoveImageValue(String imageName) {
        Banner banner = new Banner();
        banner.setImg("");
        baseMapper.update(banner,new LambdaQueryWrapper<Banner>()
                .eq(Banner::getImg,imageName));
    }
}
