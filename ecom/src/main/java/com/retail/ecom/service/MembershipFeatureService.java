package com.retail.ecom.service;

import java.util.List;

import com.retail.ecom.model.MembershipFeature;

public interface MembershipFeatureService {

	public List<MembershipFeature> getAll();

	public boolean checkValidFeatures(Integer id, Integer[] membershipFeatureIds);
}
