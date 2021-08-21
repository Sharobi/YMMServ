package com.retail.ecom.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.retail.ecom.repository.DeliveryPinRepository;
import com.retail.ecom.service.DeliveryPinService;
@Service
public class DeliveryPinServiceImpl implements DeliveryPinService {
	
	@Autowired
	private DeliveryPinRepository deliveryPinRepository;

	@Override
	public void deletePinByDeleveryAgentId(Integer id) {		
		deliveryPinRepository.deleteByAgentId(id);
	}

}
