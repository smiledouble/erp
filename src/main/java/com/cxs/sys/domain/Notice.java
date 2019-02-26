package com.cxs.sys.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Notice {
    private Integer id;

    private String title;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createtime;

    private String opername;

    private String content;

    public Notice(Integer id, String title, Date createtime, String opername, String content) {
        this.id = id;
        this.title = title;
        this.createtime = createtime;
        this.opername = opername;
        this.content = content;
    }

    public Notice() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getOpername() {
        return opername;
    }

    public void setOpername(String opername) {
        this.opername = opername == null ? null : opername.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}