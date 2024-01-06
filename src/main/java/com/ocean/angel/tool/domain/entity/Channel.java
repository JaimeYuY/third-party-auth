package com.ocean.angel.tool.domain.entity;

import lombok.Data;
import java.util.Date;

@Data
public class Channel {

    /**
     * 渠道ID
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
     * 审核状态：0-待审核，1-审核通过 2-审核失败
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
