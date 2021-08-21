package com.retail.ecom.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.retail.ecom.model.Address;
import com.retail.ecom.model.AddressShipping;
import com.retail.ecom.model.City;
import com.retail.ecom.model.Country;
import com.retail.ecom.model.State;
import com.retail.ecom.model.User;
import com.retail.ecom.service.AddressService;
import com.retail.ecom.service.UserService;
import com.retail.ecom.utils.ResponseDetails;

@Controller
@RequestMapping("/addresses")
public class AddressController {
	
	@Autowired
	AddressService as;
	
	@Autowired
	UserService us;
	
	/*@GetMapping
	public @ResponseBody List<Address> getAll() {
		return as.getAllAddresses();
	}
	*/
	/*@GetMapping
	public @ResponseBody Address getAddressById(@RequestParam("aid") int aid) {
		return as.findAddressById(aid);
	}*/
	
	@GetMapping("/{aid}")
	public @ResponseBody Address getAddressById(@PathVariable("aid") int aid) {
		//System.out.println("aid: "+aid);
		return as.findAddressById(aid);
	}
	
	@DeleteMapping("/{aid}")
	public @ResponseBody ResponseDetails deleteAddressById(@PathVariable("aid") int aid) {
		//System.out.println("aid: "+aid);
		return as.deleteAddressById(aid);
	}
	
	/*@GetMapping("/byuser")
	public @ResponseBody List<Address> getAllUserAddress(Authentication auth) {
		return as.getAllAddresses();
	}*/
	
	@PostMapping//("/{addressShipping}")
	public @ResponseBody Address saveAddress(@RequestBody Address address, Authentication auth) {
		User user = us.findUserByUserName(auth.getName());
		address.setUserId(user.getId());
		//System.out.println("Address = "+address);
		Address a = as.saveAddress(address);
		a.setUserId(0);
		//System.out.println("Updated Address: "+ a);
		//return as.findAddressById(a.getId());
		return a;
	}
	@PostMapping("/{addressShipping}")
	public @ResponseBody AddressShipping saveShippingAddress(@RequestBody AddressShipping address, Authentication auth) {
		User user = us.findUserByUserName(auth.getName());
		address.setUserId(user.getId());
		//System.out.println("Address = "+address.toString());
		AddressShipping a = as.saveShippingAddress(address);
		a.setUserId(0);
		//System.out.println("Updated Address: "+ a);
		//return as.findAddressById(a.getId());
		return a;
	}
	
	@PostMapping("/makedefault")
	public @ResponseBody ResponseDetails makeDefault(Authentication auth,@RequestBody Address add) {
		User user = us.findUserByUserName(auth.getName());
		Set<Address> addresses = user.getAddresses();
		for (Iterator iterator = addresses.iterator(); iterator.hasNext();) {
			Address address = (Address) iterator.next();
			if(address.getId().equals(add.getId())) {
				address.setIsDefault(1);
			} else {
				address.setIsDefault(0);
			}
		}
		return as.saveOrUpdateAll(addresses);
	}
	
	@GetMapping("/countries")
	public @ResponseBody List<Country> getAllCountry() {
		return as.getAllCountries();
	}
	
	@GetMapping("/countries/{cid}/states")
	public @ResponseBody List<State> getStatesByCountry(@PathVariable("cid") Integer cid) {
		return as.getStatesByCountry(cid);
	}
	
	@GetMapping("/states/{sid}/cities")
	public @ResponseBody List<City> getCitiesByState(@PathVariable("sid") Integer sid) {
		return as.getCitiesByState(sid);
	}
}
