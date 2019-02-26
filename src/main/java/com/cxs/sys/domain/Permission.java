package com.cxs.sys.domain;

public class Permission {
    private Integer id;

    private Integer pid;

    private String type;

    private Integer parent;

    private String percode;

    private String name;

    private String icon;

    private String href;

    private String target;

    private Integer open;

    private Integer ordernum;

    private Integer available;

    public Permission(Integer id, Integer pid, String type, Integer parent, String percode, String name, String icon, String href, String target, Integer open, Integer ordernum, Integer available) {
        this.id = id;
        this.pid = pid;
        this.type = type;
        this.parent = parent;
        this.percode = percode;
        this.name = name;
        this.icon = icon;
        this.href = href;
        this.target = target;
        this.open = open;
        this.ordernum = ordernum;
        this.available = available;
    }

    public Permission() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }

    public String getPercode() {
        return percode;
    }

    public void setPercode(String percode) {
        this.percode = percode == null ? null : percode.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href == null ? null : href.trim();
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target == null ? null : target.trim();
    }

    public Integer getOpen() {
        return open;
    }

    public void setOpen(Integer open) {
        this.open = open;
    }

    public Integer getOrdernum() {
        return ordernum;
    }

    public void setOrdernum(Integer ordernum) {
        this.ordernum = ordernum;
    }

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }
}