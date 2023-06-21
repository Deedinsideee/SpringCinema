package com.sbercourses.spring.Cinema.repository;

import com.sbercourses.spring.Cinema.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UserRepository extends GenericRepository<User> {

    User findUserByLogin(String login);

    User findUserByEmail (String email);


}
