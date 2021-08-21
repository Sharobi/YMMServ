package com.retail.ecom.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.retail.ecom.config.TokenExtractor;
import com.retail.ecom.exception.UnAuthorizedRequest;
import com.retail.ecom.model.Membership;
import com.retail.ecom.service.MembershipService;
import com.retail.ecom.utils.Constants;
import com.retail.ecom.utils.ResponseDetails;

import io.jsonwebtoken.Claims;

@Controller
@RequestMapping("/memberships")
public class MembershipController {
	
	@Autowired
	MembershipService ms;
	
	@Autowired
	private TokenExtractor te;
	
	@GetMapping
	public @ResponseBody List<Membership> getAll(@RequestHeader(value="Authorization") String auth) {
		Claims claims = te.extractInfo(auth);
		List<String> authorities = Arrays.asList(claims.get(Constants.AUTHORITIES_KEY).toString().split(","));
		if(auth==null || !authorities.containsAll(Arrays.asList(Constants.ROLE_ADMIN.split(",")))) {
			return ms.getAllActive();
		}
		return ms.getAll();
	}
	
	@PostMapping
	public @ResponseBody ResponseDetails addMembership(@RequestBody Membership membership, @RequestHeader(value="Authorization") String auth) {
		//if(auth==null || !auth.getAuthorities().containsAll(AuthorityUtils.createAuthorityList(Constants.ROLE_ADMIN))) {
		Claims claims = te.extractInfo(auth);
		List<String> authorities = Arrays.asList(claims.get(Constants.AUTHORITIES_KEY).toString().split(","));
		if(auth==null || !authorities.containsAll(Arrays.asList(Constants.ROLE_ADMIN.split(",")))) {
			throw new UnAuthorizedRequest("You are not authorized to add Membership");
		}
		return ms.addMembership(membership);
	}
	
	@PutMapping
	public @ResponseBody ResponseDetails updateMembership(@RequestBody Membership m,@RequestHeader(value="Authorization") String auth) {
		Claims claims = te.extractInfo(auth);
		List<String> authorities = Arrays.asList(claims.get(Constants.AUTHORITIES_KEY).toString().split(","));
		if(auth==null || !authorities.containsAll(Arrays.asList(Constants.ROLE_ADMIN.split(",")))) {
			throw new UnAuthorizedRequest("You are not authorized to delete Membership");
		}
		return ms.updateMembership(m);
	}
	
	@PutMapping("/{mid}/deactivate")
	public @ResponseBody ResponseDetails deactivateMembership(@PathVariable(name="mid") int mid) {
		return ms.deactivateMembership(mid);
	}
	
	@PutMapping("/{mid}/activate")
	public @ResponseBody ResponseDetails activateMembership(@PathVariable(name="mid") int mid) {
		return ms.activateMembership(mid);
	}
	
	@DeleteMapping("/{mid}")
	public @ResponseBody ResponseDetails deleteMembership(@PathVariable(name="mid") int mid,@RequestHeader(value="Authorization") String auth) {
		Claims claims = te.extractInfo(auth);
		List<String> authorities = Arrays.asList(claims.get(Constants.AUTHORITIES_KEY).toString().split(","));
		if(auth==null || !authorities.containsAll(Arrays.asList(Constants.ROLE_ADMIN.split(",")))) {
			throw new UnAuthorizedRequest("You are not authorized to delete Membership");
		}
		return ms.deleteMembership(mid);
	}
	@GetMapping("/getAllActive")
	public @ResponseBody List<Membership> getAllActive() {
		List<Membership> memberships = ms.getAllActive();
			return memberships;
	}	
}
