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

@Entity
@Table(name="gen_m_store")
public class Store {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private Integer companyId;
	private Integer localStoreId;
	private String name;
	private Date registrationDate;
	@Transient
	private Date terminationDate;
	private Integer currencyId;
	private Double latitude;
	private Double longitude;
	private String address;
	private String state;
	private String country;
	private String postcode;
	private String email;
	private String phone;
	private String fax;
	private Integer isActive;
	private Integer isDeleted;
	private Integer createdBy;
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	private Date createdDate;
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Transient
	@Column
	private Date updatedDate;
	@Transient
	private Integer updatedBy;
	@Transient
	private String lang;
	@Transient
	private Integer isDefault;
	private String dlLicenceNo;
	@Transient
    private Date dlRegistrationDate;
	@Transient
    private Date dlExpiryDate;
    private String stateLicenceNo;
	@Transient
    private Date stateRegistrationDate;
	@Transient
    private Date stateExpiryDate;
    private String taxRegNo;
    private Double taxPer;
    private Double vatPer;
    private Integer isMailEnable;
    private String mailPassword;
    private String mailPort;
    private String mailSmtp;
    private String taxRegNoText;
    private Integer isExclusive;
	@Transient
    private Integer isEsi;
	@Transient
    private String defaultPort;
	@Transient
	private Integer baudRate;
	@Transient
	private Integer numericKeyBoard;
	@Transient
	private Integer isCustomerDisplay ;
	@Transient
	private Integer isSalesman ;
	@Transient
	private Integer isConversion ;
	private Integer isManufacturer ;
	@Transient
	private Integer isFree ;
	@Transient
	private Integer isOnBillSale ;
	@Transient
	private Integer isOnBillPurchase ;
	@Transient
	private Integer isNewDisplay ;
	@Transient
	private Integer isMrpEnable;
	@Transient
	private Integer isSaleman;
	@Transient
	private Integer isOptical;
	
	public Store() {
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

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public Date getTerminationDate() {
		return terminationDate;
	}

	public void setTerminationDate(Date terminationDate) {
		this.terminationDate = terminationDate;
	}

	public Integer getCurrencyId() {
		return currencyId;
	}

	public void setCurrencyId(Integer currencyId) {
		this.currencyId = currencyId;
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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public Integer getIsActive() {
		return isActive;
	}

	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}

	public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public Integer getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}

	public String getDlLicenceNo() {
		return dlLicenceNo;
	}

	public void setDlLicenceNo(String dlLicenceNo) {
		this.dlLicenceNo = dlLicenceNo;
	}

	public Date getDlRegistrationDate() {
		return dlRegistrationDate;
	}

	public void setDlRegistrationDate(Date dlRegistrationDate) {
		this.dlRegistrationDate = dlRegistrationDate;
	}

	public Date getDlExpiryDate() {
		return dlExpiryDate;
	}

	public void setDlExpiryDate(Date dlExpiryDate) {
		this.dlExpiryDate = dlExpiryDate;
	}

	public String getStateLicenceNo() {
		return stateLicenceNo;
	}

	public void setStateLicenceNo(String stateLicenceNo) {
		this.stateLicenceNo = stateLicenceNo;
	}

	public Date getStateRegistrationDate() {
		return stateRegistrationDate;
	}

	public void setStateRegistrationDate(Date stateRegistrationDate) {
		this.stateRegistrationDate = stateRegistrationDate;
	}

	public Date getStateExpiryDate() {
		return stateExpiryDate;
	}

	public void setStateExpiryDate(Date stateExpiryDate) {
		this.stateExpiryDate = stateExpiryDate;
	}

	public String getTaxRegNo() {
		return taxRegNo;
	}

	public void setTaxRegNo(String taxRegNo) {
		this.taxRegNo = taxRegNo;
	}

	public Double getTaxPer() {
		return taxPer;
	}

	public void setTaxPer(Double taxPer) {
		this.taxPer = taxPer;
	}

	public Double getVatPer() {
		return vatPer;
	}

	public void setVatPer(Double vatPer) {
		this.vatPer = vatPer;
	}

	public Integer getIsMailEnable() {
		return isMailEnable;
	}

	public void setIsMailEnable(Integer isMailEnable) {
		this.isMailEnable = isMailEnable;
	}

	public String getMailPassword() {
		return mailPassword;
	}

	public void setMailPassword(String mailPassword) {
		this.mailPassword = mailPassword;
	}

	public String getMailPort() {
		return mailPort;
	}

	public void setMailPort(String mailPort) {
		this.mailPort = mailPort;
	}

	public String getMailSmtp() {
		return mailSmtp;
	}

	public void setMailSmtp(String mailSmtp) {
		this.mailSmtp = mailSmtp;
	}

	public String getTaxRegNoText() {
		return taxRegNoText;
	}

	public void setTaxRegNoText(String taxRegNoText) {
		this.taxRegNoText = taxRegNoText;
	}

	public Integer getIsExclusive() {
		return isExclusive;
	}

	public void setIsExclusive(Integer isExclusive) {
		this.isExclusive = isExclusive;
	}

