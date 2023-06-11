package com.sbercourses.spring.Cinema.dto;

import com.sbercourses.spring.Cinema.Model.Directors;
import com.sbercourses.spring.Cinema.Model.Genre;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FilmDTO extends GenericDTO {
    private String title;
    private BigInteger premier_year;
    private BigInteger county;
    private Genre genre;
    List<Long> directors_id;
    private String directors;





}
