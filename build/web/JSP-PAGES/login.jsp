<%-- 
    Document   : login
    Created on : 12.12.2012, 14:37:16
    Author     : Vinokurov


<%@page errorPage="/Testing/JSP-PAGES/error.jsp" %>
--%>

<%@page errorPage="/Testing/JSP-PAGES/error.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <h1>Login page</h1>
        <form name="login" action="/Testing/Servlet" method="POST">
            <input type=hidden name="command" value="login">
            Login:<input type=text name="login" value="root"></br>
            Password:<input type=password name="password" value="root"></br>
            <input type=submit value="Submit">            
        </form>
        <form action="index.jsp">
            <input type=submit value="Main page">
        </form>
    </body>
</html>
