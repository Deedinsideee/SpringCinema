package com.sbercourses.spring.Cinema.service;

import com.sbercourses.spring.Cinema.Mapper.DirectorMapper;
import com.sbercourses.spring.Cinema.Mapper.FilmMapper;
import com.sbercourses.spring.Cinema.Mapper.UserMapper;
import com.sbercourses.spring.Cinema.Model.Film;
import com.sbercourses.spring.Cinema.Model.Role;
import com.sbercourses.spring.Cinema.Model.User;
import com.sbercourses.spring.Cinema.dto.DirectorDTO;
import com.sbercourses.spring.Cinema.dto.FilmDTO;
import com.sbercourses.spring.Cinema.dto.UserDTO;
import com.sbercourses.spring.Cinema.repository.DirectorRepository;
import com.sbercourses.spring.Cinema.repository.FilmRepository;
import com.sbercourses.spring.Cinema.repository.RoleRepository;
import com.sbercourses.spring.Cinema.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;

@Service
public class UserService extends GenericService<User,UserDTO>{

    private final OrderService orderService;
    private final RoleRepository roleRepository;
    private final FilmRepository filmRepository;
    private final UserRepository userRepository;

    public UserService(RoleRepository roleRepository,
                           UserRepository userRepository,
                           UserMapper userMapper,
                       FilmRepository filmRepository,
                       OrderService orderService) {
        super(userRepository,userMapper);
        this.roleRepository = roleRepository;
        this.userRepository=userRepository;
        this.filmRepository=filmRepository;
        this.orderService=orderService;
    }

    public UserDTO addRole(Long userId,
                               Long roleId)
    {

        Role role = roleRepository.findById(roleId).orElseThrow(()-> new NotFoundException("Такая роль не найдена"));
        UserDTO userDTO = getOne(userId);
        userDTO.setRole_id(roleId);
        update(userDTO);
        return userDTO;
    }


    public List<FilmDTO> listofFilms (Long userId)
    {
        return orderService.ListOfFilmsId(userId);




    }






}
