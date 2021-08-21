package com.retail.ecom.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="gen_m_membership")
public class Membership {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private Double pointMin;
	@Column(name="is_active", columnDefinition="int(11) default '1'", insertable=false)
	private Integer isActive;
	
	@OneToMany(mappedBy="membership",cascade=CascadeType.ALL,orphanRemoval=true)
	private List<MembershipFeature> membershipFeatures;
	
	@Transient
	private List<MembershipFeatureDTO> membershipFeatureDTOs; 
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getIsActive() {
		return isActive;
	}
	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}
	public List<MembershipFeature> getMembershipFeatures() {
		return membershipFeatures;
	}
	public void setMembershipFeatures(List<MembershipFeature> membershipFeatures) {
		this.membershipFeatures = membershipFeatures;
	}
	public List<MembershipFeatureDTO> getMembershipFeatureDTOs() {
		return membershipFeatureDTOs;
	}
	public void setMembershipFeatureDTOs(List<MembershipFeatureDTO> membershipFeatureDTOs) {
		this.membershipFeatureDTOs = membershipFeatureDTOs;
	}
	public Double getPointMin() {
		return pointMin;
	}
	public void setPointMin(Double pointMin) {
		this.pointMin = pointMin;
	}
	@Override
	public String toString() {
		return "Membership [id=" + id + ", name=" + name + ", pointMin=" + pointMin + ", isActive=" + isActive
				+ ", membershipFeatures=" + membershipFeatures + ", membershipFeatureDTOs=" + membershipFeatureDTOs
				+ "]";
	}
}