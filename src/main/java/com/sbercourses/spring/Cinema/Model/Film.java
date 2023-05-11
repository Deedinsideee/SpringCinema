package com.sbercourses.spring.Cinema.Model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.util.List;

@Entity
@Table(name = "Film")
@Getter
@Setter
@SequenceGenerator(name = "default_generator", sequenceName = "films_sequence",allocationSize = 1)
public class Film extends GenericModel {

    @Column(name = "title",nullable = false)
    private String title;


    @Column(name = "premier_year",nullable = false)
    private BigInteger premier_year;

    @Column(name = "country",nullable = false)
    private BigInteger county;

    @Column(name = "genre",nullable = false)
    @Enumerated()
    private Genre genre;


    @ManyToMany(mappedBy = "films")
    List<Directors> directors;
}
