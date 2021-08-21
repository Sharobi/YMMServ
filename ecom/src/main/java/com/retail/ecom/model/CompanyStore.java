package com.retail.ecom.model;

import java.util.List;

public class CompanyStore {
	List<Integer> companyIds;
	List<Integer> storeIds;
	List<Integer> globalIds;
	public List<Integer> getCompanyIds() {
		return companyIds;
	}
	public void setCompanyIds(List<Integer> companyIds) {
		this.companyIds = companyIds;
	}
	public List<Integer> getStoreIds() {
		return storeIds;
	}
	public void setStoreIds(List<Integer> storeIds) {
		this.storeIds = storeIds;
	}
	public List<Integer> getGlobalIds() {
		return globalIds;
	}
	public void setGlobalIds(List<Integer> globalIds) {
		this.globalIds = globalIds;
	}
	@Override
	public String toString() {
		return "CompanyStore [companyIds=" + companyIds + ", storeIds=" + storeIds + ", globalIds=" + globalIds + "]";
	}
	public CompanyStore(List<Integer> companyIds, List<Integer> storeIds, List<Integer> globalIds) {
		super();
		this.companyIds = companyIds;
		this.storeIds = storeIds;
		this.globalIds = globalIds;
	}
}
