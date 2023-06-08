package com.sbercourses.spring.Cinema.dto;


import com.sbercourses.spring.Cinema.Model.Film;
import com.sbercourses.spring.Cinema.Model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO extends GenericDTO{
    private LocalDate rent_data;
    private LocalDate rent_period;
    private Boolean purchase;
    private Long film_id;
    private Long user_id;
}
