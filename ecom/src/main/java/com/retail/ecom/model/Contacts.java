package com.retail.ecom.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
@Table(name="gen_m_contacts")
//@JsonIgnoreProperties(value= {"password"})
public class Contacts {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	//private Integer userId;
	private String fname;
	private String lname;
	private String phone;
	private String email;
	private String yourmsg;
	@Column(columnDefinition="int(11) default '0'")
	private int isDeleted;
	//@Temporal(TemporalType.DATE)
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createdDate;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	/*public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}*/
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
	public String getYourmsg() {
		return yourmsg;
	}
	public void setYourmsg(String yourmsg) {
		this.yourmsg = yourmsg;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}
	@Override
	public String toString() {
		return "Contacts [id=" + id + ", fname=" + fname + ", lname=" + lname + ", email=" + email + ", phone=" + phone
				+ ", yourmsg=" + yourmsg + ", createdDate=" + createdDate + ", isDeleted=" + isDeleted + "]";
	}
	

}
