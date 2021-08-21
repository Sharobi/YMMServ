package com.retail.ecom.model;

import java.util.Date;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.stereotype.Component;

@Entity
@Table(name="in_t_payment_details")
public class PaytmDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "sale_order_id")
	private Integer saleOrderId;

	@Column(name = "sale_order_details_id")
	private Integer saleOrderDetailsId;

	@Column(name = "customer_id")
	private Integer customerId;

	@Column(name = "payment_mode")
	private Integer paymentMode;
	
	@Column(name = "payment_type")
	private String paymentType;

	@Column(name = "payable_amount")
	private Double payableAmount;

	@Column(name = "paid_amount")
	private Double paidAmount;

	@Column(name = "merchant_id")
	private String merchantId;

	@Column(name = "merchant_key")
	private String merchantKey;

	@Column(name = "txn_status")
	private String txtStatus;

	@Column(name = "response_message")
	private String responseMessage;
	
	@Column(name = "response_code")
	private String responseCode;

	@Column(name = "txn_id")
	private String txnId;
	
	@Column(name = "bank_txn_id")
	private String bankTxnId;
	
	@Column(name = "currency")
	private String currency;
	

	@Column(name = "txn_date")
	private String txnDate;
	
	@Column(name = "gateway_name")
	private String gatewayName;
	
	@Column(name = "bank_name")
	private String bankName;
	
	@Column(name = "refund_id")
	private Integer refundId;
	
	@Column(name = "checksum")
	private String checksum;


	public PaytmDetails() {
		super();
	}


	public PaytmDetails(Integer id, Integer saleOrderId, Integer saleOrderDetailsId, Integer customerId,
			Integer paymentMode, String paymentType, Double payableAmount, Double paidAmount, String merchantId,
			String merchantKey, String txtStatus, String responseMessage, String responseCode, String txnId,
			String bankTxnId, String currency, String txnDate, String gatewayName, String bankName, Integer refundId,
			String checksum) {
		super();
		this.id = id;
		this.saleOrderId = saleOrderId;
		this.saleOrderDetailsId = saleOrderDetailsId;
		this.customerId = customerId;
		this.paymentMode = paymentMode;
		this.paymentType = paymentType;
		this.payableAmount = payableAmount;
		this.paidAmount = paidAmount;
		this.merchantId = merchantId;
		this.merchantKey = merchantKey;
		this.txtStatus = txtStatus;
		this.responseMessage = responseMessage;
		this.responseCode = responseCode;
		this.txnId = txnId;
		this.bankTxnId = bankTxnId;
		this.currency = currency;
		this.txnDate = txnDate;
		this.gatewayName = gatewayName;
		this.bankName = bankName;
		this.refundId = refundId;
		this.checksum = checksum;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getSaleOrderId() {
		return saleOrderId;
	}


	public void setSaleOrderId(Integer saleOrderId) {
		this.saleOrderId = saleOrderId;
	}


	public Integer getSaleOrderDetailsId() {
		return saleOrderDetailsId;
	}


	public void setSaleOrderDetailsId(Integer saleOrderDetailsId) {
		this.saleOrderDetailsId = saleOrderDetailsId;
	}


	public Integer getCustomerId() {
		return customerId;
	}


	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}


	public Integer getPaymentMode() {
		return paymentMode;
	}


	public void setPaymentMode(Integer paymentMode) {
		this.paymentMode = paymentMode;
	}


	public String getPaymentType() {
		return paymentType;
	}


	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}


	public Double getPayableAmount() {
		return payableAmount;
	}


	public void setPayableAmount(Double payableAmount) {
		this.payableAmount = payableAmount;
	}


	public Double getPaidAmount() {
		return paidAmount;
	}


	public void setPaidAmount(Double paidAmount) {
		this.paidAmount = paidAmount;
	}


	public String getMerchantId() {
		return merchantId;
	}


	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}


	public String getMerchantKey() {
		return merchantKey;
	}


	public void setMerchantKey(String merchantKey) {
		this.merchantKey = merchantKey;
	}


	public String getTxtStatus() {
		return txtStatus;
	}


	public void setTxtStatus(String txtStatus) {
		this.txtStatus = txtStatus;
	}


	public String getResponseMessage() {
		return responseMessage;
	}


	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}


	public String getResponseCode() {
		return responseCode;
	}


	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}


	public String getTxnId() {
		return txnId;
	}


	public void setTxnId(String txnId) {
		this.txnId = txnId;
	}


	public String getBankTxnId() {
		return bankTxnId;
	}


	public void setBankTxnId(String bankTxnId) {
		this.bankTxnId = bankTxnId;
	}


	public String getCurrency() {
		return currency;
	}


	public void setCurrency(String currency) {
		this.currency = currency;
	}


	public String getTxnDate() {
		return txnDate;
	}


	public void setTxnDate(String txnDate) {
		this.txnDate = txnDate;
	}


	public String getGatewayName() {
		return gatewayName;
	}


	public void setGatewayName(String gatewayName) {
		this.gatewayName = gatewayName;
	}


	public String getBankName() {
		return bankName;
	}


	public void setBankName(String bankName) {
		this.bankName = bankName;
	}


	public Integer getRefundId() {
		return refundId;
	}


	public void setRefundId(Integer refundId) {
		this.refundId = refundId;
	}


	public String getChecksum() {
		return checksum;
	}


	public void setChecksum(String checksum) {
		this.checksum = checksum;
	}


	@Override
	public String toString() {
		return "PaytmDetails [id=" + id + ", saleOrderId=" + saleOrderId + ", saleOrderDetailsId=" + saleOrderDetailsId
				+ ", customerId=" + customerId + ", paymentMode=" + paymentMode + ", paymentType=" + paymentType
				+ ", payableAmount=" + payableAmount + ", paidAmount=" + paidAmount + ", merchantId=" + merchantId
				+ ", merchantKey=" + merchantKey + ", txtStatus=" + txtStatus + ", responseMessage=" + responseMessage
				+ ", responseCode=" + responseCode + ", txnId=" + txnId + ", bankTxnId=" + bankTxnId + ", currency="
				+ currency + ", txnDate=" + txnDate + ", gatewayName=" + gatewayName + ", bankName=" + bankName
				+ ", refundId=" + refundId + ", checksum=" + checksum + "]";
	}



}
