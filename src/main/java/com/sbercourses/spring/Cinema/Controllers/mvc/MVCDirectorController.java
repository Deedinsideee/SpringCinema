package com.sbercourses.spring.Cinema.Controllers.mvc;

import com.sbercourses.spring.Cinema.dto.DirectorDTO;
import com.sbercourses.spring.Cinema.dto.FilmDTO;
import com.sbercourses.spring.Cinema.service.DirectorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/directors")
public class MVCDirectorController {


    private final DirectorService directorService;

    public MVCDirectorController(DirectorService directorService) {
        this.directorService = directorService;
    }


    @GetMapping("")
    public String getAll(Model model)
    {

        List<DirectorDTO> directors = directorService.listAll();
        model.addAttribute("directors",directors);
        return "directors/viewAllDirectors";
    }


    @GetMapping("/add")
    public String create()
    {
        return "directors/addDir";
    }


    @PostMapping("/add")
    public String create(@ModelAttribute("dirForm") DirectorDTO newDir)
    {
        log.info(newDir.toString());
        directorService.create(newDir);
        return "redirect:/directors";
    }


}
