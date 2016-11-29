<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@page import="myBlog.Post" %>
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
                <h1><a href="">My Blog</a></h1>
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
            <c:if test="${sessionScope.userIsLogged}">
            <a href="new" class="myBtn"><span class="glyphicon glyphicon-plus"></span></a>
            </c:if>
            <div class="row">
                <div class="col-md-8">

                    <c:forEach items="${posts}" var="post">
                        <div class="post">
                            <div class="date">
                                 <p>published: ${post.date} </p>
                            </div>
                            <h1><a href="post?id=${post.id}"> ${post.title} </a></h1>
                            <p> ${post.text} </p>
                        </div>		
                    </c:forEach>
                    
                    

                 </div>
            </div>
        </div>
    </body>
</html>