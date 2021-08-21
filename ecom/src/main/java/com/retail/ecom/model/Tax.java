package com.retail.ecom.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="in_m_tax")
public class Tax {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private Double percentage;
	private String description;
	private Integer isGroup;
	private String taxMode;
	@Transient
	private Integer isDeleted;
	public Tax() {
		super();
	}
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
	public Double getPercentage() {
		return percentage;
	}
	public void setPercentage(Double percentage) {
		this.percentage = percentage;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getIsGroup() {
		return isGroup;
	}
	public void setIsGroup(Integer isGroup) {
		this.isGroup = isGroup;
	}
	public String getTaxMode() {
		return taxMode;
	}
	public void setTaxMode(String taxMode) {
		this.taxMode = taxMode;
	}
	public Integer getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}
	@Override
	public String toString() {
		return "Tax [id=" + id + ", name=" + name + ", percentage=" + percentage + ", description=" + description
				+ ", isGroup=" + isGroup + ", taxMode=" + taxMode + ", isDeleted=" + isDeleted + "]";
	}
}
