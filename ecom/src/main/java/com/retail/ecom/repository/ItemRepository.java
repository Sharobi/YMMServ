package com.retail.ecom.repository;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.retail.ecom.model.Item;
import com.retail.ecom.model.ItemBasicDTO;
import com.retail.ecom.model.ItemDetailsDTO;
import com.retail.ecom.model.ItemMapping;
import com.retail.ecom.model.LatLngGeoLocations;
import com.retail.ecom.model.SideEffectDetails;
import com.retail.ecom.model.SideEffectDetailsDTO;

@Repository
public interface ItemRepository extends CrudRepository<Item, Integer>,ItemRepositoryCustom{
	List<Item> findAll();
	//@Query("select new com.retail.ecom.models.ItemBasicDTO(i.id,i.name) from Item i")
	@Query("select new com.retail.ecom.model.ItemBasicDTO(i.id,i.name,g.name) from Item i left outer join i.group g")
	List<Item> getAllBasic();

	//@Query(value="select i.id,i.name,g.name from vw_in_m_item i inner join in_m_group g on g.id=vi.group_id where vi.name like '%name%'",nativeQuery=true)
	//@Query("select new com.retail.ecom.model.ItemBasicDTO(i.id,i.name,g.name,i.groupId,c.name,c.id) from Item i left outer join i.group g left outer join i.category c where i.isDeleted!=1 and trim(i.name) like ?1%")17/03/2020
	@Query("select new com.retail.ecom.model.ItemBasicDTO(i.id,i.name,g.name,i.groupId,c.name,c.id,i.price,i.conversion,i.meta_tag) from Item i left outer join i.group g left outer join i.category c where i.isDeleted =0 and i.isBanned=0 and i.isDiscontinue=0 and trim(i.name) like ?1%")
	List<Item> getSearchBasic(String name, Pageable page);
	
	//Stars, Only for transaction Items or available Items //24-10-2019
	@Query("select new com.retail.ecom.model.ItemMapping(i.id,i.globalItemId,i.currentPackQty) from ItemMapping i where i.currentPackQty>0 and i.globalItemId IN (?1)")
	List<ItemMapping> findAllAvailAbleItems(List<Integer> globalItemId);
	
	@Query("select new com.retail.ecom.model.ItemBasicDTO(i.id,i.name,g.name) from Item i left outer join i.group g where i.isDeleted!=1 and i.id IN (?1)")
	List<Item> getSearchBasicAgain(List<Integer> id);
	//Ends, Only for transaction Items or available Items //24-10-2019
	
	@Query("select count(i) from Item i left outer join i.subCategory sc where sc.id=?1 and i.isDeleted!=1")
	Integer totalItemsBySubCategory(int id);

	//@Query("select new com.retail.ecom.model.Item(i.id,i.name,i.groupId,i.conversion,i.price) from Item i where i.isDeleted!=1 and i.id IN (?1)")
	@Query("select new com.retail.ecom.model.ItemDetailsDTO(i.id,i.name,i.groupId,i.conversion,i.price) from Item i where i.isDeleted!=1 and i.id IN (?1)")
	List<ItemDetailsDTO> getAllItemsDetailsByIds(List<Integer> ids, Pageable page);//11-11-2019
	//List<Item> getAllItemsDetailsByIds(List<Integer> ids, Pageable page);//11-11-2019
	
	@Query("select new com.retail.ecom.model.SideEffectDetailsDTO(sed.id,sed.indication,sed.contraIndication,sed.cautions,sed.sideEffect,sem.contentPartName) from SideEffectDetails sed left outer join SideEffectMapping sem on sed.id = sem.compositionId where sem.contentId = ?1")
	//@Query("select sed from SideEffectDetails sed left outer join SideEffectMapping sem on sed.id = sem.compositionId where sem.contentId = ?1")
	//@Query("select sed from SideEffectDetails sed where sed.id = ?1")
	List<SideEffectDetailsDTO> getSideEffectByItemsId(int id);//12-12-2019
	
	@Query("select gl from LatLngGeoLocations gl where gl.pinCode=?1")//12-12-2019
	public List<LatLngGeoLocations> getLatLngByPin(int pinCode);
	

	//@Query("select new com.retail.ecom.model.ItemBasicDTO(i.id,i.name,g.name) from Item i left outer join i.group g where i.name like ?1%")
//	List<ItemDetailsDTO> getDetailsByContentId(int id);
}
