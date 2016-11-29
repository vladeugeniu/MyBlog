/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myBlog;


import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * A servlet serving as controller for sign in system
 */
@WebServlet(name = "SignInServletController", urlPatterns = {"/signIn"})
public class SignInServletController extends HttpServlet {
   
        
        
        public SignInServletController() {
            super();
        }
        
         /**
          * Forward to sign in form 
          */
         public void doGet(HttpServletRequest request,  HttpServletResponse response)
            throws ServletException, IOException{
            
            String nextJSP = "/signIn.jsp";
           
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
            dispatcher.forward(request,response);
                        
         }
         
         /**
          * Check the form and if it matches to a clinet from databse
          * set a true userIsLogged attribute on http session
          */
         protected void doPost(HttpServletRequest request, HttpServletResponse response) 
                 throws ServletException, IOException {

     
             try{

                String name = request.getParameter("name");
                String pass = request.getParameter("pass");
                
                Client client = new Client();
                client.setName(name);
                client.setPassword(pass);
                

                ClientBean cb = new ClientBean();
                
                
                if(cb.getByName(name) != null)
                    
                    try{
                    
                      if(cb.isValid(client)){
                           HttpSession session = request.getSession();
                           session.setAttribute("userIsLogged",  true);
                           session.setAttribute("userName", name);
                           response.sendRedirect("/MyBlog"); 
                           
                      }
                      else{
                        
                        String nextJSP = "/signIn.jsp";
                        String error = "Invalid password";
                        request.setAttribute("error",error);
                        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
                        dispatcher.forward(request,response);  
                      } 
                    }
                catch(Exception e){
                    e.printStackTrace();
                }

                else{

                    String nextJSP = "/signIn.jsp";
                    String error = "Invalid username";
                    request.setAttribute("error",error);
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
                    dispatcher.forward(request,response);       
                }
             }
            catch(Exception se){
                se.printStackTrace();
            }
	
      }
             
}
