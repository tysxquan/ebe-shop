package com.sxquan.manage.generator.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.sxquan.core.pojo.generator.GeneratorConfig;

/**
 * <p>
 * 代码生成配置表 服务类
 * </p>
 *
 * @author sxquan
 * @since 2020-05-21
 */
public interface IGeneratorConfigService extends IService<GeneratorConfig> {

    /**
     * 查询代码生存配置
     * @return
     */
    GeneratorConfig findGeneratorConfig();

    /**
     * 更新配置
     * @param generatorConfig 参数
     */
    void updateGeneratorConfig(GeneratorConfig generatorConfig);
}
