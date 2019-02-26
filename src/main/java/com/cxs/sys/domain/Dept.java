package com.cxs.sys.domain;

public class Dept {
    private Integer id;

    private Integer pid;

    private String name;

    private Integer open;

    private Integer parent;

    private String remark;

    private String loc;

    private Integer available;

    private Integer ordernum;

    public Dept(Integer id, Integer pid, String name, Integer open, Integer parent, String remark, String loc, Integer available, Integer ordernum) {
        this.id = id;
        this.pid = pid;
        this.name = name;
        this.open = open;
        this.parent = parent;
        this.remark = remark;
        this.loc = loc;
        this.available = available;
        this.ordernum = ordernum;
    }

    public Dept() {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getOpen() {
        return open;
    }

    public void setOpen(Integer open) {
        this.open = open;
    }

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc == null ? null : loc.trim();
    }

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }

    public Integer getOrdernum() {
        return ordernum;
    }

    public void setOrdernum(Integer ordernum) {
        this.ordernum = ordernum;
    }
}