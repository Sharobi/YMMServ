package com.retail.ecom.controller;

import static com.retail.ecom.utils.Constants.USER_ID;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.retail.ecom.config.TokenExtractor;
import com.retail.ecom.exception.UnAuthorizedRequest;
import com.retail.ecom.model.StoreBasicDTO;
import com.retail.ecom.model.User;
import com.retail.ecom.service.StoreService;
import com.retail.ecom.service.UserService;
import com.retail.ecom.utils.Constants;

import io.jsonwebtoken.Claims;

@Controller
@RequestMapping("/stores")
public class StoreController {
	
	@Autowired
	private StoreService storeService;
	
	@Autowired
	private UserService us;
	
	@Autowired
	TokenExtractor te;
	
	/*@GetMapping("/basicinfo")
	public @ResponseBody List<StoreBasicDTO> getAllBasicInfo() {
		return storeService.getAllBasicInfo();
	}*/
	
	@GetMapping("/getall")
	public @ResponseBody List<StoreBasicDTO> getAll(Authentication auth) {
		System.err.println(auth);
		User u = us.findUserByUserName(auth.getName());
		return storeService.getAllBasicInfo();
	}
	
	@GetMapping("/basicinfo")
	public @ResponseBody StoreBasicDTO getAllBasicInfo(Authentication auth) {
		User u = us.findUserByUserName(auth.getName());
		return storeService.getAllBasicInfoById(u.getStoreId());
	}
	
	
}
