package com.retail.ecom.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.retail.ecom.config.TokenExtractor;
import com.retail.ecom.exception.InvalidCredentials;
import com.retail.ecom.model.Cart;
import com.retail.ecom.model.User;
import com.retail.ecom.service.CartService;
import com.retail.ecom.service.UserService;
import static com.retail.ecom.utils.Constants.*;
import com.retail.ecom.utils.ResponseDetails;

import io.jsonwebtoken.Claims;

@Controller
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	private CartService cs;
	
//	@Autowired
//	private UserService us;
	
	@Autowired
	private TokenExtractor te;
	
	@GetMapping("/testauth")
	public @ResponseBody String test(@RequestHeader(value="Authorization") String auth) {
		//System.out.println("Auth = "+auth); 
		Claims claims = te.extractInfo(auth);
		//System.out.println("claims username = "+claims.get("sub"));
		te.extractInfo(auth);
		return "test";
	}
	
	@GetMapping("/wishlist")
	public @ResponseBody List<Cart> getAllWishlist(@RequestHeader(value="Authorization") String auth) {
		Claims claims = te.extractInfo(auth);
		//System.out.println("claims = "+claims);
//		User user = us.findUserByUserName(claims.get("sub").toString());
		return cs.getAllWishlistByUser(Integer.valueOf(claims.get(USER_ID).toString()));
	}
	
	@PostMapping("/wishlist")
	public @ResponseBody List<Cart> addToWishlist(@RequestHeader(value="Authorization") String auth, @RequestBody Cart cart) {
		Claims claims = te.extractInfo(auth);
		//System.out.println("claims = "+claims);
//		User user = us.findUserByUserName(claims.get("sub").toString());
		cart.setStatus(2);
		cart.setCustomerId(Integer.valueOf(claims.get(USER_ID).toString()));
		return cs.addToWishlist(cart);
//		return new ResponseDetails(new Date(), "Item Added to wishlist", null,1);
	}
	
	/*@PutMapping("/wishlisttocart/{id}")
	public @ResponseBody Cart addToWishlist(@RequestHeader(value="Authorization") String auth, @PathVariable(name="id") int id) {
		Claims claims = te.extractInfo(auth);
		System.out.println("claims = "+claims);
		User user = us.findUserByUserName(claims.get("sub").toString());
		Cart cart = cs.getById(id);
		if(user.getId().equals(cart.getCustomerId())) {
			cart.setStatus(1);
		}
		else {
			throw new InvalidCredentials("Sorry not a valid wishlist");
		}
		return cs.update(cart);
	}*/
	
	@PutMapping("/wishlisttocart")
	public @ResponseBody List<Cart> addToCartBulk(@RequestHeader(value="Authorization") String auth, @RequestBody Integer[] wids) {
		Claims claims = te.extractInfo(auth);
		//System.out.println("claims = "+claims);
		if(wids!=null && wids.length>0) {
			for (int i = 0; i < wids.length; i++) {
				Integer wid = (Integer) wids[i];
				Cart cart = cs.getById(wid);
				/*if(user.getId().equals(cart.getCustomerId())) {
					cart.setStatus(1);
				}
				else {
					throw new InvalidCredentials("Sorry not a valid wishlist");
				}*/
				cs.addToCart(cart);
			}
		}
//		return new ResponseDetails(new Date(), "Wishlist moved to cart", null,1);
		return cs.getAllWishlistByUser(Integer.valueOf(claims.get(USER_ID).toString()));
	}
	
	@PostMapping
	public @ResponseBody List<Cart> addToCart(@RequestHeader(value="Authorization") String auth, @RequestBody Cart cart) {
		Claims claims = te.extractInfo(auth);
		//System.out.println("claims = "+claims);
//		User user = us.findUserByUserName(claims.get("sub").toString());
		cart.setStatus(1);
		cart.setCustomerId(Integer.valueOf(claims.get(USER_ID).toString()));
		return cs.addToCart(cart);
	}
	
	@PostMapping("/addinbulk")
	public @ResponseBody List<Cart> bulkAddToCart(@RequestHeader(value="Authorization") String auth, @RequestBody List<Cart> carts) {
		Claims claims = te.extractInfo(auth);
		//System.out.println("claims = "+claims);
		//System.out.println("Carts = "+carts);
//		User user = us.findUserByUserName(claims.get("sub").toString());
		return cs.bulkAddToCart(carts,Integer.valueOf(claims.get(USER_ID).toString()));
	}
	
	@PostMapping("/updateinbulk")
	public @ResponseBody List<Cart> bulkUpdateCart(@RequestHeader(value="Authorization") String auth, @RequestBody List<Cart> carts) {
		Claims claims = te.extractInfo(auth);
//		User user = us.findUserByUserName(auth.getName());
		return cs.bulkUpdateCart(carts,Integer.valueOf(claims.get(USER_ID).toString()));
	}
	
	@GetMapping
	public @ResponseBody List<Cart> getAll(@RequestHeader(value="Authorization") String auth) {
		Claims claims = te.extractInfo(auth);
		//System.out.println("claims = "+claims);
//		User user = us.findUserByUserName(claims.get("sub").toString());
		return cs.getAll(Integer.valueOf(claims.get(USER_ID).toString()));
	}
	
	@GetMapping("/delete/{id}")
	public @ResponseBody List<Cart> deleteCart(@RequestHeader(value="Authorization") String auth, @PathVariable("id") int id) {
		Claims claims = te.extractInfo(auth);
		return cs.deleteCart(id,Integer.valueOf(claims.get(USER_ID).toString()));
	}
	
	@DeleteMapping("/wishlist/delete/{id}")
	public @ResponseBody List<Cart> deleteWishlist(@RequestHeader(value="Authorization") String auth, @PathVariable("id") int id) {
		Claims claims = te.extractInfo(auth);
		return cs.deleteWishlist(id,Integer.valueOf(claims.get(USER_ID).toString()));
	}
	
}
