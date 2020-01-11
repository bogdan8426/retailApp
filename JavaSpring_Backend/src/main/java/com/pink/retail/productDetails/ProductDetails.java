package com.pink.retail.productDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ProductDetails {
	
	@Column(name="PRODUCT_DETAILS_ID")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private int productDetailsId;
	
	@Column(name="PROCESOR")
	private String procesor;
	
	@Column(name="SISTEM_DE_OPERARE")
	private String sistemDeOperare;
	
	@Column(name="MEMORIE_RAM")
	private String memorieRam;
	
	@Column(name="MEMORIE_INTERNA")
	private String memorieInterna;
	
	@Column(name="REZOLUTIE")
	private String rezolutie;
	
	@Column(name="REZOLUTIE_CAMERA")
	private String rezolutieCamera;
	
	@Column(name="DESCRIPTION")
	private String description;
		
	public ProductDetails()
	{
		super();
	}
	
	public int getProductDetailsId() {
		return productDetailsId;
	}
	public void setProductDetailsId(int productDetailsId) {
		this.productDetailsId = productDetailsId;
	}
	public String getProcesor() {
		return procesor;
	}
	public void setProcesor(String procesor) {
		this.procesor = procesor;
	}
	public String getSistemDeOperare() {
		return sistemDeOperare;
	}
	public void setSistemDeOperare(String sistemDeOperare) {
		this.sistemDeOperare = sistemDeOperare;
	}
	public String getMemorieRam() {
		return memorieRam;
	}
	public void setMemorieRam(String memorieRam) {
		this.memorieRam = memorieRam;
	}
	public String getMemorieInterna() {
		return memorieInterna;
	}
	public void setMemorieInterna(String memorieInterna) {
		this.memorieInterna = memorieInterna;
	}
	public String getRezolutie() {
		return rezolutie;
	}
	public void setRezolutie(String rezolutie) {
		this.rezolutie = rezolutie;
	}
	public String getRezolutieCamera() {
		return rezolutieCamera;
	}
	public void setRezolutieCamera(String rezolutieCamera) {
		this.rezolutieCamera = rezolutieCamera;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	

}
