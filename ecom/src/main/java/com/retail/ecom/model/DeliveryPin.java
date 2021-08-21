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
@Table(name="in_m_delivery_agent_pin_mapping")
public class DeliveryPin {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="delivery_agent_id")
	private Integer deliveryAgentId;
	
	@Column(name="pincode")
	private String pincode;
	
	@Column(name="zone_id",columnDefinition="int(11) default '0'")
	private Integer zoneId;
	
	@Column(name="area_id",columnDefinition="int(11) default '0'")
	private Integer areaId;
	
	@Column(name="is_active",columnDefinition="int(11) default '0'")
	private Integer isActive;
	
	@Column(name="created_by",columnDefinition="int(11) default '0'")
	private Integer createdBy;
	
	@ManyToOne(optional=true,fetch=FetchType.LAZY)
	@JoinColumn(name="delivery_agent_id",insertable=false,updatable=false)
	@JsonBackReference
	private DeliveryAgent deliveryAgent;
	
	

	public DeliveryPin() {
		
	}
	

	public DeliveryPin(Integer id, Integer deliveryAgentId) {
		super();
		this.id = id;
		this.deliveryAgentId = deliveryAgentId;
		
	}
	
	


	public DeliveryPin(Integer id, Integer deliveryAgentId, String pincode, Integer zoneId, Integer areaId,
			Integer isActive, Integer createdBy, DeliveryAgent deliveryAgent) {
		super();
		this.id = id;
		this.deliveryAgentId = deliveryAgentId;
		this.pincode = pincode;
		this.zoneId = zoneId;
		this.areaId = areaId;
		this.isActive = isActive;
		this.createdBy = createdBy;
		this.deliveryAgent = deliveryAgent;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDeliveryAgentId() {
		return deliveryAgentId;
	}

	public void setDeliveryAgentId(Integer deliveryAgentId) {
		this.deliveryAgentId = deliveryAgentId;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public Integer getZoneId() {
		return zoneId;
	}

	public void setZoneId(Integer zoneId) {
		this.zoneId = zoneId;
	}

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public Integer getIsActive() {
		return isActive;
	}

	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public DeliveryAgent getDeliveryAgent() {
		return deliveryAgent;
	}

	public void setDeliveryAgent(DeliveryAgent deliveryAgent) {
		this.deliveryAgent = deliveryAgent;
	}

	@Override
	public String toString() {
		return "DeliveryPin [id=" + id + ", deliveryAgentId=" + deliveryAgentId + ", pincode=" + pincode + ", zoneId="
				+ zoneId + ", areaId=" + areaId + ", isActive=" + isActive + ", createdBy=" + createdBy
				+ ", deliveryAgent=" + deliveryAgent + ", getId()=" + getId() + ", getDeliveryAgentId()="
				+ getDeliveryAgentId() + ", getPincode()=" + getPincode() + ", getZoneId()=" + getZoneId()
				+ ", getAreaId()=" + getAreaId() + ", getIsActive()=" + getIsActive() + ", getCreatedBy()="
				+ getCreatedBy() + ", getDeliveryAgent()=" + getDeliveryAgent() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

	
	


}
