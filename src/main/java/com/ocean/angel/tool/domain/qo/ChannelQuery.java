package com.ocean.angel.tool.domain.qo;

import java.io.Serializable;
import lombok.Data;
import com.ocean.angel.tool.common.PageDomain;

@Data
public class ChannelQuery extends PageDomain implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 渠道名称
     */
    private String name;

    /**
     * 应用ID
     */
    private String appId;

    /**
     * 审核状态：0-未审核，1-审核通过，2-审核不通过
     */
    private Integer status;

}
