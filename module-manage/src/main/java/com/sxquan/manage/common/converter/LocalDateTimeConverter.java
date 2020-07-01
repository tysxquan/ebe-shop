package com.sxquan.manage.common.converter;

import com.sxquan.core.exception.ShopException;
import org.springframework.core.convert.converter.Converter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author sxquan
 * @since 2020/5/26 23:38
 */
// @Component
public class LocalDateTimeConverter implements Converter<String, LocalDateTime> {
    private static final String TIME_SPLIT_PATTERN = "yyyy-MM-dd HH:mm:ss";

    @Override
    public LocalDateTime convert(String source) {
        String value = source.trim();
        if ("".equals(value)) {
            return null;
        }
        LocalDateTime localDateTime = null;
        if (source.matches("^\\d{4}-\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}$")) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(TIME_SPLIT_PATTERN);
                localDateTime = LocalDateTime.parse(source, formatter);
            } catch (Exception e) {
                throw new ShopException("时间转换异常");
            }
        } else {
            throw new ShopException("时间字符串格式不正确！");
        }
        return localDateTime;
    }

}
