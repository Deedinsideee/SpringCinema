package com.sbercourses.spring.Cinema.Controllers.rest;
import com.sbercourses.spring.Cinema.Model.User;
import com.sbercourses.spring.Cinema.dto.FilmDTO;
import com.sbercourses.spring.Cinema.dto.UserDTO;
import com.sbercourses.spring.Cinema.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
@Tag(name="Пользователи",description = "Контроллеры для работы с пользователями")
public class UserController extends GenericController<User, UserDTO>
{
    protected UserController(UserService userService) {
        super(userService);
    }

    /*@Operation(description = "Добавить роль к пользователю")
    @RequestMapping (value = "/addRoletoUser",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> addRole (@RequestParam (value = "roleId") Long roleId,
                                            @RequestParam (value = "userId") Long userId)
    {
        return ResponseEntity.status(HttpStatus.OK).body(((UserService)service).addRole(userId,roleId));
    }*/

    @Operation(description = "Просмотреть фильмы пользователя")
    @RequestMapping (value = "/showFilms",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<FilmDTO>> showFilms (
                                            @RequestParam (value = "userId") Long userId)
    {
        return ResponseEntity.status(HttpStatus.OK).body(((UserService)service).listofFilms(userId));
    }


}





