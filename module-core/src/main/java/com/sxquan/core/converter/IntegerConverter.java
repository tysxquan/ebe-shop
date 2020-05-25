package com.sxquan.core.converter;


import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author sxquan
 * @Date 2020/2/5 17:30
 */
@Component
public class IntegerConverter implements Converter<String,Integer> {

    @Override
    public Integer convert(String s) {
        System.out.println(s+"-----------------------");
        return StringUtils.isBlank(s) ? null : Integer.valueOf(s);
    }
}
