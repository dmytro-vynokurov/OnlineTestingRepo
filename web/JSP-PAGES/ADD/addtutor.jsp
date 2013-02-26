<%-- 
    Document   : addtutor
    Created on : 18.12.2012, 11:50:24
    Author     : Vinokurov
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add tutor</title>
    </head>
    <body>
        <h1>Add tutor</h1>
        <form action="/Testing/Servlet">
            <input type="hidden" name="command" value="add">
            <input type="hidden" name="beanType" value="tutor">
            First name: <input type="text" name="firstName"><br>
            Second name: <input type="text" name="secondName"><br>
            Middle name: <input type="text" name="middleName"><br> 
            Login: <input type="text" name="login"><br>
            Password: <input type="text" name="password"><br> 
            <input type="submit" value="Submit">   
        </form>
        <form action="/Testing/JSP-PAGES/roothome.jsp">
            <input type="submit" value="Back to home page">
        </form>        
    </body>
</html>
