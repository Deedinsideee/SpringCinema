package com.sbercourses.spring.Cinema.Controllers.rest;
import com.sbercourses.spring.Cinema.Model.User;
import com.sbercourses.spring.Cinema.config.jwt.JWTTokenUtil;
import com.sbercourses.spring.Cinema.dto.FilmDTO;
import com.sbercourses.spring.Cinema.dto.LoginDTO;
import com.sbercourses.spring.Cinema.dto.UserDTO;
import com.sbercourses.spring.Cinema.service.UserService;
import com.sbercourses.spring.Cinema.service.userdetails.CustomUserDetailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Slf4j
@RestController
@RequestMapping("/users")
@SecurityRequirement(name = "Bearer Authentication")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Tag(name="Пользователи",description = "Контроллеры для работы с пользователями")
public class UserController extends GenericController<User, UserDTO>
{
    private final CustomUserDetailService customUserDetailService;
    private final JWTTokenUtil jwtTokenUtil;
    private final UserService userService;
    protected UserController(UserService userService,
                             CustomUserDetailService customUserDetailService,
                             JWTTokenUtil jwtTokenUtil,
                             UserService userService1) {
        super(userService);
        this.customUserDetailService = customUserDetailService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userService = userService1;
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

    @PostMapping("/auth")
    public ResponseEntity<?> auth(@RequestBody LoginDTO loginDTO)
    {
        Map<String, Object> response = new HashMap<>();
        log.info("fdsfdsfdsfds: ");
        UserDetails foundUser = customUserDetailService.loadUserByUsername(loginDTO.getLogin());
        log.info("foundUser: {}", foundUser);
        if (!userService.checkPassword(loginDTO.getPassword(), foundUser)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Ошибка авторизации! \n Неверный пароль...");
        }
        String token = jwtTokenUtil.generateToket(foundUser);
        response.put("token", token);
        response.put("username", foundUser.getUsername());
        response.put("role", foundUser.getAuthorities());
        return ResponseEntity.ok().body(response);
    }
}





