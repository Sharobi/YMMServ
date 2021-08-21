package com.retail.ecom.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="customer_order_benefit_tracking")
public class OrderBenefit {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name="order_id")
	private Integer orderId;
	private Integer activityId;
	private Integer membershipFeatureMapId;
	private Double discPerc;
	private Double discAmount;
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonBackReference
	@JoinColumn(name="order_id",insertable=false,updatable=false)
	Order order;
	
	public OrderBenefit() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer getActivityId() {
		return activityId;
	}
	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}
	public Integer getMembershipFeatureMapId() {
		return membershipFeatureMapId;
	}
	public void setMembershipFeatureMapId(Integer membershipFeatureMapId) {
		this.membershipFeatureMapId = membershipFeatureMapId;
	}
	public Double getDiscPerc() {
		return discPerc;
	}
	public void setDiscPerc(Double discPerc) {
		this.discPerc = discPerc;
	}
	public Double getDiscAmount() {
		return discAmount;
	}
	public void setDiscAmount(Double discAmount) {
		this.discAmount = discAmount;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	@Override
	public String toString() {
		return "OrderBenefit [id=" + id + ", orderId=" + orderId + ", activityId=" + activityId
				+ ", membershipFeatureMapId=" + membershipFeatureMapId + ", discPerc=" + discPerc + ", discAmount="
				+ discAmount + ", order=" + order + "]";
	}
}
