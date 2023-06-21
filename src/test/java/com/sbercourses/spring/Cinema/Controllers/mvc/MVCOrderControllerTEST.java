package com.sbercourses.spring.Cinema.Controllers.mvc;

import com.sbercourses.spring.Cinema.Model.Genre;
import com.sbercourses.spring.Cinema.dto.*;
import com.sbercourses.spring.Cinema.service.DirectorService;
import com.sbercourses.spring.Cinema.service.OrderService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Or;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Slf4j
@Transactional
@Rollback(value = false)
public class MVCOrderControllerTEST extends CommonTestMVC{

    @Autowired
    private OrderService orderService;

    private final FilmDTO filmDTO = new FilmDTO("MVCTEST_FILM",LocalDate.now(),"TEST", Genre.DRAMA,new ArrayList<>(),
            "TEST",new ArrayList<>(),"DESCRIP",true);
    private final UserDTO userDTO=new UserDTO("test","test","first","last","middle",LocalDate.now(),"phone",
            "addres","email",LocalDate.now(),new RoleDTO());

    private final OrderDTO orderDTO  = new OrderDTO(LocalDate.now(),LocalDate.now().plusDays(5),false,
            false,5,filmDTO.getId(),userDTO.getId(),filmDTO);

    @Override
    @Test
    @DisplayName("TEST ALL ORDER ")
    @Order(0)
    @WithMockUser(username = "Kekich",roles = "USER",password = "0411")
    protected void listAll() throws Exception {
        log.info("TEST ALL FILMS");
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/rent/user-films/{id}",1)

                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("userFilms/ViewAllUserFilms"))
                .andExpect(model().attributeExists("orders"))
                .andReturn();
    }




    @Override
    protected void createObject() throws Exception {

    }

    @Override
    protected void updateObject() throws Exception {

    }

    @Override
    protected void deleteObject() throws Exception {

    }


}
