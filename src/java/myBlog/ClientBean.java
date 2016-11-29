package myBlog;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.RequestDispatcher;


/**
 * This is a manager for Client objects stored in database.
 * It generates the queries and returns objects based on their result.
 *
 */


public class ClientBean {
        
        private Connection connection;
        
        /**
         * Get a database connection
         */
        public ClientBean(){
            connection = DbConnection.getInstance().getConnection();       
        }
        
        
        /**
         * @param name the name of client
         * @return the client object with the specified name
         * or null if it doesn't exist
        */
        public Client getByName(String name){
         
            String sqlGetClient = "SELECT * FROM client WHERE name='" + name + "'";
            ResultSet result = null;
            try {
                Statement statement = connection.createStatement();
                result = statement.executeQuery(sqlGetClient);
                if(result != null && result.next()){
                    Client client = new Client();
                    client.setId(result.getInt("ID"));
                    client.setName(result.getString("name"));
                    client.setPassword(result.getString("password"));
                    return client;
                    
                }
                else
                    throw new SQLException("Element not found (by name)");
            }
            catch(Exception e){
                e.printStackTrace();
            }
             return null;
        }
        
        /**
         * 
         * @param client the Client object to add in database
         * @return the id of new row or -1 if it fails
         * 
         */
        public int insert(Client client){
            
             try {
                String sql = "INSERT into client (name, password) VALUES(?,?)";
             
		PreparedStatement ps = connection.prepareStatement(sql, 
                                        Statement.RETURN_GENERATED_KEYS);
              
                ps.setString(1, client.getName());
                ps.setString(2, client.getPassword());
                ps.executeUpdate();
                
                ResultSet rs = ps.getGeneratedKeys();
                int id;
                if (rs != null && rs.next()) {
                    id = rs.getInt(1);
                    return id;
                }
                else 
                   throw new SQLException("Creating Client failed, no ID obtained.");
                
                }
                catch(Exception e){
                    e.printStackTrace();
                }
                return -1;
        }
        
        /**
         * @param toCheck Client object to be checked
         * @return true if there is a correspondent in database
         */
        public boolean isValid(Client toCheck){
            
            Client client = getByName(toCheck.getName());
            
            if(client == null)
                return false;
            
            return client.getPassword().equals(toCheck.getPassword());
            
        }
        
}