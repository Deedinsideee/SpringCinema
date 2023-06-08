package com.sbercourses.spring.Cinema.Mapper;

import com.sbercourses.spring.Cinema.Model.Role;
import com.sbercourses.spring.Cinema.Model.User;
import com.sbercourses.spring.Cinema.dto.RoleDTO;
import com.sbercourses.spring.Cinema.dto.UserDTO;
import com.sbercourses.spring.Cinema.repository.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoleMapper extends GenericMapper<Role, RoleDTO>{

    public RoleMapper(ModelMapper modelMapper) {
        super(Role.class, RoleDTO.class, modelMapper);
    }

    @Override
    protected void setupMapper() {

    }
    @Override
    protected void mapSpecificFields(RoleDTO source, Role destination) {

    }

    @Override
    protected void mapSpecificFields(Role source, RoleDTO destination) {

    }



    @Override
    protected List<Long> getIds(Role entity) {
        return null;
    }
}
