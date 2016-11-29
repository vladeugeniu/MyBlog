<%@ page language="java" contentType="text/html; charset=ISO-8859-1"

    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>My Blog</title>
        <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css" rel="stylesheet">
        <link rel="stylesheet" href="/MyBlog/index.css">
    </head>
    
    <body>
        
        <div class="page-header">
            <h1><a href="/MyBlog">My Blog</a></h1>
           
            <c:if test="${not sessionScope.userIsLogged}">
                <div class="singUp">
                    <a href="register"> Sign up <t/> </a>
                </div>
                <div class="singUp">
                    <a href="signIn"> Sign In </a>
                </div>
            </c:if>
                
            <c:if test="${sessionScope.userIsLogged}">
                <div class="singUp">
                    <a href="signOut"> Sign Out</a>
                </div>
                <div class="singUp">
                    <p> Hello, ${sessionScope.userName} ! </p>
                </div>
            </c:if>
        </div>
              
        <div class="content container">
          <c:if test="${not sessionScope.userIsLogged}"> 
            <p> ${error} </p>
            <br/>
            <form method="post" action="register">
                Name:<input type="text" name="name" /><br/> <br/>
                Password:<input type="password" name="pass" /><br/> <br/>
                <input type="submit" value="register" />
            </form>
            <div>
                <br/>
                The password policy is:
                <br/>
                <br/>
                <ul>
                    <li> At least 8 chars</li>
                    <li>Contains at least one digit</li>
                    <li>Contains at least one lower alpha char and one upper alpha char</li>
                    <li>Contains at least one char within a set of special chars (@#%$^ etc.)</li>
                    <li>Does not contain space, tab, etc.</li>
                </ul>
            </div>
          </c:if>
          <c:if test="${sessionScope.userIsLogged}">
              <h1> You are already logged in </h1>
          </c:if>
        </div>
       
    </body>
  
</html>
