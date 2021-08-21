package com.retail.ecom.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.retail.ecom.model.Prescription;
import com.retail.ecom.repository.PrescriptionRepository;
import com.retail.ecom.service.PrescriptionService;

@Service
public class PrescriptionServiceImpl implements PrescriptionService {
	
	@Autowired
	private PrescriptionRepository pr;
	
	@Override
	public int savePrescription(Prescription p) {
		pr.save(p);
		return p.getId();
	}
	
	@Override
	public Prescription findPrescriptionById(int id) {
		return pr.findPrescriptionById(id);
	}
	
	@Override
	public Prescription findPrescriptionByUId(int pid, Integer id) {
		return pr.findPrescriptionByUId(pid,id);
	}
}
