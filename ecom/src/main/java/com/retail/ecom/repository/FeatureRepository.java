package com.retail.ecom.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.retail.ecom.model.Feature;

@Repository
public interface FeatureRepository extends CrudRepository<Feature, Integer> {
	
	@Query("select f from Feature f where f.isActive=1")
	public List<Feature> findAllFeatures();

	@Transactional
	@Modifying
	@Query("update Feature f set f.isActive=0 where f.id=?1")
	public void deactivateFeature(int fid);

	@Transactional
	@Modifying
	@Query("update Feature f set f.isActive=1 where f.id=?1")
	public void activateFeature(int fid);

	@Transactional
	@Modifying
	@Query("delete from Feature f where f.id=?1")
	public void deleteFeature(int fid);
}
