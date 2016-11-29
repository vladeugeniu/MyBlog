
package myBlog;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
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
 * A Servlet serving as controller for registration
 */
@WebServlet(name = "RegisterServletController", urlPatterns = {"/register"})
public class RegisterServletController extends HttpServlet {

        public RegisterServletController() {
            super();
        }
            
        /**
         * Forward to registration form
         */
        
         public void doGet(HttpServletRequest request,  HttpServletResponse response)
            throws ServletException, IOException{
            
            String nextJSP = "/register.jsp";
           
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
            dispatcher.forward(request,response);
                        
         }
         
         
         /**
         * Check the form fields and if they are valid create a new client and save
         * it in data base
         */
         protected void doPost(HttpServletRequest request, HttpServletResponse response) 
                 throws ServletException, IOException {

             try{

                String name = request.getParameter("name");
                String pass = request.getParameter("pass");
                Validator validator = new Validator();
                if(validator.validateUsername(name) && validator.valiidatePassword(pass)){
                    
                    Client client = new Client();
                    client.setName(name);
                    client.setPassword(pass);
                    
                    ClientBean cb = new ClientBean();
                    
                    if(cb.getByName(name) != null){
                     //return error, user already in use; 
                      String nextJSP = "/register.jsp";
                      String error = "Username already taken";
                      request.setAttribute("error",error);
                      RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
                      dispatcher.forward(request,response);
                    }

                    else{

                      int id = cb.insert(client);
                      if(id>0){
                           HttpSession session = request.getSession();
                           session.setAttribute("userIsLogged",  true);
                           session.setAttribute("userName", name);
                           response.sendRedirect("/MyBlog"); 
                      }
                      else {
                           String nextJSP = "/register.jsp";
                           String error = "An error occurred while processing your request. "
                                            + "please try again later";
                           request.setAttribute("error",error);
                           RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
                           dispatcher.forward(request,response);
                      }
                     }
                }
                else {
                    String nextJSP = "/register.jsp";
                    String error = "Invalid username or password format";
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
		

