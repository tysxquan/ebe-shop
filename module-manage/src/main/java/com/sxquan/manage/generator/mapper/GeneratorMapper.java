package com.sxquan.manage.generator.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sxquan.manage.generator.pojo.Column;
import com.sxquan.manage.generator.pojo.Table;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author sxquan
 * @since 2020/5/21 14:40
 */
@Repository
public interface GeneratorMapper {

    /**
     * 获取表参数
     * @param schemaName 数据库名
     * @param tableName 表名
     * @return
     */
    List<Column> getColumns(@Param("schemaName") String schemaName, @Param("tableName") String tableName);

    /**
     *  获取表信息列表
     * @param page 分页
     * @param tableName 表名
     * @param schemaName 数据库名
     * @return
     */
    IPage<Table> getTableList(IPage<Table> page,String schemaName ,String tableName);
}
