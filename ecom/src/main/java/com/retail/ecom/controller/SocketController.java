package com.retail.ecom.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import com.retail.ecom.model.Greeting;
import com.retail.ecom.model.HelloMessage;

@Controller
public class SocketController {
	@MessageMapping("/hello")
	@SendTo("/topic/greetings")
	public Greeting greeting(HelloMessage message) throws Exception {
	    Thread.sleep(1000); // simulated delay
	    return new Greeting(HtmlUtils.htmlEscape(message.getName()));
	}
}