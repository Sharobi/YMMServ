package com.retail.ecom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.retail.ecom.model.Country;
import com.retail.ecom.model.State;

@Repository
public interface StateRepository extends CrudRepository<State, Integer> {
	@Query("select s from State s where s.countryId=?1")
	List<State> getStatesByCountry(Integer cid);
	
	@Query("select s from State s where s.id=?1")
	public State getStateById(int id);
}
