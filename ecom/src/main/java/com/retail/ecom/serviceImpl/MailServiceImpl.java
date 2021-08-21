package com.retail.ecom.serviceImpl;

import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.retail.ecom.service.MailService;

@Service
public class MailServiceImpl implements MailService {
	
	@Autowired
    private JavaMailSender sender;
	
	@Autowired
	TemplateEngine te;

	@Autowired
	private JavaMailSender mailSender;

	@Override
	public String sendSimpleMail() {
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        //System.out.println("sharobi.test");
        try {
            helper.setTo("sharobi.test@gmail.com");
            helper.setText("Greetings :)");
            helper.setSubject("Mail From Spring Boot 1");
            sender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
            return "Error while sending mail ..";
        }
        
        return "Mail Sent Success!";
    }
	
	@Override
	public String sendHtmlMail() {
		Context context = new Context();
		context.setVariable("message", "testing html mail sending");
		String htmlbody = te.process("htmlMail", context);
		
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            helper.setTo("jishnu.rohon@gmail.com");
            helper.setText(htmlbody,true);
            helper.setSubject("Mail From Spring Boot 1");
            sender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
            return "Error while sending mail ..";
        }
        
        return "Mail Sent Success!";
    }
	
	@Async
	public void sendEmail(SimpleMailMessage email) {
		mailSender.send(email);
	}


}
