package com.retail.ecom.controller;

import static com.retail.ecom.utils.Constants.USER_ID;
import static org.hamcrest.CoreMatchers.nullValue;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.internal.SessionFactoryImpl;
import org.hibernate.internal.SessionImpl;
import org.json.JSONObject;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Parsed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.paytm.merchant.models.PaymentDetail;
import com.paytm.pg.merchant.CheckSumServiceHelper;
import com.retail.ecom.config.TokenExtractor;
import com.retail.ecom.exception.UnAuthorizedRequest;
import com.retail.ecom.model.DeliveryAgent;
import com.retail.ecom.model.ItemDetailsDTO;
import com.retail.ecom.model.Order;
import com.retail.ecom.model.OrderDetails;
import com.retail.ecom.model.PaytmDetails;
import com.retail.ecom.model.PaytmResponse;
import com.retail.ecom.model.Role;
import com.retail.ecom.model.Store;
import com.retail.ecom.model.User;
import com.retail.ecom.service.OrderService;
import com.retail.ecom.service.StoreService;
import com.retail.ecom.service.UserService;
import com.retail.ecom.utils.Constants;
import com.retail.ecom.utils.NumWord;
import com.retail.ecom.utils.PaytmConstants;
import com.retail.ecom.utils.ResponseDetails;

import io.jsonwebtoken.Claims;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

@Controller
@RequestMapping("/orders")
public class OrderController {
	// public static final Logger logger =
	// LoggerFactory.getLogger(OrderController.class);
	@Autowired
	OrderService os;

//	@Autowired
//	UserService us;

	@Autowired
	TokenExtractor te;

	@Autowired
	StoreService ss;

	@Value(value = "${store.location.ip}")
	String ip;
	SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@PersistenceContext
	EntityManager em;

	@GetMapping
	public @ResponseBody List<Order> getOrders(@RequestHeader(value = "Authorization") String auth,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {
//		User u = us.findUserByUserName(auth.getName());
		Claims claims = te.extractInfo(auth);
		return os.getAllByUser(Integer.valueOf(claims.get(USER_ID).toString()), page, limit);
	}

	@GetMapping("/getOrderById/{id}")
	public @ResponseBody Order getOrderById(@RequestHeader(value = "Authorization") String auth,
			@PathVariable(value = "id") int saleOrderId) {
//		   User u = us.findUserByUserName(auth.getName());
		// System.out.println("saleOrderId: "+saleOrderId);
		Claims claims = te.extractInfo(auth);
		return os.getOrderById(saleOrderId, Integer.valueOf(claims.get(USER_ID).toString()));
	}

	@GetMapping("/forstore")
	public @ResponseBody List<Order> getOrdersForStore(@RequestHeader(value = "Authorization") String auth,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "limit", required = false, defaultValue = "20") int limit,
			@RequestParam(value = "startDate", required = false, defaultValue = "0000-00-00") String startDate,
			@RequestParam(value = "endDate", required = false, defaultValue = "0000-00-00") String endDate,
			@RequestParam(value = "itemId", required = false, defaultValue = "0") int itemId,
			@RequestParam(value = "status", required = false, defaultValue = "0") int status) {
//		User u = us.findUserByUserName(auth.getName());
		Claims claims = te.extractInfo(auth);
		List<String> authorities = Arrays.asList(claims.get(Constants.AUTHORITIES_KEY).toString().split(","));
//		System.out.println("user role = "+u.getRoles());
		if (authorities.containsAll(Arrays.asList(Constants.ROLE_ADMIN.split(",")))) {
			/// System.err.println("Role Admin");
			return os.getAllForAdmin(page, limit, startDate, endDate, itemId, status);
		}
		if (authorities.containsAll(Arrays.asList(Constants.ROLE_AGENTADMIN.split(",")))) {
			// System.err.println("Role Agent Admin");

			return os.getAllForAgentAdmin(page, limit, startDate, endDate, itemId, status,
					Integer.valueOf(claims.get(USER_ID).toString()));
		}
		/*
		 * for (Iterator iterator = u.getRoles().iterator(); iterator.hasNext();) { Role
		 * role = (Role) iterator.next(); if(role.getRole().equals("ADMIN")) { return
		 * os.getAllForAdmin(page,limit,startDate,endDate,itemId,status); } }
		 */
		// if(u.getCompanyId()!=0 && u.getStoreId()!=0) {
		if (Integer.valueOf(claims.get(Constants.COMPANY_ID).toString()) != 0
				&& Integer.valueOf(claims.get(Constants.STORE_ID).toString()) != 0) {
			/// System.err.println("Elese Or Dub");
//			return os.getAllForStoreAdmin(u.getStoreId(), u.getCompanyId(),page,limit,startDate,endDate,itemId,status);
			return os.getAllForStoreAdmin(Integer.valueOf(claims.get(Constants.STORE_ID).toString()),
					Integer.valueOf(claims.get(Constants.COMPANY_ID).toString()), page, limit, startDate, endDate,
					itemId, status);
		} else {
			return null;
		}
	}

