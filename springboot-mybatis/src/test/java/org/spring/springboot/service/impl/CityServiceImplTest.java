package test.org.spring.springboot.service.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.spring.springboot.BaseTest;
import org.spring.springboot.dao.CityMapper;
import org.spring.springboot.model.City;
import org.spring.springboot.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * CityServiceImpl Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>���� 16, 2019</pre>
 */
public class CityServiceImplTest extends BaseTest {

    @Autowired
    private CityService service;
    @Autowired
    private CityMapper cityMapper;

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: findCityByName(String cityName)
     */
    @Test
    public void testFindCityByName() throws Exception {
        City city = service.findCityByName("北京");
        System.out.println(city);
    }

    /**
     * Method: updateCity(City city)
     */
    @Test
    public void testUpdateCity() throws Exception {
        City city = new City();
        city.setId(1);
        city.setProvinceId("001");
        boolean b = service.updateCity(city);
        System.out.println(b);
    }

    @Test
    public void testGetCityByIds() throws Exception {
        List<String> list=new ArrayList<String>();
        list.add("001");
        list.add("002");
       List<City> cityList = cityMapper.getCiyByIds(list);
        System.out.println(cityList);
    }


} 
