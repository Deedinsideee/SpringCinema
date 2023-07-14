package com.sbercourses.spring.Cinema.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GradeDTO extends GenericDTO {

    private Long filmId;
    private Long userId;
    private Long gradeOfUser;


}
