<%-- 
    Document   : addanswer
    Created on : 18.12.2012, 22:49:57
    Author     : Vinokurov
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add answer</title>
    </head>
    <body>
        <h1>Add answer to the question #${param.idquestion}</h1>
        <form action="/Testing/Servlet">
            <input type="hidden" name="command" value="add">
            <input type="hidden" name="beanType" value="answer">
            <input type="hidden" name="idquestion" value="${param.idquestion}">
            <input type="text" name="text"><br>
            Is correct: <input type="checkbox" name="iscorrect"><br>
            <input type="submit" value="Add">
        </form>
        <form action="/Testing/Servlet">
            <input type="hidden" name="command" value="show">
            <input type="hidden" name="beanType" value="answer">
            <input type="hidden" name="idquestion" value=${param.idquestion}>
            <input type="submit" value="Cancel">
        </form>    
    </body>
</html>
