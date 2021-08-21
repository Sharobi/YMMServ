package com.retail.ecom.controller;

import java.sql.SQLSyntaxErrorException;
import java.util.Date;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.retail.ecom.exception.InvalidCredentials;
import com.retail.ecom.exception.InvalidOrder;
import com.retail.ecom.exception.InvalidStoreCompany;
import com.retail.ecom.exception.UnAuthorizedRequest;
import com.retail.ecom.utils.ResponseDetails;

import io.jsonwebtoken.ExpiredJwtException;

@EnableWebMvc
@ControllerAdvice
public class ExceptionHandlerController {
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public final ResponseEntity<ResponseDetails> handleUserRegistrationException(DataIntegrityViolationException ex, WebRequest request) {
		String message = ex.getMessage();
		if(message.contains("user_name")) {
			//message.replaceAll("user_name", "Email ID");
			//System.out.println(message);
			message = "Provided Email Id is already registered.";
		} else if(message.contains("phone")) {
			//System.out.println(message);
			message = "Provided Phone is already registered.";
		}
		
	    ResponseDetails errorDetails = new ResponseDetails(new Date(), message,request.getDescription(false));
	    return new ResponseEntity<>(errorDetails, HttpStatus.NOT_ACCEPTABLE);
	  }
	
	@ExceptionHandler(NullPointerException.class)
	public final ResponseEntity<ResponseDetails> handleNullPointerException(Exception ex, WebRequest request) {
		String message = "Please check the data and try again. "+ex.getMessage();
		System.out.println(ex.getMessage());
		System.out.println(ex);
		
	    ResponseDetails errorDetails = new ResponseDetails(new Date(), message,request.getDescription(false));
	    return new ResponseEntity<>(errorDetails, HttpStatus.NOT_ACCEPTABLE);
	  }
	
	@ExceptionHandler(InvalidCredentials.class)
	public final ResponseEntity<ResponseDetails> handleInvalidCredentialsException(Exception ex, WebRequest request) {
		String message = "Please check password and try again.";
		System.out.println(ex.getMessage());
		System.out.println(ex);
		
	    ResponseDetails errorDetails = new ResponseDetails(new Date(), ex.getMessage(),request.getDescription(false));
	    return new ResponseEntity<>(errorDetails, HttpStatus.NOT_ACCEPTABLE);
	  }
	
	@ExceptionHandler(InvalidOrder.class)
	public final ResponseEntity<ResponseDetails> handleInvalidOrderException(InvalidOrder io) {
		//String message = "Please check password and try again.";
		System.out.println(io.getMessage());
		System.out.println(io);
		
	    ResponseDetails errorDetails = new ResponseDetails(new Date(), io.getMessage(),null,0);
	    return new ResponseEntity<>(errorDetails, HttpStatus.NOT_ACCEPTABLE);
	  }
	
	@ExceptionHandler(UnAuthorizedRequest.class)
	public final ResponseEntity<ResponseDetails> handleUnAuthorizedRequestException(UnAuthorizedRequest uar) {
		System.out.println(uar.getMessage());
		System.out.println(uar);
		
	    ResponseDetails errorDetails = new ResponseDetails(new Date(), uar.getMessage(),null,0);
	    return new ResponseEntity<>(errorDetails, HttpStatus.FORBIDDEN);
	  }
	
	@ExceptionHandler(InvalidStoreCompany.class)
	public final ResponseEntity<ResponseDetails> handleInvalidStoreCompanyException(InvalidStoreCompany isc) {
		//String message = "Please check password and try again.";
		System.out.println(isc.getMessage());
		System.out.println(isc);
		
		ResponseDetails errorDetails = new ResponseDetails(new Date(), isc.getMessage(),null,0);
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_ACCEPTABLE);
	}
	
	@ExceptionHandler(SQLSyntaxErrorException.class)
	public final ResponseEntity<ResponseDetails> handleSQLSyntaxErrorException(Exception ex, WebRequest request) {
		String message = "Please check data sent and try again.";
		System.out.println(ex.getMessage());
		System.out.println(ex);
		
	    ResponseDetails errorDetails = new ResponseDetails(new Date(), ex.getMessage(),request.getDescription(false));
	    return new ResponseEntity<>(errorDetails, HttpStatus.NOT_ACCEPTABLE);
	  }
	
	@ExceptionHandler(ExpiredJwtException.class)
	public ResponseEntity<ResponseDetails> handleExpiredJwtException(Exception ex, WebRequest request) {
		String message = "Your session in timed out. Please login again.";
		System.out.println(ex.getMessage());
		System.out.println(ex);
		
	    ResponseDetails errorDetails = new ResponseDetails(new Date(), ex.getMessage(),request.getDescription(false));
	    return new ResponseEntity<>(errorDetails, HttpStatus.NOT_ACCEPTABLE);
	  }
	
	@ExceptionHandler(java.util.NoSuchElementException.class)
	public ResponseEntity<ResponseDetails> handleNoElementFoundException(Exception ex, WebRequest request) {
		String message = "Data you are looking for is not present";
		System.out.println(ex.getMessage());
		System.out.println(ex);
		
	    ResponseDetails errorDetails = new ResponseDetails(new Date(), message,request.getDescription(false));
	    return new ResponseEntity<>(errorDetails, HttpStatus.NOT_ACCEPTABLE);
	  }
}
