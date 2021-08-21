package com.retail.ecom.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.retail.ecom.model.Cart;

@Repository
public interface CartRepository extends CrudRepository<Cart, Integer>,CartRepositoryCustom {
	
	@Query("select count(c) from Cart c where c.customerId=?1 and c.status=1")
	int getCountByUser(Integer uid);
	
	@Query("select c from Cart c where c.customerId=?1 and c.status=1")
	List<Cart> getCartsByUser(Integer uid);
	
	@Query("select c from Cart c where c.id=?1")
	Cart findCartById(Integer id);

	@Transactional
	@Modifying
	@Query("delete from Cart c where c.id=?1 and c.customerId=?2")
	void deleteCartById(int id, Integer uid);

	@Query("select c from Cart c where c.customerId=?1 and c.status=2")
	List<Cart> getAllWishlistByUser(Integer uid);
}
