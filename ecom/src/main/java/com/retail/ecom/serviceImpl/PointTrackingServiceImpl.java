package com.retail.ecom.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.retail.ecom.model.PointTracking;
import com.retail.ecom.repository.PointTrackingRepository;
import com.retail.ecom.service.PointTrackingService;

@Service
public class PointTrackingServiceImpl implements  PointTrackingService {
	
	@Autowired
	PointTrackingRepository ptr;
	
	@Override
	public PointTracking save(PointTracking pt) {
		return ptr.save(pt);
	}

	@Override
	public Double getPointsByUser(int uid) {
		if (ptr.getPointsByUser(uid) == null) {
			return 0d;
		} else {
			return ptr.getPointsByUser(uid);
		}
	}

	@Override
	public Integer delete(int oid) {
		ptr.deleteByOrder(oid);
		return 1;
	}
}
