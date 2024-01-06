package com.ocean.angel.tool.common;

import com.ocean.angel.tool.constants.ResultCode;
import lombok.Data;

/**
 * 响应信息
 */
@Data
public class ActionResult<T> {

    private Integer code;

    private String msg;

    private T data;

    public ActionResult(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> ActionResult<T> success(T data) {
        return new ActionResult(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg(), data);
    }

    public static <T> ActionResult<T> success() {
        return new ActionResult(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg(), null);
    }

    public static <T> ActionResult<T> error() {
        return new ActionResult(ResultCode.INTERNAL_SERVER_ERROR.getCode(), ResultCode.INTERNAL_SERVER_ERROR.getMsg(), null);
    }

    public static <T> ActionResult<T> error(ResultCode messageCode) {
        return new ActionResult(messageCode.getCode(), messageCode.getMsg(), null);
    }
}
