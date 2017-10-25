/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.distjava.mjw.bookwebapp.model;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

/**
 *
 * @author mitchell
 */
public class MockAuthorDao implements IAuthorDao {

    public MockAuthorDao(){

        
    };
    
    //convoulted difficult way. But Faster then easier one line ways. 
    @Override
    public List<Author> getListOfAuthors() 
            throws SQLException, ClassNotFoundException{

            List<Author> list = Arrays.asList(
              new Author(1,"John Doe", new Date() ),
              new Author(2,"Bob Smith", new Date() )
            );
                    
    
        
        
    return list;
    }
    
  
    
//    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//        MockAuthorDao dao = new MockAuthorDao(
//                "com.mysql.jdbc.Driver",            //driver
//                "jdbc:mysql://localhost:3306/book", //url
//                "root",                             //username
//                "admin",                             //password
//                new MySqlDataAccess(                
//                "com.mysql.jdbc.Driver",            //driver
//                "jdbc:mysql://localhost:3306/book", //url
//                "root",                             //username
//                "admin"                              //password
//                 ));       
//        
//        List<Author> list = dao.getListOfAuthors();
//
//        for (Author a : list) {
//            System.out.println(a.getAuthorId() + ", "
//                    + a.getAuthorName() + ", " + a.getDateAdded() + "\n");
//        }
//    }

    @Override
    public int removeAuthorById(Integer id) throws ClassNotFoundException, SQLException {
      return 1;
    }

    @Override
    public void updateRecordByPrimaryKey(Object primaryKeyValue, List<String> colNames, List<Object> colValues) throws ClassNotFoundException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void createAuthor(List<String> colNames, List<Object> colValues) throws ClassNotFoundException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Author> getAuthorByPrimaryKey(Object primaryKeyValue) throws ClassNotFoundException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
       
}
 