	@GetMapping("/getAllStoreForAmin")
	public @ResponseBody List<Store> getAllStoreForAmin(@RequestHeader(value = "Authorization") String auth) {
		Claims claims = te.extractInfo(auth);
		List<String> authorities = Arrays.asList(claims.get(Constants.AUTHORITIES_KEY).toString().split(","));
		if (auth == null || !authorities.containsAll(Arrays.asList(Constants.ROLE_ADMIN.split(",")))) {
			throw new UnAuthorizedRequest("You are not authorized to view Contacts");
		}
		return ss.getAllStoreByAdmin();
	}

	@GetMapping("/getAllStoreForAgentAdmin")///added map_type 3-2-2021 Sayantan Mahanty
	public @ResponseBody List<Store> getAllStoreForAgentAdmin(@RequestHeader(value = "Authorization") String auth) {
		int mapType=1;
		Claims claims = te.extractInfo(auth);
		List<String> authorities = Arrays.asList(claims.get(Constants.AUTHORITIES_KEY).toString().split(","));
		if (auth == null || !authorities.containsAll(Arrays.asList(Constants.ROLE_AGENTADMIN.split(",")))) {
			throw new UnAuthorizedRequest("You are not authorized to view Contacts");
		}
		return ss.getAllStoreByAgentAdmin(Integer.valueOf(claims.get(USER_ID).toString()),mapType);
	}

	@GetMapping("/getAllPincodesForAgentAdmin")/////added map_type 3-2-2021 Sayantan Mahanty
	public @ResponseBody List<Integer> getAllPincodesForAgentAdmin(
			@RequestHeader(value = "Authorization") String auth) {
		int mapType=1;
		Claims claims = te.extractInfo(auth);
		List<String> authorities = Arrays.asList(claims.get(Constants.AUTHORITIES_KEY).toString().split(","));
		if (auth == null || !authorities.containsAll(Arrays.asList(Constants.ROLE_AGENTADMIN.split(",")))) {
			throw new UnAuthorizedRequest("You are not authorized to view Contacts");
		}
		return ss.getAllPincodesForAgentAdmin(Integer.valueOf(claims.get(USER_ID).toString()),mapType);
	}

	@GetMapping("/getAllStoreForAminByPin/{postcode}")
	public @ResponseBody List<Store> getAllStoreForAminByPin(@RequestHeader(value = "Authorization") String auth,
			@PathVariable String postcode) {
		// System.err.println("postcode service"+postcode);

		Claims claims = te.extractInfo(auth);
		List<String> authorities = Arrays.asList(claims.get(Constants.AUTHORITIES_KEY).toString().split(","));
		if (auth == null || (!authorities.containsAll(Arrays.asList(Constants.ROLE_ADMIN.split(",")))
				&& !authorities.containsAll(Arrays.asList(Constants.ROLE_AGENTADMIN.split(","))))) {
			throw new UnAuthorizedRequest("You are not authorized to view Contacts");
		}
		return ss.getAllStoreForAminByPin(postcode);
	}

	@PostMapping("/assignOrderItemStore")
	public @ResponseBody ResponseDetails assignOrderItemStore(@RequestBody OrderDetails ordDetail,
			@RequestHeader(value = "Authorization") String auth) {
		//System.err.println("Hit");
		Claims claims = te.extractInfo(auth);
		List<String> authorities = Arrays.asList(claims.get(Constants.AUTHORITIES_KEY).toString().split(","));
		if (auth == null || (!authorities.containsAll(Arrays.asList(Constants.ROLE_ADMIN.split(",")))
				&& !authorities.containsAll(Arrays.asList(Constants.ROLE_SUBADMIN.split(",")))
				&& !authorities.containsAll(Arrays.asList(Constants.ROLE_AGENTADMIN.split(","))))) {

			throw new UnAuthorizedRequest("You are not authorized to view Contacts");
		} else {

			int id = ordDetail.getId();
			int storeId = 0;
			int companyId = 0;
			String deliveryType = ordDetail.getDeliveryType();
			// os.assignOrderItemStore(id,storeId,companyId);
			if (ordDetail.getStoreId() > 0) {
				storeId = ordDetail.getStoreId();
			} else {
				storeId = Integer.valueOf(claims.get(Constants.STORE_ID).toString());
			}
			if (ordDetail.getCompanyId() > 0) {
				companyId = ordDetail.getCompanyId();
			} else {
				companyId = Integer.valueOf(claims.get(Constants.COMPANY_ID).toString());
			}
			// System.err.println("storeId " + storeId + "companyId " + companyId);

			return os.assignOrderItemStore(id, storeId, companyId, deliveryType);

		}
	}

	@PostMapping("/saleOrderItemFromStore")
	public @ResponseBody ResponseDetails saleOrderItemFromStore(@RequestBody OrderDetails ordDetail,
			@RequestHeader(value = "Authorization") String auth) {

		Claims claims = te.extractInfo(auth);
		List<String> authorities = Arrays.asList(claims.get(Constants.AUTHORITIES_KEY).toString().split(","));
		if (auth == null || (!authorities.containsAll(Arrays.asList(Constants.ROLE_ADMIN.split(",")))
				&& !authorities.containsAll(Arrays.asList(Constants.ROLE_AGENTADMIN.split(","))))) {
			throw new UnAuthorizedRequest("You are not authorized to view Contacts");
		} else {

			int id = ordDetail.getId();
			String batchNo = ordDetail.getBatchNo();
			String expiryDate = ordDetail.getExpiryDate();
			// return os.saleOrderItemFromStore(id);
			return os.saleOrderItemFromStore(id, batchNo, expiryDate);/// new added 5-8-2020

		}
	}

