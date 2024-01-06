package com.ocean.angel.tool.common;

public class PageDomain {

    /**
     * 页码
     */
    private Integer pageNum;

    /**
     * 页面条数
     */
    private Integer pageSize;

    private int start;

    public int getStart() {
        if(null == pageNum || pageNum < 1) {
            this.pageNum = 1;
        }

        if(null == pageSize || pageSize < 1) {
            pageSize = 10;
        }
        return (pageNum - 1) * pageSize;
    }

    public int getPageSize() {
        if(null == pageSize || pageSize < 1) {
            pageSize = 10;
        }
        return pageSize;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public void setStart(int start) {
        this.start = start;
    }
}
