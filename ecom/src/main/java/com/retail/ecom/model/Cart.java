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
@Table(name="sal_t_cart_details")
public class Cart {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private Integer customerId;
	private Integer itemId;
	private String name;
	private Integer packUnitId;
	private double packQty;
	private Integer conversion;
	private Integer looseQty;
	private double freeQty;
	private Integer isDeleted;
	private Integer status;
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	private Date createdDate;
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	private Date updatedDate;
	public Cart() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public Integer getItemId() {
		return itemId;
	}
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getPackUnitId() {
		return packUnitId;
	}
	public void setPackUnitId(Integer packUnitId) {
		this.packUnitId = packUnitId;
	}
	public double getPackQty() {
		return packQty;
	}
	public void setPackQty(double packQty) {
		this.packQty = packQty;
	}
	public Integer getConversion() {
		return conversion;
	}
	public void setConversion(Integer conversion) {
		this.conversion = conversion;
	}
	public Integer getLooseQty() {
		return looseQty;
	}
	public void setLooseQty(Integer looseQty) {
		this.looseQty = looseQty;
	}
	public double getFreeQty() {
		return freeQty;
	}
	public void setFreeQty(double freeQty) {
		this.freeQty = freeQty;
	}
	public Integer getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
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
		return "Cart [id=" + id + ", customerId=" + customerId + ", itemId=" + itemId + ", name=" + name
				+ ", packUnitId=" + packUnitId + ", packQty=" + packQty + ", conversion=" + conversion + ", looseQty="
				+ looseQty + ", freeQty=" + freeQty + ", isDeleted=" + isDeleted + ", status=" + status
				+ ", createdDate=" + createdDate + ", updatedDate=" + updatedDate + "]";
	}
}
