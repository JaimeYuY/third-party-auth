package com.ocean.angel.tool.interceptor;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ocean.angel.tool.common.ActionResult;
import com.ocean.angel.tool.util.TokenUtil;
import com.ocean.angel.tool.constants.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * OpenAPI拦截器
 */
@Slf4j
public class OpenApiAuthInterceptor implements HandlerInterceptor {

    // 令牌头信息
    public static String ACCESS_HEADER = "Authorization";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {

            // 获取令牌信息
            String apiToken = request.getHeader(ACCESS_HEADER);
            if (StrUtil.isEmpty(apiToken)) {
                ActionResult<Object> result = ActionResult.error(ResultCode.Unauthorized);
                responseData(response, result);
                return false;
            }

            // 校验token
            if (TokenUtil.isExpired(apiToken)) {
                ActionResult<Object> result = ActionResult.error(ResultCode.Unauthorized);
                responseData(response, result);
                return false;
            }
            return true;
        } catch (Exception e) {
            log.error("OpenApiAuthInterceptor() error, {}", e.getMessage(), e);
        }
        return false;
    }

    public static void responseData(HttpServletResponse response, Object resData) throws JsonProcessingException {

        // 将实体对象转换为jackson Object转换
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(resData);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;

        try {
            out = response.getWriter();
            out.append(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }

    }
}
