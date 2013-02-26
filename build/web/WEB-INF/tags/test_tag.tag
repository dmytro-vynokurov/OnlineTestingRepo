<%@tag description="outputs students table" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@attribute name="testList" %>
        <table>
            <tr>
                <td>Test ID</td>
                <td>Tutor ID</td>
                <td>Subject ID</td>
            </tr>
            <c:forEach var="test" items="${testList}">
                    <tr>
                        <td>${test.IDTest}</td>
                        <td>${test.IDTutor}</td>
                        <td>${test.IDSubject}</td>
                        <c:if test="${sessionScope.userType!='STUDENT'}">
                            <td>${test.ready}</td>
                        </c:if>
                        <td>
                            <form action="/Testing/Servlet" method="post">
                                <input type="hidden" name="command" value="show">
                                <input type="hidden" name="beanType" value="question">
                                <input type="hidden" name="idtest" value="${test.IDTest}">
                                <input type="submit" value="Show questions">
                            </form>
                        </td>
                        <c:if test="${sessionScope.userType=='TUTOR'}">
                            <td>
                                <form action="/Testing/Servlet" method="post">
                                    <input type="hidden" name="command" value="markready">
                                    <input type="hidden" name="beanType" value="test">
                                    <input type="hidden" name="idtest" value="${test.IDTest}">
                                    <input type="submit" value="Mark ready">
                                </form>
                            </td>
                        </c:if>
                    </tr>
                </c:forEach>
        </table>