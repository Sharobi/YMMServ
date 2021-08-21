package com.retail.ecom.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.retail.ecom.model.MembershipFeature;

@Repository
public interface MembershipFeatureRepository extends CrudRepository<MembershipFeature, Integer>,MembershipFeatureRepositoryCustom {

	@Transactional
	@Modifying
	@Query("delete from MembershipFeature m where m.membershipId = ?1")
	void deleteByMid(Integer mid);

	@Transactional
	@Modifying
	@Query("delete from MembershipFeature mf where mf.id = ?1")
	void removeMF(Integer id);

}
