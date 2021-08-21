package com.retail.ecom.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.retail.ecom.model.Feature;
import com.retail.ecom.repository.FeatureRepository;
import com.retail.ecom.service.FeatureService;
import com.retail.ecom.utils.ResponseDetails;

@Service
public class FeatureServiceImpl implements FeatureService {

	@Autowired
	FeatureRepository fr;
	
	@Override
	public Feature findById(int id) {
		Optional<Feature> f = fr.findById(id);
		if(f.isPresent()) {
			return f.get();
		} else {
			return null;
		}
	}

	@Override
	public List<Feature> findAll() {
		return (List<Feature>) fr.findAllFeatures();
	}

	@Override
	public ResponseDetails addFeature(Feature feature) {
		fr.save(feature);
		return new ResponseDetails(new Date(), "Feature added successfully", null, 1);
	}

	@Override
	public ResponseDetails deactivateFeature(int fid) {
		fr.deactivateFeature(fid);
		return new ResponseDetails(new Date(), "Feature deactivated successfully", null, 1);
	}

	@Override
	public List<Feature> findAllActive() {
		return fr.findAllFeatures();
	}

	@Override
	public ResponseDetails activateFeature(int fid) {
		fr.activateFeature(fid);
		return new ResponseDetails(new Date(), "Feature activated successfully", null, 1);
	}

	@Override
	public ResponseDetails updateFeature(Feature f) {
		Optional<Feature> uf = fr.findById(f.getId());
		if(uf.isPresent()) {
			uf.get().setName(f.getName());
			uf.get().setIsActive(f.getIsActive());
			fr.save(uf.get());
			return new ResponseDetails(new Date(), "Feature updated successfully", null, 1);
		} else {
			return new ResponseDetails(new Date(), "Feature not found", null, 0);
		}
	}

	@Override
	public ResponseDetails deleteFeature(int fid) {
		fr.deleteFeature(fid);
		return new ResponseDetails(new Date(), "Feature deleted successfully", null, 1);
	}
}
