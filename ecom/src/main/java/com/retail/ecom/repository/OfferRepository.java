package com.retail.ecom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.retail.ecom.model.Offer;

@Repository
public interface OfferRepository extends CrudRepository<Offer, Integer> {
	@Query("select o from Offer o where o.isDeleted=0")
	List<Offer> getAll();
	
	@Query("select o from Offer o where o.isDeleted=0 and o.activityId=?1")
	List<Offer> getOffersByActivity(int aid);

	@Query("select o from Offer o where o.isDeleted=0 and o.id=?1")
	Offer getById(int oid);

}