	@PostMapping
	public @ResponseBody ResponseDetails placeOrder(@RequestBody Order order,
			@RequestHeader(value = "Authorization") String auth, HttpServletRequest req,
			@RequestHeader(name = "Authorization") String authorization) throws Exception {
		// System.out.println("Order controller = "+order);
		// logger.info("Getting Orders {}", order);
		Claims claims = te.extractInfo(auth);
		return os.addOrder(order, Integer.valueOf(claims.get(USER_ID).toString()));
//		return new ResponseDetails(new Date(), "Order received.", null,1);
	}

	@PostMapping("/pgRequest")
	public @ResponseBody ResponseDetails pgRequest(@RequestBody Order order,
			@RequestHeader(value = "Authorization") String auth, HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		// System.out.println("pgRequest "+order);

		Claims claims = te.extractInfo(auth);
		if (order.getId() > 0) {
			TreeMap<String, String> paytmParams = new TreeMap<String, String>();
			paytmParams.put("MID", PaytmConstants.MERCHANT_ID);
			paytmParams.put("WEBSITE", PaytmConstants.WEBSITE);
			paytmParams.put("INDUSTRY_TYPE_ID", PaytmConstants.INDUSTRY_TYPE_ID);
			paytmParams.put("CHANNEL_ID", PaytmConstants.CHANNEL_ID);
			paytmParams.put("ORDER_ID", order.getId().toString());
			paytmParams.put("CUST_ID", claims.get(USER_ID).toString());
			paytmParams.put("MOBILE_NO", order.getAddressShipping().getContactPhone());
			// paytmParams.put("EMAIL", order.getAddress().getInvoiceMail());
			paytmParams.put("TXN_AMOUNT", order.getNetAmount().toString());
			// paytmParams.put("TXN_AMOUNT","5.0");
			paytmParams.put("CALLBACK_URL", PaytmConstants.CALLBACK_URL);
			String checksum = CheckSumServiceHelper.getCheckSumServiceHelper()
					.genrateCheckSum(PaytmConstants.MERCHANT_KEY, paytmParams);
			/* for Staging */
			// String url = "https://securegw-stage.paytm.in/order/process";
			/* for Production */
			String url = PaytmConstants.PAYTM_URL;

			/* Prepare HTML Form and Submit to Paytm */
			StringBuilder outputHtml = new StringBuilder();
			/*
			 * outputHtml.append("<html>"); outputHtml.append("<head>");
			 * outputHtml.append("<title>Merchant Checkout Page</title>");
			 * outputHtml.append("</head>");
			 */
			outputHtml.append("<body>");
			outputHtml.append("<center><h1>Please do not refresh this page...</h1></center>");
			outputHtml.append("<form method='post' action='" + url + "' name='paytm_form'>");

			for (Map.Entry<String, String> entry : paytmParams.entrySet()) {
				outputHtml
						.append("<input type='hidden' name='" + entry.getKey() + "' value='" + entry.getValue() + "'>");
			}

			outputHtml.append("<input type='hidden' name='CHECKSUMHASH' value='" + checksum + "'>");
			outputHtml.append("</form>");
			outputHtml.append("<script type='text/javascript'>");
			outputHtml.append("document.paytm_form.submit();");
			outputHtml.append("</script>");
			outputHtml.append("</body>");
			/* outputHtml.append("</html>"); */
			PrintWriter out = res.getWriter();
			// System.err.println(outputHtml);
			out.print(outputHtml);
			out.flush();

		} else {
			/* return new ResponseDetails(new Date(), "Order Not Saved.", null,1); */
		}
		/// return new ResponseDetails(new Date(), "Payment Success.", null,1);
		return null;

//		return new ResponseDetails(new Date(), "Order received.", null,1);

	}

	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping("/getPgResponse")
	public String getResponseFromPaytm(HttpServletRequest req, Model model) {
		// System.err.println("getPgResponse ");
		String result = "";
		String status = "";
		String appUrl = req.getScheme() + "://" + ip; /// for web Server
		Map<String, String[]> mapData = req.getParameterMap();
		TreeMap<String, String> parameters = new TreeMap<String, String>();
		mapData.forEach((key, val) -> parameters.put(key, val[0]));
		String paytmChecksum = "";
		if (mapData.containsKey("CHECKSUMHASH")) {
			paytmChecksum = mapData.get("CHECKSUMHASH")[0];
		}

		boolean isValidCheckSum = false;
		try {
			isValidCheckSum = validateCheckSum(parameters, paytmChecksum);
			if (isValidCheckSum && parameters.containsKey("RESPCODE")) {
				PaytmDetails pd = new PaytmDetails();
				pd.setBankName(parameters.get("BANKNAME"));
				pd.setBankTxnId(parameters.get("BANKTXNID"));
				pd.setChecksum(parameters.get("CHECKSUMHASH"));
				pd.setGatewayName(parameters.get("GATEWAYNAME"));
				pd.setMerchantId(parameters.get("MID"));
				pd.setMerchantKey(PaytmConstants.MERCHANT_KEY);
				pd.setCurrency(parameters.get("CURRENCY"));
				pd.setPaidAmount(Double.parseDouble(parameters.get("TXNAMOUNT")));
				pd.setPaymentMode(1);
				pd.setPayableAmount(Double.parseDouble(parameters.get("TXNAMOUNT")));
				pd.setCustomerId(0);
				pd.setPaymentType(parameters.get("PAYMENTMODE"));
				pd.setResponseCode(parameters.get("RESPCODE"));
				pd.setResponseMessage(parameters.get("RESPMSG"));
				pd.setSaleOrderId(Integer.parseInt(parameters.get("ORDERID")));
				pd.setSaleOrderDetailsId(0);
				pd.setTxnDate(parameters.get("TXNDATE"));
				pd.setTxnId(parameters.get("TXNID"));
				pd.setTxtStatus(parameters.get("STATUS"));
				pd.setRefundId(0);
				os.savePgDetails(pd);
				if (parameters.get("RESPCODE").equals("01")) {

					result = "Payment Successful";
					status = "1";
					// System.err.println(result+":"+status);
				} else {
					result = "Payment Failed due to " + parameters.get("RESPMSG");
					status = "2";
					// System.err.println(result+":"+status);
				}
			} else {
				result = "Checksum Mismatched";
				status = "3";
				// System.err.println(result+":"+status);
			}
		} catch (Exception e) {
			result = "Some error occurred";
			e.printStackTrace();
		}
		model.addAttribute("result", result);
		// return appUrl+"/yewmed_dup/successfulpayment?status="+status;
		return "paymentResponse";
	}

