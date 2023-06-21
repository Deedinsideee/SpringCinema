package com.sbercourses.spring.Cinema.Controllers.mvc;

import com.sbercourses.spring.Cinema.dto.FilmDTO;
import com.sbercourses.spring.Cinema.dto.OrderDTO;
import com.sbercourses.spring.Cinema.service.FilmService;
import com.sbercourses.spring.Cinema.service.OrderService;
import com.sbercourses.spring.Cinema.service.userdetails.CustomUserDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@Slf4j
@RequestMapping("/rent")
public class MVCFilmOrderController {
    private final FilmService filmService;
    private final OrderService orderService;

    public MVCFilmOrderController(FilmService filmService, OrderService orderService) {
        this.filmService = filmService;
        this.orderService = orderService;
    }
    @GetMapping("/film/{filmId}")
    public String createOrder(@PathVariable Long filmId, Model model )
    {
        model.addAttribute("film",filmService.getOne(filmId));
        return "userFilms/rentFilm";
    }
    @PostMapping("/film")
    public String createOrder(@ModelAttribute("rentFilmForm") OrderDTO rentFilmForm )
    {
        CustomUserDetail customUserDetail = (CustomUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        rentFilmForm.setUserId(Long.valueOf(customUserDetail.getId()));
        orderService.rentBook(rentFilmForm);

        return "redirect:/films";
    }


    @GetMapping("/return-film/{id}")
    public String returnBook(@PathVariable Long id) {
        CustomUserDetail customUserDetails = (CustomUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        orderService.returnFilm(id);
        return "redirect:/rent/user-films/" + customUserDetails.getId();
    }





    @GetMapping("/user-films/{id}")
    public String userFilms(/*@RequestParam(value = "page", defaultValue = "1") int page,
                            @RequestParam(value = "size", defaultValue = "5") int pageSize,*/
                            @PathVariable Long id,
                            Model model )
    {
      /*  PageRequest pageRequest = PageRequest.of(page - 1, pageSize);*/
        CustomUserDetail customUserDetail = (CustomUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        /*Page<OrderDTO> orders = orderService.listAll(pageRequest,Long.valueOf(customUserDetail.getId()));
        int i= orders.getTotalPages();*/
        List<OrderDTO> orders = orderService.listAll(Long.valueOf(customUserDetail.getId()));
        model.addAttribute("orders", orders);
        return "userFilms/ViewAllUserFilms";
    }





}
