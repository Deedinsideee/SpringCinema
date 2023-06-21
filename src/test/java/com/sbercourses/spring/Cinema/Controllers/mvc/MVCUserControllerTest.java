package com.sbercourses.spring.Cinema.Controllers.mvc;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Slf4j
@Transactional
@Rollback(value = false)
public class MVCUserControllerTest extends CommonTestMVC{

    @Override
    @Test
    @DisplayName("TEST ALL USERS ")
    @Order(0)
    @WithMockUser(username = "admin", roles = "ADMIN", password = "admin")
    protected void listAll() throws Exception {
        log.info("TEST ALL FILMS");
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/users/listOfUsers")
                        .param("page", "1")
                        .param("size", "5")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("users/listOfUsers"))
                .andExpect(model().attributeExists("users"))
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
