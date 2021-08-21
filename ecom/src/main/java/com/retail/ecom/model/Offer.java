package com.retail.ecom.model;

import java.util.Date;
import java.util.Set;

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

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="gen_m_offer")
public class Offer {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private Date startDate;
	private Date endDate;
	private Integer mode;
	@Column(name="activity_id")
	private Integer activityId;
	private int isDeleted;
	
	@JsonBackReference
	@ManyToOne(fetch=FetchType.LAZY,optional=true)
	@JoinColumn(name="activity_id",insertable=false,updatable=false)
	private Activity activity;
	
	@OneToMany(mappedBy="offer")
	private Set<Point> points;
	
	public Offer() {
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
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Integer getMode() {
		return mode;
	}
	public void setMode(Integer mode) {
		this.mode = mode;
	}
	public Integer getActivityId() {
		return activityId;
	}
	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}
	public Activity getActivity() {
		return activity;
	}
	public void setActivity(Activity activity) {
		this.activity = activity;
	}
	public int getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}
	public Set<Point> getPoints() {
		return points;
	}
	public void setPoints(Set<Point> points) {
		this.points = points;
	}
	@Override
	public String toString() {
		return "Offer [id=" + id + ", name=" + name + ", startDate=" + startDate + ", endDate=" + endDate + ", mode="
				+ mode + ", activityId=" + activityId + ", isDeleted=" + isDeleted + ", activity=" + activity
				+ ", points=" + points + "]";
	}
}
