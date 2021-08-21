package com.retail.ecom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.retail.ecom.model.Country;

@Repository
public interface CountryRepository extends CrudRepository<Country, Integer> {
	@Query("select c from Country c")
	public List<Country> getAllCountries();
	
	@Query("select c from Country c where id=?1")
	public Country getCountryById(int id);
}
