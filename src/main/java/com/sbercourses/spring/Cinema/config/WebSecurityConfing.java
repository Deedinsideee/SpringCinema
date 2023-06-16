package com.sbercourses.spring.Cinema.config;


import com.sbercourses.spring.Cinema.service.userdetails.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.List;

import static com.sbercourses.spring.Cinema.constants.UserRolesConstants.ADMIN;
import static com.sbercourses.spring.Cinema.constants.UserRolesConstants.REZ;

@Configuration
@EnableWebSecurity
public class WebSecurityConfing {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private  final CustomUserDetailService customUserDetailService;

    public WebSecurityConfing(BCryptPasswordEncoder bCryptPasswordEncoder, CustomUserDetailService customUserDetailService) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.customUserDetailService = customUserDetailService;
    }

    private final List<String> Resoures_White_List = List.of(
            "/resources/**",
            "/static/**",
            "/js/**",
            "/css/**",
            "/",
            "/swagger-ui/**"
    );
    private final List<String> Film_White_List = List.of("/film");
    private final List<String> Films_Permisions_List=List.of(
           "/films/add",
            "/films/update",
            "/films/delete",
            "/films/addDir"
    );

    private final List<String> User_White_List=List.of(
            "/login",
            "/users/registration",
            "/users/remember-password/",
            "/films",
            "/directors"
    );



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .cors().disable()
                .csrf().disable()
                .authorizeHttpRequests((requests)->requests
                        .requestMatchers(Resoures_White_List.toArray(String[]::new)).permitAll()
                        .requestMatchers(Film_White_List.toArray(String[]::new)).permitAll()
                        .requestMatchers(User_White_List.toArray(String[]::new)).permitAll()
                        .requestMatchers(Films_Permisions_List.toArray(String[]::new)).hasAnyRole(ADMIN,REZ)
                        .anyRequest().authenticated()




                )
                .formLogin((form)->form
                        .loginPage("/login")
                        .defaultSuccessUrl("/films")
                        .permitAll()


                )
                .logout((logout)->logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login")
                        .permitAll()
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))

                );

        return httpSecurity.build();
    }
    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(customUserDetailService).passwordEncoder(bCryptPasswordEncoder);

    }

}
