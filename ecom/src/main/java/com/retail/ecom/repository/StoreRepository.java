package com.retail.ecom.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.retail.ecom.model.Store;
import com.retail.ecom.model.StoreBasicDTO;

@Repository
public interface StoreRepository extends CrudRepository<Store, Integer>,StoreRepositoryCustom {
	
	//@Query("select new com.retail.ecom.model.StoreBasicDTO(s.id, s.name, s.latitude, s.longitude, s.address) from Store s where s.isDeleted=0 and s.isActive=1")
	@Query("select new com.retail.ecom.model.StoreBasicDTO(s.id, s.name, s.latitude, s.longitude, s.address) from Store s where s.latitude IS NOT NULL and s.longitude IS NOT NULL and s.isDeleted=0 and s.isActive=1")
	public List<StoreBasicDTO> getAllBasicInfo();
	
	@Query("select new com.retail.ecom.model.StoreBasicDTO(s.id, s.name, s.latitude, s.longitude, s.address, s.companyId, s.localStoreId) from Store s where s.isDeleted=0 and s.isActive=1")
	public List<StoreBasicDTO> getAllReqInfo();
	
	@Query("select s.id from Store s where s.companyId=?1 and s.localStoreId=?2")
	public Integer getGlobalStoreId(int cid,int sid);

	@Query("select new com.retail.ecom.model.StoreBasicDTO(0, s.name, s.latitude, s.longitude, s.address) from Store s where s.id=?1 and s.isDeleted=0 and s.isActive=1 ")
	public StoreBasicDTO getAllBasicInfoById(int storeId);

	@Query("select s.id from Store s where s.localStoreId=?1 and s.companyId=?2  and s.isDeleted=0 and s.isActive=1 ")
	public Integer getGStoreIdInfoByCSId(int storeId, int companyId);

	/*
	 * @Query("select s.id,s.companyId,s.name,s.address,s.phone,s.email,s.postcode,s.latitude,s.longitude from Store s where s.isActive=1"
	 * )
	 */
	@Query("select s from Store s where s.isDeleted=0 and s.isActive=1")
	public List<Store> getAllStoreforAdmin();
	
	@Query("select s from Store s where s.isDeleted=0 and s.isActive=1 and s.postcode in :pinCodes")
	public List<Store> getAllStoreforAgentAdmin(@Param("pinCodes")List<String> pinCodes);

	@Transactional
	@Query("select s from Store s where s.postcode like %:postcode%")
	public List<Store> getAllStoreForAminByPin(@Param("postcode")String postcode);
 
	@Query("select s from Store s where s.id=?1")
	public Store findBySoreId(int storeId);
	
	@Query("select s.id from Store s where s.postcode=?1")
	public List<Integer> getStoreIdsByPincode(String pinCode);

	//public Store getOne(String postcode);
	
}
