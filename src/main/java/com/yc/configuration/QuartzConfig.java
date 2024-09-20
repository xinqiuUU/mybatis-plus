package com.yc.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Configuration
@Slf4j
public class QuartzConfig {

    // 缓存管理器
    @Autowired
    private CacheManager cacheManager;

    // 定时任务
    @Scheduled(cron = "0 */1 * * * ?")  // cron表达式 每隔1分钟执行一次
    public void test() {
        System.out.println("定时任务开始执行");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(new Date()));
        System.out.println("定时任务执行结束");
    }

    // 定时任务   清理缓存
    // @Scheduled(fixedRate = 10000) // 10秒执行一次
    @Scheduled(cron = "0 */1 * * * ?")  // cron表达式 每隔1分钟执行一次
    public void clearCache() {
        log.info("清除缓存");
//        cacheManager.getCache("account").clear();
        cacheManager.getCacheNames().forEach(cacheName -> {
            cacheManager.getCache(cacheName).clear();
            log.info("清除缓存: " + cacheName);
        });
    }
}
