package com.retail.ecom.model;

public class StoreBasicDTO {
	private Integer id;
	private String name;
	private Double latitude;
	private Double longitude;
	private String address;
	private Integer companyId;
	private Integer storeId;
	public StoreBasicDTO() {
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
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "StoreBasicDTO [id=" + id + ", name=" + name + ", latitude=" + latitude + ", longitude=" + longitude
				+ ", address=" + address + "]";
	}
	public StoreBasicDTO(Integer id, String name, Double latitude, Double longitude, String address) {
		super();
		this.id = id;
		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
		this.address = address;
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
	public StoreBasicDTO(Integer id, String name, Double latitude, Double longitude, String address, Integer companyId,
			Integer storeId) {
		super();
		this.id = id;
		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
		this.address = address;
		this.companyId = companyId;
		this.storeId = storeId;
	}
	
}
