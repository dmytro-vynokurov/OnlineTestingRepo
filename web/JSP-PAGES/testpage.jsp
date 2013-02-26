<%-- 
    Document   : testpage
    Created on : 18.12.2012, 16:34:26
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
        <title>Test page</title>
    </head>
    <body>
        <h1>Test #${testID}</h1>
        <c:if test="${sessionScope.userType == 'TUTOR'}">
            <form action="/Testing/JSP-PAGES/ADD/addquestion.jsp">
                <input type="hidden" name="idtest" value=${testID}>
                <input type="submit" value="Add question">
            </form>
        </c:if>
        <table>
            <tr>
                <td>Number</td>
                <td>Text</td>
                <td>One answer</td>
            </tr>
            <c:forEach var="question" items="${questionList}">
                <tr>
                    <td>Question #${question.IDQuestion}</td>
                    <td>${question.text}</td>
                    <td>${question.oneAnswer}</td>
                    <td>
                        <form action="/Testing/Servlet" method="post">
                            <input type="hidden" name="command" value="show">
                            <input type="hidden" name="beanType" value="answer">
                            <input type="hidden" name="idquestion" value="${question.IDQuestion}">
                            <input type="hidden" name="idtest" value="${testID}">
                            <input type="submit" value="Choose">
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <c:if test="${sessionScope.userType=='STUDENT'}">
            <form action="/Testing/JSP-PAGES/studenthome.jsp">
                <input type="submit" value="Home page">
            </form>
        </c:if>
        <c:if test="${sessionScope.userType=='TUTOR'}">
            <form action="/Testing/JSP-PAGES/tutorhome.jsp">
                <input type="submit" value="Home page">
            </form>
        </c:if>
    </body>
</html>
