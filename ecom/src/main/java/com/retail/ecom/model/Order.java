package com.retail.ecom.model;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "sal_t_sale_order")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String invNo;
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private Date invDate;
	private Date dueDate;
	private String invType;
	private Integer customerId;
	private Integer customerAddressId;
	private Double customerDiscPer;
	private Double grossAmount;
	private Double discAmount;
	private Double taxAmount;
	private Double roundoff;
	private Double netAmount;
	private Double totalMrp;
	private Double totShippingCharge;
	private String remarks;
	@Column(columnDefinition = "int(11) default '0'")
	private int isPosted;
	@Column(columnDefinition = "int(11) default '0'")
	private int isCanceled;
	@Column(columnDefinition = "int(11) default '0'")
	private int isDeleted;
	private Integer paymentMode;
	private String paymentStatus;
	@Column(columnDefinition = "int(11) default '1'")
	private int orderStatus;
	private Integer shippingId;
	private Integer createdBy;
	private Integer updatedBy;
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "IST") /// new Added
																									/// 02/03/2020
	private Date createdDate;
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	private Date updatedDate;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
	List<OrderDetails> orderDetails;

	@Transient
	private Integer prescriptionId;

	@Transient
	private List<Integer> prescriptionIds;

	@Transient
	private Double lat;
	@Transient
	private Double lng;
	@Transient
	private int pinCode;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
	List<Prescription> prescriptions;
	@Transient
	Address address;// 09-01-2020

	@Transient
	AddressShipping addressShipping;// 09-01-2020

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
	List<OrderBenefit> orderBenefits;

	@Transient
	private Integer[] membershipFeatureIds;

	public Order() {
		super();
	}

	public Order(Integer customerId, List<OrderDetails> orderDetails) {
		super();
		this.customerId = customerId;
		this.orderDetails = orderDetails;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getInvNo() {
		return invNo;
	}

	public void setInvNo(String invNo) {
		this.invNo = invNo;
	}

	public Date getInvDate() {
		return invDate;
	}

	public void setInvDate(Date invDate) {
		this.invDate = invDate;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public String getInvType() {
		return invType;
	}

	public void setInvType(String invType) {
		this.invType = invType;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public Integer getCustomerAddressId() {
		return customerAddressId;
	}

	public void setCustomerAddressId(Integer customerAddressId) {
		this.customerAddressId = customerAddressId;
	}

	public Double getCustomerDiscPer() {
		return customerDiscPer;
	}

	public void setCustomerDiscPer(Double customerDiscPer) {
		this.customerDiscPer = customerDiscPer;
	}

	public Double getGrossAmount() {
		return grossAmount;
	}

	public void setGrossAmount(Double grossAmount) {
		this.grossAmount = grossAmount;
	}

	public Double getDiscAmount() {
		return discAmount;
	}

	public void setDiscAmount(Double discAmount) {
		this.discAmount = discAmount;
	}

	public Double getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(Double taxAmount) {
		this.taxAmount = taxAmount;
	}

	public Double getRoundoff() {
		return roundoff;
	}

	public void setRoundoff(Double roundoff) {
		this.roundoff = roundoff;
	}

	public Double getNetAmount() {
		return netAmount;
	}

	public void setNetAmount(Double netAmount) {
		this.netAmount = netAmount;
	}

	public Double getTotalMrp() {
		return totalMrp;
	}

	public void setTotalMrp(Double totalMrp) {
		this.totalMrp = totalMrp;
	}

	public Double getTotShippingCharge() {
		return totShippingCharge;
	}

	public void setTotShippingCharge(Double totShippingCharge) {
		this.totShippingCharge = totShippingCharge;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public int getIsPosted() {
		return isPosted;
	}

	public void setIsPosted(int isPosted) {
		this.isPosted = isPosted;
	}

	public int getIsCanceled() {
		return isCanceled;
	}

	public void setIsCanceled(int isCanceled) {
		this.isCanceled = isCanceled;
	}

	public int getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Integer getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(Integer paymentMode) {
		this.paymentMode = paymentMode;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public int getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Integer getShippingId() {
		return shippingId;
	}

	public void setShippingId(Integer shippingId) {
		this.shippingId = shippingId;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public List<OrderDetails> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetails> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLng() {
		return lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}

	public int getPinCode() {
		return pinCode;
	}

	public void setPinCode(int pinCode) {
		this.pinCode = pinCode;
	}

	public Integer getPrescriptionId() {
		return prescriptionId;
	}

	public void setPrescriptionId(Integer prescriptionId) {
		this.prescriptionId = prescriptionId;
	}

	public List<Prescription> getPrescriptions() {
		return prescriptions;
	}

	public void setPrescriptions(List<Prescription> prescriptions) {
		this.prescriptions = prescriptions;
	}

	public List<Integer> getPrescriptionIds() {
		return prescriptionIds;
	}

	public void setPrescriptionIds(List<Integer> prescriptionIds) {
		this.prescriptionIds = prescriptionIds;
	}

	public Integer[] getMembershipFeatureIds() {
		return membershipFeatureIds;
	}

	public void setMembershipFeatureIds(Integer[] membershipFeatureIds) {
		this.membershipFeatureIds = membershipFeatureIds;
	}

	public List<OrderBenefit> getOrderBenefits() {
		return orderBenefits;
	}

	public void setOrderBenefits(List<OrderBenefit> orderBenefits) {
		this.orderBenefits = orderBenefits;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public AddressShipping getAddressShipping() {
		return addressShipping;
	}

	public void setAddressShipping(AddressShipping addressShipping) {
		this.addressShipping = addressShipping;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", invNo=" + invNo + ", invDate=" + invDate + ", dueDate=" + dueDate + ", invType="
				+ invType + ", customerId=" + customerId + ", customerAddressId=" + customerAddressId
				+ ", customerDiscPer=" + customerDiscPer + ", grossAmount=" + grossAmount + ", discAmount=" + discAmount
				+ ", taxAmount=" + taxAmount + ", roundoff=" + roundoff + ", netAmount=" + netAmount + ", totalMrp="
				+ totalMrp + ", totShippingCharge=" + totShippingCharge + ", remarks=" + remarks + ", isPosted="
				+ isPosted + ", isCanceled=" + isCanceled + ", isDeleted=" + isDeleted + ", paymentMode=" + paymentMode
				+ ", paymentStatus=" + paymentStatus + ", orderStatus=" + orderStatus + ", shippingId=" + shippingId
				+ ", createdBy=" + createdBy + ", updatedBy=" + updatedBy + ", createdDate=" + createdDate
				+ ", updatedDate=" + updatedDate + ", orderDetails=" + orderDetails + ", prescriptionId="
				+ prescriptionId + ", prescriptionIds=" + prescriptionIds + ", lat=" + lat + ", lng=" + lng
				+ ", pinCode=" + pinCode + ", prescriptions=" + prescriptions + ", address=" + address
				+ ", addressShipping=" + addressShipping + ", orderBenefits=" + orderBenefits
				+ ", membershipFeatureIds=" + Arrays.toString(membershipFeatureIds) + "]";
	}

}
