package com.cxs.sys.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:chenxiaoshuang
 * @Date:2019.1.19 下午 19:37
 */
public class TreeNodeBulider {


    public static List<TreeNode> buildTd(List<TreeNode> treeNodes, Integer topId) {
        List<TreeNode> nodeList = new ArrayList<>();
        //两次for循环 找出父节点和子节点
        for (TreeNode n1 : treeNodes) {
            if (n1.getPid().intValue() == topId.intValue()) {
                //相等就加到最外面的父节点上
                nodeList.add(n1);
            }
            for (TreeNode n2 : treeNodes) {
                if (n1.getId().intValue() == n2.getPid().intValue()) {
                    //找到子元素往里面放
                    n1.getChildren().add(n2);
                }
            }
        }
        return nodeList;

    }
}
