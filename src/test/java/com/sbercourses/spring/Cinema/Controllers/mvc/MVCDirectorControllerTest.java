package com.sbercourses.spring.Cinema.Controllers.mvc;


import com.sbercourses.spring.Cinema.dto.DirectorDTO;
import com.sbercourses.spring.Cinema.service.DirectorService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Slf4j
@Transactional
@Rollback(value = false)
public class MVCDirectorControllerTest extends CommonTestMVC {

    @Autowired
    private  DirectorService directorService;

    private final DirectorDTO  directorDTO  = new DirectorDTO("MVCTEST_DIRECTOR","TESTPOS",LocalDate.now(),"TESTDESC",new ArrayList<>());

    private final DirectorDTO  directorDTOupdated  = new DirectorDTO("MVCTEST_DIRECTOR_UPDATED","TESTPOS", LocalDate.now(),"TESTDESC"
            ,new ArrayList<>());

    @Override
    @Test
    @DisplayName("TEST ALL DIRS ")
    @Order(0)
    @WithAnonymousUser
    protected void listAll() throws Exception {
        log.info("TEST ALL DIRS");
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/directors")
                        .param("page", "1")
                        .param("size", "5")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("directors/viewAllDirectors"))
                .andExpect(model().attributeExists("directors"))
                .andReturn();

    }




    @Override
    @Test
    @DisplayName("CREATE DIR MVC ")
    @Order(1)
    @WithMockUser(username = "admin",roles = "ADMIN",password = "admin")
    protected void createObject() throws Exception {
        log.info("Тест на создание авторов через MVC начат");
        mvc.perform(
                MockMvcRequestBuilders.post("/directors/add")
                .contentType(MediaType.APPLICATION_JSON)
                .flashAttr("dirForm",directorDTO)
                .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/directors"))
                .andExpect(redirectedUrlTemplate("/directors"))
                .andExpect(redirectedUrl("/directors"));
                log.info("END");

    }

    @Override
    protected void updateObject() throws Exception {

    }
    @Override
   /* @Test
    @DisplayName("DELETE DIR MVC ")
    @Order(3)
    @WithMockUser(username = "admin",roles = "ADMIN",password = "admin")*/
    protected void deleteObject() throws Exception {
        /*PageRequest pageRequest = PageRequest.of(0, 5, Sort.by(Sort.Direction.ASC, "dirfio"));
        DirectorDTO foundDirFoDelete = directorService.*/
    }


}
