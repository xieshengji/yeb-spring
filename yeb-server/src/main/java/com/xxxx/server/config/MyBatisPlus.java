package com.xxxx.server.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//用于MyBatis分页配置
@Configuration
public class MyBatisPlus {
  @Bean
  public PaginationInterceptor paginationInterceptor(){
    return new PaginationInterceptor();
  }
}
