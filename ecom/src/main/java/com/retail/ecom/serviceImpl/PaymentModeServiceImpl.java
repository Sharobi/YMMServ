package com.retail.ecom.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.retail.ecom.model.PaymentMode;
import com.retail.ecom.repository.PaymentModeRepository;
import com.retail.ecom.service.PaymentModeService;

@Service
public class PaymentModeServiceImpl implements PaymentModeService {
	
	@Autowired
	PaymentModeRepository pmr;
	
	@Override
	public List<PaymentMode> getAll() {
		return (List)pmr.findAll();
	}

}
