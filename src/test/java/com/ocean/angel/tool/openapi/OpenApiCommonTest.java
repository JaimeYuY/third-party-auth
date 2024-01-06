package com.ocean.angel.tool.openapi;

import com.ocean.angel.tool.domain.dto.AppInfo;
import com.ocean.angel.tool.domain.dto.TokenInfo;
import com.ocean.angel.tool.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
public class OpenApiCommonTest {

    protected MockMvc mockMvc;

    @BeforeEach
    public void setUp(WebApplicationContext context) {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .addFilter((request, response, chain) -> {
                    response.setCharacterEncoding("UTF-8");
                    chain.doFilter(request, response);
                }, "/*")
                .build();
    }

    public String getToken() {
        AppInfo appInfo = new AppInfo();
        appInfo.setAppId("4n5n0x28tpgs8pcb");
        appInfo.setAppSecret("udgd4u77udcelsuw8zu5de2sdot5oair");
        appInfo.setChannelId(1L);
        TokenInfo token = TokenUtil.getToken(appInfo);
        return token.getToken();
    }
}
