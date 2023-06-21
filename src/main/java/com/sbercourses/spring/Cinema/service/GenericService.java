package com.sbercourses.spring.Cinema.service;

import com.sbercourses.spring.Cinema.Mapper.GenericMapper;
import com.sbercourses.spring.Cinema.Model.GenericModel;
import com.sbercourses.spring.Cinema.dto.GenericDTO;

import com.sbercourses.spring.Cinema.repository.GenericRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;

@Service
public abstract class GenericService<E extends GenericModel,D extends GenericDTO> {

    protected final GenericRepository<E> repository;
    protected final GenericMapper<E,D> mapper;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public GenericService(GenericRepository<E> repository, GenericMapper<E, D> mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<D> listAll(){
        return mapper.toDTOs(repository.findAll());
    }

   /* public Page<D> listAll(Pageable pageable) {
        Page<E> objects = repository.findAll(pageable);
        List<D> result = mapper.toDTOs(objects.getContent());
        return new PageImpl<>(result, pageable, objects.getTotalElements());
    }*/

    public D getOne(final Long id)
    {
        return mapper.toDTO(repository.findById(id).orElseThrow(()-> new NotFoundException("Не найдено по id: "+ id)));
    }

    public D create(D newObject)
    {
        return mapper.toDTO(repository.save(mapper.toEntity(newObject)));
    }

    public D update(D updateObject)
    {
        return mapper.toDTO(repository.save(mapper.toEntity(updateObject)));
    }

    public void delete(final Long id)
    {
        repository.deleteById(id);
    }




}
