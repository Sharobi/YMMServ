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
import com.retail.ecom.model.Feature;
import com.retail.ecom.service.FeatureService;
import com.retail.ecom.utils.Constants;
import com.retail.ecom.utils.ResponseDetails;

import io.jsonwebtoken.Claims;

@Controller
@RequestMapping(value="/features")
public class FeatureController {
	
	@Autowired
	FeatureService fs;
	
	@Autowired
	private TokenExtractor te;
	
	@GetMapping
	public @ResponseBody List<Feature> getAll(@RequestHeader(value="Authorization") String auth) {
		Claims claims = te.extractInfo(auth);
		List<String> authorities = Arrays.asList(claims.get(Constants.AUTHORITIES_KEY).toString().split(","));
//		if(auth==null || !auth.getAuthorities().containsAll(AuthorityUtils.createAuthorityList(Constants.ROLE_ADMIN))) {
		if(auth==null || !authorities.containsAll(Arrays.asList(Constants.ROLE_ADMIN.split(",")))) {
			return fs.findAllActive();
		}
		return fs.findAll();
	}
	
	@PostMapping
	public @ResponseBody ResponseDetails addFeature(@RequestBody Feature f,@RequestHeader(value="Authorization") String auth) {
		//List<GrantedAuthority> roles = (List<GrantedAuthority>) auth.getAuthorities();
		Claims claims = te.extractInfo(auth);
		List<String> authorities = Arrays.asList(claims.get(Constants.AUTHORITIES_KEY).toString().split(","));
		if(auth==null || !authorities.containsAll(Arrays.asList(Constants.ROLE_ADMIN.split(",")))) {
			throw new UnAuthorizedRequest("You are not authorized to add Feature");
		}
		return fs.addFeature(f);
	}
	
	@PutMapping
	public @ResponseBody ResponseDetails updateFeature(@RequestBody Feature f,@RequestHeader(value="Authorization") String auth) {
		Claims claims = te.extractInfo(auth);
		List<String> authorities = Arrays.asList(claims.get(Constants.AUTHORITIES_KEY).toString().split(","));
		if(auth==null || !authorities.containsAll(Arrays.asList(Constants.ROLE_ADMIN.split(",")))) {
			throw new UnAuthorizedRequest("You are not authorized to update Feature");
		}
		return fs.updateFeature(f);
	}
	
	@PutMapping("/{fid}/deactivate")
	public @ResponseBody ResponseDetails deactivateFeature(@PathVariable(name="fid") int fid) {
		return fs.deactivateFeature(fid);
	}
	
	@PutMapping("/{fid}/activate")
	public @ResponseBody ResponseDetails activateFeature(@PathVariable(name="fid") int fid) {
		return fs.activateFeature(fid);
	}
	
	@DeleteMapping("/{fid}")
	public @ResponseBody ResponseDetails deleteFeature(@PathVariable(name="fid") int fid,@RequestHeader(value="Authorization") String auth) {
		Claims claims = te.extractInfo(auth);
		List<String> authorities = Arrays.asList(claims.get(Constants.AUTHORITIES_KEY).toString().split(","));
//		if(auth==null || !auth.getAuthorities().containsAll(AuthorityUtils.createAuthorityList(Constants.ROLE_ADMIN))) {
		if(auth==null || !authorities.containsAll(Arrays.asList(Constants.ROLE_ADMIN.split(",")))) {
			throw new UnAuthorizedRequest("You are not authorized to delete Feature");
		}
		return fs.deleteFeature(fid);
	}

	@GetMapping("/getAllActive")
	public @ResponseBody List<Feature> findAllActive() {
		List<Feature> feature= fs.findAllActive();
		return feature;
	}
}
