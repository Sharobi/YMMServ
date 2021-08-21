package com.retail.ecom.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.retail.ecom.repository.ActivityTrackingRepository;
import com.retail.ecom.service.ActivityTrackingService;
@Service
public class ActivityTrackingServiceImpl implements ActivityTrackingService {

	@Autowired
	private ActivityTrackingRepository act;
	@Override
	public void getData() {
		// TODO Auto-generated method stub
		
	}

}
