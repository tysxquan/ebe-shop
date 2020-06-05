package com.sxquan.manage.middle.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.sxquan.core.pojo.middle.SpecGroupSpu;

import java.util.List;

/**
 * <p>
 * SPU与规格组中间表 服务类
 * </p>
 *
 * @author sxquan
 * @since 2020-03-14
 */
public interface ISpecGroupSpuService extends IService<SpecGroupSpu> {

    /**
     * 通过spuId查询规格组
     * @param spuId
     * @return
     */
    List<SpecGroupSpu> findSpecGroupSpuBySpuId(Long spuId);

    /**
     *  通过spuId删除
     * @param spuId 条件
     */
    void deleteSpecGroupSpuBySpuId(Long spuId);

}
