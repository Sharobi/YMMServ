package com.retail.ecom.repository;


import java.util.List;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.retail.ecom.model.OrderDetails;

@Repository
public interface OrderDetailsRepository extends CrudRepository<OrderDetails, Integer> {

	@Query("select od from OrderDetails od where od.id=?1")
	public OrderDetails findOrderDetailsById(int id);

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("update OrderDetails od set od.storeId=?2,od.companyId=?3,od.status=1,od.deliveryType=?4 where od.id=?1")
	public void assignOrderItemStore(int id, int storeId, int companyId,String deliveryType);

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("update OrderDetails od set od.status=2,batchNo=?2,expiryDate=?3 where od.id=?1")
	public void saleOrderItemFromStore(int id,String batchNo,String expiryDate);
	
	/*
	 * @Query("select od from OrderDetails od where od.id=?1") public OrderDetails
	 * getSaleOrderId(int id);
	 */
	
	@Query("select od from OrderDetails od where od.deliveryType=?1 and od.status=?2")
	public List<OrderDetails> getOrderDetailsByDeliveryTypeAndStatus(String deliveryType, int status);

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("update OrderDetails od set od.deliveryAgentId=?1 where od.id=?2")
	public void updateDelveryAgentId(int deliveryAgentId,int saleOrderDetailsId);

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("update OrderDetails od set od.status=?2 where od.id=?1")
	public void setPickUpOrder(Integer id, int status);

	@Query("select od from OrderDetails od where od.storeId=?1 and od.companyId=?2 and od.id=?3")
	public List<OrderDetails> getOrderDetailsById(Integer storeId, Integer companyId, Integer id);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("update OrderDetails od set od.status=?1 where od.id=?2")
	public void setOrderDetailsStatus(int status, Integer id);

	
}
