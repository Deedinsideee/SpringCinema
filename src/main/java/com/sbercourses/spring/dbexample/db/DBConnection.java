package com.sbercourses.spring.dbexample.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static com.sbercourses.spring.dbexample.constants.DBConstants.*;

public enum DBConnection {

    INSTANCE;
    private Connection connection;
    public Connection newConnection() throws SQLException{
        if (connection ==null){
            connection= DriverManager.getConnection("jdbc:postgresql://"+ DB_HOST+":" + PORT +"/"+DB,USER,PASSWORD );
            return connection;
        }
        return connection;
    }
}
