package com.yc.aspects;


import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Aspect // 切面
@Component
@Order(9)  // 优先级
public class QuartzAspect {

    private Map<Integer, Integer> queryCountMap = new HashMap<>();

    // 冷热数据 对id操作次数进行统计
    // 增强类型:后置( after afterReturning , afterThrowing, finally)
    // 定义切入点，拦截 findAccount 方法
    @Pointcut("execution(* com.yc.service.AccountService.findAccount(int)) && args(accountid)")
    public void findAccountPointcut(int accountid) {}

    // 在方法执行后执行
    @AfterReturning("findAccountPointcut(accountid)")
    public void afterFindAccount(int accountid) {
        queryCountMap.put(accountid, queryCountMap.getOrDefault(accountid, 0) + 1);
        System.out.println("Account ID: " + accountid + " 查询次数: " + queryCountMap.get(accountid));
    }


}