	public Integer getIsEsi() {
		return isEsi;
	}

	public void setIsEsi(Integer isEsi) {
		this.isEsi = isEsi;
	}

	public String getDefaultPort() {
		return defaultPort;
	}

	public void setDefaultPort(String defaultPort) {
		this.defaultPort = defaultPort;
	}

	public Integer getBaudRate() {
		return baudRate;
	}

	public void setBaudRate(Integer baudRate) {
		this.baudRate = baudRate;
	}

	public Integer getNumericKeyBoard() {
		return numericKeyBoard;
	}

	public void setNumericKeyBoard(Integer numericKeyBoard) {
		this.numericKeyBoard = numericKeyBoard;
	}

	public Integer getIsCustomerDisplay() {
		return isCustomerDisplay;
	}

	public void setIsCustomerDisplay(Integer isCustomerDisplay) {
		this.isCustomerDisplay = isCustomerDisplay;
	}

	public Integer getIsSalesman() {
		return isSalesman;
	}

	public void setIsSalesman(Integer isSalesman) {
		this.isSalesman = isSalesman;
	}

	public Integer getIsConversion() {
		return isConversion;
	}

	public void setIsConversion(Integer isConversion) {
		this.isConversion = isConversion;
	}

	public Integer getIsManufacturer() {
		return isManufacturer;
	}

	public void setIsManufacturer(Integer isManufacturer) {
		this.isManufacturer = isManufacturer;
	}

	public Integer getIsFree() {
		return isFree;
	}

	public void setIsFree(Integer isFree) {
		this.isFree = isFree;
	}

	public Integer getIsOnBillSale() {
		return isOnBillSale;
	}

	public void setIsOnBillSale(Integer isOnBillSale) {
		this.isOnBillSale = isOnBillSale;
	}

	public Integer getIsOnBillPurchase() {
		return isOnBillPurchase;
	}

	public void setIsOnBillPurchase(Integer isOnBillPurchase) {
		this.isOnBillPurchase = isOnBillPurchase;
	}

	public Integer getIsNewDisplay() {
		return isNewDisplay;
	}

	public void setIsNewDisplay(Integer isNewDisplay) {
		this.isNewDisplay = isNewDisplay;
	}

	public Integer getIsMrpEnable() {
		return isMrpEnable;
	}

	public void setIsMrpEnable(Integer isMrpEnable) {
		this.isMrpEnable = isMrpEnable;
	}

	public Integer getIsSaleman() {
		return isSaleman;
	}

	public void setIsSaleman(Integer isSaleman) {
		this.isSaleman = isSaleman;
	}

	public Integer getIsOptical() {
		return isOptical;
	}

	public void setIsOptical(Integer isOptical) {
		this.isOptical = isOptical;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public Integer getLocalStoreId() {
		return localStoreId;
	}

	public void setLocalStoreId(Integer localStoreId) {
		this.localStoreId = localStoreId;
	}

	@Override
	public String toString() {
		return "Store [id=" + id + ", companyId=" + companyId + ", localStoreId=" + localStoreId + ", name=" + name
				+ ", registrationDate=" + registrationDate + ", terminationDate=" + terminationDate + ", currencyId="
				+ currencyId + ", latitude=" + latitude + ", longitude=" + longitude + ", address=" + address
				+ ", state=" + state + ", country=" + country + ", postcode=" + postcode + ", email=" + email
				+ ", phone=" + phone + ", fax=" + fax + ", isActive=" + isActive + ", isDeleted=" + isDeleted
				+ ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", updatedDate=" + updatedDate
				+ ", updatedBy=" + updatedBy + ", lang=" + lang + ", isDefault=" + isDefault + ", dlLicenceNo="
				+ dlLicenceNo + ", dlRegistrationDate=" + dlRegistrationDate + ", dlExpiryDate=" + dlExpiryDate
				+ ", stateLicenceNo=" + stateLicenceNo + ", stateRegistrationDate=" + stateRegistrationDate
				+ ", stateExpiryDate=" + stateExpiryDate + ", taxRegNo=" + taxRegNo + ", taxPer=" + taxPer + ", vatPer="
				+ vatPer + ", isMailEnable=" + isMailEnable + ", mailPassword=" + mailPassword + ", mailPort="
				+ mailPort + ", mailSmtp=" + mailSmtp + ", taxRegNoText=" + taxRegNoText + ", isExclusive="
				+ isExclusive + ", isEsi=" + isEsi + ", defaultPort=" + defaultPort + ", baudRate=" + baudRate
				+ ", numericKeyBoard=" + numericKeyBoard + ", isCustomerDisplay=" + isCustomerDisplay + ", isSalesman="
				+ isSalesman + ", isConversion=" + isConversion + ", isManufacturer=" + isManufacturer + ", isFree="
				+ isFree + ", isOnBillSale=" + isOnBillSale + ", isOnBillPurchase=" + isOnBillPurchase
				+ ", isNewDisplay=" + isNewDisplay + ", isMrpEnable=" + isMrpEnable + ", isSaleman=" + isSaleman
				+ ", isOptical=" + isOptical + "]";
	}

	
	


}
