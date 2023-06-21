package com.sbercourses.spring.Cinema.dto;

import com.sbercourses.spring.Cinema.Model.Genre;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FilmSearchDTO {
    private String title;
    private String dirFIO;
    private Genre genre;


}
