package com.degal.webdemo;

import java.io.Serializable;

/**
 * 作者：create by 18729 on 2018/11/20
 * 说明：返回数据类
 */
public class ResultBean implements Serializable {
    int code;
    String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
