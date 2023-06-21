package com.sbercourses.spring.Cinema.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Directors")
@Getter
@Setter
@SequenceGenerator(name = "default_generator", sequenceName = "directors_sequence",allocationSize = 1)
public class Directors extends GenericModel {

    @Column(name = "dirfio",nullable = false)
    private String dirfio;

    @Column(name = "position",nullable = false)
    private String position;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "description")
    private String description;

    @ManyToMany
    @JoinTable(name = "film_directors",joinColumns =@JoinColumn(name = "directors_id" ) ,foreignKey = @ForeignKey(name = "FK_DIRECTORS_FILMS"),
            inverseJoinColumns = @JoinColumn(name = "films_id"),inverseForeignKey = @ForeignKey(name = "FK_FILMS_DIRECTORS")
    )
    List<Film> films;

}
