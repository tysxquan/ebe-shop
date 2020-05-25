package com.sxquan.manage.business.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sxquan.core.entity.RequestPage;
import com.sxquan.manage.business.pojo.Sku;

import java.util.List;

/**
 * <p>
 * sku表 服务类
 * </p>
 *
 * @author sxquan
 * @since 2020-03-12
 */
public interface ISkuService extends IService<Sku> {

    /**
     * 查询sku列表
     * @param sku 筛选条件
     * @param requestPage 分页条件
     * @return sku列表
     */
    IPage<Sku> ListSku(Sku sku, RequestPage requestPage);

    /**
     * 新增sku
     * @param sku 参数
     */
    void addSku(Sku sku);

    /**
     * 根据主键查询详情
     * @param skuId 主键
     * @return
     */
    Sku findSkuBySkuId(Long skuId);

    /**
     * 删除单个/多个sku
     * @param skuIds id集
     */
    void deleteSku(List<String> skuIds);

    /**
     * 更新
     * @param sku 数据
     */
    void updateSku(Sku sku);

    /**
     * 清除图片属性
     * @param imageName 图片名
     */
    void updateRemoveImageValue(String imageName);
}
