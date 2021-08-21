package com.retail.ecom.model;

public class ItemSync {
	private int itemId;
	private double packQty;
	private int companyId;
	private int storeId;
	public ItemSync() {
		super();
	}
	
	public ItemSync(int itemId, double packQty, int companyId, int storeId) {
		super();
		this.itemId = itemId;
		this.packQty = packQty;
		this.companyId = companyId;
		this.storeId = storeId;
	}

	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	
	public double getPackQty() {
		return packQty;
	}

	public void setPackQty(double packQty) {
		this.packQty = packQty;
	}

	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	public int getStoreId() {
		return storeId;
	}
	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	@Override
	public String toString() {
		return "ItemSync [itemId=" + itemId + ", packQty=" + packQty + ", companyId=" + companyId + ", storeId="
				+ storeId + "]";
	}
	
}
