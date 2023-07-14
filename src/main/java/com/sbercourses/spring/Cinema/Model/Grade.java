package com.sbercourses.spring.Cinema.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Grade")
@Getter
@Setter
@SequenceGenerator(name = "default_generator", sequenceName = "grade_sequence",allocationSize = 1)
public class Grade extends GenericModel {


    @OneToOne
    @JoinColumn(name = "film_id",foreignKey = @ForeignKey(name = "FK_FILMS_GRADES"))
    private Film filmId;

    @OneToOne
    @JoinColumn(name = "user_id",foreignKey = @ForeignKey(name = "FK_USERS_GRADES"))
    private User userId;


    @Column(name = "grade", nullable = true)
    private Long gradeOfUser;

}
