/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myBlog;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * A Servlet serving as controller for deleting a post
 */



@WebServlet(name = "PostDeleteServletController", urlPatterns = {"/post/delete"})
public class PostDeleteServletController extends HttpServlet {

       /**
        * Delete the post by its id from database and
        * redirect to home page
        */
       protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        
            int id = Integer.parseInt(request.getParameter("id"));
            PostBean pb = new PostBean();
            Post post = pb.getById(id);
            pb.delete(post);
            response.sendRedirect("/MyBlog");       
    }

   
}
