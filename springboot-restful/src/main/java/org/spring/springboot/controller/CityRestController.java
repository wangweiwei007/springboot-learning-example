package org.spring.springboot.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.spring.springboot.model.City;
import org.spring.springboot.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 城市 Controller 实现 Restful HTTP 服务
 *
 * Created by bysocket on 07/02/2017.
 */
@RestController
@Api(tags = "城市信息管理相关接口")
public class CityRestController {

    @Autowired
    private CityService cityService;

    @RequestMapping(value = "/api/city/{id}", method = RequestMethod.GET)
    @ApiOperation("根据id查询城市信息接口")
    @ApiImplicitParam(name = "id", value = "城市id", defaultValue = "1", required = true)
    public City findOneCity(@PathVariable("id") Long id) {
        return cityService.findCityById(id);
    }

    @RequestMapping(value = "/api/city", method = RequestMethod.GET)
    @ApiOperation("获取城市信息列表接口")
    public List<City> findAllCity() {
        return cityService.findAllCity();
    }

    @RequestMapping(value = "/api/city", method = RequestMethod.POST)
    @ApiOperation("新增城市信息接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "城市编号", defaultValue = "0001", required = true),
            @ApiImplicitParam(name = "provinceId", value = "provinceId", defaultValue = "01", required = true),
            @ApiImplicitParam(name = "cityName", value = "城市名称", defaultValue = "郑州", required = true),
            @ApiImplicitParam(name = "description", value = "描述", defaultValue = "河南省会")
    })
    public void createCity(@RequestBody City city) {
        cityService.saveCity(city);
    }

    @RequestMapping(value = "/api/city", method = RequestMethod.PUT)
    @ApiOperation("修改城市信息接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "城市编号", defaultValue = "0001"),
            @ApiImplicitParam(name = "provinceId", value = "provinceId", defaultValue = "01"),
            @ApiImplicitParam(name = "cityName", value = "城市名称", defaultValue = "郑州"),
            @ApiImplicitParam(name = "description", value = "描述", defaultValue = "河南省会")
    })
    public void modifyCity(@RequestBody City city) {
        cityService.updateCity(city);
    }

    @RequestMapping(value = "/api/city/{id}", method = RequestMethod.DELETE)
    @ApiOperation("根据id删除城市信息接口")
    @ApiImplicitParam(name = "id", value = "城市id", defaultValue = "1", required = true)
    public void deleteCity(@PathVariable("id") Long id) {
        cityService.deleteCity(id);
    }
}
