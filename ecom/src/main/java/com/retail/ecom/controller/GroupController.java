package com.retail.ecom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.retail.ecom.model.Group;
import com.retail.ecom.service.GroupService;

@Controller
@RequestMapping("/groups")
public class GroupController {
	
	@Autowired
	GroupService gs;
	
	@GetMapping
	public @ResponseBody List<Group> getAll() {
		return gs.getAll(); 
	}
}
