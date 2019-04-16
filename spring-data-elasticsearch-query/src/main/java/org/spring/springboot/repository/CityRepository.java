package org.spring.springboot.repository;

import org.spring.springboot.model.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * ES 操作类
 * <p>
 * Created by bysocket on 20/06/2017.
 */
public interface CityRepository extends ElasticsearchRepository<City, Long> {

}
