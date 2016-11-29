/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myBlog;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * A Servlet serving as controller for sign out function
 */
@WebServlet(name = "SignOutServletController", urlPatterns = {"/signOut"})
public class SignOutServletController extends HttpServlet {

    
    public  SignOutServletController(){
        super();
    }
    

    /**
     * Set False the userIsLogged attribute on http session;
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
             HttpSession session = request.getSession();
             session.setAttribute("userIsLogged",  false);
             response.sendRedirect("/MyBlog"); 
        
    }

 
}
