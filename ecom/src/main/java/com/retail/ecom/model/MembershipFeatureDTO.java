package com.retail.ecom.model;

public class MembershipFeatureDTO {
	private Integer featureId;
	private Double conditionLimitAmount;
	private Double planAmount;
	private Integer factor;
	public Integer getFeatureId() {
		return featureId;
	}
	public void setFeatureId(Integer featureId) {
		this.featureId = featureId;
	}
	public Double getConditionLimitAmount() {
		return conditionLimitAmount;
	}
	public void setConditionLimitAmount(Double conditionLimitAmount) {
		this.conditionLimitAmount = conditionLimitAmount;
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
	@Override
	public String toString() {
		return "MembershipFeatureDTO [featureId=" + featureId + ", conditionLimitAmount=" + conditionLimitAmount
				+ ", planAmount=" + planAmount + ", factor=" + factor + "]";
	}
}
