package com.sbercourses.spring.Cinema.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class LoginDTO {
    private String login;
    private String password;
}
