
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="WEB-INF/includes/header-links.jsp" %>
    <title>New application</title>
</head>
<body>
<h1>Create request</h1>
<p>Please enter as accurate a description of the problem you want to solve as possible</p><br>
<form action="controller" method="post">
    <input type="hidden" name="command" value="newApplication">
    <textarea name="description" cols="100" rows="10" required maxlength="1000"></textarea>
    <p><input type="submit" value="Send">
</form>

<%@include file="WEB-INF/includes/footer-links.jsp" %>
</body>
</html>
