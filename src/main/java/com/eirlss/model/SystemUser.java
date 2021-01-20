package com.eirlss.model;

import javax.persistence.*;

@Entity
public class SystemUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userid;

    @Column(unique=true)
    private String userName;
    private String password;
    private String email;
    private String role;
    private String state;

    public SystemUser(Long userid, String userName, String password, String email, String role, String state) {
        this.userid = userid;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.role = role;
        this.state = state;
    }

    public String getRole() {
        return role;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public SystemUser() {
    }

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
}
