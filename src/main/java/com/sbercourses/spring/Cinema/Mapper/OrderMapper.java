package com.sbercourses.spring.Cinema.Mapper;

import com.sbercourses.spring.Cinema.Model.Order;
import com.sbercourses.spring.Cinema.dto.OrderDTO;
import com.sbercourses.spring.Cinema.repository.FilmRepository;
import com.sbercourses.spring.Cinema.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Objects;

@Component
public class OrderMapper extends GenericMapper<Order, OrderDTO> {

    private final FilmRepository filmRepository;
    private final UserRepository userRepository;

    public OrderMapper(ModelMapper modelMapper, FilmRepository filmRepository, UserRepository userRepository) {
        super(Order.class, OrderDTO.class, modelMapper);
        this.filmRepository = filmRepository;
        this.userRepository = userRepository;
    }

    @Override
    protected void setupMapper() {
        modelMapper.createTypeMap(Order.class, OrderDTO.class)
                .addMappings(m->m.skip(OrderDTO::setFilm_id))
                .addMappings(m->m.skip(OrderDTO::setUser_id))
                .setPostConverter(toDTOConverter());

        modelMapper.createTypeMap(OrderDTO.class,Order.class)
                .addMappings(m->m.skip(Order::setFilm_id))
                .addMappings(m->m.skip(Order::setUser_id))
                .setPostConverter(toEntityConverter());
    }
    @Override
    protected void mapSpecificFields(OrderDTO source, Order destination) {
        if(!Objects.isNull(source.getFilm_id()) && !Objects.isNull(source.getUser_id()))
        {
            destination.setFilm_id(filmRepository.findById(source.getFilm_id()).orElseThrow(() -> new NotFoundException("Книги не найдено")));
            destination.setUser_id(userRepository.findById(source.getUser_id()).orElseThrow(() -> new NotFoundException("Книги не найдено")));


        }
    }

    @Override
    protected void mapSpecificFields(Order source, OrderDTO destination) {
        if(!Objects.isNull(source.getFilm_id()) && !Objects.isNull(source.getUser_id()))
        {

            destination.setFilm_id(source.getFilm_id().getId());
            destination.setUser_id(source.getUser_id().getId());


        }

    }



    @Override
    protected List<Long> getIds(Order entity) {
        return null;
    }
}
