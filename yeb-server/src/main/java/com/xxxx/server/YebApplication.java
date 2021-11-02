package com.xxxx.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类
 * @Author: xsj
 * @Date: 2021/8/7 1:35
 */
@SpringBootApplication
@MapperScan("com.xxxx.server.mapper")
public class YebApplication {
  public static void main(String[] args) {
    SpringApplication.run(YebApplication.class,args);
  }
}
