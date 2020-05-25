package com.sxquan.core.util;


import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.sxquan.core.entity.TreeBO;
import org.apache.commons.lang3.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
 
/**
 * 快速将一些list 组成父子树关系 并且自动拼接parentIds
 *
 *  @author sxquan
 *  @since 2019-12-20
 */
public class TreeUtils {
    /**
     *  根节点标识
     */
    private static final Long ROOT_NUM = 0L;

    /**
     *  list以tree归类
     *
     * @param treeList
     * @return java.util.List<T>
     */
    public static <T extends TreeBO> List<T> getTree(List<T> treeList) {
        List<T> result = new ArrayList<>();
        //获取所有顶级节点
        List<T> treeRoot = getTreeRoot(treeList);
        //移除顶级节点
        treeList.removeAll(treeRoot);
        //遍历获取子节点
        treeRoot.forEach(root -> {
            result.add(getChild(treeList, root));

        });
        return result;
    }
 
    /**
     * 获取所有顶级节点
     *
     * @param nodeList
     * @return
     */
    private static <T extends TreeBO> List<T> getTreeRoot(List<T> nodeList) {
        return nodeList.stream().filter(node -> {
            boolean flag= ( ObjectUtils.isNotEmpty(node.getParentId()) && ROOT_NUM.equals(node.getParentId()));
           if(flag){
               node.setParentIds("0");
           }
            return flag;
        }).collect(Collectors.toList());
    }
 
 
    /**
     * 递归过获取无限级子节点
     *
     * @param nodeList
     * @param node
     * @return
     */
    private static<T extends TreeBO> T getChild(List<T> nodeList, T node) {
        List<T> childList = getChildList(nodeList, node);
        if (CollectionUtils.isNotEmpty(childList)) {
            //移除已经遍历过的节点
            nodeList.removeAll(childList);
            childList.forEach(child -> {
                node.getChild().add(getChild(nodeList, child));
            });

        }
        return node;
    }
 
    /**
     * 得到子节点列表
     * parentId为空或者Null 或者为 0 表示顶级节点
     * @param nodeList
     * @param node 父节点
     * @param <T>
     * @return
     */
    private static  <T extends TreeBO> List<T> getChildList(List<T> nodeList, T node) {
        return nodeList.stream().filter( x -> {
            boolean flag = ObjectUtils.isNotEmpty(x.getParentId()) && x.getParentId().equals(node.getNodeId());
            if(flag) {
                List<TreeBO> childList = new ArrayList<>();
                x.setParentIds(node.getParentIds() + "," + node.getNodeId() );
                node.setChild(childList);
            }
            return flag;
        }).collect(Collectors.toList());
    }

}