package com.ocean.angel.tool.openapi;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@Slf4j
class TestApiTest extends OpenApiCommonTest {

    // 令牌头信息
    public static String ACCESS_HEADER = "Authorization";

    @Test
    void getData() {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add(ACCESS_HEADER, getToken());
            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/openapi/test/getData")
                    .headers(headers)).andReturn();
            if(HttpStatus.OK.value() == mvcResult.getResponse().getStatus()) {
                log.info("{}", mvcResult.getResponse().getContentAsString());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
