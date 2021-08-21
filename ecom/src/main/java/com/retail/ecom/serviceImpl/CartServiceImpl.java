package com.retail.ecom.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.retail.ecom.model.Cart;
import com.retail.ecom.repository.CartRepository;
import com.retail.ecom.service.CartService;
import com.retail.ecom.utils.ResponseDetails;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	CartRepository cr;
	
	@Override
	public List<Cart> addToCart(Cart cart) {
		List<Cart> carts = cr.getCartsByUser(cart.getCustomerId());
		Cart existingCart = null;
		if(cart.getId()==0 || cart.getStatus()==2) {
			for (Iterator iterator = carts.iterator(); iterator.hasNext();) {
				Cart c = (Cart) iterator.next();
				if(c.getItemId().equals(cart.getItemId())) {
					c.setPackQty(c.getPackQty()+cart.getPackQty());
					
					if(cart.getStatus()==2) {//done for moving from wishlist to cart
						cr.delete(cart);
						c.setStatus(1);
					}
					existingCart=c;
					break;
				}
			}
		}
		if(existingCart==null) {
			cart.setStatus(1);//done for moving from wishlist to cart
			cr.save(cart);
		} else {
			cr.save(existingCart);
		}
//		int count = cr.getCountByUser(cart.getCustomerId());
		//return new ResponseDetails(new Date(), "Item is added to cart", "/addtocart",count);
		return cr.getCartsByUser(cart.getCustomerId());
	}
	
	@Override
	public List<Cart> addToWishlist(Cart cart) {
		cr.save(cart);
		return getAllWishlistByUser(cart.getCustomerId());
	}
	
	@Override
	public Cart update(Cart cart) {
		return cr.save(cart);
	}

	@Override
	public List<Cart> bulkAddToCart(List<Cart> carts,int uid) {
		HashMap<Integer, Cart> cartmap = new HashMap<>();
		List<Cart> existingcarts = cr.getCartsByUser(uid);
		for (Iterator iterator = existingcarts.iterator(); iterator.hasNext();) {
			Cart cart = (Cart) iterator.next();
			cartmap.put(cart.getItemId(), cart);
		}
		for (Iterator iterator = carts.iterator(); iterator.hasNext();) {
			Cart cart = (Cart) iterator.next();
			cart.setStatus(1);
			cart.setCustomerId(uid);
			if(cartmap.get(cart.getItemId())==null) {
				cartmap.put(cart.getItemId(),cart);
			} else {
				Cart c = cartmap.get(cart.getItemId());
				c.setPackQty(c.getPackQty()+cart.getPackQty());
				cartmap.put(cart.getItemId(),c);
			}
		}
		cr.saveAll(new ArrayList<Cart>(cartmap.values()));
		//cr.saveAll(carts);
		return cr.getCartsByUser(uid);
	}
	
	@Override
	public List<Cart> bulkUpdateCart(List<Cart> carts,int uid) {
		//cr.saveAllCarts(carts);
		//System.out.println("update = "+carts);
		for (Iterator iterator = carts.iterator(); iterator.hasNext();) {
			Cart cart = (Cart) iterator.next();
			cart.setStatus(1);
			cart.setCustomerId(uid);
		}
		cr.saveAll(carts);
		return cr.getCartsByUser(uid);
	}
	
	/*@Override
	public List<Cart> bulkUpdateCart(List<Cart> carts,int uid) {
		HashMap<Integer, Cart> cartmap = new HashMap<>();
		List<Cart> existingcarts = cr.getCartsByUser(uid);
		for (Iterator iterator = existingcarts.iterator(); iterator.hasNext();) {
			Cart cart = (Cart) iterator.next();
			cartmap.put(cart.getItemId(), cart);
		}
		for (Iterator iterator = carts.iterator(); iterator.hasNext();) {
			Cart cart = (Cart) iterator.next();
			cart.setStatus(1);
			cart.setCustomerId(uid);
			if(cartmap.get(cart.getItemId())==null) {
				cartmap.put(cart.getItemId(),cart);
			} else {
				Cart c = cartmap.get(cart.getItemId());
				c.setPackQty(cart.getPackQty());
				cartmap.put(cart.getItemId(),c);
			}
		}
		cr.saveAll(new ArrayList<Cart>(cartmap.values()));
		//cr.saveAll(carts);
		return cr.getCartsByUser(uid);
	}*/

	@Override
	public List<Cart> getAll(int uid) {
		return cr.getCartsByUser(uid);
	}
	
	@Override
	public Cart getById(int id) {
		return cr.findById(id).get();
	}

	@Override
	public List<Cart> deleteCart(int id, Integer uid) {
		cr.deleteCartById(id,uid);
		return cr.getCartsByUser(uid);
	}
	
	@Override
	public List<Cart> deleteWishlist(int id, Integer uid) {
		cr.deleteCartById(id,uid);
		return cr.getAllWishlistByUser(uid);
	}

	@Override
	public List<Cart> getAllWishlistByUser(Integer uid) {
		return cr.getAllWishlistByUser(uid);
	}

}
