package com.sbercourses.spring.Cinema.service;

import com.sbercourses.spring.Cinema.Mapper.DirectorMapper;
import com.sbercourses.spring.Cinema.Model.Directors;
import com.sbercourses.spring.Cinema.Model.Film;
import com.sbercourses.spring.Cinema.dto.DirectorDTO;
import com.sbercourses.spring.Cinema.repository.DirectorRepository;
import com.sbercourses.spring.Cinema.repository.FilmRepository;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

@Service
public class DirectorService  extends GenericService<Directors,DirectorDTO>{

    private final FilmRepository filmRepository;


    public DirectorService(FilmRepository filmRepository,
                           DirectorRepository directorRepository,
                           DirectorMapper directorMapper) {
        super(directorRepository,directorMapper);
        this.filmRepository = filmRepository;
    }

    public DirectorDTO addFilm(Long filmId,
                               Long directorId)
    {
        Film film = filmRepository.findById(filmId).orElseThrow(()->new NotFoundException("Не найдено"));
        DirectorDTO directorDTO = getOne(directorId);
        directorDTO.getFilms_id().add(film.getId());
        update(directorDTO);
        return directorDTO;
    }



}
