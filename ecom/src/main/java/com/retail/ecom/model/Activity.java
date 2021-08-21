package com.retail.ecom.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="gen_m_activity")
public class Activity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String activityType;
	
	@OneToMany(mappedBy="activity")
	private Set<Offer> offers;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getActivityType() {
		return activityType;
	}
	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}
	public Set<Offer> getOffers() {
		return offers;
	}
	public void setOffers(Set<Offer> offers) {
		this.offers = offers;
	}
	@Override
	public String toString() {
		return "Activity [id=" + id + ", activityType=" + activityType + ", offers=" + offers + "]";
	}
	
}
