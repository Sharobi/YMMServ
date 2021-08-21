package com.retail.ecom.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "sal_t_delivery_agent_tracking")
public class DeliveryAgentTracking {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "sale_order_id")
	private int saleOrderId;

	@Column(name = "sale_order_details_id")
	private int saleOrderDetailsId;
	
	@Column(name = "sale_order_date")
	private String saleOrderDate;
	
	@Column(name = "delivery_agent_id")
	private int deliveryAgentId;
	
	@Column(name = "delivery_type")
	private String deliveryType;
	@Column(name = "item_id")
	private int itemId;
	private double packQty;
	private int conversion;
	private int looseQty;
	private double freeQty;
	private double grossAmount;
	private double disc;
	private double taxAmount;
	private double netAmount;
	private int storeId;
	private int companyId;
	@Column(name = "delivery_schedule_date")
	private String deliveryScheduleDate;
	@Column(name = "delivery_pickup_date")
	private String deliveryPickupDate;
	@Column(name = "delivery_pickup_time")
	private String deliveryPickupTime;
	@Column(name = "delivery_date")
	private String deliveryDate;
	@Column(name = "delivery_time")
	private String deliveryTime;
	@Column(name = "delivery_status")
	private int deliveryStatus;
	private int shippingId;
	private int shippingDetailsId;
	private double shippingChargePerc;
	private double shippingCharge;
	@Column(columnDefinition = "int(11) default '0'")
	private int isCancelled;
	private int isReturned;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	private Date createdDate;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	private Date updatedDate;



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getSaleOrderId() {
		return saleOrderId;
	}

	public void setSaleOrderId(int saleOrderId) {
		this.saleOrderId = saleOrderId;
	}

	public int getSaleOrderDetailsId() {
		return saleOrderDetailsId;
	}

	public void setSaleOrderDetailsId(int saleOrderDetailsId) {
		this.saleOrderDetailsId = saleOrderDetailsId;
	}

	public String getSaleOrderDate() {
		return saleOrderDate;
	}

	public void setSaleOrderDate(String saleOrderDate) {
		this.saleOrderDate = saleOrderDate;
	}

	public int getDeliveryAgentId() {
		return deliveryAgentId;
	}

	public void setDeliveryAgentId(int deliveryAgenId) {
		this.deliveryAgentId = deliveryAgenId;
	}

	public String getDeliveryType() {
		return deliveryType;
	}

	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public double getPackQty() {
		return packQty;
	}

	public void setPackQty(double packQty) {
		this.packQty = packQty;
	}

	public int getConversion() {
		return conversion;
	}

	public void setConversion(int conversion) {
		this.conversion = conversion;
	}

	public int getLooseQty() {
		return looseQty;
	}

	public void setLooseQty(int looseQty) {
		this.looseQty = looseQty;
	}

	public double getFreeQty() {
		return freeQty;
	}

	public void setFreeQty(double freeQty) {
		this.freeQty = freeQty;
	}

	public double getGrossAmount() {
		return grossAmount;
	}

	public void setGrossAmount(double grossAmount) {
		this.grossAmount = grossAmount;
	}

	public double getDisc() {
		return disc;
	}

	public void setDisc(double disc) {
		this.disc = disc;
	}

	public double getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(double taxAmount) {
		this.taxAmount = taxAmount;
	}

	public double getNetAmount() {
		return netAmount;
	}

	public void setNetAmount(double netAmount) {
		this.netAmount = netAmount;
	}

	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public String getDeliveryScheduleDate() {
		return deliveryScheduleDate;
	}

	public void setDeliveryScheduleDate(String deliveryScheduleDate) {
		this.deliveryScheduleDate = deliveryScheduleDate;
	}

	public String getDeliveryPickupDate() {
		return deliveryPickupDate;
	}

	public void setDeliveryPickupDate(String deliveryPickupDate) {
		this.deliveryPickupDate = deliveryPickupDate;
	}

	public String getDeliveryPickupTime() {
		return deliveryPickupTime;
	}

	public void setDeliveryPickupTime(String deliveryPickupTime) {
		this.deliveryPickupTime = deliveryPickupTime;
	}

	public String getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(String deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public int getDeliveryStatus() {
		return deliveryStatus;
	}

	public void setDeliveryStatus(int deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

	public int getShippingId() {
		return shippingId;
	}

	public void setShippingId(int shippingId) {
		this.shippingId = shippingId;
	}

	public int getShippingDetailsId() {
		return shippingDetailsId;
	}

	public void setShippingDetailsId(int shippingDetailsId) {
		this.shippingDetailsId = shippingDetailsId;
	}

	public double getShippingChargePerc() {
		return shippingChargePerc;
	}

	public void setShippingChargePerc(double shippingChargePerc) {
		this.shippingChargePerc = shippingChargePerc;
	}

	public double getShippingCharge() {
		return shippingCharge;
	}

	public void setShippingCharge(double shippingCharge) {
		this.shippingCharge = shippingCharge;
	}

	public int getIsCancelled() {
		return isCancelled;
	}

	public void setIsCancelled(int isCancelled) {
		this.isCancelled = isCancelled;
	}

	public int getIsReturned() {
		return isReturned;
	}

	public void setIsReturned(int isReturned) {
		this.isReturned = isReturned;
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

	@Override
	public String toString() {
		return "DeliveryAgentTracking [id=" + id + ", saleOrderId=" + saleOrderId + ", saleOrderDetailsId="
				+ saleOrderDetailsId + ", saleOrderDate=" + saleOrderDate + ", deliveryAgentId=" + deliveryAgentId
				+ ", deliveryType=" + deliveryType + ", itemId=" + itemId + ", packQty=" + packQty + ", conversion="
				+ conversion + ", looseQty=" + looseQty + ", freeQty=" + freeQty + ", grossAmount=" + grossAmount
				+ ", disc=" + disc + ", taxAmount=" + taxAmount + ", netAmount=" + netAmount + ", storeId=" + storeId
				+ ", companyId=" + companyId + ", deliveryScheduleDate=" + deliveryScheduleDate
				+ ", deliveryPickupDate=" + deliveryPickupDate + ", deliveryPickupTime=" + deliveryPickupTime
				+ ", deliveryDate=" + deliveryDate + ", deliveryTime=" + deliveryTime + ", deliveryStatus="
				+ deliveryStatus + ", shippingId=" + shippingId + ", shippingDetailsId=" + shippingDetailsId
				+ ", shippingChargePerc=" + shippingChargePerc + ", shippingCharge=" + shippingCharge + ", isCancelled="
				+ isCancelled + ", isReturned=" + isReturned + ", createdDate=" + createdDate + ", updatedDate="
				+ updatedDate + "]";
	}

}
