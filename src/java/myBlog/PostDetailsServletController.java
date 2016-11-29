
package myBlog;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *A Servlet serving as controller for detailed post page.
 */

@WebServlet(name = "PostDetailsServletController", urlPatterns = {"/post"})
public class PostDetailsServletController extends HttpServlet {

        
        public PostDetailsServletController() {
            super();
        }
        
        
     public void doGet(HttpServletRequest request,  HttpServletResponse response)
            throws ServletException, IOException{
            
             /**
             * Attache to request object the specific Post object
             * 
             */

               int id = Integer.parseInt(request.getParameter("id"));
            PostBean pb = new PostBean();
            Post post = pb.getById(id);
            
            if(post != null){
                    request.setAttribute("idIsValid", true);
                    request.setAttribute("post", post);
                    String nextJSP = "/postDetails.jsp";
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
                    dispatcher.forward(request,response);
                }
                else{
                    request.setAttribute("idIsValid", false);
                    String nextJSP = "/postDetails.jsp";
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
                    dispatcher.forward(request,response);
                }
            
        }
        
        
}
