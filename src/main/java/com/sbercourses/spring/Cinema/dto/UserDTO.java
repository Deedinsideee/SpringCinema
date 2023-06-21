package com.sbercourses.spring.Cinema.dto;


import com.sbercourses.spring.Cinema.Model.Role;
import com.sbercourses.spring.Cinema.Model.User;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDTO extends GenericDTO {
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String middleName;
    private LocalDate birthDate;
    private String phone;
    private String addres;
    private String email;
    private LocalDate createdWhen;
    private RoleDTO role_id;

}
