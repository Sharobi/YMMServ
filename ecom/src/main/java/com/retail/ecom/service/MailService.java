package com.retail.ecom.service;

import java.util.Map;

import org.springframework.mail.SimpleMailMessage;

public interface MailService {

	public String sendSimpleMail();

	public String sendHtmlMail();

	public void sendEmail(SimpleMailMessage email);

	
}
