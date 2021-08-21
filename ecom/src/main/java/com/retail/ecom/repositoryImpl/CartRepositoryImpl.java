package com.retail.ecom.repositoryImpl;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.retail.ecom.model.Cart;
import com.retail.ecom.repository.CartRepositoryCustom;

@Repository
public class CartRepositoryImpl implements CartRepositoryCustom {

	@PersistenceContext
	EntityManager em;
	
	@Override
	@Transactional
	public int saveAllCarts(List<Cart> carts) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaUpdate<Cart> cu = cb.createCriteriaUpdate(Cart.class);
		
		Root r = cu.from(Cart.class);
		
		for (Iterator iterator = carts.iterator(); iterator.hasNext();) {
			Cart cart = (Cart) iterator.next();
			cu.set("packQty", cart.getPackQty());
			cu.where(cb.equal(r.get("id"), cart.getId()));
			em.createQuery(cu).executeUpdate();
		}
		return 1;
	}

}
