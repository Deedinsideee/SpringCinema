package com.sbercourses.spring.Cinema.service.userdetails;


import com.sbercourses.spring.Cinema.Model.User;
import com.sbercourses.spring.Cinema.constants.UserRolesConstants;
import com.sbercourses.spring.Cinema.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.sbercourses.spring.Cinema.constants.UserRolesConstants.ADMIN;

@Service
@Slf4j
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;


    @Value("${spring.security.user.name}")
    private String adminUserName;
    @Value("${spring.security.user.password}")
    private String adminPassword;



    public CustomUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        if (username.equals(adminUserName)) {


            return new CustomUserDetail(adminPassword, adminUserName,List.of(new SimpleGrantedAuthority("ROLE_" + ADMIN)),null);

        }else {



        User user = userRepository.findUserByLogin(username);
        List<GrantedAuthority> authority = new ArrayList<>();
        authority.add(new SimpleGrantedAuthority(user.getRole_id().getId()==1L
                ? "ROlE_"+ UserRolesConstants.USER
                : "ROLE_" + UserRolesConstants.REZ));
        return new CustomUserDetail(user.getPassword(),user.getLogin(),authority,user.getId().intValue());
        }
    }
}
