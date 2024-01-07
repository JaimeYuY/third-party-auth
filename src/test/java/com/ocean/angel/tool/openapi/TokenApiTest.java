package com.ocean.angel.tool.openapi;

import com.alibaba.fastjson.JSON;
import com.ocean.angel.tool.common.ActionResult;
import com.ocean.angel.tool.domain.dto.Signature;
import com.ocean.angel.tool.domain.dto.TokenInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import javax.annotation.Resource;

@Slf4j
@SpringBootTest
class TokenApiTest {

    @Resource
    private TokenApi tokenApi;

    @Test
    void get() {
        Signature signature = new Signature();
        signature.setAppId("4n5n0x28tpgs8pcb");
        signature.setRandom("hb@jk2ejw#");
        signature.setSign("f06e97e1780955420c47f7f4784def13");
        log.info("{}", JSON.toJSONString(signature));
        ActionResult<TokenInfo> result = tokenApi.get(signature);
        log.info("{}", JSON.toJSONString(result));
    }
}