	/*
	 * @PostMapping("/pgResponseNew") public @ResponseBody ResponseDetails
	 * pgResponseNew(@RequestBody PaytmResponse paytmResponse,
	 * 
	 * @RequestHeader(value = "Authorization") String auth, HttpServletRequest req)
	 * throws Exception {
	 * 
	 * Claims claims = te.extractInfo(auth); String paytmChecksum = "";
	 * System.err.println("pgResponseNew===" + paytmResponse.toString()); if
	 * (paytmResponse.getCHECKSUMHASH() != null) { paytmChecksum =
	 * paytmResponse.getCHECKSUMHASH(); }
	 * 
	 * String result = ""; double status = 0; boolean isValidCheckSum = false; try {
	 * isValidCheckSum = validateCheckSum(paytmResponse, paytmChecksum); if
	 * (isValidCheckSum && paytmResponse.getRESPCODE() != null) { PaytmDetails pd =
	 * new PaytmDetails(); pd.setBankName(paytmResponse.getBANKNAME());
	 * pd.setBankTxnId(paytmResponse.getBANKTXNID());
	 * pd.setChecksum(paytmResponse.getCHECKSUMHASH());
	 * pd.setGatewayName(paytmResponse.getGATEWAYNAME());
	 * pd.setMerchantId(paytmResponse.getMID());
	 * pd.setMerchantKey(PaytmConstants.MERCHANT_KEY);
	 * pd.setCurrency(paytmResponse.getCURRENCY());
	 * pd.setPaidAmount(Double.parseDouble(paytmResponse.getTXNAMOUNT()));
	 * pd.setPaymentMode(1);
	 * pd.setPayableAmount(Double.parseDouble(paytmResponse.getTXNAMOUNT()));
	 * pd.setCustomerId(Integer.parseInt(claims.get(USER_ID).toString()));
	 * pd.setPaymentType(paytmResponse.getPAYMENTMODE());
	 * pd.setResponseCode(paytmResponse.getRESPCODE());
	 * pd.setResponseMessage(paytmResponse.getRESPMSG());
	 * pd.setSaleOrderId(Integer.parseInt(paytmResponse.getORDERID()));
	 * pd.setSaleOrderDetailsId(0);
	 * pd.setTxnDate(paytmResponse.getTXNDATE().toString());
	 * pd.setTxnId(paytmResponse.getTXNID());
	 * pd.setTxtStatus(paytmResponse.getSTATUS()); os.savePgDetails(pd); if
	 * (paytmResponse.getRESPCODE().equals("01")) {
	 * 
	 * result = "Payment Successful"; status = 1; System.err.println(result); } else
	 * { result = "Payment Failed due to " + paytmResponse.getRESPMSG(); status = 2;
	 * System.err.println(result); } } else { result = "Checksum Mismatched"; status
	 * = 3; System.err.println(result); } } catch (Exception e) {
	 * e.printStackTrace(); } return new ResponseDetails(new Date(), result,
	 * paytmResponse.getRESPCODE(), status);
	 * 
	 * }
	 */
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping("/getPgTransactionStatus")
	public @ResponseBody String getTransactionStatus(@RequestHeader(value = "Authorization") String auth,
			@RequestBody Order order) throws Exception {
		Claims claims = te.extractInfo(auth);
		/* initialize a TreeMap object */
		TreeMap<String, String> paytmParams = new TreeMap<String, String>();

		/*
		 * Find your MID in your Paytm Dashboard at
		 * https://dashboard.paytm.com/next/apikeys
		 */
		paytmParams.put("MID", PaytmConstants.MERCHANT_ID);

		/* Enter your order id which needs to be check status for */
		paytmParams.put("ORDERID", order.getId().toString());

		/**
		 * Generate checksum by parameters we have You can get Checksum JAR from
		 * https://developer.paytm.com/docs/checksum/ Find your Merchant Key in your
		 * Paytm Dashboard at https://dashboard.paytm.com/next/apikeys
		 */
		String checksum = CheckSumServiceHelper.getCheckSumServiceHelper().genrateCheckSum(PaytmConstants.MERCHANT_KEY,
				paytmParams);

		/* put generated checksum value here */
		paytmParams.put("CHECKSUMHASH", checksum);

		/* prepare JSON string for request */
		JSONObject obj = new JSONObject(paytmParams);
		String post_data = obj.toString();
		/// System.out.println("post_data "+post_data);
		/* for Staging */
		URL url = new URL(PaytmConstants.TRANSACTION_STATUS_URL);

		/* for Production */
		// URL url = new URL("https://securegw.paytm.in/order/status");
		String responseData = "";
		try {
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setDoOutput(true);

			DataOutputStream requestWriter = new DataOutputStream(connection.getOutputStream());
			requestWriter.writeBytes(post_data);
			requestWriter.close();

			InputStream is = connection.getInputStream();
			BufferedReader responseReader = new BufferedReader(new InputStreamReader(is));
			if ((responseData = responseReader.readLine()) != null) {
				// System.out.append("Response: " + responseData);
			}
			// System.out.append("Request: " + post_data);
			responseReader.close();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return responseData;
	}

	public boolean validateCheckSum(TreeMap<String, String> parameters, String paytmChecksum) throws Exception {
		return CheckSumServiceHelper.getCheckSumServiceHelper().verifycheckSum(PaytmConstants.MERCHANT_KEY, parameters,
				paytmChecksum);
		// return
		// CheckSumServiceHelper.getCheckSumServiceHelper().verifycheckSum(PaytmConstants.MERCHANT_KEY,paytmResponse.toString(),
		// paytmChecksum);
	}

	@PostMapping("/cancel")
	public @ResponseBody ResponseDetails cancelOrder(@RequestBody Order order,
			@RequestHeader(value = "Authorization") String auth) {
		Claims claims = te.extractInfo(auth);
		return os.cancelOrder(order.getId(), Integer.valueOf(claims.get(USER_ID).toString()));
//		return new ResponseDetails(new Date(), "Order received.", null,1);
	}

	@PostMapping("/accept")
	public @ResponseBody ResponseDetails acceptOrder(@RequestBody OrderDetails orderdetails,
			@RequestHeader(value = "Authorization") String auth) {
		Claims claims = te.extractInfo(auth);
		if (Integer.valueOf(claims.get(Constants.COMPANY_ID).toString()) != 0
				&& Integer.valueOf(claims.get(Constants.STORE_ID).toString()) != 0) {
			return os.acceptOrder(orderdetails.getId(), Integer.valueOf(claims.get(Constants.COMPANY_ID).toString()),
					Integer.valueOf(claims.get(Constants.STORE_ID).toString()));
		} else {
			return new ResponseDetails(new Date(), "Sorry you are not a valid store", null, 0);
		}
//		return new ResponseDetails(new Date(), "Order received.", null,1);
	}

	@PostMapping("/acceptAllOrder")
	public @ResponseBody ResponseDetails acceptAllOrder(@RequestBody List<OrderDetails> orderdetails,
			@RequestHeader(value = "Authorization") String auth) {
		// System.out.println("Ids = "+orderdetails.toString());
		Claims claims = te.extractInfo(auth);
		if (Integer.valueOf(claims.get(Constants.COMPANY_ID).toString()) != 0
				&& Integer.valueOf(claims.get(Constants.STORE_ID).toString()) != 0) {
			return os.acceptAllOrder(orderdetails, Integer.valueOf(claims.get(Constants.COMPANY_ID).toString()),
					Integer.valueOf(claims.get(Constants.STORE_ID).toString()));
		} else {
			return new ResponseDetails(new Date(), "Sorry you are not a valid store", null, 0);
		}
		/*
		 * if(saleOrderId.size()==0) return null; return os.acceptAllOrder(saleOrderId);
		 */
	}

	@PostMapping("/sale")
	public @ResponseBody ResponseDetails saleOrder(@RequestBody OrderDetails orderdetails,
			@RequestHeader(value = "Authorization") String auth) {
		Claims claims = te.extractInfo(auth);
		if (Integer.valueOf(claims.get(Constants.COMPANY_ID).toString()) != 0
				&& Integer.valueOf(claims.get(Constants.STORE_ID).toString()) != 0) {
			return os.saleOrder(orderdetails.getId(), orderdetails.getExpiryDate(), orderdetails.getBatchNo(),
					Integer.valueOf(claims.get(Constants.COMPANY_ID).toString()),
					Integer.valueOf(claims.get(Constants.STORE_ID).toString()), orderdetails.getPickupDate(),
					orderdetails.getPickupTime());
		}

		else {
			return new ResponseDetails(new Date(), "Sorry you are not a valid store", null, 0);
		}
//		return new ResponseDetails(new Date(), "Order received.", null,1);

	}

	@PostMapping("/dispatch")
	public @ResponseBody ResponseDetails dispatchOrder(@RequestBody OrderDetails orderdetails,
			@RequestHeader(value = "Authorization") String auth) {
		Claims claims = te.extractInfo(auth);
		if (Integer.valueOf(claims.get(Constants.COMPANY_ID).toString()) != 0
				&& Integer.valueOf(claims.get(Constants.STORE_ID).toString()) != 0) {
			return os.dispatchOrder(orderdetails.getId(), Integer.valueOf(claims.get(Constants.COMPANY_ID).toString()),
					Integer.valueOf(claims.get(Constants.STORE_ID).toString()));
		}

		else {
			return new ResponseDetails(new Date(), "Sorry you are not a valid store", null, 0);
		}
//		return new ResponseDetails(new Date(), "Order received.", null,1);
	}

	@PostMapping("/dispatchOrderItemFromStore") /// new Added 25-08-2020 Sayantan Mahanty
	public @ResponseBody ResponseDetails dispatchOrderItemFromStore(@RequestBody OrderDetails orderdetails,
			@RequestHeader(value = "Authorization") String auth) {
		Claims claims = te.extractInfo(auth);
		List<String> authorities = Arrays.asList(claims.get(Constants.AUTHORITIES_KEY).toString().split(","));
		if (authorities.containsAll(Arrays.asList(Constants.ROLE_AGENTADMIN.split(",")))) {

			return os.dispatchItemFromStore(orderdetails.getId());
		}

		else {
			return new ResponseDetails(new Date(), "Sorry you are not a valid User", null, 0);
		}
	}

	@PostMapping("/deliver")
	public @ResponseBody ResponseDetails deliverOrder(@RequestBody OrderDetails orderdetails,
			@RequestHeader(value = "Authorization") String auth) {
		Claims claims = te.extractInfo(auth);
		if (Integer.valueOf(claims.get(Constants.COMPANY_ID).toString()) != 0
				&& Integer.valueOf(claims.get(Constants.STORE_ID).toString()) != 0) {
			return os.deliverOrder(orderdetails.getId(), Integer.valueOf(claims.get(Constants.COMPANY_ID).toString()),
					Integer.valueOf(claims.get(Constants.STORE_ID).toString()));
		} else {
			return new ResponseDetails(new Date(), "Sorry you are not a valid store", null, 0);
		}
//		return new ResponseDetails(new Date(), "Order received.", null,1);
	}

	@PostMapping("/deliverOrderItemFromStore") /// new Added 25-08-2020 Sayantan Mahanty")
	public @ResponseBody ResponseDetails deliverOrderItemFromStore(@RequestBody OrderDetails orderdetails,
			@RequestHeader(value = "Authorization") String auth) {
		Claims claims = te.extractInfo(auth);
		List<String> authorities = Arrays.asList(claims.get(Constants.AUTHORITIES_KEY).toString().split(","));
		if (authorities.containsAll(Arrays.asList(Constants.ROLE_AGENTADMIN.split(",")))) {
			return os.deliverOrderItemFromStore(orderdetails.getId());
		} else {
			return new ResponseDetails(new Date(), "Sorry you are not a valid store", null, 0);
		}
//		return new ResponseDetails(new Date(), "Order received.", null,1);
	}

	@PostMapping("/saleAllOrder")
	public @ResponseBody ResponseDetails saleAllOrder(@RequestBody List<OrderDetails> saleOrderIds,
			@RequestHeader(value = "Authorization") String auth) {
		Claims claims = te.extractInfo(auth);
		if (Integer.valueOf(claims.get(Constants.COMPANY_ID).toString()) != 0
				&& Integer.valueOf(claims.get(Constants.STORE_ID).toString()) != 0) {
			return os.saleAllOrder(saleOrderIds, Integer.valueOf(claims.get(Constants.COMPANY_ID).toString()),
					Integer.valueOf(claims.get(Constants.STORE_ID).toString()));
		} else {
			return new ResponseDetails(new Date(), "Sorry you are not a valid store", null, 0);
		}
//		return new ResponseDetails(new Date(), "Order received.", null,1);
	}

	@GetMapping("/getAllTopSaleOrderItems")
	public @ResponseBody List<Order> getAllTopSaleOrderItems() {
		List<Order> bestSalesItems = os.getAllTopSaleOrderItems();
		return bestSalesItems;
	}

	@GetMapping("/downloadInvoice/{id}")
	public void getSaleOrderDownload(@RequestHeader(value = "Authorization") String auth,
			@PathVariable(value = "id") int saleOrderId, HttpServletRequest request, HttpServletResponse response) {
		// System.out.println("saleOrderId:"+saleOrderId);
		// System.out.println(request.getRealPath("/") + "jasper\\sales\\");
		String fileName = null;
		Connection connection = null;
		// EntityManagerFactory entityManagerFactory;
		// EntityManager em = null;
		String jasperFile = null;
		try {
			Claims claims = te.extractInfo(auth);
			Order order = os.getOrderById(saleOrderId, Integer.valueOf(claims.get(USER_ID).toString()));
			Integer netAmount = order.getNetAmount().intValue();
			NumWord numWord = new NumWord();
			String netAmountInWord = numWord.convertNumToWord("" + netAmount);
			// entityManagerFactory = PersistenceListener.getEntityManager();
			// em = entityManagerFactory.createEntityManager();

			// get connection object from entity manager
			// Session session = sessionFactory.openSession();
			// SessionImpl sessionImpl = (SessionImpl) session;
			Session session = (Session) em.getDelegate();
			SessionImpl sessionImpl = (SessionImpl) session;
			// SessionFactoryImpl sessionFactory = (SessionFactoryImpl)
			// session.getSessionFactory();

			connection = sessionImpl.connection();

			Map<String, Object> parameters = new HashMap<String, Object>();

			parameters.put("W_SaleOrderID", saleOrderId);
			parameters.put("W_NetAmtInwords", netAmountInWord);
			parameters.put("W_Logo_Path", request.getRealPath("/") + "jasper/sales/logo_new.png");

			fileName = "ecomm_invoice";
			jasperFile = "ecomm_invoice.jrxml";

			fileName = fileName + ".pdf";
			generatePDF(request, response, fileName, parameters, connection, jasperFile);

			File file = new File(request.getRealPath("/") + "jasper/sales/" + fileName);
			InputStream is = new FileInputStream(file);
			// System.out.println("getRealPath: "+request.getRealPath("/") + "jasper/sales"
			// + fileName);
			response.reset();
			// D:\ECommerce\ecom\src\main\webapp\jasper\sales
			response.setHeader("Content-Type", "application/pdf");

			response.setHeader("Content-Length", String.valueOf(file.length()));
			response.setHeader("Content-Disposition", "inline; filename=\"" + fileName + "\"");
			List<Byte> buf = new ArrayList<Byte>();
			int ch = -1;
			while ((ch = is.read()) != -1) {
				buf.add((byte) ch);
			}
			byte[] array = new byte[buf.size()];
			for (int i = 0; i < buf.size(); i++) {
				array[i] = buf.get(i);
			}
			ServletOutputStream os = response.getOutputStream();
			os.write(array);
			os.flush();
			os.close();
			is.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (em != null)
				em.close();
		}
	}

	public void generatePDF(HttpServletRequest request, HttpServletResponse response, String fileName,
			Map<String, Object> parameters, Connection connection, String jasperFile)
			throws FileNotFoundException, IOException {

		try {

			File file = new File(request.getRealPath("/") + "jasper/sales/" + fileName);

			JasperReport report;

			report = JasperCompileManager.compileReport(request.getRealPath("/jasper") + "/sales/" + jasperFile);
			JasperPrint print = JasperFillManager.fillReport(report, parameters, connection);
			JasperExportManager.exportReportToPdfFile(print, request.getRealPath("/") + "/jasper/sales/" + fileName);

		}

		catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

		}

	}

