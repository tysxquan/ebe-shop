package com.sxquan.core.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Description 树业务对象
 * @Author sxquan
 * @Date 2019/12/30 17:33
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TreeBO implements Serializable {

    private static final long serialVersionUID = -1214423360871835154L;
    /**
     * 节点id
     */
    private Long nodeId;

    /**
     * 上级菜单id,父类别id当id=0时说明是根节点,一级类别
     */
    private Long parentId;


    /**
     * 子内容
     */
    private List<TreeBO> child;

    /**
     * 当前id路径
     */
    private String parentIds;
}
