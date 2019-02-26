package com.cxs.sys.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Log {
    private Integer id;

    private String loginname;

    private String loginip;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date logintime;

    public Log(Integer id, String loginname, String loginip, Date logintime) {
        this.id = id;
        this.loginname = loginname;
        this.loginip = loginip;
        this.logintime = logintime;
    }

    public Log() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname == null ? null : loginname.trim();
    }

    public String getLoginip() {
        return loginip;
    }

    public void setLoginip(String loginip) {
        this.loginip = loginip == null ? null : loginip.trim();
    }

    public Date getLogintime() {
        return logintime;
    }

    public void setLogintime(Date logintime) {
        this.logintime = logintime;
    }
}