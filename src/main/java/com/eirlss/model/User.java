package com.eirlss.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userid;	
	
	@Column(unique=true)
	private String userName;	
	private String password;
	private String email;
	private String firstName;
	private String lastName;
	private LocalDate dateOfBirth;
	private String address;
	private String contact;
	private String state;
	private String type;
	private String drivingLinence;
	private String statement;
	private String utilityBill;
	
	
	@OneToMany(mappedBy="user")
    private List<Booking> booking;
//	
//	@OneToMany(mappedBy="user")
//    private List<UserContact> contacts;
	
	public User() {}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

//	public List<Booking> getBooking() {
//		return booking;
//	}

//	public void setBooking(List<Booking> booking) {
//		this.booking = booking;
//	}
//
//	public List<UserContact> getContacts() {
//		return contacts;
//	}
//
//	public void setContacts(List<UserContact> contacts) {
//		this.contacts = contacts;
//	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDrivingLinence() {
		return drivingLinence;
	}

	public void setDrivingLinence(String drivingLinence) {
		this.drivingLinence = drivingLinence;
	}

	public String getStatement() {
		return statement;
	}

	public void setStatement(String statement) {
		this.statement = statement;
	}

	public String getUtilityBill() {
		return utilityBill;
	}

	public void setUtilityBill(String utilityBill) {
		this.utilityBill = utilityBill;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public List<Booking> getBooking() {
		return booking;
	}

	public void setBooking(List<Booking> booking) {
		this.booking = booking;
	}
	
	
	
	
}
