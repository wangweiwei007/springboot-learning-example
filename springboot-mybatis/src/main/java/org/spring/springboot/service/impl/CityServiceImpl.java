package org.spring.springboot.service.impl;

import org.spring.springboot.dao.CityMapper;
import org.spring.springboot.model.City;
import org.spring.springboot.model.CityExample;
import org.spring.springboot.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 城市业务逻辑实现类
 * <p>
 * Created by bysocket on 07/02/2017.
 */
@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityMapper cityMapper;

    @Override
    public City findCityByName(String cityName) {
        CityExample cityExample = new CityExample();
        cityExample.createCriteria().andCityNameEqualTo(cityName);
        List<City> cityList = cityMapper.selectByExample(cityExample);
        return cityList.size() > 0 ? cityList.get(0) : null;
    }

    @Override
    public boolean updateCity(City city) {
        int n = cityMapper.updateByPrimaryKeySelective(city);
        return n > 0 ? true : false;
    }

}
