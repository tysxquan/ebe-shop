package com.sxquan.manage.business.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sxquan.core.constant.SystemConstant;
import com.sxquan.core.entity.RequestPage;
import com.sxquan.core.util.LocalDateUtil;
import com.sxquan.manage.business.mapper.ShopInfoMapper;
import com.sxquan.manage.business.pojo.ShopInfo;
import com.sxquan.manage.business.service.IShopInfoService;
import com.sxquan.manage.common.properties.EbeProperties;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商铺信息表 服务实现类
 * </p>
 *
 * @author sxquan
 * @since 2020-02-24
 */
@Service
public class ShopInfoServiceImpl extends ServiceImpl<ShopInfoMapper, ShopInfo> implements IShopInfoService {

    @Autowired
    EbeProperties dispatching;

    @Override
    public IPage<ShopInfo> listShop(ShopInfo shopInfo, RequestPage requestPage) {
        Page<ShopInfo> page = new Page<>(requestPage.getPageNum(),requestPage.getPageSize());
        Page<ShopInfo> infoPage = this.baseMapper.selectPage(page, new LambdaQueryWrapper<ShopInfo>()
                .like(StringUtils.isNotBlank(shopInfo.getShopName()), ShopInfo::getShopName, shopInfo.getShopName())
                .eq(ObjectUtils.isNotEmpty(shopInfo.getStatus()),ShopInfo::getStatus,shopInfo.getStatus())
                .like(StringUtils.isNotBlank(shopInfo.getContactMobile()),ShopInfo::getContactMobile,shopInfo.getContactMobile())
                .like(StringUtils.isNotBlank(shopInfo.getContactMan()),ShopInfo::getContactMan,shopInfo.getContactMan())
                .between(StringUtils.isNotBlank(shopInfo.getCreateTimeRange()),ShopInfo::getCreateTime,shopInfo.getCreateTimeStart(),shopInfo.getCreateTimeEnd()));
        infoPage.getRecords().forEach(x -> {
            if (StringUtils.isNotBlank(x.getStoreImg())) {
                x.setStoreImg(dispatching.getFileServer() + ShopInfo.STORE_IMG_SUB_PATH + x.getStoreImg());
            }
            if (x.getBeginTime() != null && x.getEndTime() != null) {
                String beginTime = LocalDateUtil.localTimeToString(x.getBeginTime());
                String endTime = LocalDateUtil.localTimeToString(x.getEndTime());
                x.setBusinessHours(beginTime + SystemConstant.SEPARATOR_MINUS +endTime);
            }
        });
        return infoPage;
    }

    @Override
    public ShopInfo findShopInfoByShopInfoId(Long shopInfoId) {
        ShopInfo shopInfo = baseMapper.selectById(shopInfoId);
        shopInfo.setBusinessHours(LocalDateUtil.localTimeToString(shopInfo.getBeginTime()) + SystemConstant.SEPARATOR_MINUS + LocalDateUtil.localTimeToString(shopInfo.getEndTime()));
        if (StringUtils.isNotBlank(shopInfo.getStoreImg())) {
            shopInfo.setStoreImg(dispatching.getFileServer()+ShopInfo.STORE_IMG_SUB_PATH+shopInfo.getStoreImg());
        }
        return shopInfo;
    }

    @Override
    public void updateRemoveImageValue(String imageName) {
        LambdaQueryWrapper<ShopInfo> eq = new LambdaQueryWrapper<ShopInfo>()
                .eq(ShopInfo::getStoreImg, imageName);
        ShopInfo shopInfo = new ShopInfo();
        shopInfo.setStoreImg("");
        baseMapper.update(shopInfo,eq);
    }
}
