package com.retail.ecom.repository;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.retail.ecom.model.Order;
import com.retail.ecom.utils.ResponseDetails;

@Repository
public interface OrderRepository extends CrudRepository<Order, Integer>,OrderRepositoryCustom {

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("update Order o set o.isCanceled=1 where o.id=?1 and o.customerId=?2 ")
	void cancelOrder(int oid, Integer id);

	@Query("select o from Order o where o.id=?1 and o.isDeleted=0")
	Order findByOrderId(Integer oid);

	
	@Query("select new com.retail.ecom.model.ItemDetailsMaxSaleDTO(od.itemId,count(od.id)) from OrderDetails od INNER JOIN Order o ON o.id=od.saleOrderId where o.isCanceled=0 group by od.itemId order by count(od.id) desc")
	List<Order> getAllTopSaleOrderItems(Pageable page);

	/*@Query("select o from Order o where o.customerId=?1 and isDeleted=0")
	List<Order> getAllByUser(int uid);*/
	
	///new added 11-3-2020 Sayantan Mahanty
	@Query("select count(od.id) from Order od where od.id in ?1 and od.createdDate>?2")
	public int getLastOneHourOrdersForStore(List<Integer> oids,Date requiredDate);



	
	



	
	

}
