package com.retail.ecom.model;
// Generated Oct 11, 2018 6:31:29 PM by Hibernate Tools 5.3.6.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * Address generated by hbm2java
 */
@Entity
//@NamedEntityGraph(name="address.without.user",attributeNodes= {@NamedAttributeNode("userId")})
@Table(name="gen_m_user_shipping_address")
public class AddressShipping implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name="user_id")
	private Integer userId;
	private Integer orderId;
	private String receiverName;
	private String streetAddress;
	private Integer countryId;
	private Integer stateId;
	private Integer cityId;
	private String landmark;
	private Integer pincode;
	private Double latitude;
	private Double longitude;
	private String contactPhone;
	private String alternatePhone;
	private String invoiceMail;
	private String alternateInvoiceMail;
	private String type;
	private int isActive;
	private int isDefault;
	
	@OneToOne(optional=true)
	@JoinColumn(name="countryId",insertable=false,updatable=false)
	private Country country;
	
	@OneToOne(optional=true)
	@JoinColumn(name="stateId",insertable=false,updatable=false)
	private State state;
	
	@OneToOne(optional=true)
	@JoinColumn(name="cityId",insertable=false,updatable=false)
	private City city;
	
	@ManyToOne(optional=true,fetch=FetchType.LAZY)
	@JoinColumn(name="user_id",insertable=false,updatable=false)
	@JsonBackReference
	private User user;
	
	public AddressShipping() {
	}

	public AddressShipping(Integer id, Integer userId) {
		super();
		this.id = id;
		this.userId = userId;
	}

	public AddressShipping(Integer id, Integer userId, String streetAddress, Integer countryId, Integer stateId, Integer cityId,
			String landmark, Integer pincode, Double latitude, Double longitude, String contactPhone,
			String alternatePhone, String invoiceMail, String alternateInvoiceMail, int isActive,
			int isDefault, String type) {
		super();
		this.id = id;
		this.userId = userId;
		this.streetAddress = streetAddress;
		this.countryId = countryId;
		this.stateId = stateId;
		this.cityId = cityId;
		this.landmark = landmark;
		this.pincode = pincode;
		this.latitude = latitude;
		this.longitude = longitude;
		this.contactPhone = contactPhone;
		this.alternatePhone = alternatePhone;
		this.invoiceMail = invoiceMail;
		this.alternateInvoiceMail = alternateInvoiceMail;
		this.type = type;
		this.isActive = isActive;
		this.isDefault = isDefault;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	public Integer getStateId() {
		return stateId;
	}

	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public String getLandmark() {
		return landmark;
	}

	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}

	public Integer getPincode() {
		return pincode;
	}

	public void setPincode(Integer pincode) {
		this.pincode = pincode;
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

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getAlternatePhone() {
		return alternatePhone;
	}

	public void setAlternatePhone(String alternatePhone) {
		this.alternatePhone = alternatePhone;
	}

	public String getInvoiceMail() {
		return invoiceMail;
	}

	public void setInvoiceMail(String invoiceMail) {
		this.invoiceMail = invoiceMail;
	}

	public String getAlternateInvoiceMail() {
		return alternateInvoiceMail;
	}

	public void setAlternateInvoiceMail(String alternateInvoiceMail) {
		this.alternateInvoiceMail = alternateInvoiceMail;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public int getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(int isDefault) {
		this.isDefault = isDefault;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "AddressShipping [id=" + id + ", userId=" + userId + ", orderId=" + orderId + ", receiverName="
				+ receiverName + ", streetAddress=" + streetAddress + ", countryId=" + countryId + ", stateId="
				+ stateId + ", cityId=" + cityId + ", landmark=" + landmark + ", pincode=" + pincode + ", latitude="
				+ latitude + ", longitude=" + longitude + ", contactPhone=" + contactPhone + ", alternatePhone="
				+ alternatePhone + ", invoiceMail=" + invoiceMail + ", alternateInvoiceMail=" + alternateInvoiceMail
				+ ", type=" + type + ", isActive=" + isActive + ", isDefault=" + isDefault + ", country=" + country
				+ ", state=" + state + ", city=" + city + ", user=" + user + "]";
	}
}
