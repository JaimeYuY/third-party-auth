package com.ocean.angel.tool.common;

import com.ocean.angel.tool.constants.ResultCode;
import lombok.Data;
import java.io.Serializable;
import java.util.List;

@Data
public class PageResult<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer total;

    private List<T> rows;

    private Integer code;

    private String msg;

    private Integer pageNum;

    private Integer pageSize;

    private Integer pages;

    public PageResult() {
    }

    public PageResult(List<T> list, int total) {
        this.rows = list;
        this.total = total;
    }

    public PageResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public PageResult(Integer total, List<T> rows, int code, String msg) {
        this.total = total;
        this.rows = rows;
        this.code = code;
        this.msg = msg;
    }

    public PageResult(Integer total, List<T> rows, int code, String msg, int pageNum, int pageSize) {
        this.total = total;
        this.rows = rows;
        this.code = code;
        this.msg = msg;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.pages = total % pageSize == 0 ? total / pageSize : total / pageSize + 1;
    }


    public static <T> PageResult<T> success(List<T> list, Integer total) {
        return new PageResult(total, list, ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg());
    }

    public static <T> PageResult<T> success(List<T> list, Integer total, Integer pageNum, Integer pageSize) {
        return new PageResult(total, list, ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg(), pageNum, pageSize);
    }

    public static <T> PageResult<T> error(ResultCode resultCode) {
        return new PageResult(resultCode.getCode(), resultCode.getMsg());
    }

    public static <T> PageResult<T> error() {
        return new PageResult(ResultCode.INTERNAL_SERVER_ERROR.getCode(), ResultCode.INTERNAL_SERVER_ERROR.getMsg());
    }
}
