package com.degal.webdemo;

import java.io.Serializable;

/**
 * Created by user:hsj
 * data:on 2018/11/15 0015.
 * class:用户类
 * 修改人员：
 * 修改时间：
 * 修改内容：
 */
public class User implements Serializable{

    private String position;
    private String sex;
    private String flag;
    private String icsn;
    private String department;
    private String company;
    private String empcode;
    private String empname;
    private String ickh;
    private String photo;


    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getIcsn() {
        return icsn;
    }

    public void setIcsn(String icsn) {
        this.icsn = icsn;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getEmpcode() {
        return empcode;
    }

    public void setEmpcode(String empcode) {
        this.empcode = empcode;
    }

    public String getEmpname() {
        return empname;
    }

    public void setEmpname(String empname) {
        this.empname = empname;
    }

    public String getIckh() {
        return ickh;
    }

    public void setIckh(String ickh) {
        this.ickh = ickh;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
