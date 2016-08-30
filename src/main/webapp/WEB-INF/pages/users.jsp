<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>

<html>
<head>
    <title>User Page</title>

    <style type="text/css">
        table {
            font-family: "Lucida Sans Unicode", "Lucida Grande", Sans-Serif;
            text-align: center;
            border-collapse: collapse;
            border-spacing: 5px;
            background: #E1E3E0;
            border-radius: 20px;
        }
        th {
            font-size: 22px;
            font-weight: 300;
            padding: 12px 10px;
            border-bottom: 2px solid #F56433;
            color: #F56433;
        }
        tbody tr:nth-child(2) {
            border-bottom: 2px solid #F56433;
        }
        td {
            padding: 10px;
            color: #8D8173;
        }
        A {
            color: #8D8173
        }
        A:hover {
            color: #F56433
        }
        h1 {
            font-family: "Lucida Sans Unicode", "Lucida Grande", Sans-Serif;
            font-size: 22px;
            font-weight: 300;
            padding: 12px 10px;
            color: #F56433
        }
    </style>
</head>
<br/>
<br/>

<c:if test="${!empty searchByName}">
    <a href="http://localhost:8080/users">Back to User List</a>
    <table class="tg">
        <tr>
            <td>
            </td>
        <tr>
            <th width="40">ID</th>
            <th width="120">Name</th>
            <th width="30">Age</th>
            <th width="60">isAdmin</th>
            <th width="100">createDate</th>
            <th width="30">Edit</th>
            <th width="40">Delete</th>
        </tr>
        <c:forEach items="${searchByName}" var="user">
            <tr>
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${user.age}</td>
                <td>${user.isAdmin}</td>
                <td>${user.createDate}</td>
                <td><a href="<c:url value='/edit/${user.id}'/>">Edit</a></td>
                <td><a href="<c:url value='/remove/${user.id}'/>">Delete</a></td>
            </tr>
        </c:forEach>

    </table>
</c:if>


<c:if test="${!empty listUsers}">
    <table class="tg">
        <tr>
            <td>
                    <c:url var="search" value="/users/search"/>
            <form:form action="${search}" commandName="user">

            <td>by topstar</td>
            <td></td><td></td>
            <td>
                <form:input path="name"/>
            </td>
            <td colspan="3">
                <input type="submit" value="<spring:message text="Search by name"/>"/>
            </td>
        </tr>
        </form:form>

        </td>
        <tr>
            <th width="40">ID</th>
            <th width="120">Name</th>
            <th width="30">Age</th>
            <th width="60">isAdmin</th>
            <th width="100">createDate</th>
            <th width="30">Edit</th>
            <th width="40">Delete</th>
        </tr>
        <c:forEach items="${listUsers}" var="user">
            <tr>
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${user.age}</td>
                <td>${user.isAdmin}</td>
                <td>${user.createDate}</td>
                <td><a href="<c:url value='/edit/${user.id}'/>">Edit</a></td>
                <td><a href="<c:url value='/remove/${user.id}'/>">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
    <c:url var="start" value="/users/start"/>
    <c:url var="pagedown" value="/users/pagedown"/>
    <c:url var="pageup" value="/users/pageup"/>
    <c:url var="end" value="/users/end"/>

    <table>
        <form:form action="${start}" commandName="user">
            <td colspan="4">
                <input type="submit" value="<spring:message text="<<"/>"/>
            </td>
        </form:form>
        <form:form action="${pagedown}" commandName="user">
            <td colspan="5">
                <input type="submit" value="<spring:message text=" < "/>"/>
            </td>
        </form:form>
        <form:form action="${pageup}" commandName="user">
            <td colspan="6">
                <input type="submit" value="<spring:message text=" > "/>"/>
            </td>
        </form:form>
        <form:form action="${end}" commandName="user">
            <td colspan="7">
                <input type="submit" value="<spring:message text=">>"/>"/>
            </td>
        </form:form>
    </table>


</c:if>





<h1>ADD THE USER</h1>

<c:url var="addAction" value="/users/add"/>

<form:form action="${addAction}" commandName="user">
    <table>

        <c:if test="${!empty user.name}">
            <tr>
                <td>
                    <form:label path="id">
                        <spring:message text="ID"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="id" readonly="true" size="8" disabled="true"/>
                    <form:hidden path="id"/>
                </td>
            </tr>
        </c:if>
        <tr>
        <tr></tr>

            <td>
                <form:label path="name">
                    <spring:message text="Name"/>
                </form:label>
            </td>
            <td>
                <form:input path="name"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="age">
                    <spring:message text="Age"/>
                </form:label>
            </td>
            <td>
                <form:input path="age"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="isAdmin">
                    <spring:message text="isAdmin"/>
                </form:label>
            </td>
            <td>
                <form:input path="isAdmin"/>
            </td>
        </tr>


        <tr>
            <td colspan="2">
                <c:if test="${!empty user.name}">
                    <input type="submit"
                           value="<spring:message text="Edit User"/>"/>
                </c:if>
                <c:if test="${empty user.name}">
                    <input type="submit"
                           value="<spring:message text="Add User"/>"/>
                </c:if>
            </td>
        </tr>
    </table>
</form:form>


</body>
</html>
