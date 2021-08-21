package com.retail.ecom.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="in_t_side_effect_mapping")
public class SideEffectMapping {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private Integer contentId;
	private String contentName;
	private String contentPartName;
	private Integer compositionId;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getContentId() {
		return contentId;
	}
	public void setContentId(Integer contentId) {
		this.contentId = contentId;
	}
	public String getContentName() {
		return contentName;
	}
	public void setContentName(String contentName) {
		this.contentName = contentName;
	}
	public String getContentPartName() {
		return contentPartName;
	}
	public void setContentPartName(String contentPartName) {
		this.contentPartName = contentPartName;
	}
	public Integer getCompositionId() {
		return compositionId;
	}
	public void setCompositionId(Integer compositionId) {
		this.compositionId = compositionId;
	}
	@Override
	public String toString() {
		return "SideEffectMapping [id=" + id + ", contentId=" + contentId + ", contentName=" + contentName
				+ ", contentPartName=" + contentPartName + ", compositionId=" + compositionId + "]";
	}
}
