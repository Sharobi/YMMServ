package com.retail.ecom.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.retail.ecom.model.OrderEligibleStore;

@Repository
public interface OrderEligibleStoreRepository extends CrudRepository<OrderEligibleStore, Integer> {
	
	@Query("select oes.orderId from OrderEligibleStore oes where oes.storeId=?1")
	public List<Integer> getOrderIdsByStore(int sid);
	
	@Transactional
	@Modifying
	@Query("delete from OrderEligibleStore oes where oes.orderDetailsId=?1")
	public void deleteByOrderDetailsId(int odid);
	
}
