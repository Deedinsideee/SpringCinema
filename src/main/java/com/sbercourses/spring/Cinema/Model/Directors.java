package com.sbercourses.spring.Cinema.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "Directors")
@Getter
@Setter
@SequenceGenerator(name = "default_generator", sequenceName = "directors_sequence",allocationSize = 1)
public class Directors extends GenericModel {

    @Column(name = "directors_fio",nullable = false)
    private String directors_fio;

    @Column(name = "position",nullable = false)
    private String position;

    @ManyToMany
    @JoinTable(name = "film_directors",joinColumns =@JoinColumn(name = "directors_id" ) ,foreignKey = @ForeignKey(name = "FK_DIRECTORS_FILMS"),
            inverseJoinColumns = @JoinColumn(name = "films_id"),inverseForeignKey = @ForeignKey(name = "FK_FILMS_DIRECTORS")
    )
    List<Film> films;

}
