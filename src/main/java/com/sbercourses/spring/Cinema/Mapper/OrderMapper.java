package com.sbercourses.spring.Cinema.Mapper;

import com.sbercourses.spring.Cinema.Model.Order;
import com.sbercourses.spring.Cinema.dto.OrderDTO;
import com.sbercourses.spring.Cinema.repository.FilmRepository;
import com.sbercourses.spring.Cinema.repository.UserRepository;
import com.sbercourses.spring.Cinema.service.FilmService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Objects;

@Component
public class OrderMapper extends GenericMapper<Order, OrderDTO> {

    private final FilmRepository filmRepository;
    private final UserRepository userRepository;
    private final FilmService filmService;

    public OrderMapper(ModelMapper modelMapper, FilmRepository filmRepository, UserRepository userRepository, FilmService filmService) {
        super(Order.class, OrderDTO.class, modelMapper);
        this.filmRepository = filmRepository;
        this.userRepository = userRepository;
        this.filmService = filmService;
    }

    @Override
    protected void setupMapper() {
        modelMapper.createTypeMap(Order.class, OrderDTO.class)
                .addMappings(m->m.skip(OrderDTO::setFilmId))
                .addMappings(m->m.skip(OrderDTO::setUserId))
                .setPostConverter(toDTOConverter());

        modelMapper.createTypeMap(OrderDTO.class,Order.class)
                .addMappings(m->m.skip(Order::setFilmId))
                .addMappings(m->m.skip(Order::setUserId))
                .setPostConverter(toEntityConverter());
    }
    @Override
    protected void mapSpecificFields(OrderDTO source, Order destination) {
        if(!Objects.isNull(source.getFilmId()) && !Objects.isNull(source.getUserId()))
        {
            destination.setFilmId(filmRepository.findById(source.getFilmId()).orElseThrow(() -> new NotFoundException("Фильм не найден")));
            destination.setUserId(userRepository.findById(source.getUserId()).orElseThrow(() -> new NotFoundException("Пользователь не найден")));


        }
    }

    @Override
    protected void mapSpecificFields(Order source, OrderDTO destination) {
        if(!Objects.isNull(source.getFilmId()) && !Objects.isNull(source.getUserId()))
        {

            destination.setFilmId(source.getFilmId().getId());
            destination.setUserId(source.getUserId().getId());
            destination.setFilmDTO(filmService.getOne(source.getFilmId().getId()));


        }

    }



    @Override
    protected List<Long> getIds(Order entity) {
        return null;
    }
}
