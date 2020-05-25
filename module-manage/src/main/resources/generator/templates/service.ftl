package ${basePackage}.${modulePackage}.${servicePackage};


import com.sxquan.core.entity.RequestPage;
import ${basePackage}.${modulePackage}.${entityPackage}.${className};
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * ${tableComment} Service接口
 *
 * @author ${author}
 * @since ${date}
 */
public interface I${className}Service extends IService<${className}> {
    /**
     * 查询列表（分页）
     *
     * @param ${className?uncap_first} ${className?uncap_first}
     * @param request 分页条件
     * @return IPage<${className}>
     */
    IPage<${className}> find${className}List(${className} ${className?uncap_first}, RequestPage request);

    /**
     * 查询（所有）
     *
     * @param ${className?uncap_first} ${className?uncap_first}
     * @return List<${className}>
     */
    List<${className}> find${className}All(${className} ${className?uncap_first});

    /**
     * 新增
     *
     * @param ${className?uncap_first} ${className?uncap_first}
     */
    void add${className}(${className} ${className?uncap_first});

    /**
     * 修改
     *
     * @param ${className?uncap_first} ${className?uncap_first}
     */
    void update${className}(${className} ${className?uncap_first});

    /**
     * 通过主键删除
     *
     * @param idList 主键集合
     */
    void delete${className}(List<String> idList);
}
