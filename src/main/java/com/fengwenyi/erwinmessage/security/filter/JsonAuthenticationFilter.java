package com.fengwenyi.erwinmessage.security.filter;

import com.fengwenyi.javalib.convert.JsonUtils;
import lombok.SneakyThrows;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @author Erwin Feng[xfsy_2015@163.com]
 * @since 2019/12/27
 */
public class JsonAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

//    public JsonAuthenticationFilter(AuthenticationManager authenticationManager) {
//        this.authenticationManager = authenticationManager;
//        // 设置该过滤器地址
//        super.setFilterProcessesUrl("/auth/login");
//    }



//    public void setPostOnly(boolean postOnly) {
//        this.postOnly = postOnly;
//    }

    @SneakyThrows
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        } else {

            //从json中获取username和password
            String body = StreamUtils.copyToString(request.getInputStream(), StandardCharsets.UTF_8);
            String username = null, password = null;
            if(StringUtils.hasText(body)) {
                Map<String, String> loginMap = JsonUtils.convertMap(body, String.class, String.class);
                username = loginMap.get("username");
                password = loginMap.get("password");
            }

//            System.out.println(username);
//            System.out.println(password);

            if (username == null) {
                username = "";
            }

            if (password == null) {
                password = "";
            }

            username = username.trim();
//            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
//            this.setDetails(request, authRequest);
            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
                    username, password);

            // Allow subclasses to set the "details" property
            setDetails(request, authRequest);

            return this.getAuthenticationManager().authenticate(authRequest);
        }
    }
}
