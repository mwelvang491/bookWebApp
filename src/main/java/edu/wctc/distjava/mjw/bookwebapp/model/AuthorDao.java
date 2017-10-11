/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.distjava.mjw.bookwebapp.model;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

/**
 *
 * @author mitchell
 */
public class AuthorDao implements IAuthorDao {
    private String driverClass; 
    private String url; 
    private String userName;
    private String password;
    private DataAccess db;
    private final String AUTHOR_TBL = "author";
    private final String AUTHOR_PK  = "author_id";
    
    public AuthorDao(String driverClass, String url, String userName, String password, DataAccess db){
        setDriverClass(driverClass);
        setUrl(url);
        setUserName(userName);
        setPassword(password);
        setDb(db);
        
    };
    
    public int removeAuthorById(Integer id) throws ClassNotFoundException, SQLException{
     if(id == null || id < 1 ){
         throw new IllegalArgumentException("Id must be a Integer greater than 0");
     }
     
     db.openConnection(driverClass, url, userName, password);
     
     int recsDeleted = db.deleteRecordByPrimaryKey(AUTHOR_TBL, AUTHOR_PK, id );
     
    return  recsDeleted;
    }
    
    //convoulted difficult way. But Faster then easier one line ways. 
    @Override
    public List<Author> getListOfAuthors() 
            throws SQLException, ClassNotFoundException{
        
        db.openConnection(driverClass, url, userName, password);
        
        List<Author> list = new Vector<>(); //data is stored here. 
        List<Map<String,Object>> rawData = db.getAllRecords(AUTHOR_TBL, 0); //map is used to get data and then storee that data in the vector list. 
        
        Author author = null;
        
        for(Map<String,Object> rec : rawData){
            author = new Author();
            //get the authorId 
            Object objRecId = rec.get(AUTHOR_PK);
            Integer recId = objRecId == null ? 0 : Integer.parseInt(objRecId.toString());
            author.setAuthorId(recId);
            
            Object objName = rec.get("author_name");
            String authorName = objName == null ? "" : objName.toString();
            author.setAuthorName(authorName);
            
            Object objRecAdded = rec.get("date_added");
            Date recAdded = objRecAdded == null ? null : (Date) objRecAdded;
            author.setDateAdded(recAdded);
            
            list.add(author);
        }
        
        db.closeConnection();
        
    return list;
    }
    
    public DataAccess getDb() {
        return db;
    }

    public void setDb(DataAccess db) {
        this.db = db;
    }

    public String getDriverClass() {
        return driverClass;
    }

    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        AuthorDao dao = new AuthorDao(
                "com.mysql.jdbc.Driver",            //driver
                "jdbc:mysql://localhost:3306/book", //url
                "root",                             //username
                "admin",                             //password
                new MySqlDataAccess()
        );       
        
        List<Author> list = dao.getListOfAuthors();

        for (Author a : list) {
            System.out.println(a.getAuthorId() + ", "
                    + a.getAuthorName() + ", " + a.getDateAdded() + "\n");
        }
    }
       
}
 
