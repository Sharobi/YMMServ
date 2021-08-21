package com.retail.ecom.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.retail.ecom.model.MembershipFeature;
import com.retail.ecom.repository.MembershipFeatureRepository;
import com.retail.ecom.service.MembershipFeatureService;

@Service
public class MembershipFeatureServiceImpl implements MembershipFeatureService {

	@Autowired
	MembershipFeatureRepository mfr;
	
	@Override
	public List<MembershipFeature> getAll() {
		return (List<MembershipFeature>) mfr.findAll();
	}

	@Override
	public boolean checkValidFeatures(Integer id, Integer[] membershipFeatureIds) {
		return mfr.checkValidFeatures(id,membershipFeatureIds);
	}

}
