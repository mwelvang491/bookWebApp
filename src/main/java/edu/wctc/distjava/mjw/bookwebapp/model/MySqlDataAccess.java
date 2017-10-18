package edu.wctc.distjava.mjw.bookwebapp.model;
/**
 *
 * @author mitchell
 */
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.Vector;

public class MySqlDataAccess implements DataAccess {
    private final boolean DEBUG = true;
    private Connection conn;
    private Statement stmt;
    private ResultSet rs; 
    private final int ALL_RECORDS = 0;
    private PreparedStatement pstmt;
    
    public void openConnection(String driverClass, String url, String userName, String password) 
            throws ClassNotFoundException, SQLException {
        
        Class.forName (driverClass);
        conn = DriverManager.getConnection(url, userName, password);
    }
    
    @Override
    public void closeConnection() throws SQLException {
        if(conn !=null) conn.close();
    }
 
    @Override
    public int createRecord(String tableName, List<String> colNames, List<Object> colValues)
            throws SQLException{
        
        String sql = "INSERT INTO " + tableName +  " "; 
        StringJoiner sj = new StringJoiner(", ", "(" , ")");
        
        for(String col: colNames){
            sj.add(col);
        }
        
        if(DEBUG) System.out.println(sj.toString()); //check that String joiner is doing what we want. 
        
        sql += sj.toString() + " VALUES ";
       
        
        sj = new StringJoiner(", ", "(" , ")");
        
        for(Object value: colValues){
            sj.add("?");
        }
        
        sql += sj.toString();
        if(DEBUG) System.out.println(sj.toString()); //check that String joiner is doing what we want. 
        pstmt = conn.prepareStatement(sql);
        
        for(int i=1; i<=colValues.size(); i++ ){
            pstmt.setObject(i, colValues.get(i-1));
        }
        
    return pstmt.executeUpdate();
    }
    
    @Override
    public int deleteRecordByPrimaryKey(String tableName, String primaryKeyName, Object primaryKeyValue) 
            throws ClassNotFoundException, SQLException{
        
        String sql = "delete from " + tableName
                + " where " + primaryKeyName + " = ?" ;

        pstmt = conn.prepareStatement(sql);
        pstmt.setObject(1, primaryKeyValue);
        int recsDeleted = pstmt.executeUpdate();
    
        return recsDeleted;
    }
    
    /**
     * Returns records from a table. Requires an Open connection. 
     * @param tableName
     * @param maxRecords
     * @return
     * @throws SQLException 
     */
    @Override
    public List<Map <String, Object >> getAllRecords(String tableName, int maxRecords) 
            throws SQLException, ClassNotFoundException{
        
        List<Map <String, Object >> rawData = new Vector<>();
        String sql = " ";
        
        if(maxRecords > ALL_RECORDS){
            sql = "select * "
            +     "from " + tableName + "limit " + maxRecords;
        }else {
            sql = "select * from " + tableName;
        }
       
        stmt = conn.createStatement();
        rs =  stmt.executeQuery(sql);
        
        ResultSetMetaData rsmd = rs.getMetaData();
        int colCount = rsmd.getColumnCount();
        Map<String,Object> record = null; //linked hash map maintains order. Hashmaps do not. 
        
        while(  rs.next()   ){
            //#### is one bsed.
            record = new LinkedHashMap<>();
            for(int colNum=1; colNum <= colCount; colNum++){
                record.put( rsmd.getColumnName(colNum) , rs.getObject(colNum)  );
            }
            rawData.add(record);
        }
            
        
        return rawData;
    }
    
    
//         db.updateRecord("author", "author_id", a , 
//                        Arrays.asList("author_name", "date_added"),
//                        Arrays.asList("Bobby Little", 10-10-2010 ));
//    

    @Override
        public int updateRecordByPrimaryKey(String tableName, String primaryKeyName, Object primaryKeyId, 
                            List<String> colNames, List<Object> colValues) throws SQLException{
        
//      Example Update Statement.         
//      UPDATE Customers.
//      SET ContactName = 'Alfred Schmidt', City= 'Frankfurt'
//      WHERE CustomerID = 1;
        String sql = " UPDATE " + tableName + " SET ";

        StringJoiner sj = new StringJoiner(" = ?, ", "" , " = ? ");
        //need an array to set values. 
        //Will Loop through those here. 
        //  " SET "    + colNames + " = " + colValues + 
        for(String colName : colNames) {
          sj.add(colName);
        }
        sql += sj.toString();
        if(DEBUG) System.out.println(sj.toString()); //check that String joiner is doing what we want. 
            
        sql += "WHERE " + primaryKeyName + " = ?";
        
        System.out.println("Sql statement says: "+ "\n"  + sql);
        
        pstmt = conn.prepareStatement(sql);
        
        for(int i =0; i< colNames.size(); i++){
            pstmt.setObject(i+1, colValues.get(i) );
        }
        
        pstmt.setObject(colNames.size()+1, primaryKeyId );
        
        if(DEBUG) System.out.println("final query: " + "\n" + pstmt.toString());
        
        pstmt.executeUpdate();
        
        
        return 0;
    }
    
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        
        DataAccess db = new MySqlDataAccess();

        db.openConnection("com.mysql.jdbc.Driver", //driver
                "jdbc:mysql://localhost:3306/book", //url
                "root", //username
                "admin");                           //password

//        int recsAdded = db.createRecord("author",
//                        Arrays.asList("author_name", "date_added"),
//                        Arrays.asList("Bob Jones", "2012-02-14"));

       int a = 8;
                      db.updateRecordByPrimaryKey("author", "author_id", a , 
                        Arrays.asList("author_name", "date_added"),
                        Arrays.asList("Stuart Little", "2010-05-18" ));



        db.closeConnection();
        
        
//       db.deleteRecordByPrimaryKey("book.author", "author_id", "1");
//       


//        List<Map<String,Object>> list = db.getAllRecords("author", 0);
//        
//        for(Map<String,Object>  rec : list     ){
//            System.out.println(rec);
//        }
            
    }
 
}
