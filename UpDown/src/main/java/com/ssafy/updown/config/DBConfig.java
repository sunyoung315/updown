package com.ssafy.updown.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "com.ssafy.updown.model.dao")
public class DBConfig {

}
