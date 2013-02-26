<%-- 
    Document   : addquestion
    Created on : 18.12.2012, 17:31:57
    Author     : Vinokurov
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add question</title>
    </head>
    <body>
        <h1>Add question to test #${param.idtest}</h1>
        <form action="/Testing/Servlet">
            <input type="hidden" name="command" value="add">
            <input type="hidden" name="beanType" value="question">
            <input type="hidden" name="idtest" value="${param.idtest}">
            Text: <input type="text" name="text"><br>
            Has one answer: <input type="checkbox" name="oneanswer"><br>
            <input type="submit" value="Add">
        </form>
        <form action="/Testing/Servlet">
            <input type="hidden" name="command" value="show">
            <input type="hidden" name="beanType" value="question">            
            <input type="hidden" name="idtest" value=${param.idtest}>
            <input type="submit" value="Cancel">
        </form>
    </body>
</html>
