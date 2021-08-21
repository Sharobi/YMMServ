package com.retail.ecom.utils;

public class Constants {

    /*public static final long ACCESS_TOKEN_VALIDITY_SECONDS = 5*60*60;
    public static final String SIGNING_KEY = "devglan123r";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";*/
    
    
	public static final long EXPIRATIONTIME = 864_000_000; // 10 days
	public static final String SECRET = "YEWPOS";
	public static final String TOKEN_PREFIX = "Bearer";
	public static final String HEADER_STRING = "Authorization";
	public static final String AUTHORITIES_KEY = "Authorities";
	public static final String USER_ID = "user_id";
	public static final String COMPANY_ID = "company_id";
	public static final String STORE_ID = "store_id";
	public static final String COUNTRY = "country";
	public static final String STATE = "state";
	
	public static final String ROLE_ALL = "All";
	public static final String ROLE_ADMIN = "ADMIN";
	public static final String ROLE_USER = "USER";
	public static final String ROLE_SUBADMIN = "SUBADMIN";
	public static final String ROLE_AGENTADMIN = "AGENTADMIN";
	public static final String ROLE_DELIVERY_AGENT = "DELIVERY_AGENT";
	
	public static final int ACTIVITY_LOGIN = 8;
	public static final int ACTIVITY_LOGOUT = 9;
	public static final int ACTIVITY_REGISTRATION = 1;
	public static final int ACTIVITY_PURCHASE = 2;
	public static final int ACTIVITY_REDEEM = 4;
	public static final int OFFER_PURCHASE = 3;
	
}
