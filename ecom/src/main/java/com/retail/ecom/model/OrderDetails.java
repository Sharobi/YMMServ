package com.retail.ecom.model;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "sal_t_sale_order_details")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class OrderDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "sale_order_id")
	private int saleOrderId;
	@Column(name = "item_id")
	private int itemId;
	@Transient
	private String itemName;
	private int packUnitId;
	private double packQty;
	private int conversion;
	private int looseQty;
	private double freeQty;
	private double mrp;
	private double rate;
	private double saleRate;
	private double grossAmount;
	private double discPer;
	private double disc;
	private int taxId;
	private int taxTypeId;
	private double taxPercentage;
	private double taxAmount;
	private int isGroupTax;
	private String taxMode;
	private double netAmount;
	private double itemTotalMrp;
	private int itemFreeAgainstItem;
	private int storeId;
	private int companyId;
	
	@Column(name = "delivery_type")
	private String deliveryType;
	
	@Column(columnDefinition = "int(11) default '0'")
	private int status;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonBackReference
	@JoinColumn(name = "sale_order_id", insertable = false, updatable = false)
	Order order;
	
	@Transient
	private Store store;

	@Transient
	private Integer cartId;
	@Column(name = "batch_no")
	private String batchNo;
	
	@Column(name = "expiry_date")
	private String expiryDate;
	
	@Column(name = "pickup_date")
	private String pickupDate;

	@Column(name = "pickup_time ")
	private String pickupTime;
	
	@Column(name = "delivery_agent_id")
	private int deliveryAgentId;

	/*
	 * @ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	 * 
	 * @JoinColumn(name="item_id",insertable=false,updatable=false) private Item
	 * item;
	 */
	

	public OrderDetails() {
		super();
	}

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

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public int getPackUnitId() {
		return packUnitId;
	}

	public void setPackUnitId(int packUnitId) {
		this.packUnitId = packUnitId;
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

	public double getMrp() {
		return mrp;
	}

	public void setMrp(double mrp) {
		this.mrp = mrp;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public double getSaleRate() {
		return saleRate;
	}

	public void setSaleRate(double saleRate) {
		this.saleRate = saleRate;
	}

	public double getGrossAmount() {
		return grossAmount;
	}

	public void setGrossAmount(double grossAmount) {
		this.grossAmount = grossAmount;
	}

	public double getDiscPer() {
		return discPer;
	}

	public void setDiscPer(double discPer) {
		this.discPer = discPer;
	}

	public double getDisc() {
		return disc;
	}

	public void setDisc(double disc) {
		this.disc = disc;
	}

	public int getTaxId() {
		return taxId;
	}

	public void setTaxId(int taxId) {
		this.taxId = taxId;
	}

	public int getTaxTypeId() {
		return taxTypeId;
	}

	public void setTaxTypeId(int taxTypeId) {
		this.taxTypeId = taxTypeId;
	}

	public double getTaxPercentage() {
		return taxPercentage;
	}

	public void setTaxPercentage(double taxPercentage) {
		this.taxPercentage = taxPercentage;
	}

	public double getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(double taxAmount) {
		this.taxAmount = taxAmount;
	}

	public int getIsGroupTax() {
		return isGroupTax;
	}

	public void setIsGroupTax(int isGroupTax) {
		this.isGroupTax = isGroupTax;
	}

	public String getTaxMode() {
		return taxMode;
	}

	public void setTaxMode(String taxMode) {
		this.taxMode = taxMode;
	}

	public double getNetAmount() {
		return netAmount;
	}

	public void setNetAmount(double netAmount) {
		this.netAmount = netAmount;
	}

	public double getItemTotalMrp() {
		return itemTotalMrp;
	}

	public void setItemTotalMrp(double itemTotalMrp) {
		this.itemTotalMrp = itemTotalMrp;
	}

	public int getItemFreeAgainstItem() {
		return itemFreeAgainstItem;
	}

	public void setItemFreeAgainstItem(int itemFreeAgainstItem) {
		this.itemFreeAgainstItem = itemFreeAgainstItem;
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

	public String getDeliveryType() {
		return deliveryType;
	}

	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Integer getCartId() {
		return cartId;
	}

	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getPickupDate() {
		return pickupDate;
	}

	public void setPickupDate(String pickupDate) {
		this.pickupDate = pickupDate;
	}

	public String getPickupTime() {
		return pickupTime;
	}

	public void setPickupTime(String pickupTime) {
		this.pickupTime = pickupTime;
	}
	
	public Integer getDeliveryAgentId() {
		return deliveryAgentId;
	}

	public void setDeliveryAgentId(Integer deliveryAgentId) {
		this.deliveryAgentId = deliveryAgentId;
	}
	
	public void setDeliveryAgentId(int deliveryAgentId) {
		this.deliveryAgentId = deliveryAgentId;
	}
	
	

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	@Override
	public String toString() {
		return "OrderDetails [id=" + id + ", saleOrderId=" + saleOrderId + ", itemId=" + itemId + ", itemName="
				+ itemName + ", packUnitId=" + packUnitId + ", packQty=" + packQty + ", conversion=" + conversion
				+ ", looseQty=" + looseQty + ", freeQty=" + freeQty + ", mrp=" + mrp + ", rate=" + rate + ", saleRate="
				+ saleRate + ", grossAmount=" + grossAmount + ", discPer=" + discPer + ", disc=" + disc + ", taxId="
				+ taxId + ", taxTypeId=" + taxTypeId + ", taxPercentage=" + taxPercentage + ", taxAmount=" + taxAmount
				+ ", isGroupTax=" + isGroupTax + ", taxMode=" + taxMode + ", netAmount=" + netAmount + ", itemTotalMrp="
				+ itemTotalMrp + ", itemFreeAgainstItem=" + itemFreeAgainstItem + ", storeId=" + storeId
				+ ", companyId=" + companyId + ", deliveryType=" + deliveryType + ", status=" + status + ", shippingId="
				+ shippingId + ", shippingDetailsId=" + shippingDetailsId + ", shippingChargePerc=" + shippingChargePerc
				+ ", shippingCharge=" + shippingCharge + ", isCancelled=" + isCancelled + ", isReturned=" + isReturned
				+ ", createdDate=" + createdDate + ", updatedDate=" + updatedDate + ", order=" + order + ", store="
				+ store + ", cartId=" + cartId + ", batchNo=" + batchNo + ", expiryDate=" + expiryDate + ", pickupDate="
				+ pickupDate + ", pickupTime=" + pickupTime + ", deliveryAgentId=" + deliveryAgentId + "]";
	}

	



	






}
