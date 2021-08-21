package com.retail.ecom.repositoryImpl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import com.retail.ecom.model.Address;
import com.retail.ecom.model.AddressShipping;
import com.retail.ecom.repository.AddressRepositoryCustom;
import com.retail.ecom.repository.CityRepository;
import com.retail.ecom.repository.CountryRepository;
import com.retail.ecom.repository.StateRepository;

public class AddressRepositoryImpl implements AddressRepositoryCustom {
	
	@PersistenceContext
	EntityManager em;
	
	@Autowired
	CountryRepository cr;
	
	@Autowired
	StateRepository sr;
	
	@Autowired
	CityRepository cir;
	
	@Transactional
	@Override
	public Address saveAddress(Address address) {
		/*Address a = em.find(Address.class, address.getId());*/
		if(address.getCountryId()!=null)
			address.setCountry(cr.getCountryById(address.getCountryId()));
		if(address.getStateId()!=null)
			address.setState(sr.getStateById(address.getStateId()));
		if(address.getCityId()!=null)
			address.setCity(cir.getCityById(address.getCityId()));
		//em.merge(address);
		return em.merge(address);
		//em.clear();
	}
	@Transactional
	@Override
	public AddressShipping saveShippingAddress(AddressShipping addressShipping) {
		if(addressShipping.getCountryId()!=null)
			addressShipping.setCountry(cr.getCountryById(addressShipping.getCountryId()));
		if(addressShipping.getStateId()!=null)
			addressShipping.setState(sr.getStateById(addressShipping.getStateId()));
		if(addressShipping.getCityId()!=null)
			addressShipping.setCity(cir.getCityById(addressShipping.getCityId()));
		//em.merge(address);
		return em.merge(addressShipping);
		//em.clear();
	}
}
