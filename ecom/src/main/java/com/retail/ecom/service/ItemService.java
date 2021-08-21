package com.retail.ecom.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.retail.ecom.model.CategoryDTO;
import com.retail.ecom.model.GroupDTO;
import com.retail.ecom.model.Item;
import com.retail.ecom.model.ItemBasicDTO;
import com.retail.ecom.model.ItemDetailsDTO;
import com.retail.ecom.model.Order;
import com.retail.ecom.model.SideEffectDetails;
import com.retail.ecom.model.SideEffectDetailsDTO;

public interface ItemService {
	public List<Item> getAll();
	public List<Item> getAllBasic();
	public List<Item> getSearchBasic(String name);
	public List<Item> findAllAvailAbleItems(String name);//24-10-2019
	public Item getById(int id);
	public ItemDetailsDTO getDetailsById(int id);
	public List<ItemDetailsDTO> getDetailsByContentId(int id, String strength,Integer groupId, Integer page, Integer limit);
	public List<ItemDetailsDTO> getDetailsByGroupId(int id);
	public List<ItemDetailsDTO> getDetailsByGroupIdPaged(int id, Integer page, Integer limit);
	public GroupDTO getDetailsByGroupIdPagedNew(int id, Integer page, Integer limit);
	List<ItemDetailsDTO> getDetailsByCategoryIdPaged(int id, Integer page, Integer limit);
	List<ItemDetailsDTO> getDetailsBySubCategoryIdPaged(int id, Integer page, Integer limit);
	Integer totalItemsBySubCategory(int id);
	ItemDetailsDTO getDetailsByIdRadius(int id, double lat, double lng);
	public List<ItemDetailsDTO> getCartItemsDetails(List<Integer> ids, double lat, double lng);
	public List<ItemDetailsDTO> getCartItemsDetails(List<Integer> ids, int pin);
	List<Integer> getEligibleStoreIdsByIdRadius(int id,double lat,double lng,double packQty);
	public List<ItemDetailsDTO> getAllDetailsByIds(List<Integer> ids);
	public List<ItemDetailsDTO> getAllItemsDetailsByIds(List<Integer> ids);
	public List<ItemDetailsDTO> getItemQtyInRadius(List<Integer> ids, double lat, double lng);
	public List<ItemDetailsDTO> getItemQtyInRadiusByPin(List<Integer> ids, int pin);
	public List<ItemDetailsDTO> getAllTopSaleItems();
	public List<SideEffectDetailsDTO> getSideEffectByItemsId(int id);
	public Iterable<ItemDetailsDTO> getFiveItemsPerCategoryByCatId(int catId);
	public CategoryDTO getFiveItemsPerCategoryByCatIdNew(Integer catId);

}
