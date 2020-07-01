package com.sxquan.core.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;

/**
 * 分页数据
 * @Author sxquan
 * @Date 2020/2/3 15:36
 */
@Data
public class RequestPage {

    /**
     * 当前页面数据量
     */
    @Range(min = 1,max = 100)
    private int pageSize = 10;

    /**
     * 当前页码
     */
    @Min(value = 1)
    private int pageNum = 1;

    /**
     * 排序字段
     */
    private String field;

    /**
     *  排序规则，asc升序，desc降序
     */
    private String order;
}
