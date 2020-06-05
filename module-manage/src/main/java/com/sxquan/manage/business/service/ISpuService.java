package com.sxquan.manage.business.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sxquan.core.entity.RequestPage;
import com.sxquan.core.pojo.business.Spu;

import java.util.List;

/**
 * <p>
 * SPU信息表 服务类
 * </p>
 *
 * @author sxquan
 * @since 2020-03-12
 */
public interface ISpuService extends IService<Spu> {

    /**
     * 分页查询spu列表
     * @param spu 筛选条件
     * @param requestPage 分页数据
     * @return 结果列表
     */
    IPage<Spu> ListSpu(Spu spu, RequestPage requestPage);

    /**
     * 更新选择的图片值
     * @param imageName 图片名
     * @param imageParamName 图片参数名
     */
    void updateRemoveImageValue(String imageName,String imageParamName);

    /**
     * 添加spu
     * @param spu sup数据对象
     */
    void addSpu(Spu spu);

    /**
     * 更新sup
     * @param spu 更新数据对象
     */
    void updateSpu(Spu spu);

    /**
     * 通过id查询详情
     * @param spuId 主键
     * @return 对象
     */
    Spu findSpuBySpuId(Long spuId);

    /**
     * 通过spuId集删除
     * @param spuIds 需要删除的集合
     */
    void deleteSpuBySpuIds(List<String> spuIds);

    /**
     * 通过title查询spu集合（加载表单）
     * @param title 条件
     * @return
     */
    List<Spu> findSpuFormAll(String title);
}
