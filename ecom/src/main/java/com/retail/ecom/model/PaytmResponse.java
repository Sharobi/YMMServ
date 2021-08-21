package com.retail.ecom.model;

import java.util.Date;

public class PaytmResponse {
	private String MID;
	private String TXNID;
	private String ORDERID;
	private String BANKTXNID;
	private String TXNAMOUNT;
	private String CURRENCY;
	private String STATUS;
	private String RESPCODE;
	private String RESPMSG;
	private Date TXNDATE;
	private String GATEWAYNAME;
	private String BANKNAME;
	private String PAYMENTMODE;
	private String CHECKSUMHASH;
	
	
	
	public PaytmResponse() {
		super();
	}
	public PaytmResponse(String mID, String tXNID, String oRDERID, String bANKTXNID, String tXNAMOUNT, String cURRENCY,
			String sTATUS, String rESPCODE, String rESPMSG, Date tXNDATE, String gATEWAYNAME, String bANKNAME,
			String pAYMENTMODE, String cHECKSUMHASH) {
		super();
		MID = mID;
		TXNID = tXNID;
		ORDERID = oRDERID;
		BANKTXNID = bANKTXNID;
		TXNAMOUNT = tXNAMOUNT;
		CURRENCY = cURRENCY;
		STATUS = sTATUS;
		RESPCODE = rESPCODE;
		RESPMSG = rESPMSG;
		TXNDATE = tXNDATE;
		GATEWAYNAME = gATEWAYNAME;
		BANKNAME = bANKNAME;
		PAYMENTMODE = pAYMENTMODE;
		CHECKSUMHASH = cHECKSUMHASH;
	}
	public String getMID() {
		return MID;
	}
	public void setMID(String mID) {
		MID = mID;
	}
	public String getTXNID() {
		return TXNID;
	}
	public void setTXNID(String tXNID) {
		TXNID = tXNID;
	}
	public String getORDERID() {
		return ORDERID;
	}
	public void setORDERID(String oRDERID) {
		ORDERID = oRDERID;
	}
	public String getBANKTXNID() {
		return BANKTXNID;
	}
	public void setBANKTXNID(String bANKTXNID) {
		BANKTXNID = bANKTXNID;
	}
	public String getTXNAMOUNT() {
		return TXNAMOUNT;
	}
	public void setTXNAMOUNT(String tXNAMOUNT) {
		TXNAMOUNT = tXNAMOUNT;
	}
	public String getCURRENCY() {
		return CURRENCY;
	}
	public void setCURRENCY(String cURRENCY) {
		CURRENCY = cURRENCY;
	}
	public String getSTATUS() {
		return STATUS;
	}
	public void setSTATUS(String sTATUS) {
		STATUS = sTATUS;
	}
	public String getRESPCODE() {
		return RESPCODE;
	}
	public void setRESPCODE(String rESPCODE) {
		RESPCODE = rESPCODE;
	}
	public String getRESPMSG() {
		return RESPMSG;
	}
	public void setRESPMSG(String rESPMSG) {
		RESPMSG = rESPMSG;
	}
	public Date getTXNDATE() {
		return TXNDATE;
	}
	public void setTXNDATE(Date tXNDATE) {
		TXNDATE = tXNDATE;
	}
	public String getGATEWAYNAME() {
		return GATEWAYNAME;
	}
	public void setGATEWAYNAME(String gATEWAYNAME) {
		GATEWAYNAME = gATEWAYNAME;
	}
	public String getBANKNAME() {
		return BANKNAME;
	}
	public void setBANKNAME(String bANKNAME) {
		BANKNAME = bANKNAME;
	}
	public String getPAYMENTMODE() {
		return PAYMENTMODE;
	}
	public void setPAYMENTMODE(String pAYMENTMODE) {
		PAYMENTMODE = pAYMENTMODE;
	}
	public String getCHECKSUMHASH() {
		return CHECKSUMHASH;
	}
	public void setCHECKSUMHASH(String cHECKSUMHASH) {
		CHECKSUMHASH = cHECKSUMHASH;
	}
	@Override
	public String toString() {
		return "PaytmResponse [MID=" + MID + ", TXNID=" + TXNID + ", ORDERID=" + ORDERID + ", BANKTXNID=" + BANKTXNID
				+ ", TXNAMOUNT=" + TXNAMOUNT + ", CURRENCY=" + CURRENCY + ", STATUS=" + STATUS + ", RESPCODE="
				+ RESPCODE + ", RESPMSG=" + RESPMSG + ", TXNDATE=" + TXNDATE + ", GATEWAYNAME=" + GATEWAYNAME
				+ ", BANKNAME=" + BANKNAME + ", PAYMENTMODE=" + PAYMENTMODE + ", CHECKSUMHASH=" + CHECKSUMHASH + "]";
	}
	
	

}
