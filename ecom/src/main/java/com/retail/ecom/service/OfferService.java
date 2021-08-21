package com.retail.ecom.service;

import java.util.List;

import com.retail.ecom.model.Offer;

public interface OfferService {

	List<Offer> getAll();
	
	Offer getById(int oid);

	List<Offer> getOffersByActivity(int aid);

}
