package com.retail.ecom.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.retail.ecom.model.Address;
import com.retail.ecom.model.AddressShipping;
import com.retail.ecom.model.City;
import com.retail.ecom.model.Country;
import com.retail.ecom.model.State;
import com.retail.ecom.repository.AddressRepository;
import com.retail.ecom.repository.CityRepository;
import com.retail.ecom.repository.CountryRepository;
import com.retail.ecom.repository.StateRepository;
import com.retail.ecom.service.AddressService;
import com.retail.ecom.utils.ResponseDetails;

@Service
public class AddressServiceImpl implements AddressService {
	
	@Autowired
	AddressRepository addressRepository;
	
	@Autowired
	CountryRepository cr; 
	
	@Autowired
	CityRepository cir;
	
	@Autowired
	StateRepository sr; 
	
	@Value(value = "${store.location.radius}")
	Double radius;
	
	public List<Address> getAllAddresses() {
		return addressRepository.findAll();
	}
	
	@Override
	public List<Country> getAllCountries() {
		return cr.getAllCountries();
	}
	
	@Override
	public List<State> getStatesByCountry(Integer cid) {
		return sr.getStatesByCountry(cid);
	}
	
	@Override
	public List<City> getCitiesByState(Integer sid) {
		return cir.getCitiesByState(sid);
	}
	
	public Address saveAddress(Address address) {
		return addressRepository.saveAddress(address);
//		return addressRepository.findAddressById(address.getId());
//		return addressRepository.findAddressById(address.getId());
	}
	public AddressShipping saveShippingAddress(AddressShipping addressShipping) {
		return addressRepository.saveShippingAddress(addressShipping);
	}
	
	@Override
	public Address findAddressById(int id) {
		return addressRepository.findAddressById(id);
	}
	
	//Will be moved to store service
	public List<Address> getAddressInRadius(Double lat,Double lng) {
		List<Address> allAddresses = addressRepository.findAll();
		List<Address> filteredAddresses = new ArrayList<>();
		double lat1 = lat;
		double lon1 = lng;
		
		for (Iterator iterator = allAddresses.iterator(); iterator.hasNext();) {
			Address address = (Address) iterator.next();
			if(inRadius(lat1, lon1, address.getLatitude(), address.getLongitude())) {
				filteredAddresses.add(address);
			}
		}
		
		return filteredAddresses;
	}
	public boolean inRadius(double lat1,double lon1,double lat2,double lon2) {
		
		boolean test = false;
		
		/*double lat1=22.5713942800;
		double lat2=22.5720184200;
		double lon1=88.3505618700;
		double lon2=88.3489847300;*/

		double R = 6371; // radius of earth in KM
		double f1 = lat1*Math.PI/180;//lat1.toRadians(); //to radian = 1deg*Math.PI/180
		double f2 = lat2*Math.PI/180;
		double dlatr = (lat2-lat1)*Math.PI/180;
		double dlonr = (lon2-lon1)*Math.PI/180;

		double a = Math.sin(dlatr /2) * Math.sin(dlatr /2) +
		        Math.cos(f1) * Math.cos(f2) *
		        Math.sin(dlonr/2) * Math.sin(dlonr/2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

		double d = R * c;
		
		//System.out.println("Distance = "+d);
		
		if(d<=radius) {
			test = true;
		}
		
		return test;
	}

	@Override
	public ResponseDetails saveOrUpdateAll(Set<Address> addresses) {
		addressRepository.saveAll(addresses);
		return new ResponseDetails(new Date(), "Addresses updated successfully", null, 1);
	}

	@Override
	public ResponseDetails deleteAddressById(int aid) {
		addressRepository.deleteById(aid);
		return new ResponseDetails(new Date(), "Address deleted successfully", null, 1);
	}
}
