package com.sbercourses.spring.Cinema.Mapper;

import com.sbercourses.spring.Cinema.Model.Grade;
import com.sbercourses.spring.Cinema.Model.Order;
import com.sbercourses.spring.Cinema.dto.GradeDTO;
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
public class GradeMapper extends GenericMapper<Grade, GradeDTO> {

    private final FilmRepository filmRepository;
    private final UserRepository userRepository;
    private final FilmService filmService;

    public GradeMapper(ModelMapper modelMapper, FilmRepository filmRepository, UserRepository userRepository, FilmService filmService) {
        super(Grade.class, GradeDTO.class, modelMapper);
        this.filmRepository = filmRepository;
        this.userRepository = userRepository;
        this.filmService = filmService;
    }

    @Override
    protected void setupMapper() {
        modelMapper.createTypeMap(Grade.class, GradeDTO.class)
                .addMappings(m->m.skip(GradeDTO::setFilmId))
                .addMappings(m->m.skip(GradeDTO::setUserId))
                .setPostConverter(toDTOConverter());

        modelMapper.createTypeMap(GradeDTO.class,Grade.class)
                .addMappings(m->m.skip(Grade::setFilmId))
                .addMappings(m->m.skip(Grade::setUserId))
                .setPostConverter(toEntityConverter());
    }
    @Override
    protected void mapSpecificFields(GradeDTO source, Grade destination) {
        if(!Objects.isNull(source.getFilmId()) && !Objects.isNull(source.getUserId()))
        {
            destination.setFilmId(filmRepository.findById(source.getFilmId()).orElseThrow(() -> new NotFoundException("Фильм не найден")));
            destination.setUserId(userRepository.findById(source.getUserId()).orElseThrow(() -> new NotFoundException("Пользователь не найден")));


        }
    }

    @Override
    protected void mapSpecificFields(Grade source, GradeDTO destination) {
        if(!Objects.isNull(source.getFilmId()) && !Objects.isNull(source.getUserId()))
        {

            destination.setFilmId(source.getFilmId().getId());
            destination.setUserId(source.getUserId().getId());



        }

    }

    @Override
    protected List<Long> getIds(Grade entity) {
        return null;
    }
}
