package com.retail.ecom.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.retail.ecom.model.ActivityTracking;

@Repository
public interface ActivityTrackingRepository extends CrudRepository<ActivityTracking, Integer> {

}
