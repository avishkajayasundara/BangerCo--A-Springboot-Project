package com.eirlss.model;

import org.springframework.data.annotation.Persistent;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Persistent
public class FlaggedLicences {
    @Id
    String licenseNo;
    String offence;
    String dateOfOffence;
    String status;

    public FlaggedLicences(String licenseNo, String offence, String dateOfOffence, String status) {
        this.licenseNo = licenseNo;
        this.offence = offence;
        this.dateOfOffence = dateOfOffence;
        this.status = status;
    }

    public FlaggedLicences() {
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

    public String getDateOfOffence() {
        return dateOfOffence;
    }

    public void setDateOfOffence(String dateOfOffence) {
        this.dateOfOffence = dateOfOffence;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
