package com.ocean.angel.tool.domain.vo;

import java.util.Date;
import java.io.Serializable;
import lombok.Data;

@Data
public class ChannelVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 渠道名称
     */
    private String name;

    /**
     * 应用ID
     */
    private String appId;

    /**
     * 应用秘钥
     */
    private String appSecret;

    /**
     * 审核状态：0-未审核，1-审核通过，2-审核不通过
     */
    private Integer status;

    /**
     * 备注
     */
    private String notes;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

}