	/*
	 * @GetMapping("/linegraph") public @ResponseBody List<Order>
	 * getOrdersAndAmountForLineGraph(
	 * 
	 * @RequestHeader(value="Authorization") String auth
	 * , @RequestParam(value="startDate", required=false, defaultValue="0000-00-00"
	 * ) String startDate , @RequestParam(value="endDate", required=false,
	 * defaultValue="0000-00-00" ) String endDate , @RequestParam(value="companyId",
	 * required=false, defaultValue="0" ) int companyId
	 * , @RequestParam(value="storeId", required=false, defaultValue="0" ) int
	 * storeId ) { // User u = us.findUserByUserName(auth.getName()); Claims claims
	 * = te.extractInfo(auth); List<String> authorities =
	 * Arrays.asList(claims.get(Constants.AUTHORITIES_KEY).toString().split(","));
	 * // System.out.println("user role = "+u.getRoles());
	 * if(authorities.containsAll(Arrays.asList(Constants.ROLE_ADMIN.split(",")))) {
	 * return os.getAllForAdmin(page,limit,startDate,endDate,itemId,status); } for
	 * (Iterator iterator = u.getRoles().iterator(); iterator.hasNext();) { Role
	 * role = (Role) iterator.next(); if(role.getRole().equals("ADMIN")) { return
	 * os.getAllForAdmin(page,limit,startDate,endDate,itemId,status); } }
	 * //if(u.getCompanyId()!=0 && u.getStoreId()!=0) {
	 * if(Integer.valueOf(claims.get(Constants.COMPANY_ID).toString())!=0 &&
	 * Integer.valueOf(claims.get(Constants.STORE_ID).toString())!=0) { // return
	 * os.getAllForStoreAdmin(u.getStoreId(),
	 * u.getCompanyId(),page,limit,startDate,endDate,itemId,status); return
	 * os.getAllForStoreAdmin(Integer.valueOf(claims.get(Constants.STORE_ID).
	 * toString()),
	 * Integer.valueOf(claims.get(Constants.COMPANY_ID).toString()),page,limit,
	 * startDate,endDate,itemId,status); } else { return null; } }
	 */

