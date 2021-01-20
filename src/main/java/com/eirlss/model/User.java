package com.eirlss.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class User extends SystemUser{

	private String firstName;
	private String lastName;
	private LocalDate dateOfBirth;
	private String address;
	private String contact;
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

	public User(Long userid, String userName, String password, String email, String role, String state, String firstName, String lastName, LocalDate dateOfBirth, String address, String contact, String type, String drivingLinence, String statement, String utilityBill, List<Booking> booking) {
		super(userid, userName, password, email, role, state);
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.address = address;
		this.contact = contact;
		this.type = type;
		this.drivingLinence = drivingLinence;
		this.statement = statement;
		this.utilityBill = utilityBill;
		this.booking = booking;
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
