package com.retail.ecom.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.retail.ecom.model.Activity;

@Service
public interface ActivityService {
	public List<Activity> getAll();
}
