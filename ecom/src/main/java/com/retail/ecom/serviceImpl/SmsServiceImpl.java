package com.retail.ecom.serviceImpl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.retail.ecom.service.SmsService;
import com.retail.ecom.utils.OtpConstant;


@Service
public class SmsServiceImpl implements SmsService {

	@Override
	public String sendSms(String mobile,String text) {
		
		try
		{
		// TODO Auto-generated method stub
		String apiKey = "apikey=" + OtpConstant.APIKEY;
		String message = "&message=" + text;//
		String sender = "&sender=" + OtpConstant.SENDER;
		String numbers = "&numbers=" + mobile;
		//System.err.println("numbers " + numbers);
		// Send data
		HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/send/?")
				.openConnection();
		String data = apiKey + numbers + message + sender;
		conn.setDoOutput(true);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
		conn.getOutputStream().write(data.getBytes("UTF-8"));
		final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		final StringBuffer stringBuffer = new StringBuffer();
		String line;
		while ((line = rd.readLine()) != null) {
			stringBuffer.append(line);
		}
		rd.close();
		System.err.println("Resp from Text Local => "+stringBuffer.toString());
		return stringBuffer.toString();
		}
		catch(Exception e)
		{
			System.err.println("Error SMS " + e);
			return "Not Sent";
		}
		
	}
	

}
