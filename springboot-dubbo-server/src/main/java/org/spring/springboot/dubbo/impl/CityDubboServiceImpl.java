package org.spring.springboot.dubbo.impl;

import com.alibaba.dubbo.config.annotation.Service;
<<<<<<< HEAD
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.springboot.biz.CityService;
import org.spring.springboot.domain.City;
=======
import org.spring.springboot.model.City;
>>>>>>> fe746bbf7c6a355623a048f52a6ebe1ae12b4ddc
import org.spring.springboot.dubbo.CityDubboService;
import org.spring.springboot.exception.RetryException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * 城市业务 Dubbo 服务层实现层
 *
 * Created by bysocket on 28/02/2017.
 */
// 注册为 Dubbo 服务
@Service(version = "1.0.0",timeout = 50000)
public class CityDubboServiceImpl implements CityDubboService {

    @Resource
    private CityService cityService;

    @Override
    public City findCityByName(String cityName) {
      return   cityService.findCityByName(cityName);
    }


}
