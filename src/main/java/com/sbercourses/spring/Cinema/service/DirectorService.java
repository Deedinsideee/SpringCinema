package com.sbercourses.spring.Cinema.service;

import com.sbercourses.spring.Cinema.Mapper.DirectorMapper;
import com.sbercourses.spring.Cinema.Model.Directors;
import com.sbercourses.spring.Cinema.Model.Film;
import com.sbercourses.spring.Cinema.dto.DirectorDTO;
import com.sbercourses.spring.Cinema.repository.DirectorRepository;
import com.sbercourses.spring.Cinema.repository.FilmRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;

@Service
public class DirectorService  extends GenericService<Directors,DirectorDTO>{

    private final FilmRepository filmRepository;
    private final DirectorRepository directorRepository;


    public DirectorService(FilmRepository filmRepository,
                           DirectorRepository directorRepository,
                           DirectorMapper directorMapper, DirectorRepository directorRepository1) {
        super(directorRepository,directorMapper);
        this.filmRepository = filmRepository;
        this.directorRepository = directorRepository1;
    }

    public Page<DirectorDTO> getAllDirs(Pageable pageable)
    {
        Page<Directors> films = repository.findAll(pageable);
        List<DirectorDTO> result = mapper.toDTOs(films.getContent());
        return new PageImpl<>(result, pageable, films.getTotalElements());
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
