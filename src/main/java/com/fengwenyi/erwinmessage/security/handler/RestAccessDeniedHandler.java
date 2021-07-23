package com.fengwenyi.erwinmessage.security.handler;

import com.fengwenyi.mount.security.util.MyUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 权限校验
 * @author Erwin Feng[xfsy_2015@163.com]
 * @since 2019/12/25
 */
@Component
public class RestAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        // CodeMsg.ACCESS_DENIED.renderError(response);
        MyUtils.renderError(response, "无权限");
    }
}
