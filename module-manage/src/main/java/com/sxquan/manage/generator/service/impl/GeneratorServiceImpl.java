package com.sxquan.manage.generator.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sxquan.core.entity.RequestPage;
import com.sxquan.manage.common.constant.GeneratorConstant;
import com.sxquan.manage.generator.mapper.GeneratorMapper;
import com.sxquan.manage.generator.pojo.Column;
import com.sxquan.manage.generator.pojo.Table;
import com.sxquan.manage.generator.service.IGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author sxquan
 * @since 2020/5/21 14:32
 */
@Service
public class GeneratorServiceImpl implements IGeneratorService {

    @Autowired
    private GeneratorMapper generatorMapper;

    @Override
    public List<Column> getColumns(String schemaName, String tableName) {
        return generatorMapper.getColumns(schemaName,tableName);
    }

    @Override
    public IPage<Table> findTableList(String tableName, RequestPage requestPage) {
        IPage<Table> page = new Page<>(requestPage.getPageNum(),requestPage.getPageSize());
        return generatorMapper.getTableList(page, GeneratorConstant.DATABASE_NAME,tableName);
    }
}
