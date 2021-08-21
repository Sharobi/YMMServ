package com.retail.ecom.config;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.retail.ecom.model.User;
import com.retail.ecom.service.UserService;


public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {
	
	private UserService us;
	
	public JWTLoginFilter(String url, AuthenticationManager authManager,UserService us) {
	    super(new AntPathRequestMatcher(url));
	    setAuthenticationManager(authManager);
	    this.us = us;
	  }
	
	/*public JWTLoginFilter(UserService us,String url, AuthenticationManager authManager) {
	    super(new AntPathRequestMatcher(url));
	    setAuthenticationManager(authManager);
	    this.us = us;
	}*/

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
			throws AuthenticationException, IOException, ServletException {
		User user = new ObjectMapper()
		        .readValue(req.getInputStream(), User.class);
		//System.out.println("attemptAuthentication USer id = "+user.getId());
		return getAuthenticationManager().authenticate(
		        new UsernamePasswordAuthenticationToken(
		            user.getUserName(),
		            user.getPassword(),
		            Collections.emptyList()
		        )
		    );
	}
	
	@Override
	  protected void successfulAuthentication(
	      HttpServletRequest req,
	      HttpServletResponse res, FilterChain chain,
	      Authentication auth) throws IOException, ServletException {
		/*WebApplicationContext webApplicationContext =
	            WebApplicationContextUtils.getWebApplicationContext(getServletContext());
	        //reference to bean from app context
		UserService us = webApplicationContext.getBean(UserService.class);
	    System.out.println("user id = "+us.findUserByEmail(auth.getName()).getId());*/
//		System.out.println("userid = "+us.findUserByEmail(auth.getName()));
//		System.out.println("userid = "+us.getAll());
	    TokenAuthenticationService
	        .addAuthentication(res, auth.getName(),auth);
	  }
		
}
