package com.cxs.sys.domain;

public class Role {
    private Integer id;

    private String name;

    private String remark;

    private Integer available;

    public Role(Integer id, String name, String remark, Integer available) {
        this.id = id;
        this.name = name;
        this.remark = remark;
        this.available = available;
    }

    public Role() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }
}