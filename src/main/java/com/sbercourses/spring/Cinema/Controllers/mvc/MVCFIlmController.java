package com.sbercourses.spring.Cinema.Controllers.mvc;


import com.sbercourses.spring.Cinema.Model.Grade;
import com.sbercourses.spring.Cinema.dto.*;
import com.sbercourses.spring.Cinema.service.DirectorService;
import com.sbercourses.spring.Cinema.service.FilmService;
import com.sbercourses.spring.Cinema.service.GradeService;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/films")
public class MVCFIlmController {


    private final FilmService filmService;
    private final OrderService orderService;
    private final DirectorService directorService;

    private final GradeService gradeService;
    public MVCFIlmController(FilmService filmService, OrderService orderService, DirectorService directorService, GradeService gradeService) {
        this.filmService = filmService;
        this.orderService = orderService;
        this.directorService = directorService;
        this.gradeService = gradeService;
    }

    @GetMapping
    public String getAll(@RequestParam(value = "page", defaultValue = "1") int page,
                         @RequestParam(value = "size", defaultValue = "10") int pageSize,
                         Model model) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.ASC, "title"));
        Page<FilmDTO> films = filmService.getAllFilms(pageRequest);
        CustomUserDetail customUserDetail = (CustomUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
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
        }


        model.addAttribute("films", films);
        return "films/viewAllFilms";
    }

    @GetMapping("/{id}")
    public String getOne(@PathVariable Long id,
                         Model model)
    {
        FilmDTO filmDTO = filmService.getOne(id);
        CustomUserDetail customUserDetail = (CustomUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<OrderDTO> orderDTO = orderService.listAll()
                .stream()
                .filter(s ->
                        s.getUserId() == Long.valueOf(customUserDetail.getId()) &&
                                !s.getReturned()
                )
                .toList();


        for (OrderDTO order : orderDTO) {

            if (order.getFilmId() == filmDTO.getId()) {
                filmDTO.setCanBeRent(false);
                break;
            }
        }

        model.addAttribute("film", filmDTO);
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
    public String create(@ModelAttribute("filmForm") FilmDTO newFilm, @RequestParam("videoFile") MultipartFile videoFile) throws IOException {
        Path targetDirectory = Paths.get("src/main/resources/static/video");
        if (!Files.exists(targetDirectory)) {
            Files.createDirectories(targetDirectory);
        }
        String s = videoFile.getName();
        String originalFilename = videoFile.getOriginalFilename();
        Path targetFile = targetDirectory.resolve(originalFilename);
        videoFile.transferTo(targetFile);
        newFilm.setPathto(originalFilename);
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


    @GetMapping("/view/{id}")
    public String viewVideo(@PathVariable Long id, Model model ) {

        FilmDTO filmDTO = filmService.getOne(id);
        List<GradeDTO> grades = gradeService.getRatingByFilmId(filmDTO);
        List<Long> rating = grades.stream()
                .map(GradeDTO::getGradeOfUser).toList();

        double averageRating = 0.0;
        if (grades != null && !grades.isEmpty()) {
             averageRating = grades.stream()
                    .mapToLong(GradeDTO::getGradeOfUser)
                    .average()
                    .orElse(0.0);

        }

        CustomUserDetail customUserDetail = (CustomUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userRating = gradeService.getUserRate(Long.valueOf(customUserDetail.getId()),id);
        if (userRating==null)
        {
            userRating=0L;
        }
        Long a = filmDTO.getCountOfViews();
        filmDTO.setCountOfViews(a+1);
        filmService.update(filmDTO);
        String videoUrl = "/video/"+filmDTO.getPathto();
        model.addAttribute("videoUrl", videoUrl);

        List<FilmDTO> filmDTOS = filmService.getAllByGenre(filmDTO);

        List<FilmDTO> filteredFilmDTOS = filmDTOS.stream()
                .filter(ss -> ss.getId() != filmDTO.getId())
                .collect(Collectors.toList());

        Collections.reverse(filteredFilmDTOS);
        model.addAttribute("userRating",userRating);
        model.addAttribute("rating",averageRating);
        model.addAttribute("film",filmDTO);
        model.addAttribute("films", filteredFilmDTOS);
        return "films/view";
    }






    @PostMapping("/rate/{id}")
    public String rate(@ModelAttribute("GradeForm") GradeDTO gradeDTO
    ,@PathVariable Long id) {

        CustomUserDetail customUserDetail = (CustomUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        gradeService.addGrade(id,Long.valueOf(customUserDetail.getId()),gradeDTO.getGradeOfUser());
        return "redirect:/films/view/" + id;
    }



}
