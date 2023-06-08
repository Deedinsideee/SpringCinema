package com.sbercourses.spring.Cinema.service;

import com.sbercourses.spring.Cinema.Mapper.GenericMapper;
import com.sbercourses.spring.Cinema.Mapper.RoleMapper;
import com.sbercourses.spring.Cinema.Mapper.UserMapper;
import com.sbercourses.spring.Cinema.Model.Role;
import com.sbercourses.spring.Cinema.dto.RoleDTO;
import com.sbercourses.spring.Cinema.repository.GenericRepository;
import com.sbercourses.spring.Cinema.repository.RoleRepository;
import com.sbercourses.spring.Cinema.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService extends GenericService<Role, RoleDTO> {

    public RoleService(RoleRepository roleRepository,
                       RoleMapper roleMapper) {
        super(roleRepository,roleMapper);
    }




}
