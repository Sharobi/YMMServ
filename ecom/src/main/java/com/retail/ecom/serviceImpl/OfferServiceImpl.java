package com.retail.ecom.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.retail.ecom.model.Offer;
import com.retail.ecom.repository.OfferRepository;
import com.retail.ecom.service.OfferService;

@Service
public class OfferServiceImpl implements OfferService {

	@Autowired
	private OfferRepository or;
	
	@Override
	public List<Offer> getAll() {
		return (List<Offer>) or.getAll();
	}

	@Override
	public List<Offer> getOffersByActivity(int aid) {
		return or.getOffersByActivity(aid);
	}

	@Override
	public Offer getById(int oid) {
		return or.getById(oid) ;
	}

}
