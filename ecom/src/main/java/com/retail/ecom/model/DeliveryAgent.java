package com.retail.ecom.model;

import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name="gen_m_delivery_agent")
public class DeliveryAgent {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String fname;
	private String lname;
	private String userName;
	private String  password;
	private String phone;
	private String alternatePhone;
	private String email;
	private String gender;
	private String age;
	//private Integer pincode;
	
	@Column(columnDefinition="int(11) default '0'")
	private int state;
	
	@Column(columnDefinition="int(11) default '0'")
	private int country;
	
	@Column(columnDefinition="int(11) default '0'")
	private int isExclusive;

	@Column(name="company_id",columnDefinition="int(11) default '0'")
	private int companyId;
	
	@Column(columnDefinition="int(11) default '0'")
	private int storeId;
	
	@Column(columnDefinition="int(11) default '0'")
	private int organisationId;
	
	@Column(columnDefinition="int(11) default '1'")
	private int isActive;
	
	@Column(columnDefinition="int(11) default '1'")
	private int isLocked;
	
	@Column(columnDefinition="int(11) default '0'")
	private int createdBy;
	
	@CreationTimestamp
	@Temporal(TemporalType.DATE)
	@Column
	private Date createdDate;
	@UpdateTimestamp
	@Temporal(TemporalType.DATE)
	@Column
	private Date updatedDate;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "delivery_role", joinColumns = @JoinColumn(name = "delivery_agent_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="deliveryAgent",fetch=FetchType.LAZY)
	private Set<DeliveryPin> deliveryPin;
	
	@Transient
	private String loginType;
	
	public DeliveryAgent() {
		super();
	}
	
	public String getRoleArr() {
		String rolesarr = ",";
		for (Iterator iterator = roles.iterator(); 
				iterator.hasNext();) {
			Role role = (Role) iterator.next();
			rolesarr += role.getRole()+",";
		}
		return rolesarr;
		//return rolesarr.substring(0, rolesarr.length()-1);
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAlternatePhone() {
		return alternatePhone;
	}
	public void setAlternatePhone(String alternatePhone) {
		this.alternatePhone = alternatePhone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getCountry() {
		return country;
	}
	public void setCountry(int country) {
		this.country = country;
	}
	public int getIsExclusive() {
		return isExclusive;
	}
	public void setIsExclusive(int isExclusive) {
		this.isExclusive = isExclusive;
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
	public int getOrganisationId() {
		return organisationId;
	}
	public void setOrganisationId(int organisationId) {
		this.organisationId = organisationId;
	}
	public int getIsActive() {
		return isActive;
	}
	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}
	public int getIsLocked() {
		return isLocked;
	}
	public void setIsLocked(int isLocked) {
		this.isLocked = isLocked;
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
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getLoginType() {
		return loginType;
	}

	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}

	public Set<DeliveryPin> getDeliveryPin() {
		return deliveryPin;
	}

	public void setDeliveryPin(Set<DeliveryPin> deliveryPin) {
		this.deliveryPin = deliveryPin;
	}

	@Override
	public String toString() {
		return "DeliveryAgent [id=" + id + ", fname=" + fname + ", lname=" + lname + ", userName=" + userName
				+ ", password=" + password + ", phone=" + phone + ", alternatePhone=" + alternatePhone + ", email="
				+ email + ", gender=" + gender + ", age=" + age + ", state=" + state + ", country=" + country
				+ ", isExclusive=" + isExclusive + ", companyId=" + companyId + ", storeId=" + storeId
				+ ", organisationId=" + organisationId + ", isActive=" + isActive + ", isLocked=" + isLocked
				+ ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", updatedDate=" + updatedDate
				+ ", roles=" + roles + ", deliveryPin=" + deliveryPin + ", loginType=" + loginType + ", getRoleArr()="
				+ getRoleArr() + ", getId()=" + getId() + ", getFname()=" + getFname() + ", getLname()=" + getLname()
				+ ", getUserName()=" + getUserName() + ", getPassword()=" + getPassword() + ", getPhone()=" + getPhone()
				+ ", getAlternatePhone()=" + getAlternatePhone() + ", getEmail()=" + getEmail() + ", getGender()="
				+ getGender() + ", getAge()=" + getAge() + ", getState()=" + getState() + ", getCountry()="
				+ getCountry() + ", getIsExclusive()=" + getIsExclusive() + ", getCompanyId()=" + getCompanyId()
				+ ", getStoreId()=" + getStoreId() + ", getOrganisationId()=" + getOrganisationId() + ", getIsActive()="
				+ getIsActive() + ", getIsLocked()=" + getIsLocked() + ", getCreatedBy()=" + getCreatedBy()
				+ ", getCreatedDate()=" + getCreatedDate() + ", getUpdatedDate()=" + getUpdatedDate() + ", getRoles()="
				+ getRoles() + ", getLoginType()=" + getLoginType() + ", getDeliveryPin()=" + getDeliveryPin()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}





	
	
	
	
	
}


