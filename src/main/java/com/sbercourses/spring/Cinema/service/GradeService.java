package com.sbercourses.spring.Cinema.service;

import com.sbercourses.spring.Cinema.Mapper.FilmMapper;
import com.sbercourses.spring.Cinema.Mapper.GradeMapper;
import com.sbercourses.spring.Cinema.Model.Film;
import com.sbercourses.spring.Cinema.Model.Grade;
import com.sbercourses.spring.Cinema.Model.User;
import com.sbercourses.spring.Cinema.dto.FilmDTO;
import com.sbercourses.spring.Cinema.dto.GradeDTO;
import com.sbercourses.spring.Cinema.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GradeService extends GenericService<Grade, GradeDTO> {


    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final FilmRepository filmRepository;

    private final GradeRepository gradeRepository;

    private final FilmMapper filmMapper;

    public GradeService(GradeRepository gradeRepository,
                        UserRepository userRepository,
                        FilmRepository filmRepository,
                        GradeMapper gradeMapper,
                        FilmService filmService, OrderRepository orderRepository, GradeRepository gradeRepository1, FilmMapper filmMapper) {
        super(gradeRepository,gradeMapper);
        this.userRepository = userRepository;
        this.filmRepository = filmRepository;


        this.orderRepository = orderRepository;
        this.gradeRepository = gradeRepository1;
        this.filmMapper = filmMapper;
    }


    public List<GradeDTO> getRatingByFilmId(FilmDTO filmDTO) {
        return  mapper.toDTOs(gradeRepository.findGradeByFilmId(filmMapper.toEntity(filmDTO))) ;
    }


    public void addGrade(Long Filmid, Long userId, Long gradeOfUser) {
        GradeDTO a = new GradeDTO(Filmid,userId,gradeOfUser);
        gradeRepository.save(mapper.toEntity(a));
    }

    public Long getUserRate(Long UserId,Long vidId)
    {

        try {
            return   gradeRepository.findGradeByUserIdAndFilmId(userRepository.getOne(UserId),filmRepository.getOne(vidId)).getGradeOfUser() ;
        }
        catch (Exception e)
        {
            return 0L;
        }

    }


}
