package com.retail.ecom.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="in_m_side_effect")
public class SideEffectDetails {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String chemicalName;
	private String genericName;
	private String indication;
	private String contraIndication;
	private String cautions;
	private String sideEffect;
	
	
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
	@Override
	public String toString() {
		return "SideEffectDetails [id=" + id + ", chemicalName=" + chemicalName + ", genericName=" + genericName
				+ ", indication=" + indication + ", contraIndication=" + contraIndication + ", cautions=" + cautions
				+ ", sideEffect=" + sideEffect + "]";
	}
}
