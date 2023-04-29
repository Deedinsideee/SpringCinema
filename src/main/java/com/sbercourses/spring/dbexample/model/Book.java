package com.sbercourses.spring.dbexample.model;

import lombok.*;

import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Book {
    private Integer id;
    private String title;
    private String author;
    private Date dateAdded;
}
