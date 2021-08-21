package com.retail.ecom.service;

import com.retail.ecom.model.PointTracking;

public interface PointTrackingService {

	PointTracking save(PointTracking pt);
	
	Double getPointsByUser(int uid);
	
	Integer delete(int oid);

}
