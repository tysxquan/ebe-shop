package ${basePackage}.${modulePackage}.${serviceImplPackage};


import com.sxquan.core.entity.RequestPage;
import ${basePackage}.${modulePackage}.${entityPackage}.${className};
import ${basePackage}.${modulePackage}.${mapperPackage}.${className}Mapper;
import ${basePackage}.${modulePackage}.${servicePackage}.I${className}Service;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * ${tableComment} Service实现
 *
 * @author ${author}
 * @since ${date}
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ${className}ServiceImpl extends ServiceImpl<${className}Mapper, ${className}> implements I${className}Service {

    @Autowired
    private ${className}Mapper ${className?uncap_first}Mapper;

    @Override
    public IPage<${className}> find${className}List(${className} ${className?uncap_first}, RequestPage request) {
        LambdaQueryWrapper<${className}> queryWrapper = new LambdaQueryWrapper<>();
        // TODO 设置查询条件
        Page<${className}> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.page(page, queryWrapper);
    }

    @Override
    public List<${className}> find${className}All(${className} ${className?uncap_first}) {
	    LambdaQueryWrapper<${className}> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void add${className}(${className} ${className?uncap_first}) {
        this.save(${className?uncap_first});
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void update${className}(${className} ${className?uncap_first}) {
        this.updateById(${className?uncap_first});
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void delete${className}(List<String> idList) {
        this.removeByIds(idList);
	}
}
