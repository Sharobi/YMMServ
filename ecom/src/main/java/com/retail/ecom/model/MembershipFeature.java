package com.retail.ecom.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="gen_t_membership_feature_mapping")
public class MembershipFeature {
	
	/*@EmbeddedId
	private MembershipFeatureId membershipFeatureId;*/
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="membership_id")
	private Integer membershipId;
	@Column(name="feature_id")
	private Integer featureId;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="membership_id",insertable=false,updatable=false)
//	@MapsId("membershipId")
	private Membership membership;
	
	@ManyToOne
	@JoinColumn(name="feature_id",insertable=false,updatable=false)
//	@MapsId("featureId")
	private Feature feature;

	private Double minAmount;
	private Double maxAmount;
//	private Double conditionLimitAmount;
	private Double planAmount;
	private Integer factor;
	@Column(name="is_active", columnDefinition="int(11) default '1'", insertable=false)
	private int isActive;
	
	private Integer activityId;
	
	@Transient
	private int delete;
	
	public MembershipFeature() {
		super();
	}
	/*public MembershipFeature(Membership membership, Feature feature) {
		this.membership = membership;
		this.feature = feature;
		membershipFeatureId = new MembershipFeatureId(membership.getId(),feature.getId());
	}
	
	public MembershipFeature(MembershipFeatureId membershipFeatureId,
			Double conditionLimitAmount, Double planAmount, Integer factor) {
		super();
		this.membershipFeatureId = membershipFeatureId;
		this.conditionLimitAmount = conditionLimitAmount;
		this.planAmount = planAmount;
		this.factor = factor;
	}
	*/
	public Membership getMembership() {
		return membership;
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMembershipId() {
		return membershipId;
	}

	public void setMembershipId(Integer membershipId) {
		this.membershipId = membershipId;
	}

	public Integer getFeatureId() {
		return featureId;
	}

	public void setFeatureId(Integer featureId) {
		this.featureId = featureId;
	}

	public void setMembership(Membership membership) {
		this.membership = membership;
	}
	public Feature getFeature() {
		return feature;
	}
	public void setFeature(Feature feature) {
		this.feature = feature;
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
	public Double getPlanAmount() {
		return planAmount;
	}
	public void setPlanAmount(Double planAmount) {
		this.planAmount = planAmount;
	}
	public Integer getFactor() {
		return factor;
	}
	public void setFactor(Integer factor) {
		this.factor = factor;
	}
	public int getIsActive() {
		return isActive;
	}
	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}
	public int getDelete() {
		return delete;
	}
	public void setDelete(int delete) {
		this.delete = delete;
	}
	public Integer getActivityId() {
		return activityId;
	}
	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}
	@Override
	public String toString() {
		return "MembershipFeature [id=" + id + ", membershipId=" + membershipId + ", featureId=" + featureId
				+ ", membership=" + membership + ", feature=" + feature + ", minAmount=" + minAmount + ", maxAmount="
				+ maxAmount + ", planAmount=" + planAmount + ", factor=" + factor + ", isActive=" + isActive
				+ ", activityId=" + activityId + ", delete=" + delete + "]";
	}
}
