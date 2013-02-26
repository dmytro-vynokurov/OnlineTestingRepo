<%@tag description="outputs students table" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@attribute name="studentList" %>
        <table >
            <c:if test="${studentList!=null}">
                <tr>
                    <td>Student ID</td>
                    <td>First name</td>
                    <td>Second name</td>
                    <td>Middle name</td>
                    <c:if test="${sessionScope.userType == 'ROOT'}">
                        <td>Login</td>
                        <td>Password</td>
                    </c:if>
                </tr>
            </c:if>
            <c:forEach var="student" items="${studentList}">
                    <tr>
                        <td>${student.IDStudent}</td>
                        <td>${student.firstName}</td>
                        <td>${student.secondName}</td>
                        <td>${student.middleName}</td>
                        <c:if test="${sessionScope.userType == 'ROOT'}">
                            <td>${student.login}</td>
                            <td>${student.password}</td>
                        </c:if>
                    </tr>
                </c:forEach>
        </table>