package com.retail.ecom.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.retail.ecom.model.OrderBenefit;

@Repository
public interface OrderBenefitRepository extends CrudRepository<OrderBenefit, Integer> {
	
}
