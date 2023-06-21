package com.sbercourses.spring.Cinema.repository;

import com.sbercourses.spring.Cinema.Model.Order;
import com.sbercourses.spring.Cinema.Model.User;
import com.sbercourses.spring.Cinema.dto.OrderDTO;
import groovyjarjarantlr4.v4.runtime.atn.SemanticContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends GenericRepository<Order>{
    /*@Query(nativeQuery = true,
         value = """
    SELECT  distinct b.* FROM orders b WHERE b.film_id = :filmId
    """  )*/
    List<Order> getOrdersByFilmIdId(Long filmId);


    Page<Order> getOrderByUserIdId(Long id,
                                              Pageable pageRequest);

    List<Order> getOrderByUserIdId(Long id
                                   );


}
