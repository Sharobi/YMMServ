package com.retail.ecom.service;

import java.util.List;

import com.retail.ecom.model.Cart;

public interface CartService {
	public List<Cart> addToCart(Cart cart);

	public List<Cart> bulkAddToCart(List<Cart> carts,int uid);
	
	public List<Cart> getAll(int uid);

	public List<Cart> bulkUpdateCart(List<Cart> carts, int uid);

	public List<Cart> deleteCart(int id, Integer id2);

	public Cart getById(int id);

	public Cart update(Cart cart);

	public List<Cart> getAllWishlistByUser(Integer id);

	List<Cart> addToWishlist(Cart cart);

	public List<Cart> deleteWishlist(int id, Integer id2);
}
