package com.sxquan.core.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

/**
 * @Description 处理请求的数据为string，转为Intager(未使用)
 * @Author sxquan
 * @Date 2020/2/5 16:53
 */
public class StringDeserializer extends JsonDeserializer<Integer> {
    @Override
    public Integer deserialize(JsonParser jp, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        return StringUtils.isBlank(jp.getText()) ? null : Integer.valueOf(jp.getText());
    }
}
