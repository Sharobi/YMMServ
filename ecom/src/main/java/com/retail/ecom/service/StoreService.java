package com.retail.ecom.service;

import java.util.List;

import com.retail.ecom.model.Store;
import com.retail.ecom.model.StoreBasicDTO;

public interface StoreService {
	public List<StoreBasicDTO> getAllBasicInfo();

	List<StoreBasicDTO> getStoreInRadius(Double lat, Double lng);

	List<Integer> getStoreIdsInRadius(Double lat, Double lng);

	public StoreBasicDTO getAllBasicInfoById(int storeId);

	Integer getGStoreIdInfoByCSId(int storeId, int companyId);

	public List<Store> getAllStoreByAdmin();
	
	public List<Store> getAllStoreByAgentAdmin(Integer userId,int mapType);

	public List<Store> getAllStoreForAminByPin(String postcode);
	
	public List<Integer> getAllPincodesForAgentAdmin(Integer userId, int mapType);

	public List<Integer> getStoreIdsByPincode(String pinCode);

	
}
