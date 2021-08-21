package com.retail.ecom.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="gen_m_point")
public class Point {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name="offer_id")
	private Integer offerId;
	private Double minAmount;
	private Double maxAmount;
	private Integer points;
	private int factor;
	
	@JsonBackReference
	@ManyToOne(optional=true)
	@JoinColumn(name="offer_id",insertable=false,updatable=false)
	private Offer offer;
	
	public Point() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getOfferId() {
		return offerId;
	}
	public void setOfferId(Integer offerId) {
		this.offerId = offerId;
	}
	public Double getMinAmount() {
		return minAmount;
	}
	public void setMinAmount(Double minAmount) {
		this.minAmount = minAmount;
	}
	public Double getMaxAmount() {
		return maxAmount;
	}
	public void setMaxAmount(Double maxAmount) {
		this.maxAmount = maxAmount;
	}
	public Integer getPoints() {
		return points;
	}
	public void setPoints(Integer points) {
		this.points = points;
	}
	public Offer getOffer() {
		return offer;
	}
	public void setOffer(Offer offer) {
		this.offer = offer;
	}
	public int getFactor() {
		return factor;
	}
	public void setFactor(int factor) {
		this.factor = factor;
	}
	@Override
	public String toString() {
		return "Point [id=" + id + ", offerId=" + offerId + ", minAmount=" + minAmount + ", maxAmount=" + maxAmount
				+ ", points=" + points + ", factor=" + factor + ", offer=" + offer + "]";
	}
}
