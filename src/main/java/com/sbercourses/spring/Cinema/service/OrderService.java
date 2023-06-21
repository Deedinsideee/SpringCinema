package com.sbercourses.spring.Cinema.service;

import com.sbercourses.spring.Cinema.Mapper.GenericMapper;
import com.sbercourses.spring.Cinema.Mapper.OrderMapper;
import com.sbercourses.spring.Cinema.Mapper.UserMapper;
import com.sbercourses.spring.Cinema.Model.Order;
import com.sbercourses.spring.Cinema.dto.FilmDTO;
import com.sbercourses.spring.Cinema.dto.OrderDTO;

import com.sbercourses.spring.Cinema.dto.UserDTO;
import com.sbercourses.spring.Cinema.repository.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
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


    public Page<OrderDTO> listAll(Pageable pageable,Long userId) {
        Page<Order> objects = ((OrderRepository) repository).getOrderByUserIdId(userId,pageable);
        List<OrderDTO> result = mapper.toDTOs(objects.getContent());/*.stream().filter(s->s.getUser_id()==userId).toList();*/
        return new PageImpl<>(result, pageable, objects.getTotalElements());
    }
    public List<OrderDTO> listAll(Long userId) {
        List<Order> objects = ((OrderRepository) repository).getOrderByUserIdId(userId);
        List<OrderDTO> result = mapper.toDTOs(objects);/*.stream().filter(s->s.getUser_id()==userId).toList();*/
        return result;
    }



    public List<FilmDTO> ListOfFilmsId(Long userId) {
        List<OrderDTO> orderDTOS = listAll()
                .stream()
                .filter(s -> s.getUserId() == userId)
                .collect(Collectors.toList());
        List<FilmDTO> films = new ArrayList<>();
        for (OrderDTO orderDTO : orderDTOS) {
            Long filmId = orderDTO.getFilmId();
            FilmDTO filmDTO = filmService.getListOfFilms(filmId);


            films.add(filmDTO);


        }
        return films;
    }

    public OrderDTO rentBook(final OrderDTO orderDTO) {
        FilmDTO filmDTO = filmService.getOne(orderDTO.getFilmId());


        long rentPeriod = orderDTO.getRent_period() != null ? orderDTO.getRent_period() : 14L;
        orderDTO.setRent_data(LocalDate.now());
        orderDTO.setReturned(false);
        orderDTO.setRent_period((int) rentPeriod);
        orderDTO.setReturn_date(LocalDate.now().plusDays(rentPeriod));
        orderDTO.setPurchase(false);


        return mapper.toDTO(repository.save(mapper.toEntity(orderDTO)));
    }

    public void returnFilm(final Long id) {
        OrderDTO orderDTO = getOne(id);
        orderDTO.setReturned(true);
        orderDTO.setReturn_date(LocalDate.now());
        update(orderDTO);

    }

}
