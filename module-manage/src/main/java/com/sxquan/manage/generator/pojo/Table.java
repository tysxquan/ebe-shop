package com.sxquan.manage.generator.pojo;

import lombok.Data;

/**
 * @author sxquan
 * @since 2020/5/22 23:53
 */
@Data
public class Table {
    /**
     * 名称
     */
    private String name;
    /**
     * 备注
     */
    private String remark;
    /**
     * 数据量（行）
     */
    private Long dataRows;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 修改时间
     */
    private String updateTime;
}
