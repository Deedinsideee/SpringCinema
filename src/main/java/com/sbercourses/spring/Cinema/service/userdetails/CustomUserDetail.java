package com.sbercourses.spring.Cinema.service.userdetails;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CustomUserDetail implements UserDetails {

    private final String password;
    private final String username;
    private final Collection<? extends GrantedAuthority> authorities;
    private final Integer id;

    private final Boolean enabled;

    private final Boolean AccountNotExpired;

    private final Boolean AccountNotLocked;
    private final Boolean CredentialsNonExpired;

    public CustomUserDetail(String password, String login, Collection<? extends GrantedAuthority> authorities, Integer id) {
        this.password = password;
        this.username = login;
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
        return username;
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

    @Override
    public String toString() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(getFieldsToInclude());
        }catch (JsonProcessingException e)
        {
            e.printStackTrace();
        }
        return super.toString();
    }

    private Object getFieldsToInclude(){
        Map<String, Object> fields = new HashMap<>();
        fields.put("user_id", id);
        fields.put("username", username);
        fields.put("user_role", authorities);
        fields.put("password", password);
        return fields;
    }

}
