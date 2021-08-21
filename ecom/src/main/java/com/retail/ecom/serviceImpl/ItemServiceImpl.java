package com.retail.ecom.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.retail.ecom.model.CategoryDTO;
import com.retail.ecom.model.GroupDTO;
import com.retail.ecom.model.Item;
import com.retail.ecom.model.ItemBasicDTO;
import com.retail.ecom.model.ItemDetailsDTO;
import com.retail.ecom.model.ItemDetailsMaxSaleDTO;
import com.retail.ecom.model.ItemMapping;
import com.retail.ecom.model.LatLngGeoLocations;
import com.retail.ecom.model.Order;
import com.retail.ecom.model.SideEffectDetails;
import com.retail.ecom.model.SideEffectDetailsDTO;
import com.retail.ecom.repository.ItemRepository;
import com.retail.ecom.repository.OrderRepository;
import com.retail.ecom.service.ItemService;
import com.retail.ecom.service.OrderService;
import com.retail.ecom.service.StoreService;
import com.retail.ecom.utils.ResponseDetails;

@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	ItemRepository itemRepository;
	
	@Autowired
	StoreService ss;

	//@Autowired
	//OrderRepository or;
	@Autowired
	OrderService os;

	 @PersistenceContext
	 private EntityManager em;
	
	
	@Override
	public List<Item> getAll() {
		return itemRepository.findAll();
	}

	@Override
	public List<Item> getAllBasic() {
		return itemRepository.getAllBasic();
	}

	@Override
	public List<Item> getSearchBasic(String name) {
		return itemRepository.getSearchBasic(name.trim(),new PageRequest(0,100));/////value increase 17/03/2020 
	}




	@Override
	public List<Item> findAllAvailAbleItems(String name) {//24-10-2019
			List<Item> items =  itemRepository.getSearchBasic(name.trim(),new PageRequest(0,10));
            List<Item> items1 = new ArrayList<>();
			
			if (!items.isEmpty()) {
			List<Integer> itemIds =  new ArrayList<>();
			List<Integer> itemIds1 =  new ArrayList<>();
				//List<Item> filteredStores = new ArrayList<>();
				for (Iterator iterator = items.iterator(); iterator.hasNext();) {
					ItemBasicDTO item = (ItemBasicDTO) iterator.next();
					if(item.getId()>0) {
						itemIds.add(item.getId());
					}
				}
				//System.out.println("itemIds: "+itemIds.toString());
				List<ItemMapping> availAbleItems = itemRepository.findAllAvailAbleItems(itemIds);
				//System.out.println("availAbleItems: "+availAbleItems.toString());
				
				/*for (Iterator iterator = items.iterator(); iterator.hasNext();) {
					ItemBasicDTO item = (ItemBasicDTO) iterator.next();*/
					
				if (!availAbleItems.isEmpty()) {	
				for (Iterator iterator1 = availAbleItems.iterator(); iterator1.hasNext();) {
						ItemMapping availAbleItem = (ItemMapping) iterator1.next();
						//if(item.getId()==availAbleItem.getGlobalItemId()) {
							//System.out.println("availAbleItem: "+availAbleItem.getGlobalItemId());
							itemIds1.add(availAbleItem.getGlobalItemId());
						//}
					//}
				}
				System.out.println("itemIds1: "+itemIds1.toString());
				items1 =  itemRepository.getSearchBasicAgain(itemIds1);
			   }
			}
			return items1;
	}
	
	@Override
	public Item getById(int id) {
		return itemRepository.getById(id);
	}
	
	@Override
	public ItemDetailsDTO getDetailsById(int id) {
		return itemRepository.getDetailsById(id);
	}
	
	@Override
	public ItemDetailsDTO getDetailsByIdRadius(int id,double lat,double lng) {
		List<Integer> storeIds = ss.getStoreIdsInRadius(lat, lng);
		return itemRepository.getDetailsByIdRadius(id,storeIds);
	}
	
	@Override
	public List<ItemDetailsDTO> getCartItemsDetails(List<Integer> ids, double lat, double lng) {
		List<Integer> storeIds = ss.getStoreIdsInRadius(lat, lng);
//		System.out.println("stores in radius = ");
		return itemRepository.getAllDetailsByIdRadius(ids,storeIds);
	}
	@Override
	public List<ItemDetailsDTO> getCartItemsDetails(List<Integer> ids, int pinCode) {
		List<ItemDetailsDTO> itemDetailsDTO= new ArrayList<>();
		List<Integer> storeIds = null;
		List<LatLngGeoLocations> lisOflatlngs;
		lisOflatlngs = itemRepository.getLatLngByPin(pinCode);
		if (!lisOflatlngs.isEmpty()) {
			for (Iterator iterator1 = lisOflatlngs.iterator(); iterator1.hasNext();) {
				LatLngGeoLocations latlngs = (LatLngGeoLocations) iterator1.next();
						//System.out.println("Lat: "+latlngs.getLatitude()+", Lng: "+latlngs.getLongitude());
						storeIds = ss.getStoreIdsInRadius(latlngs.getLatitude(), latlngs.getLongitude());
						itemDetailsDTO = itemRepository.getAllDetailsByIdRadius(ids,storeIds);
			}
		   }else {
			   return itemDetailsDTO;
		}
		return itemDetailsDTO;
	}

	/*@Override
	public List<ItemDetailsDTO> getDetailsByContentId(int id, String strength,Integer groupId, Integer page, Integer limit) {
		return itemRepository.getDetailsByContentId(id, strength, groupId, page,limit);
	}*/
	@Override //11-11-2019 only available items will be shown in ALTERNATE BRANDS......
	public List<ItemDetailsDTO> getDetailsByContentId(int id, String strength,Integer groupId, Integer page, Integer limit) {
		List<ItemDetailsDTO> alternatItems = itemRepository.getDetailsByContentId(id, strength, groupId, page,limit);
		
		List<ItemDetailsDTO> alternatItemsInStocked = new ArrayList<ItemDetailsDTO>();
		List<Integer> alternatItemsIds = new ArrayList<Integer>();
		
		if (!alternatItems.isEmpty()) {
		for (Iterator<ItemDetailsDTO> iterator1 = alternatItems.iterator(); iterator1.hasNext();) {
			ItemDetailsDTO alternatItem = (ItemDetailsDTO) iterator1.next();
			alternatItemsIds.add(alternatItem.getId());
			//System.out.println("alternatItemsIds: "+alternatItemsIds);
		}
		List<ItemMapping> availAbleItems = itemRepository.findAllAvailAbleItems(alternatItemsIds);
		for (Iterator<ItemMapping> iterator2 = availAbleItems.iterator(); iterator2.hasNext();) {
				ItemMapping availAbleItem = (ItemMapping) iterator2.next();
				for (Iterator<ItemDetailsDTO> iterator3 = alternatItems.iterator(); iterator3.hasNext();) {
					ItemDetailsDTO stockedItem = (ItemDetailsDTO) iterator3.next();
					if(stockedItem.getId().equals(availAbleItem.getGlobalItemId())) {
						alternatItemsInStocked.add(stockedItem);
				}
			}
		 }
		}
		//return alternatItems;
		return alternatItemsInStocked;
	}
	
	@Override
	public List<ItemDetailsDTO> getDetailsByGroupId(int id) {
		return itemRepository.getDetailsByGroupId(id);
	}

	@Override
	public List<ItemDetailsDTO> getDetailsByGroupIdPaged(int id, Integer page, Integer limit) {
		return itemRepository.getDetailsByGroupIdPaged(id,page,limit);
	}

	@Override
	public List<ItemDetailsDTO> getDetailsByCategoryIdPaged(int id, Integer page, Integer limit) {
		return itemRepository.getDetailsByCategoryIdPaged(id,page,limit);
	}

	@Override
	public List<ItemDetailsDTO> getDetailsBySubCategoryIdPaged(int id, Integer page, Integer limit) {
		return itemRepository.getDetailsBySubCategoryIdPaged(id,page,limit);
	}

	@Override
	public Integer totalItemsBySubCategory(int id) {
		return itemRepository.totalItemsBySubCategory(id);
	}
	
	@Override
	public List<Integer> getEligibleStoreIdsByIdRadius(int id,double lat,double lng,double packQty) {
		List<Integer> storeIds = ss.getStoreIdsInRadius(lat, lng);
		return itemRepository.getEligibleStoreIdsByIdRadius(id,storeIds,packQty);
	}

	@Override //Using ItemRepositoryCustom
	public List<ItemDetailsDTO> getAllDetailsByIds(List<Integer> ids) {
		return itemRepository.getAllDetailsByIds(ids);
	}
	@Override //11-11-2019 Using ItemRepository
	public List<ItemDetailsDTO> getAllItemsDetailsByIds(List<Integer> ids) {
		    List<ItemDetailsDTO> items = itemRepository.getAllItemsDetailsByIds(ids, new PageRequest(0,10));
		return items;
	}

	@Override
	public List<ItemDetailsDTO> getItemQtyInRadius(List<Integer> ids, double lat, double lng) {
		List<Integer> storeIds = ss.getStoreIdsInRadius(lat, lng);
		return itemRepository.getItemQtyInRadius(ids,storeIds);
	}
	@Override
	public List<ItemDetailsDTO> getItemQtyInRadiusByPin(List<Integer> ids, int pinCode) {//30-12-2019
		List<ItemDetailsDTO> itemDetailsDTO= new ArrayList<>();
		List<Integer> storeIds = null;
		List<LatLngGeoLocations> lisOflatlngs;
		lisOflatlngs = itemRepository.getLatLngByPin(pinCode);
		if (!lisOflatlngs.isEmpty()) {
			for (Iterator iterator1 = lisOflatlngs.iterator(); iterator1.hasNext();) {
				LatLngGeoLocations latlngs = (LatLngGeoLocations) iterator1.next();
						//System.out.println("Lat: "+latlngs.getLatitude()+", Lng: "+latlngs.getLongitude());
						storeIds = ss.getStoreIdsInRadius(latlngs.getLatitude(), latlngs.getLongitude());
						itemDetailsDTO = itemRepository.getItemQtyInRadius(ids,storeIds);
			}
		   }else {
			   return itemDetailsDTO;
		}
		return itemDetailsDTO;
	}

	@Override //11-11-2019
	public List<ItemDetailsDTO> getAllTopSaleItems() {
		List<Integer> ids = new ArrayList<Integer>();
		//List<Integer> ids = Arrays.asList(1, 2, 3);
		//ids.add(78861);
		//ids.add(3); 
		List<Order> items1 = os.getAllTopSaleOrderItems();
		for (Iterator iterator = items1.iterator(); iterator.hasNext();) {
			ItemDetailsMaxSaleDTO order = (ItemDetailsMaxSaleDTO) iterator.next();
			ids.add(order.getId());
		}
		//System.out.println("ids: "+ids.toString());
		List<ItemDetailsDTO> items = itemRepository.getAllDetailsByIds(ids);//Using ItemRepositoryCustom
		//List<ItemDetailsDTO> items = itemRepository.getAllItemsDetailsByIds(ids, new PageRequest(0,10));//Using ItemRepository
		return items;
	}

	@Override
	public List<SideEffectDetailsDTO> getSideEffectByItemsId(int id) {
		// TODO Auto-generated method stub
		return itemRepository.getSideEffectByItemsId(id);
	}
		@Override
		public Iterable<ItemDetailsDTO> getFiveItemsPerCategoryByCatId(int catId) {
			List<ItemDetailsDTO> item = new ArrayList<>();
			Session session = (Session) em.getDelegate();
			//Session session = sessionFactory.openSession();
			//System.out.println("id: "+catId);
			try {
				//session.beginTransaction().begin();
				SQLQuery query = session.createSQLQuery("call get_group_wise_top_items(" + catId + ")");
				query.setResultTransformer(Transformers.aliasToBean(ItemDetailsDTO.class));
				item = (List<ItemDetailsDTO>) query.list();
				//session.getTransaction().commit();
			} catch (Exception e) {
				e.printStackTrace();
				session.getTransaction().rollback();
			} finally {
				session.close();
			}
			return item;
		}

		@Override
		public GroupDTO getDetailsByGroupIdPagedNew(int id, Integer page, Integer limit) {
			// TODO Auto-generated method stub
			return itemRepository.getDetailsByGroupIdPagedNew(id,page,limit);
		}

		@Override
		public CategoryDTO getFiveItemsPerCategoryByCatIdNew(Integer catId) {
			List<ItemDetailsDTO> item = new ArrayList<>();
			Session session = (Session) em.getDelegate();
			//Session session = sessionFactory.openSession();
			//System.out.println("id: "+catId);
			 CategoryDTO ctg = null;
			try {
				//session.beginTransaction().begin();
				SQLQuery query = session.createSQLQuery("call get_group_wise_top_items(" + catId + ")");
				query.setResultTransformer(Transformers.aliasToBean(ItemDetailsDTO.class));
				item = (List<ItemDetailsDTO>) query.list();
				CriteriaBuilder cb = em.getCriteriaBuilder();
				CriteriaQuery<CategoryDTO> cq2 = cb.createQuery(CategoryDTO.class);
				
				
				 Query query2=em.
				 createQuery("select new com.retail.ecom.model.CategoryDTO(c.id,c.name) from Category c  where c.id=:catId "); 
				 query2.setParameter("catId", catId);
				 ctg=(CategoryDTO)query2.getSingleResult(); 
				 ctg.setItems(item); 
				//session.getTransaction().commit();
			} catch (Exception e) {
				e.printStackTrace();
				session.getTransaction().rollback();
			} finally {
				session.close();
			}
			
			
			 
			 return ctg;
			 
			 
			
			//return item;
			
		}
}
