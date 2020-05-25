package com.sxquan.manage.spec.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sxquan.manage.spec.pojo.SpecParam;

import java.util.List;

/**
 * <p>
 * 规格参数组下的参数表 Mapper 接口
 * </p>
 *
 * @author sxquan
 * @since 2020-03-10
 */
public interface SpecParamMapper extends BaseMapper<SpecParam> {

    /**
     * 通过groupId查询规格
     * @param groupId
     * @return
     */
    List<SpecParam> selectParamByGroupId(Long groupId);
}
