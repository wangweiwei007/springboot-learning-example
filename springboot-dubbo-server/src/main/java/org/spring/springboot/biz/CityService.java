package org.spring.springboot.biz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.springboot.domain.City;
import org.spring.springboot.dubbo.impl.CityDubboServiceImpl;
import org.spring.springboot.exception.RetryException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class CityService {

    private Logger log = LoggerFactory.getLogger(CityDubboServiceImpl.class);

    private static int count=1;

    @Retryable(value = {RuntimeException.class}, maxAttempts = 3, backoff = @Backoff(delay = 1000, multiplier = 1 ))
    public City findCityByName(String cityName) {
        log.info("重试测试...");
        count++;
        log.info("count========={}",count);
        if(StringUtils.isEmpty(cityName)){
            try {
                throw new RetryException("9000", "连接超时");
            }catch (Exception e){
                //throw new RetryException("9999", "系統异常");
                log.info("系统异常");
            }
        }
        return new City(1L,2L,"温岭","是我的故乡");
    }

    @Recover
    public City recover(RetryException e){
        log.info("recover:....");
        log.info("message:...."+e.getMsg());
        return new City(1L,1L,"河南","是我的故乡");
    }
}
