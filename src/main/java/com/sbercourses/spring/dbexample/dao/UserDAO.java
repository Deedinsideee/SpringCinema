/*
package com.sbercourses.spring.dbexample.dao;

import com.sbercourses.spring.dbexample.model.Book;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDAO {
    private final Connection  conn;
    public final String BOOK_SELECT_BY_ID_Q="select * from customer where id=?";
    public final String BOOK_FIND_BY_PHONE="select book.id,title,author,date from book join book_list bl on book.id = bl.idknigi join customer c on c.id = bl.idpolzov\n" +
            "where c.phonenumber=?";
    public final String USER_INSERT_Q="insert into customer( name, dataofbirdth, phonenumber, email, idknigi) values (?,?,?,?,?)";
    public UserDAO(Connection conn) {
        this.conn = conn;
    }

    public User fingBoolById (Integer customerId) throws SQLException
    {
        PreparedStatement selectQ = conn.prepareStatement(BOOK_SELECT_BY_ID_Q);
        selectQ.setInt(1,customerId);
        ResultSet resultSet= selectQ.executeQuery();
        User user= new User();
        while (resultSet.next())
        {

            user.setId(resultSet.getInt("id"));
            user.setName(resultSet.getString("name"));
            user.setEmail(resultSet.getString("email"));
            user.setPhoneNumber(resultSet.getString("phonenumber"));
            user.setDateOfBirdth(resultSet.getString("DataofBirdth"));
            if(resultSet.getInt("IDKnigi")==0)
            {
                user.setIdknigi(0);
            }
            else {
                user.setIdknigi(resultSet.getInt("IDKnigi"));
            }
            System.out.println(user.toString());
        }
        return user;
    }


    public void AddUser(User user) throws SQLException {

        PreparedStatement selectQ = conn.prepareStatement(USER_INSERT_Q);
        selectQ.setString(1,user.getName());
        selectQ.setString(2,user.getDateOfBirdth());
        selectQ.setString(3,user.getPhoneNumber());
        selectQ.setString(4, user.getEmail());
        selectQ.setInt(5,user.getIdknigi());
        selectQ.execute();
    }

    public void FindByPhone_Number(String phone) throws SQLException
    {
        PreparedStatement findQ = conn.prepareStatement(BOOK_FIND_BY_PHONE);
        findQ.setString(1,"+7904985445");
        ResultSet resultSet = findQ.executeQuery();

        List<Book> books = new ArrayList<>();
        while (resultSet.next())
        {
            Book a = new Book();
            a.setId(resultSet.getInt("id"));
            a.setTitle(resultSet.getString("title"));
            a.setAuthor(resultSet.getString("author"));
            a.setDateAdded(resultSet.getDate("date"));
            books.add(a);
        }
        books.forEach(r -> System.out.println(r.toString()));

    }
}
*/
