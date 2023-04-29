package com.sbercourses.spring.dbexample;

import com.sbercourses.spring.dbexample.dao.BookDaoBean;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static com.sbercourses.spring.dbexample.constants.DBConstants.*;
@Configuration
/*@ComponentScan*/
public class MyDBConfigContext {
    @Bean
    @Scope("singleton")
    public Connection newConnection() throws SQLException
    {
        return DriverManager.getConnection("jdbc:postgresql://"+ DB_HOST+":" + PORT +"/"+DB,USER,PASSWORD );
    }


}
