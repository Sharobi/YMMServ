package com.retail.ecom.service;

import com.retail.ecom.model.ItemSync;
import com.retail.ecom.utils.ResponseDetails;

public interface ItemMappingService {
	
	public ResponseDetails checkDeliverable(int itemId, double lat, double lng);

	public ResponseDetails updateQuantity(ItemSync is);
	
	public ResponseDetails synccsv();

	public ResponseDetails checkdeliverableByPin(int id, int pinCode);
}
