<%-- 
    Document   : roothome
    Created on : 14.12.2012, 20:08:44
    Author     : Vinokurov
--%>

<%@page errorPage="/Testing/JSP-PAGES/error.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin home</title>
    </head>
    <body>
        <h1>Hello Admin! </h1>
        <form action="/Testing/Servlet" method="post">
            <input type="hidden" name="command" value="show">
            <input type="hidden" name="beanType" value="tutor">
            <input type="number" name="showTo" value=20>
            <input type="submit" value="Show tutors">
        </form>
        <form action="/Testing/Servlet" method="post">
            <input type="hidden" name="command" value="show">
            <input type="hidden" name="beanType" value="student">
            <input type="number" name="showTo" value="20">
            <input type="submit" value="Show students">
        </form>
        <form action="/Testing/Servlet" method="post">
            <input type="hidden" name="command" value="show">
            <input type="hidden" name="beanType" value="subject">
            <input type="number" name="showTo" value="20">
            <input type="submit" value="Show subjects">
        </form>        
        <form action="/Testing/JSP-PAGES/ADD/addtutor.jsp">
            <input type="submit" value="Add tutor">
        </form>
        <form action="/Testing/JSP-PAGES/ADD/addstudent.jsp">
            <input type="submit" value="Add student">
        </form>
        <form action="/Testing/JSP-PAGES/ADD/addsubject.jsp">
            <input type="submit" value="Add subject">
        </form>
        
        
        <tag:tutor_tag>${tutorList}</tag:tutor_tag>
        <tag:student_tag>${studentList}</tag:student_tag>
        <tag:subject_tag>${subjectList}</tag:subject_tag>
        
        <form action="/Testing/JSP-PAGES/login.jsp">
            <input type="submit" value="Log out">
        </form>
    </body>
</html>
