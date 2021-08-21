package com.retail.ecom.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name="user_activity_tracking")
public class ActivityTracking {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private Integer userId;
	private String userName;
	private Integer activityTypeId;
	private String clientIp;
	private Integer status;
	@CreationTimestamp
	private Date createdDate;
	public ActivityTracking(Integer userId,String userName, Integer activityTypeId, String clientIp, Integer status) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.activityTypeId = activityTypeId;
		this.clientIp = clientIp;
		this.status = status;
	}
	public ActivityTracking() {
		super();
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
	public Integer getActivityTypeId() {
		return activityTypeId;
	}
	public void setActivityTypeId(Integer activityTypeId) {
		this.activityTypeId = activityTypeId;
	}
	public String getClientIp() {
		return clientIp;
	}
	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Override
	public String toString() {
		return "ActivityTracking [id=" + id + ", userId=" + userId + ", userName=" + userName + ", activityTypeId="
				+ activityTypeId + ", clientIp=" + clientIp + ", status=" + status + ", createdDate=" + createdDate
				+ "]";
	}
}
