package com.sbercourses.spring.Cinema.Controllers.mvc;


import com.sbercourses.spring.Cinema.dto.DirectorDTO;
import com.sbercourses.spring.Cinema.dto.FilmDTO;
import com.sbercourses.spring.Cinema.service.DirectorService;
import com.sbercourses.spring.Cinema.service.FilmService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/films")
public class MVCFIlmController {


    private final FilmService filmService;
    private final DirectorService directorService;
    public MVCFIlmController(FilmService filmService, DirectorService directorService) {
        this.filmService = filmService;
        this.directorService = directorService;
    }

    @GetMapping("")
    public String getAll(Model model)
    {

        List<FilmDTO> films = filmService.listAll();

        model.addAttribute("films",films);
        return "films/viewAllFilms";
    }

    @GetMapping("/add")
    public String create()
    {
        return "films/addFilm";

    }
    @PostMapping("/add")
    public String create(@ModelAttribute("filmForm") FilmDTO newFilm)
    {
        log.info(newFilm.toString());
        filmService.create(newFilm);
        return "redirect:/films";
    }
    @GetMapping("/addDir")
    public String adddir(Model model)
    {
        List<DirectorDTO> directorDTOS = directorService.listAll();

        model.addAttribute("directors",directorDTOS);
        List<FilmDTO> filmDTOS = filmService.listAll();
        model.addAttribute("films",filmDTOS);
        return "films/addDir";

    }
    @PostMapping("/addDir")
    public String adddir(@RequestParam("film") Long filmId,
                         @RequestParam("director") Long directorId
    )
    {
        filmService.addDirector(filmId,directorId);
        return "redirect:/films";

    }


}
