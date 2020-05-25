package com.sxquan.manage.business.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.sxquan.manage.business.pojo.Region;

import java.util.List;

/**
 * <p>
 * 中国行政地址信息表 服务类
 * </p>
 *
 * @author sxquan
 * @since 2020-03-06
 */
public interface IRegionService extends IService<Region> {

    /**
     *  通过parentCode查询下面的地址
     * @param parentCode 父id
     * @return
     */
    List<Region> findRegionByParentCode(String parentCode);
}
