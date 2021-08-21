package com.retail.ecom.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="in_m_sub_category")
@JsonIgnoreProperties(value= {"companyId","isDeleted","createdBy","createdDate","updatedDate","updatedBy"})
public class SubCategory {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
    
	@Column(name="category_id")
    private int categoryId;
    
    private int parentId;
    
    private String name;
    
    private int companyId;

    private int isDeleted;

	@Transient
    private int createdBy;

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
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="category_id",insertable=false,updatable=false)
	@JsonBackReference
	private Category category;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	public int getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}
	public int getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Integer getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	@Override
	public String toString() {
		return "SubCategory [id=" + id + ", categoryId=" + categoryId + ", parentId=" + parentId + ", name=" + name
				+ ", companyId=" + companyId + ", isDeleted=" + isDeleted + ", createdBy=" + createdBy
				+ ", createdDate=" + createdDate + ", updatedBy=" + updatedBy + ", updatedDate=" + updatedDate
				+ ", category=" + category + "]";
	}
	public SubCategory(int id, String name,int categoryId) {
		super();
		this.id = id;
		this.name = name;
		this.categoryId = categoryId;
	}
	public SubCategory() {
		super();
	}
	

}
