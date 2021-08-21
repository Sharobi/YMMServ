package com.retail.ecom.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.retail.ecom.model.Order;
import com.retail.ecom.model.OrderDetails;
import com.retail.ecom.model.PaytmDetails;
import com.retail.ecom.utils.ResponseDetails;

@Service
public interface OrderService {

	ResponseDetails addOrder(Order o, int uid);

	ResponseDetails cancelOrder(int oid, Integer id);

	List<Order> getAllForStoreAdmin(int lsid, int cid, int page, int limit,String startDate,String endDate,int itemId,int status);

	ResponseDetails acceptOrder(Integer oid, int companyId, int storeId);
	ResponseDetails acceptAllOrder(List<OrderDetails> saleOrderIds, int companyId, int storeId);
	
	//ResponseDetails saleOrder(Integer id, int companyId, int storeId);
	ResponseDetails saleOrder(Integer id, String expiryDate, String batchNo, int companyId, int storeId,String pickupDate,String pickupTime);
	
	ResponseDetails saleAllOrder(List<OrderDetails> saleOrderIds, int companyId, int storeId);

	List<Order> getAllForAdmin(int page, int limit, String startDate, String endDate, int itemId, int status);
	
	List<Order> getAllForAgentAdmin(int page, int limit, String startDate, String endDate, int itemId, int status, Integer userId);

	List<Order> getAllByUser(int uid, int page, int limit);
	
	Order getOrderById(int saleOrderId, int uid);

	List<Order> getAllTopSaleOrderItems(); //11-11-2019

	int getLastOneHourOrdersForStore(int storeId);//new added 11-3-2020
	

	//ResponseDetails assignOrderItemStore(OrderDetails ordDetail);

	ResponseDetails assignOrderItemStore(int id, int storeId, int companyId,String deliveryType);

	ResponseDetails saleOrderItemFromStore(int id,String batchNo,String expiryDate);

	void savePgDetails(PaytmDetails pd);

	ResponseDetails dispatchOrder(Integer oid, int companyId, int storeId);

	ResponseDetails deliverOrder(Integer oid, int companyId, int storeId);

	ResponseDetails dispatchItemFromStore(Integer oid);

	ResponseDetails deliverOrderItemFromStore(Integer oid);

	OrderDetails getSaleOrderDetailsById(Integer id);

	void setDaliveryTimeAndDate(OrderDetails orderdet);

	List<Order> getOrderDetailsByDeliveryTypeAndStatus(String deliveryType, int status);

	///List<OrderDetails> getOrderDetailsByDeliveryTypeAndStatus(Integer deliveryPersonId, int status);

	void setPickUpOrder(Integer id, int status);

	List<OrderDetails> getOrderDetailsById(Integer storeId, Integer companyId, Integer id);

	List<Order> getOrderDetailsByDeliveryPersonId(Integer deliveryPersonId,int status);

	void setOrderDetailsStatus(int status, Integer id);

	

	
}
