package org.spring.springboot.controller;


import cn.com.yitong.inte.resource.service.AccountService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.spring.springboot.dubbo.CityDubboConsumerService;
import org.spring.springboot.model.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/city")
public class CityController {

   @Autowired
    private CityDubboConsumerService cityDubboConsumerService;

    @RequestMapping("")
    public City findCityByName(String name){
       City city = cityDubboConsumerService.printCity(name);
       return city;
    }

}
