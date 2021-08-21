package com.retail.ecom.controller;

import java.util.Date;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.retail.ecom.model.User;
import com.retail.ecom.service.MailService;
import com.retail.ecom.service.OtpService;
import com.retail.ecom.service.SmsService;
import com.retail.ecom.utils.ResponseDetails;


@Controller
@RequestMapping(value = "/otp")
public class OtpController {

	@Autowired
	public OtpService otpService;

	@Autowired
	public MailService myEmailService;
	
	@Autowired
	public SmsService smsService;

	@PostMapping("/generateOtp") // Added by Sayantan Mahanty 19/10/20202
	public @ResponseBody ResponseDetails generateOtp(@RequestBody User user) {
		//String email = user.getEmail();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		int otp = otpService.generateOTP(username);
		if (user.getEmail() != null && user.getEmail().length()>0) {
			// System.err.println(email);
			SimpleMailMessage sendRegEmail = new SimpleMailMessage();
			sendRegEmail.setFrom("YewMedMart");
			sendRegEmail.setTo(user.getEmail());
			sendRegEmail.setSubject("Your one time password for email verification in Yewmedmart");
			sendRegEmail.setText("OTP is " + otp);
			myEmailService.sendEmail(sendRegEmail);
			return new ResponseDetails(new Date(), "Email Sent ", null, 1);
		}
		if (user.getPhone() != null && user.getPhone().length()>0) {
			String text= otp + " is the OTP for your ywemedmart.in registration ";
			String smsRes=smsService.sendSms(user.getPhone(), text);
			System.err.println("Resp from Sms Controller => "+smsRes);
			JSONObject obj=new JSONObject(smsRes);
			if("success".equals(obj.getString("status")))
				{
				return new ResponseDetails(new Date(), "Sms Sent ", null, 1);
				}
			else
			{
				return new ResponseDetails(new Date(), " Sms not Sent ", null, 0);
			}
			
		}
		return new ResponseDetails(new Date(), "Please Enter Email or phone number", null, 0);

	}

	@RequestMapping(value = "/validateOtp", method = RequestMethod.GET) // Added by Sayantan Mahanty 19/10/20202
	public @ResponseBody ResponseDetails validateOtp(@RequestParam("otpnum") int otpnum) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		if (otpnum >= 0) {
			int serverOtp = otpService.getOtp(username);

			if (serverOtp > 0) {
				if (otpnum == serverOtp) {
					otpService.clearOTP(username);
					return new ResponseDetails(new Date(), "Valid Otp", null, 1);
				} else {
					return new ResponseDetails(new Date(), "Please Enter Valid Otp", null, 0);
				}
			} else {
				return new ResponseDetails(new Date(), "Please Enter Valid Otp", null, 0);
			}
		} else {
			return new ResponseDetails(new Date(), "Please Enter Valid Otp", null, 0);
		}
	}

	//// Generate Otp by mobile number
	/*
	 * @PostMapping("/sendSms") // Added by Sayantan Mahanty 30/10/20202
	 * public @ResponseBody ResponseDetails sendSms(@RequestBody Otp otpObj) {
	 * 
	 * try { // Construct data // String apiKey = "apikey=" +
	 * "yaut9xrJGUo-W2uFciLJStKbGQGe7Te0MmUg51xJU4"; // String sender = "&sender=" +
	 * "TXTLCL"; // String numbers = "&numbers=" + "9830293661"; Authentication auth
	 * = SecurityContextHolder.getContext().getAuthentication(); String username =
	 * auth.getName(); int otp = otpService.generateOTP(username);
	 * System.err.println("otp "+otp);
	 * 
	 * String apiKey = "apikey=" + OtpConstant.APIKEY; String message = "&message="
	 * + otp + " is the OTP for your ywemedmart.in registration "; String sender =
	 * "&sender=" + OtpConstant.SENDER; String numbers = "&numbers=" +
	 * otpObj.getMobile(); System.err.println("numbers "+numbers); // Send data
	 * HttpURLConnection conn = (HttpURLConnection) new
	 * URL("https://api.textlocal.in/send/?").openConnection(); String data = apiKey
	 * + numbers + message + sender; conn.setDoOutput(true);
	 * conn.setRequestMethod("POST"); conn.setRequestProperty("Content-Length",
	 * Integer.toString(data.length()));
	 * conn.getOutputStream().write(data.getBytes("UTF-8")); final BufferedReader rd
	 * = new BufferedReader(new InputStreamReader(conn.getInputStream())); final
	 * StringBuffer stringBuffer = new StringBuffer(); String line; while ((line =
	 * rd.readLine()) != null) { stringBuffer.append(line); } rd.close();
	 * 
	 * // return stringBuffer.toString(); return new ResponseDetails(new Date(),
	 * "Message Sent", null, 1); } catch (Exception e) {
	 * System.err.println("Error SMS " + e); return new ResponseDetails(new Date(),
	 * "Error SMS " + e, null, 0); } }
	 * 
	 * 
	 * public static void main(String[] args) {
	 * System.out.println("sms output==="+sendSms()); }
	 */

}
