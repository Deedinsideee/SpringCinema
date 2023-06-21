package com.sbercourses.spring.Cinema.Mapper;

import com.sbercourses.spring.Cinema.Model.Directors;
import com.sbercourses.spring.Cinema.Model.Film;
import com.sbercourses.spring.Cinema.Model.GenericModel;
import com.sbercourses.spring.Cinema.dto.FilmDTO;
import com.sbercourses.spring.Cinema.repository.DirectorRepository;
import com.sbercourses.spring.Cinema.repository.FilmRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
@Component
public class FilmMapper extends GenericMapper<Film, FilmDTO> {

    private final DirectorMapper directorMapper;
    private final DirectorRepository directorRepository;
    private final FilmRepository filmRepository;

    public FilmMapper(ModelMapper modelMapper, DirectorMapper directorMapper, DirectorRepository directorRepository, FilmRepository filmRepository) {
        super(Film.class, FilmDTO.class, modelMapper);
        this.directorMapper = directorMapper;
        this.directorRepository = directorRepository;
        this.filmRepository = filmRepository;
    }

    @Override
    protected void setupMapper() {
        modelMapper.createTypeMap(Film.class,FilmDTO.class)
                .addMappings(m->
                {
                    m.skip(FilmDTO::setDirectors_id);
                    m.skip(FilmDTO::setDirInfo);
                    m.skip(FilmDTO::setCanBeRent);
                })
                       .setPostConverter(toDTOConverter());

        modelMapper.createTypeMap(FilmDTO.class,Film.class)
                .addMappings(m->m.skip(Film::setDirectors)).setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapSpecificFields(FilmDTO source, Film destination) {
        if(!Objects.isNull(source.getDirectors_id()))
        {
            destination.setDirectors(directorRepository.findAllById(source.getDirectors_id()));
        }
        else
        {
            destination.setDirectors(Collections.emptyList());
        }
    }

    @Override
    protected void mapSpecificFields(Film source, FilmDTO destination) {

        destination.setDirectors(String.join(", ", source.getDirectors()
                .stream()
                .map(Directors::getDirfio)
                .collect(Collectors.toList())));
        destination.setDirectors_id(getIds(source));
        destination.setDirInfo(directorMapper.toDTOs(source.getDirectors()));

    }



    @Override
    protected List<Long> getIds(Film source) {
        return Objects.isNull(source) || Objects.isNull(source.getDirectors())
                ? Collections.emptyList()
                : source.getDirectors()
                .stream().map(GenericModel::getId)
                .collect(Collectors.toList());
    }
}
