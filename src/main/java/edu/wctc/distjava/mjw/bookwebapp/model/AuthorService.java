/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.distjava.mjw.bookwebapp.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 *
 * @author mitchell
 */
public class AuthorService {

    private IAuthorDao authorDao;
    private final String AUTHOR_TBL = "author";
    private final String AUTHOR_PK  = "author_id";
    
    public AuthorService(IAuthorDao authorDao) {
        setAuthorDao(authorDao);
    }

    public int removeAuthor(String id) throws ClassNotFoundException, SQLException, NumberFormatException {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("Id must be a Integer greater than 0");
        }
        Integer value = Integer.parseInt(id);
        return authorDao.removeAuthorById(value);
    }
    
    public List<Author> getAuthorList()
            throws SQLException, ClassNotFoundException {

        return authorDao.getListOfAuthors();
    }

    public IAuthorDao getAuthorDao() {
        return authorDao;
    }

    public void setAuthorDao(IAuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        IAuthorDao dao = new AuthorDao(
                "com.mysql.jdbc.Driver", //driver
                "jdbc:mysql://localhost:3306/book", //url
                "root", //username
                "admin", //password
                new MySqlDataAccess( )
        );

        AuthorService authorService = new AuthorService(dao);

        List<Author> list = authorService.getAuthorList();

        for (Author a : list) {
            System.out.println(a.getAuthorId() + ", "
                    + a.getAuthorName() + ", " + a.getDateAdded() + "\n");

        }

    }

}

