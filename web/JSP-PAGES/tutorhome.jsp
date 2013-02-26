<%-- 
    Document   : tutorhome
    Created on : 14.12.2012, 19:48:58
    Author     : Vinokurov
--%>

<%@page errorPage="/Testing/JSP-PAGES/error.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tutor home</title>
    </head>
    <body>
        <h1>Hello Tutor ${sessionScope.userID}!</h1>
        <form action="/Testing/Servlet" method="post">
            <input type="hidden" name="command" value="show">
            <input type="hidden" name="beanType" value="student">
            <input type="number" name="showTo" value="20">
            <input type="submit" value="Show students">
        </form>
        <form action="/Testing/Servlet" method="post">
            <input type="hidden" name="command" value="show">
            <input type="hidden" name="beanType" value="test">
            <input type="number" name="showTo" value="20">
            <input type="submit" value="Show tests">            
        </form>
        <form action="/Testing/Servlet" method="post">
            <input type="hidden" name="command" value="show">
            <input type="hidden" name="beanType" value="subject">
            <input type="number" name="showTo" value="20">
            <input type="submit" value="Show subjects">
        </form>
        
        
        <tag:student_tag>${studentList}</tag:student_tag>
        <tag:test_tag>${testList}</tag:test_tag>
        <tag:subject_tag>${subjectList}</tag:subject_tag>
        <form action="/Testing/JSP-PAGES/login.jsp">
            <input type="submit" value="Log out">
        </form>
    </body>
</html>