	/// new added 11-3-2020
	@GetMapping("/getLastOneHourOrders")
	public @ResponseBody int getLastOneHourOrdersForStore(@RequestHeader(value = "Authorization") String auth) {

		// return List<Order> os.getLastOneHourOrdersForStore(auth,status);
		int lastOneHourordersforstore = 0;
		Claims claims = te.extractInfo(auth);
		List<String> authorities = Arrays.asList(claims.get(Constants.AUTHORITIES_KEY).toString().split(","));
		// System.out.println("user role = "+authorities);
		if (authorities.containsAll(Arrays.asList(Constants.ROLE_SUBADMIN.split(",")))) {
			int storeId = Integer.valueOf(claims.get(Constants.STORE_ID).toString());
			int companyId = Integer.valueOf(claims.get(Constants.COMPANY_ID).toString());
			lastOneHourordersforstore = os.getLastOneHourOrdersForStore(storeId);
			// System.err.println("strDate "+lastOneHourordersforstore);
			/// System.out.println("lastOneHourordersforstore "+lastOneHourordersforstore);

			// return lastOneHourordersforstore;
		}
		return lastOneHourordersforstore;
	}

	/*
	 * @PostMapping("/getOrderDetailsByDeliveryTypeAndStatus")///Developed By
	 * Sayantan Mahanty 22-12-20 public @ResponseBody List<OrderDetails>
	 * getOrderDetailsByDeliveryTypeAndStatus(
	 * 
	 * @RequestHeader(value = "Authorization") String auth, @RequestBody
	 * OrderDetails orderdetails) { List<OrderDetails> orderDetails =
	 * os.getOrderDetailsByDeliveryTypeAndStatus(orderdetails.getDeliveryType(),
	 * orderdetails.getStatus()); return orderDetails; }
	 */
	
