package myBlog;

import java.sql.DriverManager;
import java.sql.*;
/**
 * a singleton designed to generate a database connection
 */
 public final class DbConnection {
    
    private static DbConnection INSTANCE = null; 
    private DbConnection() {
    }
    
    public Connection getConnection(){
            
            Connection connection = null;
            try{
              Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
              connection = DriverManager.getConnection("jdbc:derby://localhost:1527/mydb","mydb","mydb");
            }
            catch(Exception e){
              e.printStackTrace();
            }
            return connection;
    }

    public static DbConnection getInstance() {
        if(INSTANCE ==null ) {
            INSTANCE = new DbConnection(); 
        }
        return INSTANCE;
    }
}
    
    

