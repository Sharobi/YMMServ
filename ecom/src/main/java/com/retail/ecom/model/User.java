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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


@Entity
@Table(name="gen_m_user")
//@JsonIgnoreProperties(value= {"password"})
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String userName;
	private String password;
	private String email;
	private Date dob;
	private String fname;
	private String lname;
	private String phone;
	private String gender;
	
	@Column(name="is_active",columnDefinition="smallint(11) default 1")
	private Integer isActive;
	@Column(name="is_locked",columnDefinition="smallint(11) default 0")
	private Integer isLocked;
	@CreationTimestamp
	@Temporal(TemporalType.DATE)
	@Column
	private Date createdDate;
	@UpdateTimestamp
	@Temporal(TemporalType.DATE)
	@Column
	private Date updatedDate;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="user",fetch=FetchType.LAZY)
	private Set<Address> addresses;
	
	@Column(columnDefinition="int(11) default '0'")
	private int companyId;
	
	@Column(columnDefinition="int(11) default '0'")
	private int storeId;
	
	@Column(columnDefinition="int(11) default '0'")
	private int state;
	
	@Column(columnDefinition="int(11) default '0'")
	private int country;
	@Transient
	private String newPassword;
	
	@Transient
	private String loginType;
	
	@Transient
	private Double points;
	
	private String resetToken;
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Integer getIsActive() {
		return isActive;
	}

	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}

	public Integer getIsLocked() {
		return isLocked;
	}

	public void setIsLocked(Integer isLocked) {
		this.isLocked = isLocked;
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

	public Set<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(Set<Address> addresses) {
		this.addresses = addresses;
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

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getLoginType() {
		return loginType;
	}

	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}

	public Double getPoints() {
		return points;
	}

	public void setPoints(Double points) {
		this.points = points;
	}

	public String getResetToken() {
		return resetToken;
	}

	public void setResetToken(String resetToken) {
		this.resetToken = resetToken;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", password=" + password + ", email=" + email + ", dob="
				+ dob + ", fname=" + fname + ", lname=" + lname + ", phone=" + phone + ", gender=" + gender
				+ ", isActive=" + isActive + ", isLocked=" + isLocked + ", createdDate=" + createdDate
				+ ", updatedDate=" + updatedDate + ", roles=" + roles + ", addresses=" + addresses + ", companyId="
				+ companyId + ", storeId=" + storeId + ", state=" + state + ", country=" + country + ", newPassword="
				+ newPassword + ", loginType=" + loginType + ", points=" + points + ", resetToken=" + resetToken + "]";
	}

	


}
