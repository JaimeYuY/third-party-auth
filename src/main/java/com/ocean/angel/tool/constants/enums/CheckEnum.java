package com.ocean.angel.tool.constants.enums;

import java.util.Arrays;
import java.util.List;

public enum CheckEnum {

    DEFAULT(0, "已审核"),

    YES(1, "已审核"),

    NO(2, "未审核");

    private Integer code;

    private String msg;

    CheckEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static List<Integer> LEGAL_PARAM() {
        return Arrays.asList(YES.getCode(), NO.getCode());
    }
}