	@PostMapping("/getOrderDetailsByDeliveryTypeAndStatus")///Developed By Sayantan Mahanty 22-12-20
	public @ResponseBody List<Order> getOrderDetailsByDeliveryTypeAndStatus(
			@RequestHeader(value = "Authorization") String auth, @RequestBody OrderDetails orderdetails) {
		List<Order> order = os.getOrderDetailsByDeliveryTypeAndStatus(orderdetails.getDeliveryType(),
				orderdetails.getStatus());
		return order;
	}
	
	@GetMapping("/getOrderDetailsByDeliveryPersonId/{status}") /// Developed by Sayantan Mahanty 26-12-2020
	public @ResponseBody List<Order> getOrderDetailsByDeliveryPersonId(
			@RequestHeader(value = "Authorization") String auth, @PathVariable(value = "status") int status) {
		Claims claims = te.extractInfo(auth);
		//System.err.println(Integer.valueOf(claims.get(USER_ID).toString()));
		if (Integer.valueOf(claims.get(USER_ID).toString()) != null) {
			List<Order> order = os
					.getOrderDetailsByDeliveryPersonId(Integer.valueOf(claims.get(USER_ID).toString()), status);
			return order;
		}
		return null;
	}
	
	@PostMapping("/setPickUpOrder")///Developed By Sayantan Mahanty 29-12-20
	public @ResponseBody ResponseDetails setPickUpOrder(@RequestHeader(value = "Authorization") String auth,
			@RequestBody OrderDetails orderDetails) {
		Claims claims = te.extractInfo(auth);

		if (Integer.valueOf(claims.get(USER_ID).toString()) != null) {
			
			os.setPickUpOrder(orderDetails.getId(),orderDetails.getStatus());
			return new ResponseDetails(new Date(), "Pickup Order added Sucessfully.", null, 1);
		}
			else {
			return new ResponseDetails(new Date(), "Sorry User Not Avalable.", null, 0);
		}
	}
	
