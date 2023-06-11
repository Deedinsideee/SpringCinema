package com.sbercourses.spring.Cinema.Controllers.rest;


import com.sbercourses.spring.Cinema.Model.Role;
import com.sbercourses.spring.Cinema.dto.RoleDTO;
import com.sbercourses.spring.Cinema.service.RoleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
@Tag(name="Роли",description = "Контроллеры для работы с ролями")
public class RoleController extends GenericController<Role, RoleDTO>
{

    protected RoleController(RoleService roleService) {
        super(roleService);
    }
}
