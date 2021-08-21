package com.retail.ecom.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.retail.ecom.model.Activity;

@Repository
public interface ActivityRepository extends CrudRepository<Activity, Integer> {
	
}
