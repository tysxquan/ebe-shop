package com.sxquan.core.util;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * @author sxquan
 * @since 2020/3/6 19:08
 */
public class LocalDateUtil {

    public static final String TIME_PATTERN = "HH:mm:ss";

    public static final String TIME_SPLIT_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     *  LocalTime 转 string
     * @param localTime HH:mm:ss 类型的时间格式
     * @return 格式化成string
     */
    public static String localTimeToString(LocalTime localTime) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(TIME_PATTERN);
        return df.format(localTime);
    }

    /**
     *  localDateTime 转 string
     * @param localDateTime yyyy-MM-dd HH:mm:ss 类型的时间格式
     * @return 格式化成string
     */
    public static String localDateTimeToString(LocalDateTime localDateTime) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(TIME_SPLIT_PATTERN);
        return df.format(localDateTime);
    }
}
