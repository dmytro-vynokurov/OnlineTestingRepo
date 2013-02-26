<%-- 
    Document   : addsubject
    Created on : 18.12.2012, 11:57:43
    Author     : Vinokurov
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add subject</title>
    </head>
    <body>
        <h1>Add subject</h1>
        <form action="/Testing/Servlet">
            <input type="hidden" name="command" value="add">
            <input type="hidden" name="beanType" value="subject">
            Subject name: <input type="text" name="subjectName"><br>
            <input type="submit" value="Add subject">    
        </form>
        <form action="/Testing/JSP-PAGES/roothome.jsp">
            <input type="submit" value="Back to home page"
        </form>
    </body>
</html>
