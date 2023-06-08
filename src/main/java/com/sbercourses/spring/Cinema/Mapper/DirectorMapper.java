package com.sbercourses.spring.Cinema.Mapper;

import com.sbercourses.spring.Cinema.Model.Directors;
import com.sbercourses.spring.Cinema.Model.GenericModel;
import com.sbercourses.spring.Cinema.dto.DirectorDTO;
import com.sbercourses.spring.Cinema.repository.DirectorRepository;
import com.sbercourses.spring.Cinema.repository.FilmRepository;
import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class DirectorMapper extends GenericMapper<Directors, DirectorDTO> {


    private final DirectorRepository directorRepository;
    private final FilmRepository filmRepository;

    public DirectorMapper(ModelMapper modelMapper, DirectorRepository directorRepository,FilmRepository filmRepository) {
        super(Directors.class, DirectorDTO.class, modelMapper);
        this.directorRepository = directorRepository;
        this.filmRepository = filmRepository;
    }


    @PostConstruct
    protected void setupMapper() {
    modelMapper.createTypeMap(Directors.class,DirectorDTO.class)
            .addMappings(m->m.skip(DirectorDTO::setFilms_id)).setPostConverter(toDTOConverter());

    modelMapper.createTypeMap(DirectorDTO.class,Directors.class)
            .addMappings(m->m.skip(Directors::setFilms)).setPostConverter(toEntityConverter());
    }



    @Override
    protected void mapSpecificFields(DirectorDTO source, Directors destination) {
        if(!Objects.isNull(source.getFilms_id()))
        {
            destination.setFilms(filmRepository.findAllById(source.getFilms_id()));
        }
        else
        {
            destination.setFilms(Collections.emptyList());
        }
    }
    @Override
    protected void mapSpecificFields(Directors source, DirectorDTO destination) {

        destination.setFilms_id(getIds(source));
    }

    @Override
    protected List<Long> getIds(Directors source) {
        return Objects.isNull(source) || Objects.isNull(source.getFilms())
                ? Collections.emptyList()
                : source.getFilms()
                .stream().map(GenericModel::getId)
                .collect(Collectors.toList());
    }




}
