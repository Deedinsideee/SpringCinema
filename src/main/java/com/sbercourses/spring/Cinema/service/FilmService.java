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

@Service
public class FilmService extends GenericService<Film, FilmDTO>{

    private final FilmRepository filmRepository;
    private final DirectorRepository directorRepository ;


    public FilmService(
            FilmMapper filmMapper,
            FilmRepository filmRepository, DirectorRepository directorRepository) {
        super( filmRepository,filmMapper);
        this.filmRepository = filmRepository;
        this.directorRepository = directorRepository;
    }

    public FilmDTO addDirector(Long filmId,
                               Long directorId)
    {
       FilmDTO filmDTO = getOne(filmId);
       Directors directors = directorRepository.findById(directorId).orElseThrow(()->new NotFoundException("Директор не найден"));
       filmDTO.getDirectors_id().add(directors.getId());
       update(filmDTO);
       return filmDTO;

    }


    public FilmDTO getListOfFilms(Long id)
    {

       return  getOne(id);

    }






}
