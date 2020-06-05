package com.sxquan.manage.business.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sxquan.core.pojo.business.Region;

import java.util.List;

/**
 * <p>
 * 中国行政地址信息表 Mapper 接口
 * </p>
 *
 * @author sxquan
 * @since 2020-03-06
 */

public interface RegionMapper extends BaseMapper<Region> {

    /**
     * 根据父id查找其子项是否还有子项的父id集合
     * @param parentCode 父id
     * @return 含有子项的父id集
     */
    List<String> findParentCodeByParentCode(String parentCode);

}
