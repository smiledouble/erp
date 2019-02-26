package com.cxs.bus.domain;

public class Goods {
    private Integer id;

    private String goodsname;

    private String produceplace;

    private String size;

    private String goodspackage;

    private String productcode;

    private String promitcode;

    private String description;

    private Double price;

    private Integer number;

    private Integer dangernum;

    private String goodsimg;

    private Integer available;

    private Integer providerid;

    private String providername;

    public String getProvidername() {
        return providername;
    }

    public void setProvidername(String providername) {
        this.providername = providername;
    }

    public Goods(Integer id, String goodsname, String produceplace, String size, String goodspackage, String productcode, String promitcode, String description, Double price, Integer number, Integer dangernum, String goodsimg, Integer available, Integer providerid) {
        this.id = id;
        this.goodsname = goodsname;
        this.produceplace = produceplace;
        this.size = size;
        this.goodspackage = goodspackage;
        this.productcode = productcode;
        this.promitcode = promitcode;
        this.description = description;
        this.price = price;
        this.number = number;
        this.dangernum = dangernum;
        this.goodsimg = goodsimg;
        this.available = available;
        this.providerid = providerid;
    }

    public Goods() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGoodsname() {
        return goodsname;
    }

    public void setGoodsname(String goodsname) {
        this.goodsname = goodsname == null ? null : goodsname.trim();
    }

    public String getProduceplace() {
        return produceplace;
    }

    public void setProduceplace(String produceplace) {
        this.produceplace = produceplace == null ? null : produceplace.trim();
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size == null ? null : size.trim();
    }

    public String getGoodspackage() {
        return goodspackage;
    }

    public void setGoodspackage(String goodspackage) {
        this.goodspackage = goodspackage == null ? null : goodspackage.trim();
    }

    public String getProductcode() {
        return productcode;
    }

    public void setProductcode(String productcode) {
        this.productcode = productcode == null ? null : productcode.trim();
    }

    public String getPromitcode() {
        return promitcode;
    }

    public void setPromitcode(String promitcode) {
        this.promitcode = promitcode == null ? null : promitcode.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getDangernum() {
        return dangernum;
    }

    public void setDangernum(Integer dangernum) {
        this.dangernum = dangernum;
    }

    public String getGoodsimg() {
        return goodsimg;
    }

    public void setGoodsimg(String goodsimg) {
        this.goodsimg = goodsimg == null ? null : goodsimg.trim();
    }

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }

    public Integer getProviderid() {
        return providerid;
    }

    public void setProviderid(Integer providerid) {
        this.providerid = providerid;
    }
}