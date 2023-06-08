package com.sbercourses.spring.Cinema.Mapper;

import com.sbercourses.spring.Cinema.Model.GenericModel;
import com.sbercourses.spring.Cinema.dto.GenericDTO;

import java.util.List;

public interface Mapper<E extends GenericModel,D extends GenericDTO> {

    E toEntity(D dto);

    D toDTO (E entity);

    List<E> toEntities(List<D> dtos);

    List<D> toDTOs(List<E> entities);


}
