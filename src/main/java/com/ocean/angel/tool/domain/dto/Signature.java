package com.ocean.angel.tool.domain.dto;

import lombok.Data;

/**
 * 签名信息
 */
@Data
public class Signature {

    /**
     *  应用ID
     */
    private String appId;

    /**
     *  随机数
     */
    private String random;

    /**
     *  签名
     */
    private String sign;
}
