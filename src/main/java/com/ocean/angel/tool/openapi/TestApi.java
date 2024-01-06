package com.ocean.angel.tool.openapi;

import com.ocean.angel.tool.common.ActionResult;
import com.ocean.angel.tool.domain.vo.DataVO;
import com.ocean.angel.tool.service.ChannelService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

@RestController
@RequestMapping("/openapi/test")
public class TestApi {

    @Resource
    private ChannelService channelService;

    /**
     * 获取测试数据
     */
    @GetMapping("/getData")
    public ActionResult<DataVO> getData() {
        DataVO dataVO = new DataVO();
        dataVO.setId(1L);
        dataVO.setName("apple");
        return ActionResult.success(dataVO);
    }
}
