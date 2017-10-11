//package edu.wctc.distjava.mjw.bookwebapp.model;
///**
// *
// * @author mitchell
// */
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.ResultSetMetaData;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.LinkedHashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Vector;
//
//public class Ms_SqlServer_DataAccess implements DataAccess {
//    private Connection conn;
//    private Statement stmt;
//    private ResultSet rs; 
//    private final int ALL_RECORDS = 0;
//    private String driverClass; 
//    private String url; 
//    private String userName;
//    private String password;
//    
//    public Ms_SqlServer_DataAccess(String driverClass, String url, String userName, String password){
//        
//        setDriverClass(driverClass);
//        setUrl(url);
//        setUserName(userName);
//        setPassword(password);
//    }
//
//    @Override
//    public void openConnection() throws ClassNotFoundException, SQLException {
//        
//        Class.forName (driverClass);
//        conn = DriverManager.getConnection(url, userName, password);
//    }
//    
//    @Override
//    public void closeConnection() throws SQLException {
//        if(conn !=null) conn.close();
//    }
//    
//    /**
//     * Returns records from a table. Requires an Open connection. 
//     * @param tableName
//     * @param maxRecords
//     * @return
//     * @throws SQLException 
//     */
//    @Override
//    public List<Map <String, Object >> getAllRecords(String tableName, int maxRecords) 
//            throws SQLException, ClassNotFoundException{
//        
//        List<Map <String, Object >> rawData = new Vector<>();
//        String sql = " ";
//        
//        if(maxRecords > ALL_RECORDS){
//            sql = "select Top " + maxRecords 
//            +     " * from " + tableName;
//        }else {
//            sql = "select * from " + tableName;
//        }
//        openConnection();
//        stmt = conn.createStatement();
//        rs =  stmt.executeQuery(sql);
//        
//        ResultSetMetaData rsmd = rs.getMetaData();
//        int colCount = rsmd.getColumnCount();
//        Map<String,Object> record = null; //linked hash map maintains order. Hashmaps do not. 
//        
//        while(  rs.next()   ){
//            //#### is one bsed.
//            record = new LinkedHashMap<>();
//            for(int colNum=1; colNum <= colCount; colNum++){
//                record.put( rsmd.getColumnName(colNum) , rs.getObject(colNum)  );
//            }
//            rawData.add(record);
//        }
//            closeConnection();
//        
//        return rawData;
//    }
//
//    
//     
//    @Override
//    public String getDriverClass() {
//        return driverClass;
//    }
//
//    @Override
//    public void setDriverClass(String driverClass) {
//        this.driverClass = driverClass;
//    }
//
//    @Override
//    public String getUrl() {
//        return url;
//    }
//
//    @Override
//    public void setUrl(String url) {
//        this.url = url;
//    }
//
//    @Override
//    public String getUserName() {
//        return userName;
//    }
//
//    @Override
//    public void setUserName(String userName) {
//        this.userName = userName;
//    }
//
//    @Override
//    public String getPassword() {
//        return password;
//    }
//
//    @Override
//    public void setPassword(String password) {
//        this.password = password;
//    }
//    
//    
//    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//        
//
//        Ms_SqlServer_DataAccess db = new Ms_SqlServer_DataAccess(
//                "org.apache.derby.jdbc.ClientDriver", //driver
//                "jdbc:derby://localhost:1527/sample", //url
//                "app", //username
//                "app"); //password
//        
//        List<Map<String,Object>> list = db.getAllRecords("CUSTOMER", 0);
//        
//        for(Map<String,Object>  rec : list     ){
//            System.out.println(rec);
//        }
//            
//    }
//
//    @Override
//    public int deleteRecordByPrimaryKey(String tableName, String primaryKeyName, Object primaryKeyValue) throws ClassNotFoundException, SQLException {
//        //To change body of generated methods, choose Tools | Templates.
//    
//    
//    
//    return 1;
//            }
//    
//    
//}
