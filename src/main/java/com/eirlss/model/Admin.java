package com.eirlss.model;

public class Admin extends SystemUser{

    public Admin() {
    }

    public Admin(Long userid, String userName, String password, String email, String role, String state) {
        super(userid, userName, password, email, role, state);
    }
}
