/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myBlog;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * A Servlet serving as controller for creating a new post
 */
@WebServlet(name = "PostNewServletController", urlPatterns = {"/new"})
public class PostNewServletController extends HttpServlet {
    
    
    /**
     * Forward to the submit form
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
            Post post = new Post();
            request.setAttribute("edit", false);
            request.setAttribute("invalid", false);
            String nextJSP = "/newPost.jsp";
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
            dispatcher.forward(request,response);   
    }
    
    
    /**
     * Check the form fileds and if they are valid save the new post in database
     * Redirect to the new post detailed page
     */
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
                String title = (String)request.getParameter("title");
                String text = (String)request.getParameter("text"); 
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
                Post toAdd = new Post(title,text);
                PostBean pb = new PostBean();
                int id;
                id = pb.Insert(toAdd);
                response.sendRedirect("/MyBlog/post?id=" + id); 
                }
	}
    
}
