package com.retail.ecom.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Entity
@Table(name="in_m_category")
@JsonIgnoreProperties(value= {"companyId","isDeleted","createdBy","createdDate","updatedDate","updatedBy"})
public class Category {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private Integer companyId;
	private Integer isDeleted;
	@Transient
	private Integer createdBy;
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Transient
	@Column(nullable=false)
	private Date createdDate;
	@Transient
	private Integer updatedBy;
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Transient
	@Column(nullable=false)
	private Date updatedDate;
	
	@OneToMany(mappedBy="category",fetch=FetchType.LAZY)
	private Set<SubCategory> subCategories = new HashSet<>();

	@OneToMany(mappedBy="category",fetch=FetchType.LAZY)
	private Set<Group> groups = new HashSet<>();
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the companyId
	 */
	public Integer getCompanyId() {
		return companyId;
	}

	/**
	 * @param companyId the companyId to set
	 */
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	/**
	 * @return the isDeleted
	 */
	public Integer getIsDeleted() {
		return isDeleted;
	}

	/**
	 * @param isDeleted the isDeleted to set
	 */
	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

	/**
	 * @return the createdBy
	 */
	public Integer getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the updatedBy
	 */
	public Integer getUpdatedBy() {
		return updatedBy;
	}

	/**
	 * @return the updatedDate
	 */
	public Date getUpdatedDate() {
		return updatedDate;
	}

	/**
	 * @param updatedDate the updatedDate to set
	 */
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Set<SubCategory> getSubCategories() {
		return subCategories;
	}

	public void setSubCategories(Set<SubCategory> subCategories) {
		this.subCategories = subCategories;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	
	//01-10-2019
	public Set<Group> getGroups() {
		return groups;
	}

	public void setGroups(Set<Group> groups) {
		this.groups = groups;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", companyId=" + companyId + ", isDeleted=" + isDeleted
				+ ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", updatedBy=" + updatedBy
				+ ", updatedDate=" + updatedDate + ", subCategories=" + subCategories + ", groups=" + groups + "]";
	}
	
	public Category() {
		super();
	}

	public Category(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

}
