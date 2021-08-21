package com.retail.ecom.model;

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
@Table(name="in_m_group")
@JsonIgnoreProperties(value={"companyId","companyId","isDeleted","createdBy","createdDate","updatedBy","updatedDate"})
public class Group {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	

	@Column(name="category_id")
    private int categoryId;
	private String categoryName;
    private String name;
    private String description;
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
    private int updatedBy;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public int getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	
	//01-01-2019
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
	/*
	public Group(int id, String name, String description, int companyId, int isDeleted, int createdBy, Date createdDate,
			int updatedBy, Date updatedDate) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.companyId = companyId;
		this.isDeleted = isDeleted;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.updatedBy = updatedBy;
		this.updatedDate = updatedDate;
	}*/
	
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public Group() {
		super();
	}
	public Group(int id, int categoryId, String name, String description, int companyId, int isDeleted, int createdBy,
			Date createdDate, int updatedBy, Date updatedDate, Category category) {
		super();
		this.id = id;
		this.categoryId = categoryId;
		this.name = name;
		this.description = description;
		this.companyId = companyId;
		this.isDeleted = isDeleted;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.updatedBy = updatedBy;
		this.updatedDate = updatedDate;
		this.category = category;
	}
	
	public Group(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public Group(Integer id,String name,Integer categoryId,String categoryName) {
		super();
		this.id = id;
		this.name = name;
		this.categoryId = categoryId;
		this.categoryName = categoryName;
	}
	public Group(int id, String name, int categoryId) {
		super();
		this.id = id;
		this.categoryId = categoryId;
		this.name = name;
	}
	@Override
	public String toString() {
		return "Group [id=" + id + ", categoryId=" + categoryId + ", categoryName=" + categoryName + ", name=" + name
				+ ", description=" + description + ", companyId=" + companyId + ", isDeleted=" + isDeleted
				+ ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", updatedBy=" + updatedBy
				+ ", updatedDate=" + updatedDate + ", category=" + category + "]";
	}

    
}
