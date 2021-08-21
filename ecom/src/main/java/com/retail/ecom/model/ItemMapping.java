package com.retail.ecom.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="in_t_item_mapping")
public class ItemMapping {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name="global_item_id")
	private Integer globalItemId;
	private Integer localItemId;
	private Integer companyId;
	private Integer storeId;
	private Integer currentPackQty;
	private Integer currentLooseQty;
	
	/*@OneToMany(fetch=FetchType.LAZY)
	@JoinColumn(name="id",referencedColumnName="global_item_id",insertable=false,updatable=false)*/
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="global_item_id",insertable=false,updatable=false)
	private Item item;
	
	public ItemMapping(Integer id) {
		super();
		this.id = id;
	}
	public ItemMapping() {
		super();
	}
	
	public ItemMapping(Integer id, Integer globalItemId, Integer currentPackQty) { //24-10-2019
		super();
		this.id = id;
		this.globalItemId = globalItemId;
		this.currentPackQty = currentPackQty;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getGlobalItemId() {
		return globalItemId;
	}
	public void setGlobalItemId(Integer globalItemId) {
		this.globalItemId = globalItemId;
	}
	public Integer getLocalItemId() {
		return localItemId;
	}
	public void setLocalItemId(Integer localItemId) {
		this.localItemId = localItemId;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	public Integer getCurrentPackQty() {
		return currentPackQty;
	}
	public void setCurrentPackQty(Integer currentPackQty) {
		this.currentPackQty = currentPackQty;
	}
	public Integer getCurrentLooseQty() {
		return currentLooseQty;
	}
	public void setCurrentLooseQty(Integer currentLooseQty) {
		this.currentLooseQty = currentLooseQty;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	@Override
	public String toString() {
		return "ItemMapping [id=" + id + ", globalItemId=" + globalItemId + ", localItemId=" + localItemId
				+ ", companyId=" + companyId + ", storeId=" + storeId + ", currentPackQty=" + currentPackQty
				+ ", currentLooseQty=" + currentLooseQty + ", item=" + item + "]";
	}
}
