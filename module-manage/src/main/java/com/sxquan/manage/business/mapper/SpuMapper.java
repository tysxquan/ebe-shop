package com.sxquan.manage.business.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sxquan.manage.business.pojo.Spu;
import org.springframework.stereotype.Component;

/**
 * <p>
 * SPU信息表 Mapper 接口
 * </p>
 *
 * @author sxquan
 * @since 2020-03-12
 */
@Component
public interface SpuMapper extends BaseMapper<Spu> {

    /**
     * 查询spu列表
     * @param spu 查询条件
     * @return
     */
    IPage<Spu> selectSpuList(Page<?> page, Spu spu);
}
