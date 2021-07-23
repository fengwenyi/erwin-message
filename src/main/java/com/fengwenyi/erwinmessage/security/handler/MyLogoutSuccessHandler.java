package com.fengwenyi.erwinmessage.security.handler;

import com.alibaba.fastjson.JSON;
import com.fengwenyi.api_result.helper.ResultHelper;
import com.fengwenyi.api_result.model.ResultModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @author Erwin Feng[xfsy_2015@163.com]
 * @since 2019/12/25
 */
@Component
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {

    protected Logger logger = LoggerFactory.getLogger(MyLogoutSuccessHandler.class);

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//        logger.info("logout user {}",authentication.getName());

        response.setContentType("application/json;charset=UTF-8");
        ServletOutputStream out = response.getOutputStream();
        ResultModel<Map<String, String>> resultModel = ResultHelper.success("Success");
        String str = JSON.toJSONString(resultModel);
        out.write(str.getBytes(StandardCharsets.UTF_8));
        out.flush();
        out.close();
    }
}
