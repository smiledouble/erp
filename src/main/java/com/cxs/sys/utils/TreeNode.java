package com.cxs.sys.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:chenxiaoshuang
 * @Date:2019/2/20 15:31
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TreeNode {

    private Integer id;
    @JsonProperty("pId")
    private Integer pid;
    private String type;
    private String title;
    private String href;
    private String icon;
    private Boolean spread;
    private List<TreeNode> children = new ArrayList<>();

    //zTree的树
    private String name;
    private Boolean isParent;
    private Boolean open;
    private Boolean checked; //复选树的属性

    /**
     * ztree复选树的构造方法
     *
     * @param id
     * @param pid
     * @param name
     * @param isParent
     * @param open
     * @param checked
     */
    public TreeNode(Integer id, Integer pid, String name, Boolean isParent, Boolean open, Boolean checked) {
        this.id = id;
        this.pid = pid;
        this.name = name;
        this.isParent = isParent;
        this.open = open;
        this.checked = checked;
    }

    /**
     * zTree的树的构造方法
     *
     * @param id
     * @param pid
     * @param name
     * @param isParent
     * @param open
     */
    public TreeNode(Integer id, Integer pid, String name, Boolean isParent, Boolean open) {
        this.id = id;
        this.pid = pid;
        this.name = name;
        this.isParent = isParent;
        this.open = open;
    }

    /**
     * 左边菜单的树的json构造器
     *
     * @param id
     * @param pid
     * @param title
     * @param href
     * @param icon
     * @param spread
     */
    public TreeNode(Integer id, Integer pid, String title, String href, String icon, Boolean spread) {
        this.id = id;
        this.pid = pid;
        this.title = title;
        this.href = href;
        this.icon = icon;
        this.spread = spread;
    }
}
