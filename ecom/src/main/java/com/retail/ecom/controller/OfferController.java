package com.retail.ecom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.retail.ecom.model.Offer;
import com.retail.ecom.service.OfferService;

@Controller
@RequestMapping("/offers")
public class OfferController {
	
	@Autowired
	private OfferService os;
	
	@GetMapping
	public @ResponseBody List<Offer> getOffers() {
		return os.getAll();
	}
	
	@GetMapping("/activity/{aid}")
	public @ResponseBody List<Offer> getOffersByActivity(@PathVariable(name="aid")int aid) {
		return os.getOffersByActivity(aid);
	}
	
}
