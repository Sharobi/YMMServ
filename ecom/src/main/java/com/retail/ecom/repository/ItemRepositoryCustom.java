package com.retail.ecom.repository;

import java.util.List;

import com.retail.ecom.model.GroupDTO;
import com.retail.ecom.model.Item;
import com.retail.ecom.model.ItemDetailsDTO;

public interface ItemRepositoryCustom {
	public Item getById(int id);
	public ItemDetailsDTO getDetailsById(int id);
	public List<ItemDetailsDTO> getDetailsByContentId(int id, String strength,Integer groupId, Integer page, Integer limit);
	List<ItemDetailsDTO> getDetailsByGroupId(int id);
	public List<ItemDetailsDTO> getDetailsByGroupIdPaged(int id, Integer page, Integer limit);
	public GroupDTO getDetailsByGroupIdPagedNew(int id, Integer page, Integer limit);
	List<ItemDetailsDTO> getDetailsByCategoryIdPaged(int id, Integer page, Integer limit);
	List<ItemDetailsDTO> getDetailsBySubCategoryIdPaged(int id, Integer page, Integer limit);
	public ItemDetailsDTO getDetailsByIdRadius(int id, List<Integer> storeIds);
	public List<ItemDetailsDTO> getAllDetailsByIdRadius(List<Integer> ids, List<Integer> storeIds);
	List<Integer> getEligibleStoreIdsByIdRadius(int id, List<Integer> storeIds,double packQty);
	List<ItemDetailsDTO> getAllDetailsByIds(List<Integer> ids);
	List<ItemDetailsDTO> getItemQtyInRadius(List<Integer> ids, List<Integer> storeIds);
}
