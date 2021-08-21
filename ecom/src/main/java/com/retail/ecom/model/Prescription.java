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

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="in_t_prescription")
public class Prescription {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private Date prescriptionDate;
	private String prescriptionNo;
	private String doctorName;
	private String userFilename;
	private String sysGenFilename;
	@Column(name="sale_order_id")
	private Integer saleOrderId;
	private int userId;
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date createdDate;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonBackReference
	@JoinColumn(name="sale_order_id",insertable=false,updatable=false)
	Order order;
	
	public Prescription() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getPrescriptionDate() {
		return prescriptionDate;
	}
	public void setPrescriptionDate(Date prescriptionDate) {
		this.prescriptionDate = prescriptionDate;
	}
	public String getPrescriptionNo() {
		return prescriptionNo;
	}
	public void setPrescriptionNo(String prescriptionNo) {
		this.prescriptionNo = prescriptionNo;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public String getUserFilename() {
		return userFilename;
	}
	public void setUserFilename(String userFilename) {
		this.userFilename = userFilename;
	}
	public String getSysGenFilename() {
		return sysGenFilename;
	}
	public void setSysGenFilename(String sysGenFilename) {
		this.sysGenFilename = sysGenFilename;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Integer getSaleOrderId() {
		return saleOrderId;
	}
	public void setSaleOrderId(Integer saleOrderId) {
		this.saleOrderId = saleOrderId;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	@Override
	public String toString() {
		return "Prescription [id=" + id + ", prescriptionDate=" + prescriptionDate + ", prescriptionNo="
				+ prescriptionNo + ", doctorName=" + doctorName + ", userFilename=" + userFilename + ", sysGenFilename="
				+ sysGenFilename + ", saleOrderId=" + saleOrderId + ", userId=" + userId + ", createdDate="
				+ createdDate + ", order=" + order + "]";
	}
}
