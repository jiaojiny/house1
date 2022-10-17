package com.atguigu.en;

/**
 * 包名:com.atguigu.en
 *
 * @author Leevi
 * 日期2022-03-26  22:51
 */
public enum HouseStatus {
PUBLISH(1,"已发布"),UNPUBLISH(0,"未发布");
private int code;
private String message;

    HouseStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }
}
