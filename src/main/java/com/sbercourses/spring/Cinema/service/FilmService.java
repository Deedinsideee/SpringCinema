package com.sbercourses.spring.Cinema.service;

import com.sbercourses.spring.Cinema.Mapper.FilmMapper;
import com.sbercourses.spring.Cinema.Model.Directors;
import com.sbercourses.spring.Cinema.Model.Film;
import com.sbercourses.spring.Cinema.Model.Grade;
import com.sbercourses.spring.Cinema.Model.Order;
import com.sbercourses.spring.Cinema.dto.FilmDTO;
import com.sbercourses.spring.Cinema.dto.FilmSearchDTO;
import com.sbercourses.spring.Cinema.repository.DirectorRepository;
import com.sbercourses.spring.Cinema.repository.FilmRepository;
import com.sbercourses.spring.Cinema.repository.GradeRepository;
import com.sbercourses.spring.Cinema.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class FilmService extends GenericService<Film, FilmDTO>{

    private final DirectorService directorService;
    private final FilmRepository filmRepository;
    private final DirectorRepository directorRepository ;
    private final OrderRepository orderRepository;

    private final GradeRepository gradeRepository;

    public FilmService(
            FilmMapper filmMapper,
            DirectorService directorService, FilmRepository filmRepository, DirectorRepository directorRepository, OrderRepository orderRepository, GradeRepository gradeRepository) {
        super( filmRepository,filmMapper);
        this.directorService = directorService;
        this.filmRepository = filmRepository;
        this.directorRepository = directorRepository;
        this.orderRepository = orderRepository;

        this.gradeRepository = gradeRepository;
    }


    public Page<FilmDTO> getAllFilms(Pageable pageable)
    {
        Page<Film> films = repository.findAll(pageable);
        List<FilmDTO> result = mapper.toDTOs(films.getContent());
        return new PageImpl<>(result, pageable, films.getTotalElements());
    }

    @Override
    public FilmDTO create(FilmDTO newObject)
    {
        newObject.setCountOfViews(0L);
        return mapper.toDTO(repository.save(mapper.toEntity(newObject)));
    }



    public Page<FilmDTO> search(FilmSearchDTO filmSearchDTO, Pageable pageable)
    {
       String genre = filmSearchDTO.getGenre()!=null
               ? String.valueOf(filmSearchDTO.getGenre().ordinal())
               :null;
       Page<Film> filmspag = filmRepository.searchBooks(
               genre,
               filmSearchDTO.getTitle(),
               filmSearchDTO.getDirFIO(),
               pageable
       );
       List<FilmDTO> res = mapper.toDTOs(filmspag.getContent());
       return new PageImpl<>(res, pageable, filmspag.getTotalElements());
    }



    public FilmDTO addDirector(Long filmId,
                               Long directorId)
    {
        FilmDTO film = getOne(filmId);
       directorService.addFilm(filmId,directorId);
       return film;

    }




    public Page<FilmDTO> searchFilm(final String title,
                                         Pageable pageable) {
        Page<Film> films = ((FilmRepository)repository).findAllByTitle(title, pageable);
        List<FilmDTO> result = mapper.toDTOs(films.getContent());
        return new PageImpl<>(result, pageable, films.getTotalElements());
    }



    public FilmDTO getListOfFilms(Long id)
    {

       return  getOne(id);

    }

    public void delete(final Long id)
    {
        Film a = repository.getOne(id);
        List<Directors> directors = directorRepository.findAll();
        List<Grade>  grades = gradeRepository.findGradeByFilmId(filmRepository.getOne(id));
        gradeRepository.deleteAll(grades);



        List<Order> orders= orderRepository.getOrdersByFilmIdId(id);
            directors.forEach(director -> {
                List<Film> films = director.getFilms();
                films.removeIf(film -> film.getId() == a.getId());
            });

            orderRepository.deleteAll(orders);
            directorRepository.saveAll(directors);
            repository.deleteById(id);
    }


    public List<FilmDTO> getAllByGenre(FilmDTO filmDTO) {

        return mapper.toDTOs(filmRepository.getTop6FilmsByGenreOrderByCountOfViews(filmDTO.getGenre()));
    }
}
