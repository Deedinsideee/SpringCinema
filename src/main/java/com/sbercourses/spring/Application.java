package com.sbercourses.spring;

import com.sbercourses.spring.dbexample.dao.BookDaoBean;
import com.sbercourses.spring.dbexample.MyDBConfigContext;

import com.sbercourses.spring.dbexample.model.Book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

@SpringBootApplication
public class Application implements CommandLineRunner {


    /*private BookDaoBean bookDaoBean;
    private UserDAO userDAO;
    @Autowired
    public void setBookDaoBean(BookDaoBean bookDaoBean)
    {
        this.bookDaoBean=bookDaoBean;
    }*/

    /*@Autowired
    public void setUserDAO(UserDAO userDAO){this.userDAO=userDAO;}*/
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(" http://localhost:8081/swagger-ui/index.html ");


        //1 JDBC (связанный)
     /*   BookDaoJDBC bookDaoJDBC = new BookDaoJDBC();
        bookDaoJDBC.findBoolByID(1);*/

        //2
        /*BookDaoBean bookDaoBean= new BookDaoBean(DBConnection.INSTANCE.newConnection());
        bookDaoBean.fingBoolById(1);*/

        //3
        /*ApplicationContext context = new AnnotationConfigApplicationContext(MyDBConfigContext.class);
        BookDaoBean bookDaoBean = context.getBean(BookDaoBean.class);
        bookDaoBean.fingBoolById(1);*/

        //4
      /*  bookDaoBean.fingBoolById(1);*/

        /*userDAO.fingBoolById(1);*/

/*
        Scanner scanner = new Scanner(System.in);
        User a = new User("4444","15.08.12","+7777770","uuuu@mgail/com",0);
        userDAO.AddUser(a);
*/
       /*userDAO.FindByPhone_Number("+7904985445");*/


    }
}
