package com.sbercourses.spring.Cinema.Controllers.mvc;

import com.sbercourses.spring.Cinema.dto.DirectorDTO;
import com.sbercourses.spring.Cinema.dto.FilmDTO;
import com.sbercourses.spring.Cinema.service.DirectorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/directors")
public class MVCDirectorController {


    private final DirectorService directorService;

    public MVCDirectorController(DirectorService directorService) {
        this.directorService = directorService;
    }


    @GetMapping("")
    public String getAll(@RequestParam(value = "page", defaultValue = "1") int page,
                         @RequestParam(value = "size", defaultValue = "5") int pageSize,
                         Model model)
    {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.ASC, "dirfio"));
        Page<DirectorDTO> directors = directorService.getAllDirs(pageRequest);
        model.addAttribute("directors",directors);
        return "directors/viewAllDirectors";
    }

    @GetMapping("/{id}")
    public String getOne(@PathVariable Long id,
                         Model model)
    {
        DirectorDTO a = new DirectorDTO();


        model.addAttribute("director",directorService.getOne(id));
        return "directors/viewDir";
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
