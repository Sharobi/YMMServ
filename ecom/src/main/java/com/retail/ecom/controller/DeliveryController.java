package com.retail.ecom.controller;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.retail.ecom.config.TokenExtractor;
import com.retail.ecom.model.Address;
import com.retail.ecom.model.DeliveryAgent;
import com.retail.ecom.model.DeliveryAgentTracking;
import com.retail.ecom.model.DeliveryPin;
import com.retail.ecom.model.Order;
import com.retail.ecom.model.OrderDetails;
import com.retail.ecom.model.User;
import com.retail.ecom.service.DeliveryPinService;
import com.retail.ecom.service.DeliveryService;
import com.retail.ecom.service.MailService;
import com.retail.ecom.utils.Constants;
import com.retail.ecom.utils.ResponseDetails;

import io.jsonwebtoken.Claims;


@Controller
@RequestMapping(value = "/delivery")
public class DeliveryController {
	@Autowired
	private DeliveryService deliveryService;

	@Autowired
	MailService ms;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	TokenExtractor te;

	@Autowired
	private DeliveryPinService deliveryPinService;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody DeliveryAgent user, HttpServletRequest request) { // System.err.println("Hit");
		// System.out.println("IP address : "+request.getRemoteAddr()); User chku =
		DeliveryAgent chku = deliveryService.findUserByUserName(user.getUserName());
		if (chku != null) {
			// String password = chku.getPassword();
			int isActive = chku.getIsActive();
			if (isActive != 0) {
				if (bCryptPasswordEncoder.matches(user.getPassword(), chku.getPassword())) {
					TokenExtractor te = new TokenExtractor();
					HttpHeaders headers = new HttpHeaders();

					if (!chku.getRoleArr().contains("," + user.getLoginType() + ",")) {
						return new ResponseEntity<ResponseDetails>(new ResponseDetails(new Date(),
								"Sorry you do not have the authority requested.", null, 0), HttpStatus.FORBIDDEN);
					}
					headers.add(Constants.HEADER_STRING,
							te.getJWT(user.getUserName(), chku.getId(),
									chku.getRoleArr().substring(1, chku.getRoleArr().length() - 1), chku.getCompanyId(),
									chku.getStoreId(), chku.getCountry(), chku.getState())); // te.getJWT(user.getUserName(),
																								// auth);
					return new ResponseEntity<ResponseDetails>(
							new ResponseDetails(new Date(), "Login successful", null, 1), headers, HttpStatus.OK);

				} else {
					return new ResponseEntity<ResponseDetails>(
							new ResponseDetails(new Date(), "Sorry you provided a wrong password.", null, 0),
							HttpStatus.FORBIDDEN);
				}
			} else {
				return new ResponseEntity<ResponseDetails>(
						new ResponseDetails(new Date(), "Sorry You are not Activated .", null, 0),
						HttpStatus.FORBIDDEN);

			}
		} else {
			return new ResponseEntity<ResponseDetails>(
					new ResponseDetails(new Date(), "Sorry You are not registered.", null, 0), HttpStatus.FORBIDDEN);
		}

	}

	@PostMapping("/register")
	public @ResponseBody ResponseDetails register(@RequestHeader(value = "Authorization") String auth,
			@RequestBody DeliveryAgent user) {
		// System.err.println("User = "+user);
		if (deliveryService.findUserByUserName(user.getUserName()) != null) {
			// System.err.println("hit "+user.getEmail());
			return new ResponseDetails(new Date(), "Sorry Delivery Person already Registered.", null, 0);
		}

		Claims claims = te.extractInfo(auth);
		List<String> authorities = Arrays.asList(claims.get(Constants.AUTHORITIES_KEY).toString().split(","));
		if (Integer.valueOf(claims.get(Constants.COMPANY_ID).toString()) != 0
				&& Integer.valueOf(claims.get(Constants.STORE_ID).toString()) != 0) {

			user.setCompanyId(Integer.valueOf(claims.get(Constants.COMPANY_ID).toString()));
			user.setStoreId(Integer.valueOf(claims.get(Constants.STORE_ID).toString()));

		}
		if (authorities.containsAll(Arrays.asList(Constants.ROLE_DELIVERY_AGENT.split(",")))) {
			user.setOrganisationId(Integer.valueOf(claims.get(Constants.USER_ID).toString()));
		}
		deliveryService.saveUser(user);

		return new ResponseDetails(new Date(), "Delivery Person Registered successfully", null, 1.0);
	}

	@GetMapping("/details")
	public @ResponseBody DeliveryAgent getdetailsByEmail(Authentication auth) {
		// Claims claims = te.extractInfo(auth);
		// System.out.println("claims = "+claims);
		// User user = us.findUserByUserName(claims.get("sub").toString());
		DeliveryAgent user = deliveryService.findUserByUserName(auth.getName());

		// user.setId(0);
		user.setPassword("");
		return user;
	}

	@GetMapping("/getDeliveryPersonById")
	public @ResponseBody DeliveryAgent getDeliveryPersonById(@RequestHeader(value = "Authorization") String auth,
			@RequestParam("id") int id) {
		Claims claims = te.extractInfo(auth);
		DeliveryAgent user = deliveryService.getDeliveryPersonById(id);
		// user.setPassword("");
		return user;
	}

	@GetMapping("/getDeliveryPersonList")
	public @ResponseBody List<DeliveryAgent> getDeliveryPersonList(
			@RequestHeader(value = "Authorization") String auth) {

		Claims claims = te.extractInfo(auth);
		List<DeliveryAgent> user;
		List<String> authorities = Arrays.asList(claims.get(Constants.AUTHORITIES_KEY).toString().split(","));
		if (Integer.valueOf(claims.get(Constants.COMPANY_ID).toString()) != 0
				&& Integer.valueOf(claims.get(Constants.STORE_ID).toString()) != 0) {
			// System.err.println("Datas From Company And Store");
			user = deliveryService.getDeliveryAgentListByCompanyIdAndStoreId(
					Integer.valueOf(claims.get(Constants.COMPANY_ID).toString()),
					Integer.valueOf(claims.get(Constants.STORE_ID).toString()));
			return user;
		}

		if (authorities.containsAll(Arrays.asList(Constants.ROLE_DELIVERY_AGENT.split(",")))) {
			// System.err.println("Datas From ROLE_DELIVERY_AGENT");
			return user = deliveryService
					.getDeliveryAgentListByOrganisationId(Integer.valueOf(claims.get(Constants.USER_ID).toString()));

		}
		// System.err.println("Datas From All Agents");
		return user = deliveryService.getDeliveryAgentList();

	}

	@PostMapping("/disableOrEnableDeliveryPerson")
	public @ResponseBody ResponseDetails disableOrEnableDeliveryPerson(
			@RequestHeader(value = "Authorization") String auth, @RequestBody DeliveryAgent deliveryAgent) {
		//// Claims claims = te.extractInfo(auth);
		int id = deliveryAgent.getId();
		int isActive = deliveryAgent.getIsActive();
		deliveryService.disableOrEnableDeliveryPerson(id, isActive);
		DeliveryAgent user = deliveryService.getDeliveryPersonById(id);
		if (user.getIsActive() != 0)
			return new ResponseDetails(new Date(), "Delivery Person Enabled successfully", null, 1.0);
		else
			return new ResponseDetails(new Date(), "Delivery Person Disabled successfully", null, 0.0);

	}

	@GetMapping("/getDeliveryPersonByPinCode")
	public @ResponseBody List<DeliveryAgent> getDeliveryPersonByPinCode(
			@RequestHeader(value = "Authorization") String auth, @RequestParam("pincode") String pincode) {
		List<DeliveryAgent> user;

		Claims claims = te.extractInfo(auth);
		// System.err.println("UID
		// "+Integer.valueOf(claims.get(Constants.USER_ID).toString()));
		user = deliveryService.getDeliveryPersonByPinCode(pincode,
				Integer.valueOf(claims.get(Constants.USER_ID).toString()));
		// user.setPassword("");

		return user;
	}

	@PostMapping("/updateDeliveryPerson")
	public @ResponseBody ResponseDetails updateDeliveryPerson(@RequestHeader(value = "Authorization") String auth,
			@RequestBody DeliveryAgent deliveryAgent) {

		if (deliveryAgent.getId() != null) {
			deliveryPinService.deletePinByDeleveryAgentId(deliveryAgent.getId());
			deliveryService.updateDeliveryPerson(deliveryAgent);
			return new ResponseDetails(new Date(), "Delivery Person updated Sucessfully.", null, 1);
		} else
			return new ResponseDetails(new Date(), "Sorry Delivery Person Not Avalable.", null, 0);
	}

	
	@PostMapping("/assignDeliveryPerson")
	public @ResponseBody ResponseDetails assignDeliveryPerson(@RequestHeader(value = "Authorization") String auth,
			@RequestBody OrderDetails orderDtls) {
		Claims claims = te.extractInfo(auth);
		//OrderDetails ordls = deliveryService.findOrderDetailsById(orderDtls.getId());
		//System.err.println(orderDtls);
		if(Integer.valueOf(claims.get(Constants.USER_ID).toString())!=0)
		{
			DeliveryAgentTracking dat=new DeliveryAgentTracking();
			LocalDate currentdate = LocalDate.now();
			dat.setCompanyId(orderDtls.getCompanyId());
			dat.setConversion(orderDtls.getConversion());
			dat.setCreatedDate(orderDtls.getCreatedDate());
			dat.setDeliveryAgentId(orderDtls.getDeliveryAgentId());
			//dat.setDeliveryDate();
			dat.setDeliveryPickupDate(orderDtls.getPickupDate());
			dat.setDeliveryPickupTime(orderDtls.getPickupTime());
			//dat.setDeliveryScheduleDate();
			dat.setDeliveryType(orderDtls.getDeliveryType());
			dat.setDisc(orderDtls.getDisc());
			dat.setFreeQty(orderDtls.getFreeQty());
			dat.setGrossAmount(orderDtls.getGrossAmount());
			//dat.setId(id);
			dat.setIsCancelled(orderDtls.getIsCancelled());
			dat.setIsReturned(orderDtls.getIsReturned());
			dat.setItemId(orderDtls.getItemId());
			dat.setLooseQty(orderDtls.getLooseQty());
			dat.setNetAmount(orderDtls.getNetAmount());
			dat.setPackQty(orderDtls.getPackQty());
			dat.setSaleOrderDate(currentdate.toString());
			dat.setSaleOrderId(orderDtls.getSaleOrderId());
			dat.setSaleOrderDetailsId(orderDtls.getId());
			dat.setShippingCharge(orderDtls.getShippingCharge());
			dat.setShippingChargePerc(orderDtls.getShippingChargePerc());
			dat.setShippingDetailsId(orderDtls.getShippingDetailsId());
			dat.setShippingId(orderDtls.getShippingId());
			dat.setStoreId(orderDtls.getStoreId());
			dat.setTaxAmount(orderDtls.getTaxAmount());
			dat.setUpdatedDate(orderDtls.getUpdatedDate());	
		//	deliveryService.updateDelveryAgentId(orderDtls.getId(),orderDtls.getDeliveryAgenId());
			deliveryService.assignDeliveryPerson(dat);
			
			
			return new ResponseDetails(new Date(), "Delivery Person assigned Sucessfully.", null, 1);
		}
		else
		
		return new ResponseDetails(new Date(), "You are not authorized user..", null, 0);
	}
}// Controller End
