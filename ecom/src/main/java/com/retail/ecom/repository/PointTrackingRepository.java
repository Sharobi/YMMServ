package com.retail.ecom.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.retail.ecom.model.PointTracking;

@Repository
public interface PointTrackingRepository extends CrudRepository<PointTracking, Integer>{

	@Query("select sum(pt.pointAmount) from  PointTracking pt where pt.uid=?1")
	Double getPointsByUser(int uid);

	@Transactional
	@Modifying
	@Query("delete from PointTracking pt where pt.orderId=?1")
	void deleteByOrder(int oid);
	
}
