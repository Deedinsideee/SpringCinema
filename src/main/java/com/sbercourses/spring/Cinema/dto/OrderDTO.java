package com.sbercourses.spring.Cinema.dto;


import com.sbercourses.spring.Cinema.Model.Film;
import com.sbercourses.spring.Cinema.Model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO extends GenericDTO{
    private LocalDate rent_data;
    private LocalDate return_date;
    private Boolean returned;
    private Boolean purchase;
    private Integer rent_period;
    private Long filmId;
    private Long userId;
    private FilmDTO filmDTO;
}
