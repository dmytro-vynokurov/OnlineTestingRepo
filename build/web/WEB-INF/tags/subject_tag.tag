<%@tag description="outputs subjects table" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@attribute name="subjectList" %>
<table>
    <c:if test="${subjectList!=null}">
        <tr>
            <td>ID</td>
            <td>Name</td>
        </tr>
    </c:if>
    <tr>
    <c:forEach var="subject" items="${subjectList}">
        <tr>
            <td>${subject.IDSubject}</td>
            <td>${subject.subjectName}</td>
            <c:if test="${sessionScope.userType=='TUTOR'}">
                <td>
                    <form action="/Testing/Servlet" method="post">
                        <input type="hidden" name="command" value="add">
                        <input type="hidden" name="beanType" value="test">
                        <input type="hidden" name="idsubject" value="${subject.IDSubject}">
                        <input type="submit" value="Add test">
                    </form>
                </td>
            </c:if>
        </tr>
    </c:forEach>        
</table>