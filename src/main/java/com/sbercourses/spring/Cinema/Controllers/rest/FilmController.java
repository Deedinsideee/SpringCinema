package com.sbercourses.spring.Cinema.Controllers.rest;


import com.sbercourses.spring.Cinema.Model.Film;
import com.sbercourses.spring.Cinema.dto.FilmDTO;
import com.sbercourses.spring.Cinema.service.FilmService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/film")
@Tag(name="Фильмы",description = "Контроллеры для работы с фильмами")
public class FilmController extends GenericController<Film, FilmDTO>
{

    protected FilmController(FilmService service) {
        super(service);
    }
    @Operation(description = "Добавить директора к фильму ")
    @RequestMapping (value = "/addDirToFilm",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FilmDTO> addFilm (@RequestParam (value = "filmid") Long filmId,
                                                @RequestParam (value = "directorid") Long dirId)
    {
        return ResponseEntity.status(HttpStatus.OK).body(((FilmService) service).addDirector(filmId,dirId));
    }


}




