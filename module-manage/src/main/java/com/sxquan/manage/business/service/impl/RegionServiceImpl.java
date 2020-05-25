package com.sxquan.manage.business.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sxquan.manage.business.mapper.RegionMapper;
import com.sxquan.manage.business.pojo.Region;
import com.sxquan.manage.business.service.IRegionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * <p>
 * 中国行政地址信息表 服务实现类
 * </p>
 *
 * @author sxquan
 * @since 2020-03-06
 */
@Service
public class RegionServiceImpl extends ServiceImpl<RegionMapper, Region> implements IRegionService {

    @Override
    public List<Region> findRegionByParentCode(String parentCode) {
        List<Region> list = baseMapper.selectList(new LambdaQueryWrapper<Region>()
                .select(Region::getParentCode,Region::getCityCode,Region::getName,Region::getId)
                .eq(Region::getParentCode, parentCode)
                .orderByAsc(Region::getName));
        // List<String> parentCodes = baseMapper.findParentCodeByParentCode(parentCode);
        list.forEach(x -> {
            if (!StringUtils.equals(x.getCityCode(),x.getParentCode())) {
                x.setHaveChild(true);
            } else {
                x.setCityCode(String.valueOf(x.getId()));
            }
        });
        return list;
    }
}
