<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="WEB-INF/includes/header-links.jsp"%>
    <title>Welcome</title>
</head>
<body>
<%@include file="WEB-INF/includes/nav-bar.jsp"%>

<h3>Welcome</h3>
<a href="${pageContext.request.contextPath}/register.jsp">register New User</a><br>
<a href="${pageContext.request.contextPath}/newApplication.jsp">new Application</a><br>
<a href="${pageContext.request.contextPath}/controller?command=getAllApplications">Application's list</a>
<hr/>
${sessionScope.user.role} ${sessionScope.user.name}, hello!
<hr/>
<br>
<%@include file="WEB-INF/includes/footer-links.jsp"%>
</body>
</html>
