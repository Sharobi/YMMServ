package com.retail.ecom.serviceImpl;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.retail.ecom.model.ItemMapping;
import com.retail.ecom.model.ItemSync;
import com.retail.ecom.model.LatLngGeoLocations;
import com.retail.ecom.model.StoreBasicDTO;
import com.retail.ecom.repository.ItemMappingRepository;
import com.retail.ecom.service.ItemMappingService;
import com.retail.ecom.service.StoreService;
import com.retail.ecom.utils.ResponseDetails;

@Service
public class ItemMappingServiceImpl implements ItemMappingService {

	@Autowired
	ItemMappingRepository imr;
	
	@Autowired
	StoreService ss;
	
	@Override
	public ResponseDetails checkDeliverable(int itemId, double lat, double lng) {
		//System.out.println(itemId+" "+lat+" "+lng);
		List<Integer> storeIds = ss.getStoreIdsInRadius(lat, lng);
		
		//storeIds = storeIds.substring(0, storeIds.length()-1);
		//System.out.println("storeIds = "+storeIds);
		Double qty = 0.0;
		if(storeIds.size()>0) {
			
			qty = imr.getQuantityByItemIdRadius(itemId, storeIds);
			
		}
		
		if(qty!=null && qty>0) {
			return new ResponseDetails(new Date(), "We Can Deliver at your location", null,qty);
		}
		return new ResponseDetails(new Date(), "Sorry! We cannot Deliver this item at your location", null,0);
	}
	@Override
	public ResponseDetails checkdeliverableByPin(int itemId, int pinCode) {
		ResponseDetails responseDetails = null;
		List<LatLngGeoLocations> lisOflatlngs;
		lisOflatlngs = imr.getLatLngByPin(pinCode);
		if (!lisOflatlngs.isEmpty()) {
			for (Iterator iterator1 = lisOflatlngs.iterator(); iterator1.hasNext();) {
				LatLngGeoLocations latlngs = (LatLngGeoLocations) iterator1.next();
						//System.out.println("Lat: "+latlngs.getLatitude()+", Lng: "+latlngs.getLongitude());
						responseDetails = checkDeliverable(itemId, latlngs.getLatitude(), latlngs.getLongitude());
			if (responseDetails.getStatus()>0) {//Here status is no of available qty..
				break;
			   }
			}
		   }else {
			   return new ResponseDetails(new Date(), "InvalidPinCode", null,403);
		}
		return responseDetails;
	}
	@Override
	public ResponseDetails updateQuantity(ItemSync is) {
		imr.updateQuantity(is.getCompanyId(),is.getStoreId(),is.getItemId(),(int)is.getPackQty());
		return new ResponseDetails(new Date(), "Insert Successful", null, 1);
	}

	@Override
//	@Scheduled(fixedDelay=5000)
	public ResponseDetails synccsv() {
		//System.out.println("sync called at : " + new Date());
		imr.synccsv();
		//System.out.println("sync call end  at : " + new Date());
		return null;
	}

}
