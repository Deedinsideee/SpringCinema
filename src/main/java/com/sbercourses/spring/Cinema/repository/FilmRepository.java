package com.sbercourses.spring.Cinema.repository;

import com.sbercourses.spring.Cinema.Model.Film;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmRepository extends GenericRepository<Film> {

    @Query(nativeQuery = true,
    value = """
     select distinct b.*, a.dirfio
     from film b
              left join film_directors ba on b.id = ba.films_id
              left join directors a on a.id = ba.directors_id
     where b.title ilike '%' || coalesce(:title, '%')  || '%'
       and cast(b.genre as char) like coalesce(:genre, '%')
       and coalesce(a.dirfio, '%') ilike '%' ||  coalesce(:fio, '%')  || '%'
     
     
     
     
     
     
     
     """ )
    Page<Film> searchBooks (@Param(value = "genre") String genre,
                            @Param(value = "title") String title,
                            @Param(value = "fio") String dirFio,
                            Pageable pageable);


    Page<Film> findAllByTitle(String title,
                              Pageable pageable);





}
