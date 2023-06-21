package com.sbercourses.spring.Cinema.Controllers.mvc;

import com.sbercourses.spring.Cinema.Model.Film;
import com.sbercourses.spring.Cinema.Model.Genre;
import com.sbercourses.spring.Cinema.dto.DirectorDTO;
import com.sbercourses.spring.Cinema.dto.FilmDTO;
import com.sbercourses.spring.Cinema.service.DirectorService;
import com.sbercourses.spring.Cinema.service.FilmService;
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

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

@Slf4j
@Transactional
@Rollback(value = false)
public class MVCFilmControllerTest extends CommonTestMVC {
    @Autowired
    private FilmService filmService;

    private final FilmDTO filmDTO = new FilmDTO("MVCTEST_FILM",LocalDate.now(),"TEST", Genre.DRAMA,new ArrayList<>(),
            "TEST",new ArrayList<>(),"DESCRIP",true);

    private final FilmDTO filmDTOupdated = new FilmDTO("MVCTEST_FILMUPDATED",LocalDate.now(),"TEST", Genre.DRAMA,new ArrayList<>(),
            "TEST",new ArrayList<>(),"DESCRIP",true);


    @Override
    @Test
    @DisplayName("TEST ALL FILMS ")
    @Order(0)
    @WithMockUser(username = "admin", roles = "ADMIN", password = "admin")
    protected void listAll() throws Exception {
        log.info("TEST ALL FILMS");
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/films")
                        .param("page", "1")
                        .param("size", "5")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("films/viewAllFilms"))
                .andExpect(model().attributeExists("films"))
                .andReturn();
    }

    @Override
    @Test
    @DisplayName("CREATE FIlm MVC ")
    @Order(1)
    @WithMockUser(username = "admin", roles = "ADMIN", password = "admin")
    protected void createObject() throws Exception {
        mvc.perform(
                        MockMvcRequestBuilders.post("/films/add")
                                .contentType(MediaType.APPLICATION_JSON)
                                .flashAttr("filmForm",filmDTO)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/films"))
                .andExpect(redirectedUrlTemplate("/films"))
                .andExpect(redirectedUrl("/films"));
        log.info("END");








    }

    @Order(2)
    @Test
    @DisplayName("UPDATE FIlm MVC")
    @WithMockUser(username = "admin", roles = "ADMIN", password = "admin")
    //@WithUserDetails(userDetailsServiceBeanName = "customUserDetailsService", value = "andy_user")
    protected void updateObject() throws Exception {
        log.info("Тест по обновлению автора через MVC начат успешно");
        PageRequest pageRequest = PageRequest.of(0, 5, Sort.by(Sort.Direction.ASC, "title"));
        FilmDTO foundFIlmForUpdate = filmService.searchFilm(filmDTO.getTitle(), pageRequest).getContent().get(0);
        foundFIlmForUpdate.setTitle(filmDTOupdated.getTitle());
        mvc.perform(post("/films/update")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .flashAttr("filmForm", foundFIlmForUpdate)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                )
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/films"))
                .andExpect(redirectedUrl("/films"));
        log.info("Тест по обновлению автора через MVC закончен успешно");
    }


    @Override
    @Test
    @DisplayName("DELETE FIlm MVC ")
    @Order(3)
    @WithMockUser(username = "admin", roles = "ADMIN", password = "admin")
    protected void deleteObject() throws Exception {

        mvc.perform(get("/films/delete/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/films"))
                .andExpect(redirectedUrl("/films"));



    }

}
