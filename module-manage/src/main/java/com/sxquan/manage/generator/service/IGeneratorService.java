package com.sxquan.manage.generator.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sxquan.core.entity.RequestPage;
import com.sxquan.manage.generator.pojo.Column;
import com.sxquan.manage.generator.pojo.Table;

import java.util.List;

/**
 * @author sxquan
 * @since 2020/5/21 14:31
 */
public interface IGeneratorService {

    /**
     * 获取表参数
     * @param schemaName 数据库名
     * @param tableName 表名
     * @return
     */
    List<Column> getColumns(String schemaName, String tableName);

    /**
     * 获取所有表信息列表
     * @param tableName 表名
     * @param requestPage 分页参数
     * @return
     */
    IPage<Table> findTableList(String tableName, RequestPage requestPage);
}
