<nav class="navbar navbar-expand-sm bg-primary navbar-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">Repair agency</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">

                <li class="nav-item">
                    <a class="nav-link active" href="${pageContext.request.contextPath}/controller?command=getAllApplications">Applications</a>
                </li>


                <%--REGISTRATION AND USERSLIST LINKS--%>

                <c:if test="${sessionScope.user.role eq 'ADMIN' or sessionScope.user.role eq 'MANAGER'}">
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/register.jsp">Register New User</a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/controller?command=getAllUsers">Users</a>
                    </li>

                </c:if>

                <%--NEW APPLICATION LINK--%>
                <c:if test="${sessionScope.user.role eq 'CLIENT'}">
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/newApplication.jsp">new Application</a>
                    </li>
                </c:if>

            </ul>

            <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="controller?command=logout">Logout</a>
                </li>
            </ul>
        </div>
    </div>
</nav>