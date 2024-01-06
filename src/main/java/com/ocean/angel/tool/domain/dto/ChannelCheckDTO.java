package com.ocean.angel.tool.domain.dto;

import lombok.Data;

@Data
public class ChannelCheckDTO {

    /**
     * 主键
     */
    private Long id;

    /**
     * 审核状态：0-未审核，1-审核通过，2-审核不通过
     */
    private Integer status;

}
