package com.sbercourses.spring.Cinema.Controllers.mvc;


import com.sbercourses.spring.Cinema.dto.FilmSearchDTO;
import com.sbercourses.spring.Cinema.dto.DirectorDTO;
import com.sbercourses.spring.Cinema.dto.FilmDTO;
import com.sbercourses.spring.Cinema.dto.OrderDTO;
import com.sbercourses.spring.Cinema.repository.OrderRepository;
import com.sbercourses.spring.Cinema.service.DirectorService;
import com.sbercourses.spring.Cinema.service.FilmService;
import com.sbercourses.spring.Cinema.service.OrderService;
import com.sbercourses.spring.Cinema.service.userdetails.CustomUserDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/films")
public class MVCFIlmController {


    private final FilmService filmService;
    private final OrderService orderService;
    private final DirectorService directorService;
    public MVCFIlmController(FilmService filmService, OrderService orderService, DirectorService directorService) {
        this.filmService = filmService;
        this.orderService = orderService;
        this.directorService = directorService;
    }

    @GetMapping
    public String getAll(@RequestParam(value = "page", defaultValue = "1") int page,
                         @RequestParam(value = "size", defaultValue = "5") int pageSize,
                         Model model) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.ASC, "title"));
        Page<FilmDTO> films = filmService.getAllFilms(pageRequest);
        /*CustomUserDetail customUserDetail = (CustomUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!customUserDetail.getUsername().equals("admin"))
        {
            List<OrderDTO> orderDTO = orderService.listAll()
                    .stream()
                    .filter(s ->
                            s.getUserId() == Long.valueOf(customUserDetail.getId()) &&
                            !s.getReturned()

                    )

                    .toList();
            String sw = "dsa";
            films.getContent().forEach((s)->
                    s.setCanBeRent(orderDTO.stream()
                            .anyMatch(v->v.getFilmId().equals(s.getId()) && !v.getReturned())));
        }*/


        model.addAttribute("films", films);
        return "films/viewAllFilms";
    }

    @GetMapping("/{id}")
    public String getOne(@PathVariable Long id,
                         Model model)
    {
        FilmDTO filmDTO= new FilmDTO();
        String a =filmDTO.getDirectors();
        model.addAttribute("film",filmService.getOne(id));
        return "films/viewFilm";
    }



    @PostMapping("/search")
    public String searchFilm(@RequestParam(value = "page", defaultValue = "1") int page,
                         @RequestParam(value = "size", defaultValue = "5") int pageSize,
                         @ModelAttribute("filmSearchForm") FilmSearchDTO filmSearchDTO,
                         Model model) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.ASC, "title"));

        model.addAttribute("films", filmService.search(filmSearchDTO,pageRequest));

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

    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id,
                         Model model) {

        model.addAttribute("film", filmService.getOne(id));
        return "films/updateFilm";
    }

   @PostMapping("/update")
    public String update(@ModelAttribute("filmForm") FilmDTO filmDTO) {
        filmService.update(filmDTO);
        return "redirect:/films";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id)  {
        filmService.delete(id);
        return "redirect:/films";
    }














}
