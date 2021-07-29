package com.fengwenyi.erwinmessage.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author <a href="https://www.fengwenyi.com">Erwin Feng</a>
 * @since 2021-07-30
 */
@Configuration
@MapperScan("com.fengwenyi.erwinmessage.dao")
public class MyBatisPlusConfiguration {
}
