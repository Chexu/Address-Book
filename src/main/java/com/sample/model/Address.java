package com.sample.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ADDRESS")
public class Address implements Serializable{
	
	private static final long serialVersionUID = -32226280946827701L;

	public Address(){}
	
	
	public Address(long addrId, String addrLineOne, String addrLineTwo,
			String city, String state, String country) {
		this.addrId = addrId;
		this.addrLineOne = addrLineOne;
		this.addrLineTwo = addrLineTwo;
		this.city = city;
		this.state = state;
		this.country = country;
	}


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ADDR_ID", unique = true, nullable = false)
	private long addrId;
	
	@Column(name = "ADDR_LINE_ONE", nullable = false, length = 100)
	private String addrLineOne;
	
	@Column(name = "ADDR_LINE_TWO", length = 100)
	private String addrLineTwo;
	
	@Column(name = "CITY", length = 100)
	private String city;
	
	@Column(name = "STATE", length = 100)
	private String state;
	
	@Column(name = "COUNTRY", length = 100)
	private String country;

	public long getAddrId() {
		return addrId;
	}

	public void setAddrId(long addrId) {
		this.addrId = addrId;
	}

	public String getAddrLineOne() {
		return addrLineOne;
	}

	public void setAddrLineOne(String addrLineOne) {
		this.addrLineOne = addrLineOne;
	}

	public String getAddrLineTwo() {
		return addrLineTwo;
	}

	public void setAddrLineTwo(String addrLineTwo) {
		this.addrLineTwo = addrLineTwo;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (obj == null || obj.getClass() != this.getClass()) {
			return false;
		}
		Address person = (Address) obj;
		
		return addrId == person.addrId && 
			  (addrLineOne == person.addrLineOne || (addrLineOne != null && addrLineOne .equals(person.getAddrLineOne()))) && 
			  (addrLineTwo.equals(person.addrLineTwo)) &&
			  (city.equals(person.city)) &&
			  (state.equals(person.state)) &&
			  (country.equals(person.country));
	}
	
	@Override
	public int hashCode() {
		final int prime = 30;
		int result = 1;
		result = prime * result + Long.valueOf(addrId).hashCode();
		result = prime * result + ((addrLineOne == null) ? 0 : addrLineOne.hashCode());
		result = prime * result + ((addrLineTwo == null) ? 0 : addrLineTwo.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		return result;
	}
}
