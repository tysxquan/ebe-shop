package com.sxquan.core.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @Description layui的table模块数据封装
 * @Author sxquan
 * @Date 2020/2/3 21:41
 */
@Data
@AllArgsConstructor
public class LayuiPage<T> {

    private List<T> list;

    private Long total;
}
