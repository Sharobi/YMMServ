package com.retail.ecom.service;

import java.util.List;

import com.retail.ecom.model.Feature;
import com.retail.ecom.utils.ResponseDetails;

public interface FeatureService {
	public Feature findById(int id);

	public List<Feature> findAll();
	
	public List<Feature> findAllActive();

	ResponseDetails addFeature(Feature feature);

	public ResponseDetails deactivateFeature(int fid);

	public ResponseDetails activateFeature(int fid);

	public ResponseDetails updateFeature(Feature f);

	public ResponseDetails deleteFeature(int fid);
}
