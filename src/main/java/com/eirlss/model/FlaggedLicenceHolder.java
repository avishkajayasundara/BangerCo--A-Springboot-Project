package com.eirlss.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class FlaggedLicenceHolder implements Serializable {

    @Id
    @Length(max = 15)
    String licenseNo;
    String offence;
    String dateTimeofOffence;
    String status;

    public FlaggedLicenceHolder(String licenseNo, String offence, String dateTimeofOffence, String status) {
        this.licenseNo = licenseNo;
        this.offence = offence;
        this.dateTimeofOffence = dateTimeofOffence;
        this.status = status;
    }

    public FlaggedLicenceHolder() {
    }

    public String getLicenseNo() {
        return licenseNo;
    }

    public void setLicenseNo(String licenseNo) {
        this.licenseNo = licenseNo;
    }

    public String getOffence() {
        return offence;
    }

    public void setOffence(String offence) {
        this.offence = offence;
    }

    public String getDateTimeofOffence() {
        return dateTimeofOffence;
    }

    public void setDateTimeofOffence(String dateTimeofOffence) {
        this.dateTimeofOffence = dateTimeofOffence;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
