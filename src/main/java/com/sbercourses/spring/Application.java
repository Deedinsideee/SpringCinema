package com.sbercourses.spring;

import com.sbercourses.spring.dbexample.dao.BookDaoBean;
import com.sbercourses.spring.dbexample.MyDBConfigContext;

import com.sbercourses.spring.dbexample.model.Book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Scanner;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Value("${server.port}")
    private String serverPort;



    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashpass = encoder.encode("admin");
        System.out.println(hashpass);

        System.out.println(" http://localhost:"+ serverPort + "/swagger-ui/index.html ");
        System.out.println(" http://localhost:"+ serverPort);

    }
}
