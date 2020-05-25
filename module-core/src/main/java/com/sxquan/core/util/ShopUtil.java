package com.sxquan.core.util;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

/**
 * @author sxquan
 * @description 工具类
 * @since 2020/2/27 17:57
 */
public class ShopUtil {

    /**
     * 驼峰转下划线
     *
     * @param value 待转换值
     * @return 结果
     */
    public static String camelToUnderscoreLowerCase(String value) {
        if (StringUtils.isBlank(value)) {
            return value;
        }
        String[] arr = StringUtils.splitByCharacterTypeCamelCase(value);
        if (arr.length == 0) {
            return value;
        }
        StringBuilder result = new StringBuilder();
        IntStream.range(0, arr.length).forEach(i -> {
            if (i != arr.length - 1) {
                result.append(arr[i]).append(StringPool.UNDERSCORE);
            } else {
                result.append(arr[i]);
            }
        });
        return StringUtils.lowerCase(result.toString());
    }

    /**
     * 下划线转驼峰
     *
     * @param value 待转换值
     * @return 结果
     */
    public static String underscoreToCamel(String value) {
        StringBuilder result = new StringBuilder();
        String[] arr = value.split("_");
        for (String s : arr) {
            result.append((String.valueOf(s.charAt(0))).toUpperCase()).append(s.substring(1));
        }
        return result.toString();
    }


    /**
     * 判断是否包含中文
     *
     * @param value 内容
     * @return 结果
     */
    public static boolean containChinese(String value) {
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(value);
        return m.find();
    }

    /**
     *  捕获异常栈
     * @param t Throwable
     * @return
     */
    public static String getTrace(Throwable t) {
        StringWriter stringWriter= new StringWriter();
        PrintWriter writer= new PrintWriter(stringWriter);
        t.printStackTrace(writer);
        StringBuffer buffer= stringWriter.getBuffer();
        return buffer.toString();
    }

    /**
     * 判断是否为 ajax请求
     *
     * @param request HttpServletRequest
     * @return boolean
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {
        return (request.getHeader("X-Requested-With") != null
                && "XMLHttpRequest".equals(request.getHeader("X-Requested-With")));
    }

    /**
     * 姓名脱敏
     * @param fullName 完整姓名
     * @return
     */
    public static String desensitizedIdName(String fullName){
       if (StringUtils.isNotBlank(fullName)) {
           StringBuilder sb = new StringBuilder();
           sb.append(fullName, 0, 1);
           sb.append(StringPool.ASTERISK);
           if (fullName.length() > 2) {
               sb.append(fullName.substring(fullName.length()-1));
           }
           return sb.toString();
       }
      return fullName;
    }

    /**
     * 手机号码脱敏
     * @param fullMobile 完整手机
     * @return 脱敏手机号
     */
    public static String desensitizedIdMobile(String fullMobile){
      if (StringUtils.isBlank(fullMobile) || fullMobile.length() != 11) {
          return fullMobile;
      }
       return fullMobile.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
    }

}
