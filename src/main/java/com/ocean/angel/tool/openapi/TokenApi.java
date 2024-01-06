package com.ocean.angel.tool.openapi;

import cn.hutool.core.util.StrUtil;
import com.ocean.angel.tool.common.ActionResult;
import com.ocean.angel.tool.constants.ResultCode;
import com.ocean.angel.tool.constants.enums.CheckEnum;
import com.ocean.angel.tool.domain.dto.AppInfo;
import com.ocean.angel.tool.domain.dto.Signature;
import com.ocean.angel.tool.domain.dto.TokenInfo;
import com.ocean.angel.tool.domain.entity.Channel;
import com.ocean.angel.tool.service.ChannelService;
import com.ocean.angel.tool.util.SignatureUtil;
import com.ocean.angel.tool.util.TokenUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

@RestController
@RequestMapping("/openapi/token")
public class TokenApi {

    @Resource
    private ChannelService channelService;

    /**
     * 获取令牌
     * @param signature
     * @return
     */
    @GetMapping("get")
    public ActionResult<TokenInfo> get(Signature signature) {

        // 参数非空校验
        if(StrUtil.isEmpty(signature.getAppId()) || StrUtil.isEmpty(signature.getRandom()) || StrUtil.isEmpty(signature.getSign())) {
            return ActionResult.error(ResultCode.PARAM_ERROR);
        }

        // 获取渠道商
        Channel channel = channelService.getChannelByAppId(signature.getAppId());
        if(null == channel || channel.getStatus().equals(CheckEnum.NO.getCode())) {
            return ActionResult.error(ResultCode.PARAM_ERROR);
        }

        // 签名校验
        boolean checkResult = SignatureUtil.checkSignature(channel.getAppSecret(), signature.getRandom(), signature.getSign());
        if(!checkResult) {
            return ActionResult.error(ResultCode.PARAM_ERROR);
        }

        AppInfo appInfo = new AppInfo();
        appInfo.setChannelId(channel.getId());
        appInfo.setAppSecret(channel.getAppSecret());
        appInfo.setAppId(channel.getAppId());

        // 生成令牌信息
        TokenInfo tokenInfo = TokenUtil.getToken(appInfo);
        return ActionResult.success(tokenInfo);
    }
}
