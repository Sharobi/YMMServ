package com.retail.ecom.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="point_tracking")
public class PointTracking {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private Integer uid;
	private Integer orderId;
	private Double pointAmount;
	private Double pointValue;
	private Integer factor;
	private Integer activityId;
	private Integer offerId;
	private Integer pointId;
	private Date createdDate;
	
	public PointTracking() { }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Double getPointAmount() {
		return pointAmount;
	}

	public void setPointAmount(Double pointAmount) {
		this.pointAmount = pointAmount;
	}

	public Double getPointValue() {
		return pointValue;
	}

	public void setPointValue(Double pointValue) {
		this.pointValue = pointValue;
	}

	public Integer getFactor() {
		return factor;
	}

	public void setFactor(Integer factor) {
		this.factor = factor;
	}

	public Integer getActivityId() {
		return activityId;
	}

	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}

	public Integer getOfferId() {
		return offerId;
	}

	public void setOfferId(Integer offerId) {
		this.offerId = offerId;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getPointId() {
		return pointId;
	}

	public void setPointId(Integer pointId) {
		this.pointId = pointId;
	}

	@Override
	public String toString() {
		return "PointTracking [id=" + id + ", uid=" + uid + ", orderId=" + orderId + ", pointAmount=" + pointAmount
				+ ", pointValue=" + pointValue + ", factor=" + factor + ", activityId=" + activityId + ", offerId="
				+ offerId + ", createdDate=" + createdDate + "]";
	}
	
}
