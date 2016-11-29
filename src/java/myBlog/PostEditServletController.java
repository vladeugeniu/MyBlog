
package myBlog;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



   /**
     * A Servlet serving as controller for post editing.     *
     */

@WebServlet(name = "PostEditServletController", urlPatterns = {"/post/edit"})
public class PostEditServletController extends HttpServlet {
  
    /**
     * Attache to request object the post needed to edit
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
            int id = Integer.parseInt(request.getParameter("id"));
            PostBean pb = new PostBean();
            Post post = pb.getById(id);
            request.setAttribute("post", post);
            request.setAttribute("edit", true);
            String nextJSP = "/newPost.jsp";
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
            dispatcher.forward(request,response);
        
        
    }
    
    /**
     * Check the form fields and if they are valid update the object in database.
     * Redirect to detailed post url
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        String text = request.getParameter("text");

        title = title.trim();
        text = text.trim();
        if(text.length() < 2 || title.length() < 2){
            Post post = new Post();
            request.setAttribute("edit", false);
            request.setAttribute("invalid",true);
            String nextJSP = "/newPost.jsp";
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
            dispatcher.forward(request,response);
        }
        else{
        PostBean pb = new PostBean();
        Post post = pb.getById(id);
        post.setTitle(title);
        post.setText(text);
        pb.Update(post);
        response.sendRedirect("/MyBlog/post?id=" + id);        
        }
       
    }


}
