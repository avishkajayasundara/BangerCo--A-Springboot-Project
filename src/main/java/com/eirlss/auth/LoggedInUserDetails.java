package com.eirlss.auth;

import com.eirlss.model.SystemUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class LoggedInUserDetails implements UserDetails {
    private String username;
    private String email;
    private String password;
    private String userRole;
    private List<GrantedAuthority> authorities;
    private String state;

    public LoggedInUserDetails(SystemUser user) {
        this.username = user.getUserName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.userRole = user.getRole();
        this.authorities = AuthorityUtils.createAuthorityList(user.getRole());
        this.state = user.getState();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public void setAuthorities(List<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {

        if(this.state.equals("Blacklisted"))
            return false;
        else
            return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        if(this.state.equals("Blacklisted"))
            return false;
        else
            return true;
    }
}
