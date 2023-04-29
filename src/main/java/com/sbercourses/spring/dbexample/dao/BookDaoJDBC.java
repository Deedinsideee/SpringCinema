package com.sbercourses.spring.dbexample.dao;

import com.sbercourses.spring.dbexample.db.DBConnection;
import com.sbercourses.spring.dbexample.constants.DBConstants;
import com.sbercourses.spring.dbexample.model.Book;

import java.sql.*;

public class BookDaoJDBC implements DBConstants {
    public Book findBoolByID(Integer bookId)
    {
        try(Connection connection = DBConnection.INSTANCE.newConnection())
        {
                if(connection!= null)
                {
                    System.out.println("Povezlo!!");
                }
                else {
                    System.out.println("(((((((((((((((((");
                }
                String select = "select * from book where id=?";
            PreparedStatement selectQ = connection.prepareStatement(select);
            selectQ.setInt(1,bookId);
            ResultSet resultSet = selectQ.executeQuery();
            while (resultSet.next())
            {
                Book book= new Book();
                book.setId(resultSet.getInt("id"));
                book.setAuthor(resultSet.getString("author"));
                book.setTitle(resultSet.getString("title"));
                book.setDateAdded(resultSet.getDate("date"));
                System.out.println(book.toString());
                return book;
            }
        }
        catch (SQLException ex)
        {
            System.out.println("oy oy "+ ex.getMessage());
        }
        return null;
    }
}
