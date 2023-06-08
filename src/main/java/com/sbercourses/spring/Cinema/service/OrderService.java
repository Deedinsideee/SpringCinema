package com.sbercourses.spring.Cinema.service;

import com.sbercourses.spring.Cinema.Mapper.GenericMapper;
import com.sbercourses.spring.Cinema.Mapper.OrderMapper;
import com.sbercourses.spring.Cinema.Mapper.UserMapper;
import com.sbercourses.spring.Cinema.Model.Order;
import com.sbercourses.spring.Cinema.dto.FilmDTO;
import com.sbercourses.spring.Cinema.dto.OrderDTO;

import com.sbercourses.spring.Cinema.dto.UserDTO;
import com.sbercourses.spring.Cinema.repository.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService extends GenericService<Order, OrderDTO> {

    private final FilmService filmService;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final FilmRepository filmRepository;
    private final OrderMapper orderMapper;

    public OrderService( OrderRepository orderRepository,
                       UserRepository userRepository,
                       FilmRepository filmRepository,
                       OrderMapper orderMapper,
                         FilmService filmService) {
        super(orderRepository,orderMapper);
        this.userRepository = userRepository;
        this.filmRepository = filmRepository;
        this.orderMapper=orderMapper;
        this.orderRepository=orderRepository;
        this.filmService = filmService;
    }


    public List<FilmDTO> ListOfFilmsId(Long userId) {
        List<OrderDTO> orderDTOS = listAll()
                .stream()
                .filter(s -> s.getUser_id() == userId)
                .collect(Collectors.toList());
        List<FilmDTO> films = new ArrayList<>();
        for (OrderDTO orderDTO : orderDTOS) {
            Long filmId = orderDTO.getFilm_id();
            FilmDTO filmDTO = filmService.getListOfFilms(filmId);

            // Add the retrieved FilmDTO object to the films list
            films.add(filmDTO);


        }
        return films;
    }



}
