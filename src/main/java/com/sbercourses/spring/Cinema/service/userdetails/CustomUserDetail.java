package com.sbercourses.spring.Cinema.service.userdetails;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class CustomUserDetail implements UserDetails {

    private final String password;
    private final String login;
    private final Collection<? extends GrantedAuthority> authorities;
    private final Integer id;

    private final Boolean enabled;

    private final Boolean AccountNotExpired;

    private final Boolean AccountNotLocked;
    private final Boolean CredentialsNonExpired;

    public CustomUserDetail(String password, String login, Collection<? extends GrantedAuthority> authorities, Integer id) {
        this.password = password;
        this.login = login;
        this.authorities = authorities;
        this.id = id;
        this.AccountNotLocked = true;
        this.AccountNotExpired=true;
        this.enabled=true;
        this.CredentialsNonExpired=true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return AccountNotExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return AccountNotLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return CredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public Integer getId() {
        return id;
    }
}
