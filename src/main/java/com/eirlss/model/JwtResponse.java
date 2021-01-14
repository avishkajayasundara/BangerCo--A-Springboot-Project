package com.eirlss.model;

import java.io.Serializable;

import org.springframework.web.bind.annotation.CrossOrigin;

public class JwtResponse implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;
	private final String jwttoken;
	private final String username;
	private final String type;
	private final String firstName;
	private final String lastNAme;
	private final Long userId;
	private final String drivingLicense;
	private final String state;



	public JwtResponse(String jwttoken, String username, String type, String firstName, String lastNAme, Long userId,String drivingLicens,String state) {
		this.jwttoken = jwttoken;
		this.username = username;
		this.type = type;
		this.firstName = firstName;
		this.lastNAme = lastNAme;
		this.userId = userId;
		this.drivingLicense = drivingLicens;
		this.state=state;
	}

	public String getToken() {
		return this.jwttoken;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getUsername() {
		return username;
	}

	public String getType() {
		return type;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastNAme() {
		return lastNAme;
	}

	public String getJwttoken() {
		return jwttoken;
	}

	public Long getUserId() {
		return userId;
	}

	public String getDrivingLicense() {
		return drivingLicense;
	}

	public String getState() {
		return state;
	}
	
	

}
