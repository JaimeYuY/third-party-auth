package com.ocean.angel.tool.domain.dto;

import java.io.Serializable;
import lombok.Data;

@Data
public class ChannelDTO implements Serializable {

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

}
