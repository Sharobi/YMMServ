package com.retail.ecom.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.retail.ecom.model.ItemMapping;
import com.retail.ecom.model.LatLngGeoLocations;
import com.retail.ecom.model.StoreBasicDTO;
import com.retail.ecom.utils.ResponseDetails;

@Repository
public interface ItemMappingRepository extends CrudRepository<ItemMapping, Integer>,ItemMappingRepositoryCustom {
	
	@Query("select sum(im.currentPackQty) from ItemMapping im where im.globalItemId = ?1 and im.storeId IN (?2)")
	public Double getQuantityByItemIdRadius(int itemId, List<Integer> storeIds);
	
	@Query("select sum(im.currentPackQty) from ItemMapping im where im.globalItemId = ?1 and im.companyId=?2 and im.storeId=?3")
	public Double getQuantityByItemIdStore(int itemId, int companyid, int storeId);

	@Transactional
	@Modifying
	@Query("update ItemMapping im set im.currentPackQty = ?4 where im.companyId=?1 and im.storeId=?2 and im.localItemId=?3")
	public void updateQuantity(int companyId, int storeId, int itemId, int qty);
	
	
	@Transactional
	@Modifying
	@Query("update ItemMapping im set im.currentPackQty = (im.currentPackQty - ?4) where im.companyId=?1 and im.storeId=?2 and im.globalItemId=?3")
	public void updateQuantityOnAccept(int companyId, int storeId, int itemId, int orderQty);

	@Query("select gl from LatLngGeoLocations gl where gl.pinCode=?1")//12-12-2019
	public List<LatLngGeoLocations> getLatLngByPin(int pinCode);
}
