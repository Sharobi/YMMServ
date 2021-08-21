package com.retail.ecom.aspect;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;

import com.retail.ecom.model.ActivityTracking;
import com.retail.ecom.model.Order;
import com.retail.ecom.model.User;
import com.retail.ecom.repository.ActivityTrackingRepository;
import com.retail.ecom.service.UserService;
import com.retail.ecom.utils.Constants;

@Aspect
public class LoginLogger {
	
	@Autowired
	ActivityTrackingRepository atr;
	
	@Autowired
	UserService us;
	
	@Pointcut("execution (* com.retail.ecom.controller.UserController.login(..)) && args(user,request,..)")
	public void loginPoint(User user,HttpServletRequest request) {}
	
	/*@Pointcut("execution (* com.retail.ecom.controller.OrderController.placeOrder(..)) && args(order,auth,req,..)")
	public void placeOrderPoint(Order order, Authentication auth,HttpServletRequest req) {}*/
	
	/*@Before("loginPoint(user,request)")
	public void beforelogin(User user,HttpServletRequest request) {
		System.out.println("before login Called");
	}
	
	@After("loginPoint(user,request)")
	public void afterlogin(User user,HttpServletRequest request) {
		System.out.println("after login Called");
	}*/
	
	@AfterReturning(pointcut = "loginPoint(user,request)", returning = "retVal")
	public void afterLoginAdvice(Object retVal,User user,HttpServletRequest request){
		ResponseEntity re = (ResponseEntity) retVal;
		User u = us.findUserByUserName(user.getUserName());
		//System.out.println("Returning:" + re.getStatusCodeValue() + " ip = "+request.getRemoteAddr());// + " args = "+ pjp.getArgs());// + " ip = "+request.getRemoteAddr());
		int status=0;
		if(re.getStatusCode()==HttpStatus.OK) {
			status=1;
		}
		if(u!=null) {
			user.setId(u.getId());
		}
		ActivityTracking at = new ActivityTracking(user.getId(),user.getUserName(),Constants.ACTIVITY_LOGIN,request.getRemoteAddr(),status);
		atr.save(at);
	}
	
	/*@Around("execution (* com.retail.ecom.controller.UserController.login(..))")
	public void aroundAdvice(ProceedingJoinPoint pjp){
		System.out.println("Arguments: " + pjp.getArgs());// + " args = "+ pjp.getArgs());// + " ip = "+request.getRemoteAddr());
		try {
            pjp.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
	}*/
}
