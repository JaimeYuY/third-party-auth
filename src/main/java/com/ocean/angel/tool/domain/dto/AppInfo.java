package com.ocean.angel.tool.domain.dto;

import lombok.Data;

/**
 * 第三方应用信息
 */
@Data
public class AppInfo {

    /**
     *  渠道ID
     */
    private Long channelId;

    /**
     *  应用ID
     */
    private String appId;

    /**
     *  应用秘钥
     */
    private String appSecret;

}
