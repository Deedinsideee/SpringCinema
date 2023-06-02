package com.sbercourses.spring.Cinema.Controllers;

import com.sbercourses.spring.Cinema.Model.GenericModel;
import com.sbercourses.spring.Cinema.Model.User;
import com.sbercourses.spring.Cinema.repository.GenericRepository;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;

import java.util.ArrayList;
import java.util.List;


@RestController
public abstract class GenericController<T extends GenericModel> {

    private final GenericRepository<T> genericRepository;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    protected GenericController(GenericRepository<T> genericRepository) {
        this.genericRepository = genericRepository;
    }

    @Operation(description = "Получить по Id", method = "getOneById")
    @RequestMapping(value = "/getOneById", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<T> getOneById (@RequestParam(value = "id") Long id)
    {
        return ResponseEntity.status(HttpStatus.OK).body(genericRepository.findById(id).orElseThrow(()-> new NotFoundException("Данные не найдены")));
    }

    @Operation(description = "Получить всё по Id", method = "getAll")
    @RequestMapping(value = "/getAll", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<T>>  getAll ()
    {
        return  ResponseEntity.status(HttpStatus.OK).body(genericRepository.findAll());

    }



    @Operation(description = "Создать запись", method = "create")
    @RequestMapping(value = "/create", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<T> create (@RequestBody T newEntity)
    {
        genericRepository.save(newEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(newEntity);

    }

    @Operation(description = "Обновить запись", method = "update")
    @RequestMapping(value = "/update", method = RequestMethod.PUT,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<T> update (@RequestBody T updatedEntity,
                                     @RequestParam(value = "id") Long id)
    {
        updatedEntity.setId(id);
        genericRepository.save(updatedEntity);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedEntity);

    }

    @Operation(description = "Удалить запись по id", method = "delete")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void delete (@PathVariable(value = "id") Long id)
    {
        genericRepository.deleteById(id);

    }



}
