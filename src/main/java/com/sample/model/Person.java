package com.sample.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.sample.utility.Constants;

@Entity
@Table(name="PERSON")
public class Person implements Serializable {
	
	private static final long serialVersionUID = -7036611969693559707L;

	public Person(){}
	
	public Person(long personId, String firstName, String lastName,
			String emailId, Date dateOfBirth, String phoneNo,
			Set<Address> addresses) {
		this.personId = personId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.dateOfBirth = dateOfBirth;
		this.phoneNo = phoneNo;
		this.addresses = addresses;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PERSON_ID", unique = true, nullable = false)
	private long personId;
	
	@Column(name = "FIRST_NAME", nullable = false, length = 100)
	private String firstName;
	
	@Column(name = "LAST_NAME", nullable = false, length = 100)
	private String lastName;
	
	@Column(name = "EMAIL_ID", length = 100)
	private String emailId;
	
	@Column(name = "DOB")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = Constants.DEFAULT_DATE_FORMAT)
	private Date dateOfBirth;
	
	@Column(name = "PHONE_NO", length = 20)
	private String phoneNo;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "PERSON_ID")
	private Set<Address> addresses;

	public long getPersonId() {
		return personId;
	}

	public void setPersonId(long personId) {
		this.personId = personId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public Set<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(Set<Address> addresses) {
		this.addresses = addresses;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (obj == null || obj.getClass() != this.getClass()) {
			return false;
		}
		Person person = (Person) obj;
		
		return personId == person.personId && 
			  (firstName == person.firstName || (firstName != null && firstName .equals(person.getFirstName()))) && 
			  (lastName.equals(person.lastName)) &&
			  (emailId.equals(person.emailId)) &&
			  (dateOfBirth == person.dateOfBirth || (dateOfBirth != null && dateOfBirth.equals(person.getDateOfBirth()))) &&
			  (phoneNo.equals(person.phoneNo));
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Long.valueOf(personId).hashCode();
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((emailId == null) ? 0 : emailId.hashCode());
		result = prime * result + ((dateOfBirth == null) ? 0 : dateOfBirth.hashCode());
		result = prime * result + ((phoneNo == null) ? 0 : phoneNo.hashCode());
		return result;
	}
}
