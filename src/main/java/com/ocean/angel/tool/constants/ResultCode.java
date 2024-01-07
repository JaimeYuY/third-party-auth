package com.ocean.angel.tool.constants;

/**
 * 响应码枚举
 */
public enum ResultCode {

    SUCCESS(200, "Success"),

    BAD_REQUEST(400, "Bad Request"),

    Unauthorized(401, "Unauthorized"),

    DATA_PERMISSION_CHECK_FAILED(403, "Data permission verification failed."),

    NOT_FOUND(404, "Not Found"),

    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),

    PARAM_ERROR(505, "Parameter error."),

    RECORD_NOT_EXIST(60100, "Record does not exist.");

    private int code;

    private String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
