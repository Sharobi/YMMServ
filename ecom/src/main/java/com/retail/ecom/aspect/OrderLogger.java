package com.retail.ecom.aspect;

import static com.retail.ecom.utils.Constants.USER_ID;

import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestHeader;

import com.retail.ecom.config.TokenExtractor;
import com.retail.ecom.model.ActivityTracking;
import com.retail.ecom.model.Offer;
import com.retail.ecom.model.Order;
import com.retail.ecom.model.Point;
import com.retail.ecom.model.PointTracking;
import com.retail.ecom.model.User;
import com.retail.ecom.repository.ActivityTrackingRepository;
import com.retail.ecom.service.OfferService;
import com.retail.ecom.service.PointTrackingService;
import com.retail.ecom.service.UserService;
import com.retail.ecom.utils.Constants;
import com.retail.ecom.utils.ResponseDetails;

import io.jsonwebtoken.Claims;

@Aspect
public class OrderLogger {
	@Autowired
	ActivityTrackingRepository atr;
	
//	@Autowired
//	UserService us;
	
	@Autowired
	OfferService os;
	
	@Autowired
	PointTrackingService pts;
	
	@Autowired
	TokenExtractor te;
	
	@Pointcut("execution (* com.retail.ecom.controller.OrderController.placeOrder(..)) && args(order,auth,req,..)")
	public void placeOrderPoint(Order order,@RequestHeader(value="Authorization") String auth,HttpServletRequest req) {}
	
	@AfterReturning(pointcut = "placeOrderPoint(order,auth,req)", returning = "retVal")
	public void afterplaceOrderAdvice(Object retVal,Order order, @RequestHeader(value="Authorization") String auth,HttpServletRequest req){
		ResponseDetails rd = (ResponseDetails) retVal;
		Claims claims = te.extractInfo(auth);
//		User u = us.findUserByUserName(auth.getName());
//		ActivityTracking at = new ActivityTracking(Integer.valueOf(claims.get(USER_ID).toString()),claims.get("sub").toString(),Constants.ACTIVITY_PURCHASE,req.getRemoteAddr(),rd.getStatus().intValue()>0?1:0);
		ActivityTracking at = new ActivityTracking(Integer.valueOf(claims.get(USER_ID).toString()),claims.get("sub").toString(),Constants.ACTIVITY_PURCHASE,req.getRemoteAddr(),rd.getStatus().intValue());
		atr.save(at);
		
		//List<Offer> offers = os.getOffersByActivity(Constants.ACTIVITY_PURCHASE);
		Offer offer = os.getById(Constants.OFFER_PURCHASE);
		Set<Point> points = offer.getPoints();
		for (Iterator itr = points.iterator(); itr.hasNext();) {
			Point point = (Point) itr.next();
			if(order.getGrossAmount()>=point.getMinAmount() && order.getGrossAmount()<=point.getMaxAmount()) {
				PointTracking pt = new PointTracking();
				pt.setActivityId(Constants.ACTIVITY_PURCHASE);
				pt.setOrderId(rd.getStatus().intValue());
				pt.setOfferId(point.getOfferId());
				pt.setPointId(point.getId());
				pt.setPointAmount((double)point.getPoints());
				pt.setPointValue((double)point.getPoints());
				pt.setFactor(point.getFactor());
				pt.setUid(Integer.valueOf(claims.get(USER_ID).toString()));
				
				pts.save(pt);
				break;
			}
		}
	}
}