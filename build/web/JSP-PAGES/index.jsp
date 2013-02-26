<%-- 
    Document   : index
    Created on : 06.12.2012, 1:37:18
    Author     : Vinokurov
--%>

<%@page errorPage="/Testing/JSP-PAGES/error.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>index</title>
    </head>
    <body>
        <h1><fmt:message key="INDEX_HEADER"/></h1>
        <fmt:setLocale value="ru_RU"/>
        <fmt:setBundle basename="localization.locale" var="lang"></fmt:setBundle>
        <fmt:message key="INDEX_HEADER" bundle="${lang}"/>        
        <form action="login.jsp">
            <input type="Submit" name=action value="Login page">
        </form>

    </body>
</html>
