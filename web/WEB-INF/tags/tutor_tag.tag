<%@tag description="outputs tutors table" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@attribute name="tutorList" %>
        <table >
            <c:if test="${tutorList!=null}">
                <tr>
                    <td>Tutor ID</td>
                    <td>First name</td>
                    <td>Second name</td>
                    <td>Middle name</td>
                    <c:if test="${sessionScope.userType=='ROOT'}">
                        <td>Login</td>
                        <td>Password</td>
                    </c:if>
                </tr>
            </c:if>
            <c:forEach var="tutor" items="${tutorList}">
                    <tr>
                        <td>${tutor.IDTutor}</td>
                        <td>${tutor.firstName}</td>
                        <td>${tutor.secondName}</td>
                        <td>${tutor.middleName}</td>
                        <c:if test="${sessionScope.userType=='ROOT'}">
                            <td>${tutor.login}</td>
                            <td>${tutor.password}</td>
                        </c:if>
                    </tr>
                </c:forEach>
        </table>