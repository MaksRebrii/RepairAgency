<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="WEB-INF/includes/taglib.jspf" %>

<html>
<head>
    <%@include file="WEB-INF/includes/header-links.jsp" %>
    <title>Applications</title>

</head>
<body>
<%@include file="WEB-INF/includes/nav-bar.jsp" %>

<div class="container">
    <div class="row">
        <div class="col-sm-8 text-center text-secondary"><h4>Filtration</h4></div>
        <div class="col-sm-2 text-center text-secondary"><h4>Search</h4></div>
        <div class="col-sm-2 text-center text-secondary"><h4>Sorting</h4></div>
    </div>

    <div class="row">
        <div class="col-sm-2 </div>">

            <h6>Completion status</h6>

            <div class="form-check text-left">
                <input class="form-check-input" type="checkbox" value="not started" id="notStartedCheck">
                <label class="form-check-label" for="notStartedCheck">Not started</label>
            </div>

            <div class="form-check">
                <input class="form-check-input" type="checkbox" value="in work" id="inWorkCheck">
                <label class="form-check-label" for="inWorkCheck">In work</label>
            </div>

            <div class="form-check">
                <input class="form-check-input" type="checkbox" value="done" id="doneCheck">
                <label class="form-check-label" for="doneCheck">Done</label>
            </div>
        </div>
        <div class="col-sm-2 text-center">

            <h6>Payment status</h6>

            <div class="form-check text-left">
                <input class="form-check-input" type="checkbox" value="waiting for payment" id="waitingForPayment">
                <label class="form-check-label" for="waitingForPayment">Waiting for payment</label>
            </div>

            <div class="form-check text-left">
                <input class="form-check-input" type="checkbox" value="paid" id="Paid">
                <label class="form-check-label" for="Paid">Paid</label>
            </div>

            <%--<div class="form-check">
                <input class="form-check-input" type="checkbox" value="done" id="doneCheck">   maybe rejected
                <label class="form-check-label" for="doneCheck">Done</label>
            </div>--%>
        </div>


        <div class="col-sm-2 text-center">

            <h6>Price</h6>

            <div class="form-check">
                <input class="form-check-input" type="radio" id="flexRadioDefault1" name="price" checked>
                <label class="form-check-label" for="flexRadioDefault1">
                    Any
                </label>
            </div>
            <div class="form-check">
                <input class="form-check-input" type="radio" id="flexRadioDefault2" name="price">
                <label class="form-check-label" for="flexRadioDefault2">
                    <input type="number" min="0" max="" placeholder="min">
                    <input type="number" min="0" max="" placeholder="max">
                </label>
            </div>


        </div>
        <div class="col-sm-2 text-center">

            <h6>date of issue</h6>

            <div class="form-check">
                <input class="form-check-input" type="radio" id="flexRadioDefault3" name="date" checked>
                <label class="form-check-label te" for="flexRadioDefault1">
                    Any
                </label>
            </div>
            <div class="form-check">
                <input class="form-check-input" type="radio" id="flexRadioDefault24" name="date">
                <label class="form-check-label" for="flexRadioDefault2">
                    <input type="date">
                    <input type="date">
                </label>
            </div>

        </div>

        <div class="col-sm-2 text-center">

            <h6>Enter master mask</h6>
            <input type="text" placeholder="mask">

        </div>

        <div class="col-sm-2 text-center">

            <h6>Order by</h6> <%--list id and date--%>
            <input type="text" placeholder="mask">

        </div>

    </div>


</div>

<div class="container-fluid">

    <c:choose>
        <c:when test="${fn:length(applicationList) == 0}"><h4 class="bg-warning">No such orders</h4></c:when>

        <c:otherwise>
            <table class="table">
                <caption class="text-center">found applications</caption>
                <thead>
                <tr>
                    <th>№</th>
                    <th>Description</th>
                    <th>Date of issue</th>
                    <th>Master</th>
                    <th>Completion status</th>
                    <th>Price</th>
                    <th>Payment status</th>
                </tr>
                </thead>

                <tbody>
                <c:forEach var="application" items="${applicationList}">
                    <tr>
                        <td>${application.id}</td>
                        <td>${application.description}</td>
                        <td>${application.date}</td>
                        <td>

                            <%--MASTER COLUMN--%>

                            <c:choose>
                                <c:when test="${application.master.id != 0}">
                                    ${application.master.surname}
                                </c:when>
                                <c:otherwise>

                                    <form action="controller" method="post">
                                        <input type="hidden" name="command" value="setMaster">
                                        <input type="hidden" name="applicationId" value="${application.id}">

                                        <select class="select" name="masterId">
                                            <option value="1" disabled>Master</option>
                                            <c:forEach var="master" items="${masterList}">
                                                <option value="${master.id}">${master.surname}</option>
                                            </c:forEach>
                                        </select>
                                        <br>
                                        <br>
                                        <input type="submit" class="btn btn-sm btn-primary btn-block" value="Submit">
                                    </form>

                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>${application.completionStatus}</td>
                        <td>
                            <%--PRICE COLUMN--%>
                            <c:choose>
                                <c:when test="${not empty application.price}">
                                    <div class="text-warning">${application.price}</div>
                                </c:when>
                                <c:otherwise>

                                    <form action="controller" method="post">
                                        <input type="hidden" name="command" value="setPrice">
                                        <input type="hidden" name="applicationId" value="${application.id}">

                                        <input type="number" min="0" name="price" step=".01" style="max-width: 60px">
                                        <br>
                                        <br>
                                        <input type="submit" class="btn btn-sm btn-primary btn-block" value="Submit">
                                    </form>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>${application.paymentStatus}</td>
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
