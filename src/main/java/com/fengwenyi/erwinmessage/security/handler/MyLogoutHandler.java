package com.fengwenyi.erwinmessage.security.handler;

import com.fengwenyi.erwinmessage.security.service.UserService;
import com.fengwenyi.erwinmessage.security.util.MyUserUtils;
import com.fengwenyi.erwinmessage.security.util.TokenUtils;
import com.fengwenyi.javalib.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 退出
 * @author Erwin Feng[xfsy_2015@163.com]
 * @since 2019/12/27
 */
@Component
public class MyLogoutHandler implements LogoutHandler {

    @Autowired
    private UserService userService;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        // 清除token
        // AuthenticationUser loginUser = MyUserUtils.getLoginUser();

        User user =  MyUserUtils.getLoginUser();
        if (user != null) {
            String username = user.getUsername();
            if (StringUtils.isNotEmpty(username)) {
                TokenUtils.remove(username);
            }
        }
    }
}
