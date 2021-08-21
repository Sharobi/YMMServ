package com.retail.ecom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.retail.ecom.model.City;
import com.retail.ecom.model.State;

@Repository
public interface CityRepository extends CrudRepository<City, Integer> {
	
	@Query("select c from City c where c.stateId=?1")
	List<City> getCitiesByState(Integer sid);
	
	@Query("select c from City c where c.id=?1")
	public City getCityById(int id);
}
