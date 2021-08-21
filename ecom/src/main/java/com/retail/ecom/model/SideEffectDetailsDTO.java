package com.retail.ecom.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(value={ "chemicalName", "genericName", "genericName", "contentId", "contentName", "compositionId" }, allowSetters= true)
public class SideEffectDetailsDTO {
	
	private Integer id;
	private String chemicalName;
	private String genericName;
	private String indication;
	private String contraIndication;
	private String cautions;
	private String sideEffect;
	//private Integer id; //These variables are from "SideEffectMapping"
	private Integer contentId;
	private String contentName;
	private String contentPartName;
	private Integer compositionId;
	
	
	public SideEffectDetailsDTO(Integer id, String indication, String contraIndication, String cautions,
			String sideEffect, String contentPartName) {
		super();
		this.id = id;
		this.indication = indication;
		this.contraIndication = contraIndication;
		this.cautions = cautions;
		this.sideEffect = sideEffect;
		this.contentPartName = contentPartName;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getChemicalName() {
		return chemicalName;
	}
	public void setChemicalName(String chemicalName) {
		this.chemicalName = chemicalName;
	}
	public String getGenericName() {
		return genericName;
	}
	public void setGenericName(String genericName) {
		this.genericName = genericName;
	}
	public String getIndication() {
		return indication;
	}
	public void setIndication(String indication) {
		this.indication = indication;
	}
	public String getContraIndication() {
		return contraIndication;
	}
	public void setContraIndication(String contraIndication) {
		this.contraIndication = contraIndication;
	}
	public String getCautions() {
		return cautions;
	}
	public void setCautions(String cautions) {
		this.cautions = cautions;
	}
	public String getSideEffect() {
		return sideEffect;
	}
	public void setSideEffect(String sideEffect) {
		this.sideEffect = sideEffect;
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
		return "SideEffectDetailsDTO [id=" + id + ", chemicalName=" + chemicalName + ", genericName=" + genericName
				+ ", indication=" + indication + ", contraIndication=" + contraIndication + ", cautions=" + cautions
				+ ", sideEffect=" + sideEffect + ", contentId=" + contentId + ", contentName=" + contentName
				+ ", contentPartName=" + contentPartName + ", compositionId=" + compositionId + "]";
	}
}
