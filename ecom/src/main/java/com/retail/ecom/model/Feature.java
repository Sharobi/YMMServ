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

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="gen_m_membership_features")
public class Feature {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String name;
	@Column(name="is_active", columnDefinition="int(11) default 1" , insertable=false)
	private int isActive;
	
	@JsonBackReference
	@OneToMany(mappedBy="feature",cascade=CascadeType.ALL,orphanRemoval=true)
	private List<MembershipFeature> membershipFeatures;
	
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
	public int getIsActive() {
		return isActive;
	}
	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}
	public List<MembershipFeature> getMembershipFeatures() {
		return membershipFeatures;
	}
	public void setMembershipFeatures(List<MembershipFeature> membershipFeatures) {
		this.membershipFeatures = membershipFeatures;
	}
	@Override
	public String toString() {
		return "Feature [id=" + id + ", name=" + name + ", isActive=" + isActive + ", membershipFeatures="
				+ membershipFeatures + "]";
	}
}
