package com.pink.retail.address;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pink.retail.users.Users;


@Entity
public class Address {
	
	@Column(name = "ADDRESS_ID")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int addressId;

	@Column(name = "STREET_ADDRESS")
	private String streetAddress;

	@Column(name = "POSTAL_CODE")
	private String postalCode;

	@Column(name = "CITY_ADDRESS")
	private String cityAddress;

	@Column(name = "COUNTY_ADDRESS")
	private String countyAddress;

	@ManyToMany(mappedBy = "addresses",cascade = CascadeType.MERGE)
	private List<Users> users=new ArrayList<Users>();
	
	public Address()
	{
		super();
	}
	
	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCityAddress() {
		return cityAddress;
	}

	public void setCityAddress(String cityAddress) {
		this.cityAddress = cityAddress;
	}

	public String getCountyAddress() {
		return countyAddress;
	}

	public void setCountyAddress(String countyAddress) {
		this.countyAddress = countyAddress;
	}

	public Address(String streetAddress, String postalCode, String cityAddress, String countyAddress) {
		super();
		this.streetAddress = streetAddress;
		this.postalCode = postalCode;
		this.cityAddress = cityAddress;
		this.countyAddress = countyAddress;
	}

	@JsonIgnore
	public List<Users> getUsers() {
		return users;
	}

	public void setUsers(List<Users> users) {
		this.users = users;
	}

	
}
