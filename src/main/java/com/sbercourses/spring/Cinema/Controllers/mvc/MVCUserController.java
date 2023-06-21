package com.sbercourses.spring.Cinema.Controllers.mvc;

import com.sbercourses.spring.Cinema.dto.UserDTO;
import com.sbercourses.spring.Cinema.service.UserService;
import com.sbercourses.spring.Cinema.service.userdetails.CustomUserDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import static com.sbercourses.spring.Cinema.constants.UserRolesConstants.ADMIN;

@Controller
@Slf4j
@RequestMapping("/users")
public class MVCUserController {

    private final UserService userService;

    public MVCUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String registration(Model model)
    {
        model.addAttribute("userForm",new UserDTO());
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") UserDTO userDTO,
                               BindingResult bindingResult) {
        if (userDTO.getLogin().equalsIgnoreCase(ADMIN) || userService.getUserByLogin(userDTO.getLogin()) != null) {
            bindingResult.rejectValue("login", "error.login", "Такой логин уже существует");
            return "registration";
        }
        if (userService.getUserByEmail(userDTO.getEmail()) != null) {
            bindingResult.rejectValue("email", "error.email", "Такой e-mail уже существует");
            return "registration";
        }


        userService.create(userDTO);
        return "redirect:login";
    }


    @GetMapping("/listOfUsers")
    public String ListOfUsers(@RequestParam(value = "page", defaultValue = "1") int page,
                              @RequestParam(value = "size", defaultValue = "5") int pageSize,
                              Model model)
    {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.ASC, "id"));
        Page<UserDTO> users = userService.getAllUsers(pageRequest);
        UserDTO a= new UserDTO();

        model.addAttribute("users",users);
        return "users/listOfUsers";
    }

    @GetMapping("/profile/{id}")
    public String getOne(@PathVariable Long id,
                         Model model)
    {

        model.addAttribute("user",userService.getOne(id));
        return "users/viewProfile";
    }




    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id,
            Model model)
    {
        CustomUserDetail customUserDetail = (CustomUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDTO userDTO = userService.getOne(Long.valueOf(customUserDetail.getId()));

        model.addAttribute("user",userDTO);
        return "users/edit";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute("userForm") UserDTO userDTOEdited,Model model)
    {
        CustomUserDetail customUserDetail = (CustomUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDTO userDTOOrig = userService.getOne(Long.valueOf(customUserDetail.getId()));

        userDTOOrig.setPassword(userDTOEdited.getPassword());
        userDTOOrig.setFirstName(userDTOEdited.getFirstName());
        userDTOOrig.setMiddleName(userDTOEdited.getMiddleName());
        userDTOOrig.setLastName(userDTOEdited.getLastName());
        userDTOOrig.setEmail(userDTOEdited.getEmail());
        userDTOOrig.setPhone(userDTOEdited.getPhone());
        userDTOOrig.setBirthDate(userDTOEdited.getBirthDate());
        userDTOOrig.setAddres(userDTOEdited.getAddres());
        userService.update(userDTOOrig);

        /*model.addAttribute("user",userDTOOrig);*/

        return "redirect:/users/profile/"+userDTOOrig.getId();

    }


}
