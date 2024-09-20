package com.yc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan(basePackages = "com.yc.mappers")
@EnableCaching  //开启spring cache缓存
@EnableAsync  //开启spring async异步
@EnableScheduling // 开启spring scheduling定时任务
public class MybatisplusBaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisplusBaseApplication.class, args);
    }

}
