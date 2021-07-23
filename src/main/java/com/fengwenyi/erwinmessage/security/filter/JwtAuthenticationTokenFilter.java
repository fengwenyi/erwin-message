package com.fengwenyi.erwinmessage.security.filter;

import com.fengwenyi.mount.security.bean.AuthenticationUser;
import com.fengwenyi.mount.security.entity.UserEntity;
import com.fengwenyi.mount.security.service.UserService;
import com.fengwenyi.mount.security.util.JwtTokenUtils;
import com.fengwenyi.mount.security.util.MyUtils;
import com.fengwenyi.mount.security.util.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Erwin Feng[xfsy_2015@163.com]
 * @since 2019/12/25
 */
@Slf4j
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Resource
    private UserDetailsService userDetailsService;
    @Resource
    private JwtTokenUtils jwtTokenUtils;

    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain) throws ServletException, IOException {

        // 首页判断客户端请求有没有携带token

        // 再判断token是否是正确的

        /*
        如果token存在bearer中，取：
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            final String authToken = authHeader.substring("Bearer ".length());
         */

        String token = request.getHeader(jwtTokenUtils.getHeader());
//        log.info("token: {}", token);

        if (!StringUtils.isEmpty(token)) {
            String username = jwtTokenUtils.getUsernameFromToken(token);
            if (!StringUtils.isEmpty(username)) {
                UserEntity userEntity = userService.findByUsername(username);
                String storageToken = TokenUtils.get(userEntity.getUsername());
                if (!StringUtils.isEmpty(storageToken) && storageToken.equals(token)) {
                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                    if (userDetails != null) {
                        if (jwtTokenUtils.validateToken(token, userDetails)) {
                            // 将用户信息存入 authentication，方便后续校验
                            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//                            AuthenticationUser authentication = new AuthenticationUser(userDetails, null, userDetails.getAuthorities());
//                            authentication.setUid(userEntity.getUid());
                            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                            // 将 authentication 存入 ThreadLocal，方便后续获取用户信息
                            SecurityContextHolder.getContext().setAuthentication(authentication);

                        }
                    }
                }
            } else {
                MyUtils.renderError(response, "token不正确");
                return;
            }
        }

        //
        chain.doFilter(request, response);
    }


}
