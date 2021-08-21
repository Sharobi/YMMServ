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
@Table(name="in_m_geo_locations")
public class LatLngGeoLocations {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String countryCode;
	private Integer pinCode;
	private String placeName;
	private String stateName;
	private Integer stateCode;
	private String provinceName;
	private Integer provinceCode;
	private String communityName;
	private Integer communityCode;
	private Double latitude;
	private Double longitude;
	private Integer accuracy;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public Integer getPinCode() {
		return pinCode;
	}
	public void setPinCode(Integer pinCode) {
		this.pinCode = pinCode;
	}
	public String getPlaceName() {
		return placeName;
	}
	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public Integer getStateCode() {
		return stateCode;
	}
	public void setStateCode(Integer stateCode) {
		this.stateCode = stateCode;
	}
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	public Integer getProvinceCode() {
		return provinceCode;
	}
	public void setProvinceCode(Integer provinceCode) {
		this.provinceCode = provinceCode;
	}
	public String getCommunityName() {
		return communityName;
	}
	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}
	public Integer getCommunityCode() {
		return communityCode;
	}
	public void setCommunityCode(Integer communityCode) {
		this.communityCode = communityCode;
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
	public Integer getAccuracy() {
		return accuracy;
	}
	public void setAccuracy(Integer accuracy) {
		this.accuracy = accuracy;
	}
	@Override
	public String toString() {
		return "LatLngGeoLocations [id=" + id + ", countryCode=" + countryCode + ", pinCode=" + pinCode + ", placeName="
				+ placeName + ", stateName=" + stateName + ", stateCode=" + stateCode + ", provinceName=" + provinceName
				+ ", provinceCode=" + provinceCode + ", communityName=" + communityName + ", communityCode="
				+ communityCode + ", latitude=" + latitude + ", longitude=" + longitude + ", accuracy=" + accuracy
				+ "]";
	}
}
