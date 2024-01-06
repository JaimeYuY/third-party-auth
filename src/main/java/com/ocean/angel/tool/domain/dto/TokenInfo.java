package com.ocean.angel.tool.domain.dto;

import lombok.Data;

/**
 * 令牌信息
 */
@Data
public class TokenInfo {

    /**
     *  OpenAPI令牌
     */
    private String token;

    /**
     *  令牌有效期，单位：s
     */
    private long expiration;

}
