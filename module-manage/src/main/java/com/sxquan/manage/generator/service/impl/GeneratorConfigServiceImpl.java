package com.sxquan.manage.generator.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sxquan.manage.generator.mapper.GeneratorConfigMapper;
import com.sxquan.manage.generator.pojo.GeneratorConfig;
import com.sxquan.manage.generator.service.IGeneratorConfigService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 代码生成配置表 服务实现类
 * </p>
 *
 * @author sxquan
 * @since 2020-05-21
 */
@Service
public class GeneratorConfigServiceImpl extends ServiceImpl<GeneratorConfigMapper, GeneratorConfig> implements IGeneratorConfigService {

    @Override
    public GeneratorConfig findGeneratorConfig() {
        return baseMapper.selectOne(null);
    }

    @Override
    public void updateGeneratorConfig(GeneratorConfig generatorConfig) {
        baseMapper.updateById(generatorConfig);
    }
}
