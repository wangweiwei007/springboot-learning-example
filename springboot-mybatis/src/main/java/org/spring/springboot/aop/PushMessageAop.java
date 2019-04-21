package org.spring.springboot.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * @author: weiwei wang
 * @date: 2019/4/16
 * @description:
 */

@Aspect
public class PushMessageAop {
    @Pointcut("execution(* org.spring.springboot.service.impl.CityServiceImpl.updateCity(..)) || execution(* org.spring.springboot.service.impl.CityServiceImpl.findCityByName(..))")
    public void pointCut() {
    }

    @Before("pointCut()")
    public void before() {
        System.out.println("before.........");
    }

    @After("pointCut()")
    public void after() {
        System.out.println("after.........");
    }

    @AfterReturning("pointCut()")
    public void afterReturning() {
        System.out.println("afterReturning.........");
    }

    @AfterThrowing("pointCut()")
    public void afterThrowing() {
        System.out.println("afterThrowing.........");
    }

    @Around("pointCut()")
    public Object doAroundService(ProceedingJoinPoint point) {
        System.out.println("环绕通知方法名：" + point.getSignature().getName());
        try {
            Object[] args = point.getArgs();
            for (Object arg : args) {
                System.out.println("参数="+arg+" ");
            }

            Object object = point.proceed();
            System.out.println("环绕通知对象："+object);
            return object;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }
}
