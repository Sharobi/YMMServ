package com.retail.ecom.controller;

import static com.retail.ecom.utils.Constants.USER_ID;

import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.retail.ecom.config.TokenExtractor;
import com.retail.ecom.exception.InvalidCredentials;
import com.retail.ecom.exception.UnAuthorizedRequest;
import com.retail.ecom.model.Address;
import com.retail.ecom.model.PointTracking;
import com.retail.ecom.model.User;
import com.retail.ecom.service.MailService;
import com.retail.ecom.service.PointTrackingService;
import com.retail.ecom.service.StoreService;
import com.retail.ecom.service.UserService;
import com.retail.ecom.utils.Constants;
import com.retail.ecom.utils.ResponseDetails;

import io.jsonwebtoken.Claims;

@Controller
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	UserService us;
	
	@Autowired
	TokenExtractor te;
	
	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	PointTrackingService pts;
	
	@Autowired
	MailService ms;

	@Value(value = "${store.location.portNo}")
	Integer portNo;
	
	@Value(value = "${store.location.ip}")
	String ip;
	
	@Autowired
	StoreService ss;
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody User user, HttpServletRequest request) {
		//System.err.println("Hit");
		//System.out.println("IP address : "+request.getRemoteAddr());
		User chku = us.findUserByUserName(user.getUserName());
		if(chku!=null) {
			String password = chku.getPassword();
			if(bCryptPasswordEncoder.matches(user.getPassword(), chku.getPassword())) {
				TokenExtractor te = new TokenExtractor();
				HttpHeaders headers = new HttpHeaders();
				
				if(!chku.getRoleArr().contains(","+user.getLoginType()+",")) {
					return new ResponseEntity<ResponseDetails>(new ResponseDetails(new Date(), "Sorry you do not have the authority requested.", null,0), HttpStatus.FORBIDDEN);
				}
				headers.add(Constants.HEADER_STRING, te.getJWT(user.getUserName(), chku.getId(), chku.getRoleArr().substring(1, chku.getRoleArr().length()-1),chku.getCompanyId(),chku.getStoreId(),chku.getCountry(),chku.getState()));
//				te.getJWT(user.getUserName(), auth);
				return new ResponseEntity<ResponseDetails>(new ResponseDetails(new Date(), "Login successful", null,1),headers, HttpStatus.OK);
				
			} else {
				return new ResponseEntity<ResponseDetails>(new ResponseDetails(new Date(), "Sorry you provided a wrong password.", null,0), HttpStatus.FORBIDDEN);
			}
		} else {
			return new ResponseEntity<ResponseDetails>(new ResponseDetails(new Date(), "Sorry You are not registered.", null,0), HttpStatus.FORBIDDEN);
		}
	}
	
	@PostMapping
	public @ResponseBody ResponseDetails register(@RequestBody User user) {
		//System.err.println("User = "+user);
		if(us.findUserByUserName(user.getUserName())!=null) {
			//System.err.println("hit "+user.getEmail());	
			// Email message
			
			SimpleMailMessage sendRegEmail = new SimpleMailMessage();
			sendRegEmail.setFrom("YewMedMart");//Optional......
			sendRegEmail.setTo(user.getEmail());///user.getEmail();
			sendRegEmail.setSubject("YewMedMart");
			sendRegEmail.setText("You are already registared at YewMedMart,\n Plese Login or click on Forget Password.");
			ms.sendEmail(sendRegEmail);
			//return new ResponseDetails(new Date(), "Sorry User already Registered. Please Check your email", null, 0);
			
			// Add success message to view
			//return new ResponseEntity<ResponseDetails>(new ResponseDetails(new Date(), "Login successful", null,1),null, HttpStatus.OK);
			//return new ResponseEntity<ResponseDetails>(new ResponseDetails(new Date(), "Email id is Avalable '"+user.getEmail()+"'", null), HttpStatus.OK);

			//return new ResponseDetails(new Date(), "Sorry User already Registered.", null);
			return new ResponseDetails(new Date(), "Sorry User already Registered.", null, 0);
		}
		
		if(user.getAddresses()!=null) {
			Set<Address> add = user.getAddresses();
			for (Iterator iterator = add.iterator(); iterator.hasNext();) {
				Address address = (Address) iterator.next();
				address.setIsDefault(1);
				break;
			}
		}
		/*
		 * if(us.findUserByUserName(user.getEmail())!=null) { }
		 */
		
		us.saveUser(user);
		
		return new ResponseDetails(new Date(), "User Registered successfully", null);
	}
	
	@GetMapping("/details")
	public @ResponseBody User getdetailsByEmail(Authentication auth) {
		//Claims claims = te.extractInfo(auth);
		//System.out.println("claims = "+claims);
		//User user = us.findUserByUserName(claims.get("sub").toString());
		User user = us.findUserByUserName(auth.getName());
		if(pts.getPointsByUser(user.getId())==null)
			user.setPoints(0D);
		else 
			user.setPoints(pts.getPointsByUser(user.getId()));
		user.setId(0);
		user.setPassword("");
		return user;
	}
	
	@GetMapping("/checkEmail")
	public @ResponseBody ResponseDetails checkEmail(@RequestParam("email") String email) {
		User user = us.findUserByUserName(email);
		//System.out.println("users = "+user+" email = "+email);
		if(user == null) {
			return new ResponseDetails(new Date(), "Email Address available.", null,1);
		} else {
			return new ResponseDetails(new Date(), "Email Address already registered.", null,0);
		} 
	}
	
	@PostMapping("/updateName")
	public @ResponseBody User updateName(@RequestBody User u,Authentication auth) {
		//Claims claims = te.extractInfo(auth);
		//System.out.println("claims = "+claims);
		//User user = us.findUserByUserName(claims.get("sub").toString());
		User user = us.findUserByUserName(auth.getName());
		user.setFname(u.getFname());
		user.setLname(u.getLname());
		
		us.updateUser(user);
		
		user.setId(0);
		user.setPassword("");
		return user;
	}
	@PostMapping("/updateEmail")
	public @ResponseBody User updateEmail(@RequestBody User u,Authentication auth) {
		//Claims claims = te.extractInfo(auth);
		//System.out.println("claims = "+claims);
		//User user = us.findUserByUserName(claims.get("sub").toString());
		User user = us.findUserByUserName(auth.getName());
		user.setEmail(u.getEmail());
		
		us.updateUser(user);
		
		user.setId(0);
		user.setPassword("");
		return user;
	}
	@PostMapping("/updatePassword")
	public @ResponseBody User updatePassword(@RequestBody User u,Authentication auth) {
		//Claims claims = te.extractInfo(auth);
		//System.out.println("claims = "+claims);
		//User user = us.findUserByUserName(claims.get("sub").toString());
		
		User user = us.findUserByUserName(auth.getName());
		//System.out.println("match password = "+bCryptPasswordEncoder.matches(u.getPassword(), user.getPassword()));
		//System.out.println("match password = "+u.getPassword() +" old = "+ user.getPassword());
		
		if(!bCryptPasswordEncoder.matches(u.getPassword(), user.getPassword())) {
			//System.out.println("Not a match");
			throw new InvalidCredentials("Sorry Password does not match.");
		}
		
		user.setPassword(bCryptPasswordEncoder.encode(u.getNewPassword()));
		
		us.updateUser(user);
		
		user.setId(0);
		user.setPassword("");
		return user;
	}
	
	@PostMapping("/updatePhone")
	public @ResponseBody User updatePhone(@RequestBody User u,Authentication auth) {
		User user = us.findUserByUserName(auth.getName());
		
		if(!bCryptPasswordEncoder.matches(u.getPassword(), user.getPassword())) {
			//System.out.println("Not a match");
			throw new InvalidCredentials("Sorry Password does not match.");
		}
		
		user.setPhone(u.getPhone());
		
		us.updateUser(user);
		
		user.setId(0);
		user.setPassword("");
		return user;
	}
	
	@GetMapping("/getAllPincodesUserId")
	public @ResponseBody List<Integer> getAllPincodesForAgentAdmin(
			@RequestHeader(value = "Authorization") String auth) {
		int mapType=0;
		Claims claims = te.extractInfo(auth);
		if (auth == null) {
			throw new UnAuthorizedRequest("You are not authorized to view Contacts");
		}
		return ss.getAllPincodesForAgentAdmin(Integer.valueOf(claims.get(USER_ID).toString()),mapType);
	}
}
