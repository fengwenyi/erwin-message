package com.fengwenyi.erwinmessage.security.handler;

import com.fengwenyi.api.result.ResultTemplate;
import com.fengwenyi.erwinmessage.security.entity.UserEntity;
import com.fengwenyi.erwinmessage.security.service.UserService;
import com.fengwenyi.erwinmessage.security.util.JwtTokenUtils;
import com.fengwenyi.erwinmessage.security.util.TokenUtils;
import com.fengwenyi.javalib.convert.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Erwin Feng[xfsy_2015@163.com]
 * @since 2019/12/25
 */
@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private JwtTokenUtils jwtTokenUtils;

    @Autowired
    private UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenUtils.generateToken(userDetails.getUsername());

        UserEntity userEntity = userService.findByUsername(userDetails.getUsername());

        // 存储token
        TokenUtils.set(userEntity.getUsername(), token);

        renderToken(httpServletResponse, token);
    }

    /**
     * 渲染返回 token 页面,因为前端页面接收的都是Result对象，故使用application/json返回
     *
     * @param response
     * @throws IOException
     */
    public void renderToken(HttpServletResponse response, String token) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        ServletOutputStream out = response.getOutputStream();
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        ResultTemplate<Map<String, String>> resultModel = ResultTemplate.success(map);
        String str = JsonUtils.convertString(resultModel);
        out.write(str.getBytes(StandardCharsets.UTF_8));
        out.flush();
        out.close();
    }
}
