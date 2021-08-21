package com.retail.ecom.service;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.retail.ecom.model.Address;
import com.retail.ecom.model.AddressShipping;
import com.retail.ecom.model.City;
import com.retail.ecom.model.Country;
import com.retail.ecom.model.State;
import com.retail.ecom.utils.ResponseDetails;

public interface AddressService {
	public List<Address> getAllAddresses();
	public Address saveAddress(Address address);
	public AddressShipping saveShippingAddress(AddressShipping addressShipping);
	List<Country> getAllCountries();
	List<State> getStatesByCountry(Integer cid);
	List<City> getCitiesByState(Integer sid);
	public ResponseDetails saveOrUpdateAll(Set<Address> addresses);
	Address findAddressById(int id);
	public ResponseDetails deleteAddressById(int aid);
}
