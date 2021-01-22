package com.eirlss.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "fraud_user")
public class FraudUser {
    @Id
    @Length(max=20)
    String licenceNo;
    String fname;
    String lname;

    public FraudUser(@Length(max = 20) String licenceNo, String fname, String lname) {
        this.licenceNo = licenceNo;
        this.fname = fname;
        this.lname = lname;
    }

    public FraudUser() {
    }

    public String getLicenceNo() {
        return licenceNo;
    }

    public void setLicenceNo(String lecenceNo) {
        this.licenceNo = lecenceNo;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }
}
