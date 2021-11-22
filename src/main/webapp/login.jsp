<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="WEB-INF/includes/header-links.jsp" %>
    <title>Login</title>
</head>
<body>
<div class="text-center">
    <form action="controller" method="post" style="max-width: 300px; margin: auto">
        <h1 class="h3 mt-4 mb-3 font-weight-normal">Please sign in</h1>
        <input type="hidden" name="command" value="login">
        <input name="email" type="email" id="emailAddress" class="form-control " placeholder="Email" required><br>
        <input name="password" type="password" id="password" class="form-control"  placeholder="Password" required><br>
        <input type="submit" class="btn btn-lg btn-primary btn-block" value="Log in">
        <br><h6 class="text-danger">${sessionScope.error}</h6>

    </form>
</div>
<%@include file="WEB-INF/includes/footer-links.jsp" %>
</body>
</html>
