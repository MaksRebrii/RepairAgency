<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="WEB-INF/includes/taglib.jspf" %>
<html>
<head>
    <%@include file="WEB-INF/includes/header-links.jsp" %>
    <title>Users</title>
</head>
<body>
<%@include file="WEB-INF/includes/nav-bar.jsp" %>

<div class="container">

    <div class="row">
        <div class="col-sm-2 text-center text-secondary"><h4>Filtration</h4></div>
        <div class="col-sm-4 text-center text-secondary"><h4>Search</h4></div>
        <div class="col-sm-6 text-center text-secondary"></div>
    </div>

    <div class="row">
        <%--ROLE FILTRATION--%>
        <div class="col-sm-3">
            <h6>Role</h6>

            <form method="get" action="controller">
                <input type="hidden" name="command" value="filterByRole">

                <div class="form-check text-left">
                    <input class="form-check-input" type="checkbox" value="CLIENT" id="clientCheck"
                           name="role">
                    <label class="form-check-label" for="clientCheck">Client</label>
                </div>

                <div class="form-check">
                    <input class="form-check-input" type="checkbox" value="MASTER" id="masterCheck"
                           name="role">
                    <label class="form-check-label" for="masterCheck">Master</label>
                </div>

                <c:if test="${sessionScope.user.role eq 'ADMIN'}">
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" value="MANAGER" id="managerCheck"
                               name="role">
                        <label class="form-check-label" for="managerCheck">Manager</label>
                    </div>
                </c:if>
                <input type="submit" class="btn btn-sm btn-primary btn-block" value="Submit">

            </form>
        </div>

        <%--SEARCH BY MASK--%>
        <div class="col-sm-2 text-center">

            <h6>Enter user mask</h6>

            <form action="controller" method="get">
                <input type="hidden" name="command" value="filterBySurname">
                <input type="text" placeholder="mask" required name="mask">
                <br>
                <br>
                <input type="submit" class="btn btn-sm btn-primary btn-block" value="Submit">
            </form>
        </div>
    </div>

</div>

<div class="container-fluid">
    <c:choose>
        <c:when test="${fn:length(userList) == 0}"><h4 class="bg-warning">No such users</h4></c:when>

        <c:otherwise>
            <table class="table">
                <caption class="text-center">found users</caption>

                <thead>
                <tr>
                    <th>Email</th>
                    <th>Surname</th>
                    <th>Name</th>
                    <th>Account</th>
                    <th>Role</th>
                </tr>
                </thead>

                <tbody>
                <c:forEach var="user" items="${userList}">
                <c:if test="${sessionScope.user.role ne 'ADMIN' and user.role eq 'MANAGER'}">
                <<continue>>
                    </c:if>
                    <tr>
                        <td>
                            <c:out value="${user.email}"></c:out>
                            <c:out value="${sessionScope.user.role ne 'ADMIN' and (user.role eq 'MANAGER')}"></c:out>

                        </td>

                        <td>
                            <c:out value="${user.surname}"></c:out>
                        </td>

                        <td>
                            <c:out value="${user.name}"></c:out>
                        </td>

                        <%--USER ACCOUNT--%>
                        <td>
                            <c:choose>
                                <c:when test="${user.role ne 'CLIENT'}">
                                    Absent
                                </c:when>

                                <c:otherwise>
                                    <form action="controller" method="post">
                                        <input type="hidden" name="command" value="topUpAccount">
                                        <input type="hidden" name="userId" value="${user.id}">

                                        <input type="number" min="${user.account}" name="sum" step=".01" style="max-width: 70px" value="${user.account}">
                                        <br>
                                        <br>
                                        <input type="submit" class="btn btn-sm btn-primary btn-block" value="top up">
                                    </form>
                                </c:otherwise>
                            </c:choose>
                        </td>

                        <td>
                            ${user.role}
                        </td>

                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:otherwise>
    </c:choose>
</div>

<%@include file="WEB-INF/includes/footer-links.jsp" %>
</body>
</html>
