package com.sbercourses.spring.dbexample.dao;

import com.sbercourses.spring.dbexample.model.Book;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


@Component
public class BookDaoBean {
    private final Connection connection;
    public final String BOOK_SELECT_BY_ID_Q="select * from book where id=?";
    public BookDaoBean(Connection connection)
    {
        this.connection = connection;


    }
    public Book fingBoolById (Integer bookId) throws SQLException
    {
        PreparedStatement selectQ = connection.prepareStatement(BOOK_SELECT_BY_ID_Q);
        selectQ.setInt(1,bookId);
        ResultSet resultSet= selectQ.executeQuery();
        Book book= new Book();
        while (resultSet.next())
        {

            book.setId(resultSet.getInt("id"));
            book.setAuthor(resultSet.getString("author"));
            book.setTitle(resultSet.getString("title"));
            book.setDateAdded(resultSet.getDate("date"));
            System.out.println(book.toString());
        }
        return book;
    }
}
