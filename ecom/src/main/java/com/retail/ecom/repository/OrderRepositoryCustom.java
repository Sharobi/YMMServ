package com.retail.ecom.repository;

import java.util.List;

import com.retail.ecom.model.Order;

public interface OrderRepositoryCustom {
	
	List<Order> getAllforStoreAdmin(List<Integer> oids, int page, int limit,String startDate,String endDate,int itemId,int status);

	List<Order> getAllforAdmin(int page, int limit, String startDate, String endDate, int itemId, int status);
	
	List<Order> getAllforAgentAdmin(int page, int limit, String startDate, String endDate, int itemId, int status,List<Integer> pinCodes);

	List<Order> getAllforStoreAdmin(int sid, int page, int limit, String startDate, String endDate, int itemId, int status);

	List<Order> getAllByUser(int uid, int page, int limit);
	
    List<Order> getOrderDetailsByDeliveryTypeAndStatus(String deliveryType, int status);
    
	List<Order> getOrderDetailsByDeliveryPersonId(Integer deliveryPersonId, int status);
	
}
