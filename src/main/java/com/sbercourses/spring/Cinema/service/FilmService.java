package com.sbercourses.spring.Cinema.service;

import com.sbercourses.spring.Cinema.Mapper.FilmMapper;
import com.sbercourses.spring.Cinema.Model.Directors;
import com.sbercourses.spring.Cinema.Model.Film;
import com.sbercourses.spring.Cinema.dto.DirectorDTO;
import com.sbercourses.spring.Cinema.dto.FilmDTO;
import com.sbercourses.spring.Cinema.repository.DirectorRepository;
import com.sbercourses.spring.Cinema.repository.FilmRepository;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FilmService extends GenericService<Film, FilmDTO>{

    private final DirectorService directorService;
    private final FilmRepository filmRepository;
    private final DirectorRepository directorRepository ;


    public FilmService(
            FilmMapper filmMapper,
            DirectorService directorService, FilmRepository filmRepository, DirectorRepository directorRepository) {
        super( filmRepository,filmMapper);
        this.directorService = directorService;
        this.filmRepository = filmRepository;
        this.directorRepository = directorRepository;
    }

    public FilmDTO addDirector(Long filmId,
                               Long directorId)
    {
        FilmDTO film = getOne(filmId);
       directorService.addFilm(filmId,directorId);
       return film;

    }




    public FilmDTO getListOfFilms(Long id)
    {

       return  getOne(id);

    }






}
