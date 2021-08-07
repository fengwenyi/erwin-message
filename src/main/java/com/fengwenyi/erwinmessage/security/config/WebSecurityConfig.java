package com.fengwenyi.erwinmessage.security.config;

import com.fengwenyi.erwinmessage.security.filter.JsonUsernamePasswordAuthenticationFilter;
import com.fengwenyi.erwinmessage.security.filter.JwtAuthenticationTokenFilter;
import com.fengwenyi.erwinmessage.security.handler.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.header.Header;
import org.springframework.security.web.header.writers.StaticHeadersWriter;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * @author Erwin Feng[xfsy_2015@163.com]
 * @since 2019/12/25
 */
@Configuration
@EnableWebSecurity
//添加annotation 支持,包括（prePostEnabled，securedEnabled...）
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;

    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Autowired
    private MyLogoutSuccessHandler myLogoutSuccessHandler;

    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Autowired
    private MyLogoutHandler myLogoutHandler;

    @Autowired
    private EntryPointUnauthorizedHandler entryPointUnauthorizedHandler;

    @Autowired
    private RestAccessDeniedHandler restAccessDeniedHandler;


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.addFilterAt(jsonUsernamePasswordAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        // java.lang.IllegalArgumentException: role should not start with 'ROLE_' since it is automatically inserted. Got 'ROLE_USER'
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        http
                //.addFilterBefore(jwtAuthenticationTokenFilter, JsonUsernamePasswordAuthenticationFilter.class)
//        http.addFilterBefore(jwtAuthenticationTokenFilter, JsonAuthenticationFilter.class)
                .authorizeRequests()
                    .antMatchers("/static/**", "/favicon.ico").permitAll() //静态资源访问无需认证
                    .antMatchers("/", "/swagger-ui/**", "/v3/api-docs").permitAll() // 允许访问的接口
                    .antMatchers("/common/**").permitAll() // 允许访问的接口
                    .antMatchers("/admin/**").hasAnyRole("ADMIN") //admin开头的请求，需要admin权限
                    .antMatchers("/api-admin/**").hasAnyRole("ADMIN") // admin开头的请求，需要admin权限
                    .antMatchers("/api-index/**").permitAll() // 无权限
                    .antMatchers("/api-user/**").hasAnyRole("USER") // 用户权限
                    .antMatchers("/user/**").hasRole("USER") //需登陆才能访问的url
                    .anyRequest().authenticated()  //默认其它的请求都需要认证，这里一定要添加
                 .and()
                    .cors()  //支持跨域
                .and()   //添加header设置，支持跨域和ajax请求
                    .headers()
                        .addHeaderWriter(
                            new StaticHeadersWriter(Arrays.asList(
                                    new Header("Access-control-Allow-Origin","*"),
                                    new Header("Access-Control-Expose-Headers","Authorization"))))
                //.and()
                    //指定登录界面，并且设置为所有人都能访问；
                    //.formLogin()
//                    .loginPage("/login")
//                    .usernameParameter("username")
//                    .passwordParameter("password")
                    //.loginProcessingUrl("/auth/login")
                    //.permitAll()
//                    .successHandler(myAuthenticationSuccessHandler)
//                    .failureHandler(myAuthenticationFailureHandler)
                .and()
                    //使用默认的logoutFilter
                    .logout()
                    .logoutUrl("/auth/logout")   //默认就是"/logout"
                    .addLogoutHandler(myLogoutHandler)  //logout时清除token
                    .logoutSuccessHandler(myLogoutSuccessHandler) //logout成功后返回200
                .and()
                    // 处理异常情况：认证失败和权限不足
                    .exceptionHandling()
                        .authenticationEntryPoint(entryPointUnauthorizedHandler)
                        .accessDeniedHandler(restAccessDeniedHandler)
                .and()
                    .csrf()
                        .disable()
                    .sessionManagement()
                        .disable()
        ;


    }

    @Autowired
    public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                // 设置UserDetailsService
                .userDetailsService(this.userDetailsService)
                // 使用加密
                .passwordEncoder(passwordEncoder);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JsonUsernamePasswordAuthenticationFilter jsonUsernamePasswordAuthenticationFilter() throws Exception {
        JsonUsernamePasswordAuthenticationFilter filter = new JsonUsernamePasswordAuthenticationFilter();
        filter.setAuthenticationManager(authenticationManagerBean());
        filter.setAuthenticationSuccessHandler(myAuthenticationSuccessHandler);
        filter.setAuthenticationFailureHandler(myAuthenticationFailureHandler);
        filter.setUsernameParameter("username");
        filter.setPasswordParameter("password");
        filter.setFilterProcessesUrl("/auth/login");
        return filter;
    }
}
