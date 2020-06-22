package com.sxquan.manage.spec.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sxquan.core.entity.RequestPage;
import com.sxquan.core.pojo.spec.SpecParam;

import java.util.List;

/**
 * <p>
 * 规格参数组下的参数表 服务类
 * </p>
 *
 * @author sxquan
 * @since 2020-03-10
 */
public interface ISpecParamService extends IService<SpecParam> {

    /**
     * 分页查询该规格组下的参数列表
     * @param specParam 条件
     * @param requestPage 分页参数
     * @return
     */
    IPage<SpecParam> listSpecParam(SpecParam specParam, RequestPage requestPage);

    /**
     * 添加规格参数
     * @param specParam 数据
     */
    void saveSpecParam(SpecParam specParam);

    /**
     * 删除规格参数
     * @param specParamIds 规格id拼串（1,2...）
     */
    void deleteSpecParam(String specParamIds);

    /**
     * 查询规格参数详情
     * @param specParamId 主键
     * @return 单个实体
     */
    SpecParam findSpecParamById(Long specParamId);

    /**
     * 通过groupId查询规格
     * @param groupId
     * @return
     */
    List<SpecParam> findSpecParamByGroupId(Long groupId);
}
