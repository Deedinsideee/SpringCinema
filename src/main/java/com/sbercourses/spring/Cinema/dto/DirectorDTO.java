package com.sbercourses.spring.Cinema.dto;

import com.sbercourses.spring.Cinema.Model.Directors;
import com.sbercourses.spring.Cinema.Model.Film;
import com.sbercourses.spring.Cinema.Model.GenericModel;
import com.sbercourses.spring.Cinema.Model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DirectorDTO extends GenericDTO {


    private String dirfio;
    private String position;
    private LocalDate birthDate;
    private String description;
    List<Long> films_id;



}
