package org.spring.springboot.dao;

import org.apache.ibatis.annotations.Param;
import org.aspectj.lang.annotation.Pointcut;
import org.spring.springboot.model.City;
import org.spring.springboot.model.CityExample;

import java.util.List;

public interface CityMapper {
    long countByExample(CityExample example);

    int deleteByExample(CityExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(City record);

    int insertSelective(City record);

    List<City> selectByExample(CityExample example);

    City selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") City record, @Param("example") CityExample example);

    int updateByExample(@Param("record") City record, @Param("example") CityExample example);

    int updateByPrimaryKeySelective(City record);

    int updateByPrimaryKey(City record);

    List<City> getCiyByIds( List<String> ids);

}