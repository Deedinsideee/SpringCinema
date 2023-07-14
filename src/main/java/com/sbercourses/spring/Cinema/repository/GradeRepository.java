package com.sbercourses.spring.Cinema.repository;

import com.sbercourses.spring.Cinema.Model.Film;
import com.sbercourses.spring.Cinema.Model.Grade;
import com.sbercourses.spring.Cinema.Model.User;
import com.sbercourses.spring.Cinema.dto.FilmDTO;

import java.util.List;

public interface GradeRepository extends GenericRepository<Grade>{



    public List<Grade> findGradeByFilmId(Film film_id);

    public Grade findGradeByUserIdAndFilmId(User UserId,Film FilmId);

}
