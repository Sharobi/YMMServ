package com.retail.ecom.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.retail.ecom.model.Category;
import com.retail.ecom.service.CategoryService;

@Controller
@RequestMapping("/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService cs;
	
	@GetMapping("/tree")
	public @ResponseBody Set<Category> getTree() {
		return cs.getTree();
	}
}
