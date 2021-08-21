package com.retail.ecom.repository;

import java.util.List;

import com.retail.ecom.model.Cart;

public interface CartRepositoryCustom {
	public int saveAllCarts(List<Cart> carts);
}
