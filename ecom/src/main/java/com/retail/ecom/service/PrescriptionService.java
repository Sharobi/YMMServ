package com.retail.ecom.service;

import org.springframework.stereotype.Service;

import com.retail.ecom.model.Prescription;
import com.retail.ecom.utils.ResponseDetails;

public interface PrescriptionService {

	int savePrescription(Prescription p);

	Prescription findPrescriptionById(int id);

	Prescription findPrescriptionByUId(int pid, Integer id);
	
}
