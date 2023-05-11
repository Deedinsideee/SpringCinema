package com.sbercourses.spring.Cinema.Model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(name = "uniqueEmail",columnNames = "email"),@UniqueConstraint(name = "login",columnNames = "login")})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name = "default_generator", sequenceName = "users_sequence",allocationSize = 1)
public class User  extends GenericModel{


@Column(name = "login", nullable = false)
private String login;

@Column(name = "password", nullable = false)
private String password;

@Column(name = "firstName", nullable = false)
private String firstName;

@Column(name = "lastName", nullable = false)
private String lastName;


@Column(name = "middleName", nullable = false)
private String middleName;

@Column(name = "birthDate", nullable = false)
private LocalDate birthDate;

@Column(name = "phone")
private String phone;

@Column(name = "addres")
private String addres;

@Column(name = "email",nullable = false)
private String email;

@Column(name = "created_When",nullable = false)
private LocalDate createdWhen;


@OneToOne
@JoinColumn(name = "role_id",foreignKey = @ForeignKey(name = "FK_USERS_ROLES"))
private Role role_id;



}
