package com.sxquan.manage.common.config;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author sxquan
 * @since 2020/5/26 22:39
 */
@Configuration
public class DateFormatConfig {

    /**
     * 此处通过Value注解读取配置文件中的 date-format
     */
    @Value("${spring.jackson.date-format}")
    private String pattern;

    /**
     *  注册响应json格式的LocalDateTime日期格式序列化器
     */
    @Bean
    public LocalDateTimeSerializer localDateTimeSerializer() {
        return new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     *  注册json请求方式的LocalDateTime反序列化器
     */
    @Bean
    public LocalDateTimeDeserializer localDateTimeDeserializer() {
        return new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     *  格式化日期类型,响应对应格式化日期字符串
     */
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return builder -> {
            //返回json格式,前端序列化为字符串
            builder.serializerByType(LocalDateTime.class, localDateTimeSerializer());
            //从json对象日期字符串反序列化为日期对象
            builder.deserializerByType(LocalDateTime.class, localDateTimeDeserializer());
        };
    }

}
