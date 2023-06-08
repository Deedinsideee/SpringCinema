package com.sbercourses.spring.Cinema.Controllers;
import com.sbercourses.spring.Cinema.Model.Directors;
import com.sbercourses.spring.Cinema.Model.Film;
import com.sbercourses.spring.Cinema.dto.DirectorDTO;
import com.sbercourses.spring.Cinema.repository.DirectorRepository;
import com.sbercourses.spring.Cinema.repository.FilmRepository;
import com.sbercourses.spring.Cinema.repository.GenericRepository;
import com.sbercourses.spring.Cinema.service.DirectorService;
import com.sbercourses.spring.Cinema.service.GenericService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.webjars.NotFoundException;

@RestController
@RequestMapping("/directors")
@Tag(name="Директоры",description = "Контроллеры для работы с директорами")
public class DirectorController  extends GenericController<Directors,DirectorDTO> {
    protected DirectorController(DirectorService directorService) {
        super(directorService);
    }




    @Operation(description = "Добавить фильм к директору")
    @RequestMapping (value = "/addFilmToDir",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DirectorDTO> addFilm (@RequestParam (value = "filmid") Long filmId,
                                              @RequestParam (value = "directorid") Long dirId)
    {
        return ResponseEntity.status(HttpStatus.OK).body(((DirectorService) service).addFilm(filmId,dirId));
    }




}
