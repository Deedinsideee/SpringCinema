package com.sbercourses.spring.Cinema.Mapper;

import com.sbercourses.spring.Cinema.Model.Directors;
import com.sbercourses.spring.Cinema.Model.GenericModel;
import com.sbercourses.spring.Cinema.Model.Role;
import com.sbercourses.spring.Cinema.Model.User;
import com.sbercourses.spring.Cinema.dto.DirectorDTO;
import com.sbercourses.spring.Cinema.dto.UserDTO;
import com.sbercourses.spring.Cinema.repository.DirectorRepository;
import com.sbercourses.spring.Cinema.repository.FilmRepository;
import com.sbercourses.spring.Cinema.repository.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.webjars.NotFoundException;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class UserMapper extends GenericMapper<User, UserDTO> {


    private final RoleRepository roleRepository;

    public UserMapper(ModelMapper modelMapper,RoleRepository roleRepository) {
        super(User.class, UserDTO.class, modelMapper);
        this.roleRepository = roleRepository;

    }

    @Override
    protected void setupMapper() {
     /*   modelMapper.createTypeMap(User.class,UserDTO.class)
                .addMappings(m->m.skip(UserDTO::setRole_id)).setPostConverter(toDTOConverter());

        modelMapper.createTypeMap(UserDTO.class,User.class)
                .addMappings(m->m.skip(User::setRole_id)).setPostConverter(toEntityConverter());*/
    }
    @Override
    protected void mapSpecificFields(UserDTO source, User destination) {


    }

    @Override
    protected void mapSpecificFields(User source, UserDTO destination) {
        if(!Objects.isNull(source.getRole_id()))
        {




        }
    }




    @Override
    protected List<Long> getIds(User entity) {
        return null;
    }
}
