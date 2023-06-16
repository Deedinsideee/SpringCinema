package com.sbercourses.spring.Cinema.service;

import com.sbercourses.spring.Cinema.Mapper.*;
import com.sbercourses.spring.Cinema.Model.Role;
import com.sbercourses.spring.Cinema.Model.User;
import com.sbercourses.spring.Cinema.dto.FilmDTO;
import com.sbercourses.spring.Cinema.dto.RoleDTO;
import com.sbercourses.spring.Cinema.dto.UserDTO;
import com.sbercourses.spring.Cinema.repository.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService extends GenericService<User,UserDTO>{

    private final OrderService orderService;
    private final RoleRepository roleRepository;
    private final FilmRepository filmRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(RoleRepository roleRepository,
                       GenericRepository<User> repository,
                       GenericMapper<User,UserDTO> mapper,
                       FilmRepository filmRepository,
                       OrderService orderService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        super(repository,mapper);
        this.roleRepository = roleRepository;

        this.filmRepository=filmRepository;
        this.orderService=orderService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDTO create(UserDTO newObject) {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(1L);
        newObject.setRole_id(roleDTO);
        newObject.setPassword(bCryptPasswordEncoder.encode(newObject.getPassword()));

        newObject.setCreatedWhen(LocalDate.now());
        return mapper.toDTO(repository.save(mapper.toEntity(newObject)));
    }


   /* public UserDTO addRole(Long userId,
                               Long roleId)
    {

        Role role = roleRepository.findById(roleId).orElseThrow(()-> new NotFoundException("Такая роль не найдена"));
        UserDTO userDTO = getOne(userId);
        userDTO.setRole_id(mapper.toDTO(role));
        update(userDTO);
        return userDTO;
    }*/


    public List<FilmDTO> listofFilms (Long userId)
    {
        return orderService.ListOfFilmsId(userId);




    }

    public UserDTO getUserByLogin(final String login)
    {

        return mapper.toDTO(((UserRepository) repository).findUserByLogin(login));
    }
    public UserDTO getUserByEmail(final String email)
    {

        return mapper.toDTO(((UserRepository) repository).findUserByEmail(email));
    }






}
