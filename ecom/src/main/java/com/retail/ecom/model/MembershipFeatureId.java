package com.retail.ecom.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class MembershipFeatureId implements Serializable {
	
	private static final long serialVersionUID = -1589571417118608702L;
	@Column(name="membership_id")
	private Integer membershipId;
	@Column(name="feature_id")
	private Integer featureId;
	public Integer getMembershipId() {
		return membershipId;
	}
	
	public MembershipFeatureId() {
		super();
	}

	public MembershipFeatureId(Integer membershipId, Integer featureId) {
		super();
		this.membershipId = membershipId;
		this.featureId = featureId;
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((featureId == null) ? 0 : featureId.hashCode());
		result = prime * result + ((membershipId == null) ? 0 : membershipId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MembershipFeatureId other = (MembershipFeatureId) obj;
		if (featureId == null) {
			if (other.featureId != null)
				return false;
		} else if (!featureId.equals(other.featureId))
			return false;
		if (membershipId == null) {
			if (other.membershipId != null)
				return false;
		} else if (!membershipId.equals(other.membershipId))
			return false;
		return Objects.equals(membershipId, other.membershipId) && Objects.equals(featureId, other.featureId);
	}

	@Override
	public String toString() {
		return "MembershipFeatureId [membershipId=" + membershipId + ", featureId=" + featureId + "]";
	}
}
