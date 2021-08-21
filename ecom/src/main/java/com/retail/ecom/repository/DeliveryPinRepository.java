package com.retail.ecom.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.retail.ecom.model.DeliveryPin;


@Repository("deliveryPinRepository")
public interface DeliveryPinRepository extends CrudRepository<DeliveryPin, Integer> {
	
	@Transactional
	@Modifying
	@Query("delete from DeliveryPin d where d.deliveryAgentId=?1 ")
	void deleteByAgentId(Integer id);
	
	

}
