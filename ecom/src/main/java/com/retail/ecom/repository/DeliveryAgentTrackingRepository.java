package com.retail.ecom.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.retail.ecom.model.DeliveryAgentTracking;


@Repository("deliveryAgentTracking")
public interface DeliveryAgentTrackingRepository extends CrudRepository<DeliveryAgentTracking, Integer>{

	

}
