package com.atguigu.en;

public enum YesOrNo {
    YES(1,"可用"),NO(0,"不可用");

  private Integer code;
  private String message;

    YesOrNo(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }
}
