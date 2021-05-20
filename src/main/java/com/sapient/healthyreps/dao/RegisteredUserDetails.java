package com.sapient.healthyreps.dao;

import java.util.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


public class RegisteredUserDetails implements UserDetails {
    String userName;
    String emailId;
    String passWord;
    private boolean isLoggedIn;
    private List<GrantedAuthority> authorities;

    public RegisteredUserDetails(User user) {
        this.userName = user.userName;
        this.emailId = user.emailId;
        this.passWord = user.passWord;
        this.isLoggedIn = user.isLoggedIn;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        return authorities;
    }

    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
        return passWord;
    }

    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return isLoggedIn;
    }
    
}
