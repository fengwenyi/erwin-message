package com.fengwenyi.erwinmessage.security.handler;

import com.fengwenyi.mount.security.util.MyUtils;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.security.auth.login.AccountLockedException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证失败
 * @author Erwin Feng[xfsy_2015@163.com]
 * @since 2019/12/25
 */
@Component
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        String message;
        if (e instanceof UsernameNotFoundException) {
            message = "Erwin ID 不正确，登录失败";
        } else if (e instanceof BadCredentialsException) {
            message = "密码输入错误，登录失败!";
        } else if (e instanceof LockedException) {
            message = "Erwin ID 被锁定，登录失败!";
        } else if (e instanceof AccountExpiredException) {
            message = "账户已过期，登录失败!";
        } else {
            message = "登录失败，原因：" + e.getMessage();
        }
        MyUtils.renderError(httpServletResponse, message);
    }
}
