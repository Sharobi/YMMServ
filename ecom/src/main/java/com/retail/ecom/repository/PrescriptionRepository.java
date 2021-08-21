package com.retail.ecom.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.retail.ecom.model.Prescription;

@Repository
public interface PrescriptionRepository extends CrudRepository<Prescription, Integer> {
	
	@Query("select p from Prescription p where p.id = ?1")
	public Prescription findPrescriptionById(int id);

	@Query("select p from Prescription p where p.id = ?1 and p.userId = ?2")
	public Prescription findPrescriptionByUId(int pid, Integer id);
}
