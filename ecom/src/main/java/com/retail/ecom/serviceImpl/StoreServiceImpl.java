package com.retail.ecom.serviceImpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.retail.ecom.model.CompanyStore;
import com.retail.ecom.model.Contacts;
import com.retail.ecom.model.Store;
import com.retail.ecom.model.StoreBasicDTO;
import com.retail.ecom.repository.AddressRepository;
import com.retail.ecom.repository.StoreRepository;
import com.retail.ecom.service.StoreService;

@Service
public class StoreServiceImpl implements StoreService {
	
	@Autowired
	StoreRepository sr;
	@Autowired
	AddressRepository addressRepository;
	
	@Value(value = "${store.location.radius}")
	Double radius;
	
	@Override
	public List<StoreBasicDTO> getAllBasicInfo() {
		return sr.getAllBasicInfo();
	}
	
	@Override
	public List<StoreBasicDTO> getStoreInRadius(Double lat,Double lng) {
		List<StoreBasicDTO> allStores = sr.getAllBasicInfo();
		List<StoreBasicDTO> filteredStores = new ArrayList<>();
		double lat1 = lat;
		double lon1 = lng;
		
		for (Iterator iterator = allStores.iterator(); iterator.hasNext();) {
			StoreBasicDTO store = (StoreBasicDTO) iterator.next();
			if(inRadius(lat1, lon1, store.getLatitude(), store.getLongitude())) {
				filteredStores.add(store);
			}
		}
		
		return filteredStores;
	}
	
	@Override
	public List<Integer> getStoreIdsInRadius(Double lat,Double lng) {
		List<StoreBasicDTO> allStores = sr.getAllBasicInfo();
		String filteredStoreIds = "";
		List<Integer> filteredStoreIdsl = new ArrayList<>();
		
		List<Integer> store_ids = new ArrayList<>();
		List<Integer> company_ids = new ArrayList<>();
		
		double lat1 = lat;
		double lon1 = lng;
		
		for (Iterator iterator = allStores.iterator(); iterator.hasNext();) {
			StoreBasicDTO store = (StoreBasicDTO) iterator.next();
			//System.out.println("lat1: "+lat1+", lon1: "+lon1+", Store_lat: "+store.getLatitude()+", Store_lng: "+store.getLongitude());
			if(inRadius(lat1, lon1, store.getLatitude(), store.getLongitude())) {
				filteredStoreIds+=store.getId().toString()+"";
				filteredStoreIdsl.add(store.getId());
			}
		}
		
		return filteredStoreIdsl;
	}
	
	
	public boolean inRadius(double lat1,double lon1,double lat2,double lon2) {
		
		boolean test = false;
		
		/*double lat1=22.5713942800;
		double lat2=22.5720184200;
		double lon1=88.3505618700;
		double lon2=88.3489847300;*/

		double R = 6371; // radius of earth in KM
		double f1 = lat1*Math.PI/180;//lat1.toRadians(); //to radian = 1deg*Math.PI/180
		double f2 = lat2*Math.PI/180;
		double dlatr = (lat2-lat1)*Math.PI/180;
		double dlonr = (lon2-lon1)*Math.PI/180;

		double a = Math.sin(dlatr /2) * Math.sin(dlatr /2) +
		        Math.cos(f1) * Math.cos(f2) *
		        Math.sin(dlonr/2) * Math.sin(dlonr/2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

		double d = R * c;
		
		//System.out.println("Distance = "+d);
		
		if(d<=radius) {
			test = true;
		}
		
		return test;
	}

	@Override
	public StoreBasicDTO getAllBasicInfoById(int storeId) {
		return sr.getAllBasicInfoById(storeId);
	}
	
	@Override
	public Integer getGStoreIdInfoByCSId(int storeId, int companyId) {
		return sr.getGStoreIdInfoByCSId(storeId, companyId);
	}

	@Override
	public List<Store> getAllStoreByAdmin() {
		// TODO Auto-generated method stub
		/* return (List<Store>)sr.findAll(); */
		return sr.getAllStoreforAdmin();
	}
	@Override
	public List<Store> getAllStoreByAgentAdmin(Integer userId,int mapType) {
		// TODO Auto-generated method stub
		/* return (List<Store>)sr.findAll(); */
		List<Integer> pinCodes =addressRepository.getPinCodeByUserId(userId,mapType);
		List<String> pins=new ArrayList<String>();
		for(int i=0;i<pinCodes.size();i++)
		{
			String pin=pinCodes.get(i).toString();
			pins.add(pin);
		}
		return sr.getAllStoreforAgentAdmin(pins);
	}

	@Override
	public List<Store> getAllStoreForAminByPin(String postcode) {
		// TODO Auto-generated method stub
		///System.err.println("postcode "+postcode);
		return sr.getAllStoreForAminByPin(postcode);
	}
	@Override
	public List<Integer> getAllPincodesForAgentAdmin(Integer userId,int mapType)
	{
		List<Integer> pinCodes =addressRepository.getPinCodeByUserId(userId,mapType);
		return pinCodes;
	}

	@Override
	public List<Integer> getStoreIdsByPincode(String pinCode) {
		// TODO Auto-generated method stub
		return sr.getStoreIdsByPincode(pinCode);
	}

}
