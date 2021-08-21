package com.retail.ecom.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ItemDetailsMaxSaleDTO {
	private Integer id;
	//private Integer customerId;
	private Long maxSaleItemsNo;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Long getMaxSaleItemsNo() {
		return maxSaleItemsNo;
	}
	public void setMaxSaleItemsNo(Long maxSaleItemsNo) {
		this.maxSaleItemsNo = maxSaleItemsNo;
	}
	public ItemDetailsMaxSaleDTO(Integer id, Long maxSaleItemsNo) {
		super();
		this.id = id;
		this.maxSaleItemsNo = maxSaleItemsNo;
	}
	
}
