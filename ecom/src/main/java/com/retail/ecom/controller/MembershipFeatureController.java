package com.retail.ecom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.retail.ecom.model.MembershipFeature;
import com.retail.ecom.service.MembershipFeatureService;

@Controller
@RequestMapping("/membershipfeatures")
public class MembershipFeatureController {
	
	@Autowired
	MembershipFeatureService mfs;
	
	@GetMapping
	public @ResponseBody List<MembershipFeature> getAll() {
		return mfs.getAll();
	}
}
