package com.sbercourses.spring.Cinema.Controllers.rest;

import com.sbercourses.spring.Cinema.Model.GenericModel;
import com.sbercourses.spring.Cinema.Model.User;
import com.sbercourses.spring.Cinema.dto.GenericDTO;
import com.sbercourses.spring.Cinema.dto.OrderDTO;
import com.sbercourses.spring.Cinema.repository.GenericRepository;
import com.sbercourses.spring.Cinema.service.GenericService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public abstract class GenericController<E extends GenericModel,D extends GenericDTO> {


    protected GenericService<E,D> service;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    protected GenericController(GenericService<E,D> genericService) {
        this.service = genericService;
    }

    @Operation(description = "Получить по Id", method = "getOneById")
    @RequestMapping(value = "/getOneById", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<D> getOneById (@RequestParam(value = "id") Long id)
    {
        return ResponseEntity.status(HttpStatus.OK).body(service.getOne(id));
    }

    @Operation(description = "Получить всё по Id", method = "getAll")
    @RequestMapping(value = "/getAll", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<D>>  getAll ()
    {
        return  ResponseEntity.status(HttpStatus.OK).body(service.listAll());

    }



    @Operation(description = "Создать запись", method = "create")
    @RequestMapping(value = "/create", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<D> create (@RequestBody D newEntity)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(newEntity));

    }

    @Operation(description = "Обновить запись", method = "update")
    @RequestMapping(value = "/update", method = RequestMethod.PUT,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<D> update (@RequestBody D updatedEntity,
                                     @RequestParam(value = "id") Long id)
    {
        updatedEntity.setId(id);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.update(updatedEntity));

    }

    @Operation(description = "Удалить запись по id", method = "delete")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void delete (@PathVariable(value = "id") Long id)
    {
        service.delete(id);

    }



}
