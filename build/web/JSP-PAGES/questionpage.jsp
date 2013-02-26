<%-- 
    Document   : questionpage
    Created on : 18.12.2012, 16:55:44
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
        <title>Question Page</title>
    </head>
    <body>
        <h1>Question #${questionID} of test #${testID}</h1>
        <c:if test="${sessionScope.userType=='TUTOR'}">
            <form action="/Testing/JSP-PAGES/ADD/addanswer.jsp" method="POST">
                <input type="hidden" name="idquestion" value="${questionID}">
                <input type="submit" value="Add answer">
            </form>            
        </c:if>
        <table>
            <c:forEach var="answer" items="${answerList}">
                <tr>
                    <td>${answer.text}</td> 
                    <c:if test="${sessionScope.userType=='TUTOR'}">
                        <td>${answer.correct}</td>
                    </c:if>
                    <c:if test="${sessionScope.userType=='STUDENT'}">
                        <td>
                            <c:choose>
                                <c:when test="${markedTrue.contains(answer)}">
                                    True
                                </c:when>
                                <c:otherwise>
                                    False
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <form action="/Testing/Servlet" method="post">
                                <input type="hidden" name="command" value="edit">
                                <input type="hidden" name="beanType" value="answer">
                                <input type="hidden" name="idanswer" value="${answer.IDAnswer}">
                                <input type="hidden" name="idquestion" value="${questionID}">
                                <input type="submit" value="Alter">
                            </form>
                        </td>
                    </c:if>
                </tr>
            </c:forEach>
        </table>
        <form action="/Testing/Servlet" method="post">
            <input type="hidden" name="command" value="show">
            <input type="hidden" name="beanType" value="question">
            <input type="hidden" name="idtest" value="${testID}">
            <input type="submit" value="Reurn to test page">
        </form>
    </body>
</html>
