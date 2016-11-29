
package myBlog;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.RequestDispatcher;

/**
 * This is a manager for Post objects stored in database.
 * It generates the queries and returns objects based on their result.
 *
 */


public class PostBean {
        
        
        private Connection connection;
        
        /**
         * Get a database connection
         */
        PostBean(){
            connection = DbConnection.getInstance().getConnection();       
        }
        
        /**
         * @return an ArrayList of Post with every post from database
         */
        public ArrayList<Post> getAll(){
            ResultSet result = null;
            ArrayList<Post> posts = new ArrayList<Post>();
                try{
                    Statement statement = connection.createStatement();
                    String sql = "SELECT * FROM post"; 
                    result = statement.executeQuery(sql);
                    while(result.next()){
                        Post post = new Post();
                        post.setId(result.getInt("id"));
                        post.setTitle(result.getString("title"));
                        post.setText(result.getString("text"));
                        java.util.Date date = result.getTimestamp("date");
                        post.setDate(date);            
                        posts.add(post);
                    } 
                }
                catch(Exception e){
                    e.printStackTrace();
                }
                return posts;
                
        }
        
        /**
         * @param id the id of post
         * @return the Post object with the specified id or
         * null if it doesn't exist
         */
        public Post getById(int id){
            
            String sqlGetPost = "SELECT * FROM post WHERE ID=" + id;
            ResultSet result = null;
            try {
                Statement statement = connection.createStatement();
                result = statement.executeQuery(sqlGetPost);
                if(result != null && result.next()){
                    Post post = new Post();
                    post.setId(result.getInt("id"));
                    post.setTitle(result.getString("title"));
                    post.setText(result.getString("text"));
                    java.util.Date date = result.getTimestamp("date");
                    post.setDate(date);  
                    return post;
                    
                }
                else
                    throw new SQLException("Element not found (by ID)");
            }
            catch(Exception e){
                e.printStackTrace();
            }
             return null;
        }
        
        /**
         * 
         * @param post the Post object to add in database
         * @return the id of new row or -1 if it fails
         * 
         */
        public int Insert(Post post){
            
             try {
                String sql = "INSERT into post (date, title, text) VALUES(?,?,?)";
                java.util.Date date = new java.util.Date();
                Timestamp timestamp = new Timestamp(date.getTime());
            
             
		PreparedStatement ps = connection.prepareStatement(sql, 
                                        Statement.RETURN_GENERATED_KEYS);
              
                ps.setTimestamp(1, timestamp);
                ps.setString(2,post.getTitle());
                ps.setString(3,post.getText());
                ps.executeUpdate();
                
                ResultSet rs = ps.getGeneratedKeys();
                int id;
                
                if (rs != null && rs.next()) {
                    id = rs.getInt(1);
                    return id;
                }
                else 
                   throw new SQLException("Creating Post failed, no ID obtained.");
                
                }
                catch(Exception e){
                    e.printStackTrace();
                }
                return -1;
        }
        
        /**
         * Update a row in database by id and fields from post parameter
         * @param post the post to be updated
         */
        public void Update(Post post){
            
            String sqlUpdate = "UPDATE POST SET title=?, text=?,date=? WHERE id=" + post.getId();
            try{
                PreparedStatement ps = connection.prepareStatement(sqlUpdate, 
                                        Statement.RETURN_GENERATED_KEYS);
                
                ps.setString(1, post.getTitle());
                ps.setString(2, post.getText());
                
                java.util.Date date = new java.util.Date();
                Timestamp timestamp = new Timestamp(date.getTime());
                
                ps.setTimestamp(3,timestamp);
                ps.executeUpdate();
                
            }catch(Exception e){
                e.printStackTrace();
            }
            
        }
        
    /**
     * Delete a row from database corresponding to given post
     * @param post the post to be deleted
     */
    public void delete(Post post){
    
        String sql = "DELETE FROM Post WHERE id=?";
        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, post.getId());
            ps.executeUpdate();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