	@PostMapping("/getOrderDetailsById") /// Developed by Sayantan Mahanty 6-1-2021
	public @ResponseBody List<OrderDetails> getOrderDetailsById(
			@RequestHeader(value = "Authorization") String auth,@RequestBody OrderDetails od) {		
		Claims claims = te.extractInfo(auth);
		if (Integer.valueOf(claims.get(USER_ID).toString()) != null) {
			List<OrderDetails> orderDetails = os
					.getOrderDetailsById(Integer.valueOf(claims.get(Constants.STORE_ID).toString()),Integer.valueOf(claims.get(Constants.COMPANY_ID).toString()),od.getId());
			return orderDetails;
		}
		return null;
	}
	
	@PostMapping("/orderDelivered") /// Developed by Sayantan Mahanty 13-1-2021
	public @ResponseBody ResponseDetails orderDelivered(
			@RequestHeader(value = "Authorization") String auth,@RequestBody Order od) {		
		Claims claims = te.extractInfo(auth);

		if (Integer.valueOf(claims.get(USER_ID).toString()) != null) {
			if(od.getPaymentMode()==2)
			{
				for(int i=0;i<od.getOrderDetails().size();i++) {
					
				PaytmDetails pd = new PaytmDetails();
				pd.setSaleOrderDetailsId(od.getOrderDetails().get(i).getId());
				pd.setSaleOrderId(od.getId());
				pd.setPaymentMode(od.getPaymentMode());
				//pd.setCurrency();
				pd.setPayableAmount(od.getOrderDetails().get(i).getNetAmount());
				pd.setPaidAmount(od.getOrderDetails().get(i).getNetAmount());
				pd.setRefundId(0);
				
				if(od.getCustomerId()!=null) {
				pd.setCustomerId(od.getCustomerId());
				
				}
				os.savePgDetails(pd);
				os.setOrderDetailsStatus(od.getOrderDetails().get(i).getStatus(),od.getOrderDetails().get(i).getId());
				
				}
				
				return new ResponseDetails(new Date(), "Order delivered sucessfully.", null, 1);
			}
			else
			{
				for(int i=0;i<od.getOrderDetails().size();i++) {
				os.setOrderDetailsStatus(od.getOrderDetails().get(i).getStatus(),od.getOrderDetails().get(i).getId());
				}
				return new ResponseDetails(new Date(), "Order delivered sucessfully.", null, 1);
			}
			
			
			//os.orderDelivered(od.getId(),od.getPaymentMode());
			
		}
			else {
			return new ResponseDetails(new Date(), "Sorry You are not eligible.", null, 0);
		}
	}

}
