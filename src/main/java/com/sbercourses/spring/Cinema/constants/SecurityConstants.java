package com.sbercourses.spring.Cinema.constants;

import java.util.List;

public interface SecurityConstants {
    
    List<String> Resoures_White_List = List.of(
            "/resources/**",
            "/static/**",
            "/js/**",
            "/css/**",
            "/",
            "/swagger-ui/**",
            "/v3/api-docs/**"
    );
    
    List<String> Film_White_List = List.of(
            "/films",
            "/films/search",
            "/films/{id}");
    List<String> Films_Permisions_List = List.of(
            "/films/add",
            "/films/update",
            "/films/delete",
            "/films/addDir"
    );
    
    List<String> User_White_List = List.of(
            "/login",
            "/users/registration",
            "/users/remember-password/",
            "/films",
            "/directors"
    );
    
    List<String> USERS_REST_WHITE_LIST = List.of("/users/auth");
}
