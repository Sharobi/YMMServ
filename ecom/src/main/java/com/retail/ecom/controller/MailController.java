package com.retail.ecom.controller;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.retail.ecom.model.User;
import com.retail.ecom.service.MailService;
import com.retail.ecom.service.UserService;
import com.retail.ecom.utils.ResponseDetails;

@Controller
@RequestMapping(value="/mail")
public class MailController {
	
	@Autowired
	MailService ms;
	

	@Autowired
	private UserService userService;

	//@Autowired
	//private EmailService emailService;

	@Value(value = "${store.location.portNo}")
	Integer portNo;
	
	@Value(value = "${store.location.ip}")
	String ip;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	@GetMapping("/simplemail")
	public @ResponseBody String sendSimpleMail() {
		return ms.sendSimpleMail();
	}
	
	@GetMapping("/htmlmail")
	public @ResponseBody String sendHtmlMail() {
		return ms.sendHtmlMail();
	}
	
	
	



	@PostMapping("/forgot")
	public ResponseEntity<?> processForgotPasswordForm(@RequestBody User user, HttpServletRequest request) {
		User chku = userService.findUserByEmail(user.getEmail());
		if(chku!=null) {
				User user1 = chku;
				user1.setResetToken(UUID.randomUUID().toString());

				// Save token to database
				userService.saveUser(user1);

				//String appUrl = request.getScheme() + "://" + request.getServerName();
				//String appUrl = request.getScheme() + "://" + "192.168.1.123";
				//String appUrl = request.getScheme() + "://" + "192.168.1.123:8080";
				String appUrl = request.getScheme() + "://" + ip;   ///for web Server
			//	System.out.println("appUrl: "+appUrl);
				// Email message
				SimpleMailMessage passwordResetEmail = new SimpleMailMessage();
				passwordResetEmail.setFrom("mdmubarakhussain0786@gmail.com");//Optional......
				passwordResetEmail.setTo(user1.getEmail());
				passwordResetEmail.setSubject("Password Reset Request");
				// For localhost only
				//passwordResetEmail.setText("To reset your password, click the link below:\n" + appUrl
					//	+ ":"+portNo+"/yewmed/Forgotpassword?token=" + user1.getResetToken());
				//For Server 
				passwordResetEmail.setText("To reset your password, click the link below:\n" + appUrl
						+"/Forgotpassword?token=" + user1.getResetToken());
				
				//System.out.println("appUrl: "+appUrl+":"+portNo+"/yewmed/Forgotpassword?token=" + user1.getResetToken());
				//System.out.println("appUrl: "+appUrl+":8087/mail/reset?token=" + user1.getResetToken());//Server url and port
				ms.sendEmail(passwordResetEmail);

				// Add success message to view
				//return new ResponseEntity<ResponseDetails>(new ResponseDetails(new Date(), "Login successful", null,1),null, HttpStatus.OK);
				return new ResponseEntity<ResponseDetails>(new ResponseDetails(new Date(), "A password reset link has been sent to '"+user.getEmail()+"'", null,1), HttpStatus.OK);
				
		} else {
			return new ResponseEntity<ResponseDetails>(new ResponseDetails(new Date(), "Sorry You are not registered.", null,0), HttpStatus.FORBIDDEN);
		}
	}
	// Display form to reset password
	@RequestMapping(value = "/reset", method = RequestMethod.GET)
	public ResponseEntity<?> displayResetPasswordPage(@RequestParam("token") String token) {
       //  System.out.println("token: "+token);
		User chkToken = userService.findUserByResetToken(token);
		//Optional<User> user = userService.findUserByResetToken(token);
		if(chkToken!=null) {// Token found in DB
			User user = chkToken;
			
			return new ResponseEntity<ResponseDetails>(new ResponseDetails(new Date(), token, null,1), HttpStatus.OK);
			
		} else {// Token not found in DB
			return new ResponseEntity<ResponseDetails>(new ResponseDetails(new Date(), "This is an invalid password reset link.", null,0), HttpStatus.FORBIDDEN);
		}
		
	}

	// Process reset password form
	@RequestMapping(value = "/reset", method = RequestMethod.POST)
//	public ResponseEntity<?> setNewPassword(@RequestParam Map<String, String> requestParams) {
		public ResponseEntity<?> setNewPassword(@RequestBody User requestParams) {
		// Find the user associated with the reset token
		//User chkToken = userService.findUserByResetToken(requestParams.get("token"));
		User chkToken = userService.findUserByResetToken(requestParams.getResetToken());
		//Optional<User> user = userService.findUserByResetToken(requestParams.get("token"));
		// This should always be non-null but we check just in case
		/*if (user.isPresent()) {
			User resetUser = user.get(); */
			if (chkToken!=null) {
				User resetUser = chkToken;
			// Set new password
	        //resetUser.setPassword(bCryptPasswordEncoder.encode(requestParams.get("password")));    
	        //resetUser.setPassword(bCryptPasswordEncoder.encode(requestParams.getResetToken()));    
            resetUser.setPassword(requestParams.getPassword());
			// Set the reset token to null so it cannot be used again
			resetUser.setResetToken(null);
			// Save user
			userService.saveUser(resetUser);
			// In order to set a model attribute on a redirect, we must use
			// RedirectAttributes
			return new ResponseEntity<ResponseDetails>(new ResponseDetails(new Date(), "You have successfully reset your password.", null,1), HttpStatus.OK);
			
		} else {
			return new ResponseEntity<ResponseDetails>(new ResponseDetails(new Date(), "This is an invalid password reset link.", null,0), HttpStatus.FORBIDDEN);
			}
   }

}
