package com.retail.ecom.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.retail.ecom.model.Membership;

public interface MembershipRepository extends CrudRepository<Membership, Integer>,MembershipRepositoryCustom {
	
	@Query("select m from Membership m where m.isActive=1 order by m.pointMin")
	List<Membership> getAllActive();

	@Transactional
	@Modifying
	@Query("update Membership m set m.isActive=0 where m.id=?1")
	void deactivateMembership(int mid);

	@Transactional
	@Modifying
	@Query("update Membership m set m.isActive=1 where m.id=?1")
	void activateMembership(int mid);

	@Transactional
	@Modifying
	@Query("delete from Membership m where m.id=?1")
	void deleteMembership(int mid);

	@Query("select m from Membership m where m.isActive=1 and ?1>=m.pointMin order by m.pointMin desc")
	List<Membership> getMembershipByUid(Double points);

}
