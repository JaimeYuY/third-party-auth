package com.ocean.angel.tool.adminapi;

import com.alibaba.fastjson.JSON;
import com.ocean.angel.tool.common.ActionResult;
import com.ocean.angel.tool.common.PageResult;
import com.ocean.angel.tool.constants.enums.CheckEnum;
import com.ocean.angel.tool.domain.dto.ChannelCheckDTO;
import com.ocean.angel.tool.domain.dto.ChannelDTO;
import com.ocean.angel.tool.domain.entity.Channel;
import com.ocean.angel.tool.domain.qo.ChannelQuery;
import com.ocean.angel.tool.domain.vo.ChannelVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import javax.annotation.Resource;

@Slf4j
@SpringBootTest
class ChannelControllerTest {

    @Resource
    private ChannelController channelController;

    @Test
    void page() {
        ChannelQuery query = new ChannelQuery();
        PageResult<ChannelVO> result = channelController.page(query);
        log.info("{}", JSON.toJSONString(result));
    }

    @Test
    void get() {
        ActionResult<Channel> result = channelController.get(1L);
        log.info("{}", JSON.toJSONString(result));
    }

    @Test
    void save() {
        ChannelDTO dto = new ChannelDTO();
        dto.setName("雅迪");
        dto.setNotes("备注");
        dto.setStatus(CheckEnum.NO.getCode());
        ActionResult<Long> result = channelController.save(dto);
        log.info("{}", JSON.toJSONString(result));
    }

    @Test
    void update() {
        ChannelDTO dto = new ChannelDTO();
        dto.setId(1L);
        dto.setName("雅迪");
        dto.setNotes("备注备注");
        dto.setStatus(CheckEnum.NO.getCode());
        ActionResult<Long> result = channelController.update(dto);
        log.info("{}", JSON.toJSONString(result));
    }

    @Test
    void delete() {
        ActionResult<Void> result = channelController.delete(0L);
        log.info("{}", JSON.toJSONString(result));

    }

    @Test
    void check() {
        ChannelCheckDTO dto = new ChannelCheckDTO();
        dto.setId(1L);
        dto.setStatus(CheckEnum.YES.getCode());
        ActionResult<Long> result = channelController.check(dto);
        log.info("{}", JSON.toJSONString(result));
    }
}
