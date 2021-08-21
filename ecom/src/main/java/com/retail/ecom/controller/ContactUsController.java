package com.retail.ecom.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.retail.ecom.config.TokenExtractor;
import com.retail.ecom.exception.UnAuthorizedRequest;
import com.retail.ecom.model.Contacts;
import com.retail.ecom.model.Feature;
import com.retail.ecom.model.Subscriber;
import com.retail.ecom.model.User;
import com.retail.ecom.service.ContactsService;
import com.retail.ecom.utils.Constants;
import com.retail.ecom.utils.ResponseDetails;

import io.jsonwebtoken.Claims;

@Controller
@RequestMapping("/contact")
public class ContactUsController {
	
	@Autowired
	ContactsService cs;

	@Autowired
	private TokenExtractor te;
	
	@PostMapping("/saveContacts")
	public @ResponseBody ResponseDetails saveContacts(@RequestBody Contacts contacts) {
		//System.out.println("Contacts = "+contacts);
		//us.saveContacts(contacts);
		return cs.saveContacts(contacts);
		//return new ResponseDetails(new Date(), "Contacts save successfully", null);
	}

	/*@GetMapping("/getAllContacts")
	public @ResponseBody List<Contacts> getAllContacts() {
		
		return cs.getAllContacts();
	}*/

	@GetMapping("/getAllContacts")
	public @ResponseBody List<Contacts> getAllContacts(@RequestHeader(value="Authorization") String auth) {
		Claims claims = te.extractInfo(auth);
		List<String> authorities = Arrays.asList(claims.get(Constants.AUTHORITIES_KEY).toString().split(","));
//		if(auth==null || !auth.getAuthorities().containsAll(AuthorityUtils.createAuthorityList(Constants.ROLE_ADMIN))) {
		if(auth==null || !authorities.containsAll(Arrays.asList(Constants.ROLE_ADMIN.split(",")))) {
			throw new UnAuthorizedRequest("You are not authorized to view Contacts");
		}
		return cs.getAllContacts();
	}
	@DeleteMapping("/deleteContact/{cid}")
	public @ResponseBody ResponseDetails deleteContacts(@PathVariable(name="cid") int cid,@RequestHeader(value="Authorization") String auth) {
		Claims claims = te.extractInfo(auth);
		List<String> authorities = Arrays.asList(claims.get(Constants.AUTHORITIES_KEY).toString().split(","));
//		if(auth==null || !auth.getAuthorities().containsAll(AuthorityUtils.createAuthorityList(Constants.ROLE_ADMIN))) {
		if(auth==null || !authorities.containsAll(Arrays.asList(Constants.ROLE_ADMIN.split(",")))) {
			throw new UnAuthorizedRequest("You are not authorized to delete Contacts");
			//return new ResponseDetails(new Date(), "You are not authorized to delete Contacts", null, 0);
		}
		return cs.deleteContacts(cid);
	}
	@PostMapping("/deleteAllContacts")
	public @ResponseBody ResponseDetails deleteAllContacts(@RequestBody List<Contacts> contacts,@RequestHeader(value="Authorization") String auth) {
		Claims claims = te.extractInfo(auth);
		List<String> authorities = Arrays.asList(claims.get(Constants.AUTHORITIES_KEY).toString().split(","));
//		if(auth==null || !auth.getAuthorities().containsAll(AuthorityUtils.createAuthorityList(Constants.ROLE_ADMIN))) {
		if(auth==null || !authorities.containsAll(Arrays.asList(Constants.ROLE_ADMIN.split(",")))) {
			throw new UnAuthorizedRequest("You are not authorized to delete Contacts");
			//return new ResponseDetails(new Date(), "You are not authorized to delete Contacts", null, 0);
		}
		return cs.deleteAllContacts(contacts);
	}
	@PostMapping("/subscribedemail")
	public @ResponseBody ResponseDetails subscribedEmailIds(@RequestBody Subscriber subscriber){
		Subscriber chku = cs.findSubscriberByEmail(subscriber.getEmail());
		
		if(chku==null) {
			//chku.setEmail(subscriber.getEmail());
			Subscriber subscriberId = cs.saveSubscriber(subscriber);
			if (subscriberId.getId()!=null) {
				return new ResponseDetails(new Date(), "Thank you for subscribing", null, 1);
			}
			return new ResponseDetails(new Date(), "Sorry, Please try again later", null, 403);
		}
		return new ResponseDetails(new Date(), "You have already subscribred", null, 0);
	}
}
