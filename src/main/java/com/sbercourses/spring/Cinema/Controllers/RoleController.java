package com.sbercourses.spring.Cinema.Controllers;


import com.sbercourses.spring.Cinema.Model.Role;
import com.sbercourses.spring.Cinema.Model.User;
import com.sbercourses.spring.Cinema.dto.RoleDTO;
import com.sbercourses.spring.Cinema.repository.GenericRepository;
import com.sbercourses.spring.Cinema.repository.UserRepository;
import com.sbercourses.spring.Cinema.service.GenericService;
import com.sbercourses.spring.Cinema.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.webjars.NotFoundException;

import java.io.Serializable;

@RestController
@RequestMapping("/role")
@Tag(name="Роли",description = "Контроллеры для работы с ролями")
public class RoleController extends GenericController<Role, RoleDTO>
{

    protected RoleController(RoleService roleService) {
        super(roleService);
    }
}
