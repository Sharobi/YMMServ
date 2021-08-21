package com.retail.ecom.serviceImpl;

import java.util.List;

import com.retail.ecom.model.Activity;
import com.retail.ecom.repository.ActivityRepository;
import com.retail.ecom.service.ActivityService;

public class ActivityServiceImpl implements ActivityService {

	private ActivityRepository ar;
	
	@Override
	public List<Activity> getAll() {
		return (List<Activity>) ar.findAll();
	}

}
