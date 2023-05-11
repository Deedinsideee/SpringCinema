package com.sbercourses.spring.Cinema.Model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Role")
@Getter
@Setter
@SequenceGenerator(name = "default_generator", sequenceName = "roles_sequence",allocationSize = 1)
public class Role extends GenericModel {


    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "description", nullable = false)
    private String description;
}
