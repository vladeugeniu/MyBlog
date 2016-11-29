package myBlog;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;


/**
 * A Servlet serving as controller for home page
 * 
 */


@WebServlet("")
public class HomeServletController extends HttpServlet {
     
        public HomeServletController() {
            super();
        }
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
                throws ServletException, IOException {
		
                    PostBean post = new PostBean();
                    ArrayList<Post> posts = post.getAll();
 
                    request.setAttribute("posts", posts);
                    String nextJSP = "/index.jsp";
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
                    dispatcher.forward(request,response);
	}
}
