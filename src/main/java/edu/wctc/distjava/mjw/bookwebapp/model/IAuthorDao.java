/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.distjava.mjw.bookwebapp.model;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author mitchell
 */
public interface IAuthorDao {

    //convoulted difficult way. But Faster then easier one line ways.
    List<Author> getListOfAuthors() throws SQLException, ClassNotFoundException;
    
    int removeAuthorById(Integer id) throws ClassNotFoundException, SQLException;
    
    public void updateRecordByPrimaryKey(Object primaryKeyValue, List<String> colNames, List<Object> colValues) 
            throws ClassNotFoundException, SQLException;
    
    
}
