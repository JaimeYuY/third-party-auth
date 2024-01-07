package com.ocean.angel.tool.openapi;

import com.ocean.angel.tool.common.ActionResult;
import com.ocean.angel.tool.common.BaseController;
import com.ocean.angel.tool.constants.ResultCode;
import com.ocean.angel.tool.domain.vo.DataVO;
import com.ocean.angel.tool.service.ChannelService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

@RestController
@RequestMapping("/openapi/test")
public class TestApi extends BaseController {

    @Resource
    private ChannelService channelService;

    /**
     * 获取测试数据
     */
    @GetMapping("/getData")
    public ActionResult<DataVO> getData() {

        // 数据权限校验
        Long channelId = getChannelId();
        if(null == channelId) {
            return ActionResult.error(ResultCode.DATA_PERMISSION_CHECK_FAILED);
        }

        // 模拟返回数据
        DataVO dataVO = new DataVO();
        dataVO.setId(1L);
        dataVO.setName("apple");

        return ActionResult.success(dataVO);
    }
}
