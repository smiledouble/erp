package com.cxs.bus.domain;

public class Customer {
    private Integer id;

    private String customername;

    private String zip;

    private String address;

    private String telephone;

    private String connectionperson;

    private String phone;

    private String bank;

    private String account;

    private String email;

    private String fax;

    private Integer available;

    public Customer(Integer id, String customername, String zip, String address, String telephone, String connectionperson, String phone, String bank, String account, String email, String fax, Integer available) {
        this.id = id;
        this.customername = customername;
        this.zip = zip;
        this.address = address;
        this.telephone = telephone;
        this.connectionperson = connectionperson;
        this.phone = phone;
        this.bank = bank;
        this.account = account;
        this.email = email;
        this.fax = fax;
        this.available = available;
    }

    public Customer() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername == null ? null : customername.trim();
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip == null ? null : zip.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getConnectionperson() {
        return connectionperson;
    }

    public void setConnectionperson(String connectionperson) {
        this.connectionperson = connectionperson == null ? null : connectionperson.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank == null ? null : bank.trim();
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax == null ? null : fax.trim();
    }

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }
}