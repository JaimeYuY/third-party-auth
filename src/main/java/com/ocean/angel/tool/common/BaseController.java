package com.ocean.angel.tool.common;

import cn.hutool.core.util.StrUtil;
import com.ocean.angel.tool.util.TokenUtil;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

public class BaseController {

    @Resource
    private HttpServletRequest request;

    // 令牌头信息
    public static String ACCESS_HEADER = "Authorization";

    /**
     * 获取渠道ID
     * @return
     */
    public Long getChannelId() {
        String token = request.getHeader(ACCESS_HEADER);
        if(StrUtil.isEmpty(token)) {
            return null;
        }
        Long channelId = TokenUtil.getChannelId(token);
        return channelId;
    }
}
