package com.fengwenyi.erwinmessage.security.util;

import com.fengwenyi.mount.security.bean.AuthenticationUser;
import com.fengwenyi.mount.security.entity.UserEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author Erwin Feng[xfsy_2015@163.com]
 * @since 2019/12/25
 */
public class MyUserUtils {

    public static User getLoginUser() {

        /**
         SecurityContextHolder.getContext()获取安全上下文对象，就是那个保存在 ThreadLocal 里面的安全上下文对象
         总是不为null(如果不存在，则创建一个authentication属性为null的empty安全上下文对象)
         获取当前认证了的 principal(当事人),或者 request token (令牌)
         如果没有认证，会是 null,该例子是认证之后的情况
         */
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //有登陆用户就返回登录用户，没有就返回null
        if (authentication != null) {
            if (authentication instanceof AnonymousAuthenticationToken) {
                return null;
            }

            if (authentication instanceof UsernamePasswordAuthenticationToken) {
                return (User) authentication.getPrincipal();
            }
        }

        return null;
    }
}
