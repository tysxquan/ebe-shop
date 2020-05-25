package com.sxquan.manage.spec.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.sxquan.manage.spec.pojo.SpecGroupParam;

import java.util.List;

/**
 * <p>
 * 规格组与规格参数中间表 服务类
 * </p>
 *
 * @author sxquan
 * @since 2020-03-10
 */
public interface ISpecGroupParamService extends IService<SpecGroupParam> {

    /**
     * 通过规格组id查找参数Id集
     * @param specGroupId 规格组id
     * @return
     */
    List<SpecGroupParam> findParamIdByGroupId(Long specGroupId);

    /**
     * 通过skuId查询
     * @param skuId
     * @return
     */
    List<SpecGroupParam> findSpecGroupParamBySkuId(Long skuId);

}
