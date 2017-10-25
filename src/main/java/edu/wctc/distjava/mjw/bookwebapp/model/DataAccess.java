/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.distjava.mjw.bookwebapp.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author mitchell
 */
public interface DataAccess {
    /**
     * Returns records from a table. Requires an Open connection.
     * @param tableName
     * @param maxRecords
     * @return
     * @throws SQLException
     */
    public abstract int updateRecordByPrimaryKey(String tableName, String primaryKeyName, Object primaryKeyId, 
                            List<String> colNames, List<Object> colValues) throws SQLException;
    
    List<Map<String, Object>> getAllRecords(String tableName, int maxRecords) 
            throws SQLException, ClassNotFoundException;
    
    int deleteRecordByPrimaryKey(String tableName, String primaryKeyName, Object primaryKeyValue)  
            throws ClassNotFoundException, SQLException; 
    
    public abstract void openConnection(String driverClass, String url, String userName, String password) 
            throws ClassNotFoundException, SQLException;

    void closeConnection() throws SQLException;
    
    public int createRecord(String tableName, List<String> colNames, List<Object> colValues) 
            throws SQLException;

    public Object getRecordByPrimaryKey(String tableName, String primaryKeyName, Object primaryKey) 
            throws SQLException;
    
}
