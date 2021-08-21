package com.retail.ecom.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.retail.ecom.exception.InvalidStoreCompany;
import com.retail.ecom.model.ItemSync;
import com.retail.ecom.service.ItemMappingService;
import com.retail.ecom.service.StoreService;
import com.retail.ecom.utils.ResponseDetails;

@Controller
@RequestMapping("/mapping")
public class ItemMappingController {
	
	@Autowired
	ItemMappingService ims;
	
	@Autowired
	StoreService ss;
	
	@PostMapping("/synchronize")
	public @ResponseBody ResponseDetails synchronize(@RequestBody ItemSync is) {
		//System.out.println("ItemSync = "+is);
		Integer sid = ss.getGStoreIdInfoByCSId(is.getStoreId(), is.getCompanyId());
		if(sid==null) {
			//return new ResponseDetails(new Date(), "You Are not valid store", null, 2);
			throw new InvalidStoreCompany("You Are not valid store");
		}
		is.setStoreId(sid);
		//System.out.println("ItemSync with global store id = "+is);
//		is.setCompanyId(0);
		return ims.updateQuantity(is);
		//return null;
	}
	
	@PostMapping("/synccsv")
	public @ResponseBody ResponseDetails synccsv() {
		//System.out.println("synccsv");
		return ims.synccsv();
		//return null;
	}
}
