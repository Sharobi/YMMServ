package com.retail.ecom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.retail.ecom.model.PaymentMode;
import com.retail.ecom.service.PaymentModeService;

@Controller
@RequestMapping(value="/paymentmodes")
public class PaymentModeController {
	
	@Autowired
	PaymentModeService pms;
	
	@GetMapping
	public @ResponseBody List<PaymentMode> getAll() {
		return pms.getAll();
	}
}
