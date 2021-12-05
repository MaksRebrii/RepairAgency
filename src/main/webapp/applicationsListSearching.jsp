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

        <%--COMPLETION STATUS FILTRATION--%>

        <div class="col-sm-2">

            <h6>Completion status</h6>

            <form method="get" action="controller">

                <input type="hidden" name="command" value="filterByCompletionStatus">

                <div class="form-check text-left">
                    <input class="form-check-input" type="checkbox" value="NOT_STARTED" id="notStartedCheck"
                           name="completionStatus">
                    <label class="form-check-label" for="notStartedCheck">Not started</label>
                </div>

                <div class="form-check">
                    <input class="form-check-input" type="checkbox" value="IN_WORK" id="inWorkCheck"
                           name="completionStatus">
                    <label class="form-check-label" for="inWorkCheck">In work</label>
                </div>

                <div class="form-check">
                    <input class="form-check-input" type="checkbox" value="DONE" id="doneCheck" name="completionStatus">
                    <label class="form-check-label" for="doneCheck">Done</label>
                </div>
                <input type="submit" class="btn btn-sm btn-primary btn-block" value="Submit">
            </form>

        </div>


        <%--PAYMENT_STATUS FILTRATION--%>

        <div class="col-sm-2">

            <h6>Payment status</h6>

            <form action="controller" method="get">
                <input type="hidden" name="command" value="filterByPaymentStatus">

                <div class="form-check text-left">
                    <input class="form-check-input" type="checkbox" value="AWAITING_PROCESSING" id="awaitingProcessing"
                           name="paymentStatus">
                    <label class="form-check-label" for="waitingForPayment">Awaiting processing</label>
                </div>

                <div class="form-check text-left">
                    <input class="form-check-input" type="checkbox" value="WAITING_FOR_PAYMENT" id="waitingForPayment"
                           name="paymentStatus">
                    <label class="form-check-label" for="waitingForPayment">Waiting for payment</label>
                </div>

                <div class="form-check text-left">
                    <input class="form-check-input" type="checkbox" value="PAID" id="Paid" name="paymentStatus">
                    <label class="form-check-label" for="Paid">Paid</label>
                </div>
                <input type="submit" class="btn btn-sm btn-primary btn-block" value="Submit">
            </form>


            <
        </div>


        <%--PRICE FILTRATION--%>

        <div class="col-sm-2 text-center">

            <h6>Price</h6>

            <div class="form-check">
                <form method="get" action="controller">
                    <input type="hidden" name="command" value="filterByPrice">
                    <input type="number" min="0" placeholder="min" style="max-width: 100px" name="minValue">
                    <input type="number" min="0" placeholder="max" style="max-width: 100px" name="maxValue">

                    <br>
                    <br>
                    <input type="submit" class="btn btn-sm btn-primary btn-block" value="Submit">
                </form>

            </div>


        </div>

        <%--DATE FILTRATION--%>
        <div class="col-sm-2 text-center">

            <h6>date of issue</h6>

            <div class="form-check">
                <form method="get" action="controller">
                    <input type="hidden" name="command" value="filterByDate">
                    <input type="date" style="max-width: 130px" name="minValue">
                    <input type="date" style="max-width: 130px" name="maxValue">
                    <br>
                    <br>
                    <input type="submit" class="btn btn-sm btn-primary btn-block" value="Submit">
                </form>


            </div>

        </div>


        <%--Master FILTRATION--%>
        <div class="col-sm-2 text-center">

            <h6>Enter master mask</h6>

            <form action="controller" method="get">
                <input type="hidden" name="command" value="filterByMaster">
                <input type="text" placeholder="mask" required name="mask">
                <br>
                <br>
                <input type="submit" class="btn btn-sm btn-primary btn-block" value="Submit">
            </form>
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
                <c:if test="${application.paymentStatus eq 'CANCELED' and sessionScope.user.role eq 'MANAGER'}">
                <<continue>>
                    </c:if>
                    <tr>
                        <td>
                            <c:out value="${application.id}"></c:out>
                        </td>
                        <td>
                            <c:out value="${application.description}"></c:out>
                        </td>
                        <td>
                            <c:out value="${application.date}"></c:out>
                        </td>


                            <%--MASTER COLUMN--%>

                        <td>
                            <c:choose>
                                <c:when test="${application.master.id != 0}">
                                    <c:out value="${application.master.surname}"></c:out>

                                </c:when>

                                <%-- WHEN CURRENT USER IS MANAGER--%>

                                <c:when test="${sessionScope.user.role  eq 'MANAGER'and application.paymentStatus eq 'PAID'}">
                                    <form action="controller" method="post">
                                        <input type="hidden" name="command" value="setMaster">
                                        <input type="hidden" name="applicationId" value="${application.id}">

                                        <select class="select" name="masterId">
                                            <option value="1" disabled>Master</option>
                                            <c:forEach var="master" items="${masterList}">
                                                <option value="${master.id}">
                                                    <c:out value="${master.surname}"></c:out></option>
                                            </c:forEach>
                                        </select>
                                        <br>
                                        <br>
                                        <input type="submit" class="btn btn-sm btn-primary btn-block" value="Submit">
                                    </form>
                                </c:when>

                                <c:otherwise>
                                    <h6 class="text-danger">Master is not assigned</h6>
                                </c:otherwise>
                            </c:choose>
                        </td>

                            <%--COMPLETION STATUS--%>

                        <td>
                            <c:choose>
                                <c:when test="${sessionScope.user.role  eq 'MASTER' and application.completionStatus  ne 'DONE'}">


                                    <form action="controller" method="post">
                                        <input type="hidden" name="command" value="changeCompletionStatus">
                                        <input type="hidden" name="applicationId" value="${application.id}">

                                        <select class="select_completion" name="completionStatus">
                                            <option value="${application.completionStatus}1" hidden></option>
                                            <option value="NOT_STARTED">not started</option>
                                            <option value="IN_WORK">in work</option>
                                            <option value="DONE">done</option>
                                        </select>
                                        <br>
                                        <br>
                                        <input type="submit" class="btn btn-sm btn-primary btn-block" value="Submit">
                                    </form>
                                </c:when>

                                <c:otherwise>
                                    <c:out value="${application.completionStatus}"></c:out>
                                </c:otherwise>
                            </c:choose>
                        </td>

                            <%--PRICE COLUMN--%>

                        <td>

                            <c:choose>
                                <c:when test="${not empty application.price}">
                                    <div class="text-warning"><c:out value="${application.price}"></c:out></div>
                                </c:when>

                                <%-- WHEN CURRENT USER IS MANAGER--%>

                                <c:when test="${sessionScope.user.role  eq 'MANAGER'}">
                                    <form action="controller" method="post">
                                        <input type="hidden" name="command" value="setPrice">
                                        <input type="hidden" name="applicationId" value="${application.id}">

                                        <input type="number" min="0" name="price" step=".01" style="max-width: 100px">
                                        <br>
                                        <br>
                                        <input type="submit" class="btn btn-sm btn-primary btn-block" value="Submit">
                                    </form>
                                </c:when>

                                <c:otherwise>
                                    <h6 class="text-danger">Price is not set</h6>
                                </c:otherwise>
                            </c:choose>
                        </td>

                            <%--PAYMENT STATUS--%>

                        <td>

                            <c:choose>
                                <c:when test="${sessionScope.user.role  eq 'MANAGER' and application.paymentStatus  ne 'PAID'}">
                                    <form action="controller" method="post">
                                        <input type="hidden" name="command" value="changePaymentStatus">
                                        <input type="hidden" name="applicationId" value="${application.id}">

                                        <select class="select_payment" name="paymentStatus">
                                            <option value="${application.paymentStatus}1" hidden></option>

                                            <option value="WAITING_FOR_PAYMENT">waiting for payment</option>

                                            <option value="PAID"
                                                    <c:if test="${empty application.price}">disabled</c:if>>paid
                                            </option>
                                            <option value="AWAITING_PROCESSING" hidden>awaiting processing</option>

                                        </select>
                                        <br>
                                        <br>
                                        <input type="submit" class="btn btn-sm btn-primary btn-block" value="Submit">
                                    </form>
                                </c:when>

                                <c:otherwise>
                                    <c:out value="${application.paymentStatus}"></c:out>
                                </c:otherwise>
                            </c:choose>


                        </td>


                        <c:if test="${sessionScope.user.role eq 'CLIENT'}">

                            <%--BUTTON CANCELED AND EDIT THE APP--%>
                            <c:if test="${application.paymentStatus eq 'AWAITING_PROCESSING'}">
                                <td>
                                    <form action="controller" method="post">
                                        <input type="hidden" name="command" value="cancelApplication">
                                        <input type="hidden" name="applicationId" value="${application.id}">
                                        <input type="submit" class="btn btn-outline-danger" value="Cancel">
                                    </form>

                                        <%--TODO--%>
                                    <form action="controller" method="get">
                                        <input type="hidden" name="applicationId" value="${application.id}">
                                        <input type="submit" class="btn btn-outline-info" value="Edit">
                                    </form>
                                </td>
                            </c:if>
                            <%--TODO--%>
                            <%--PAY BUTTTON--%>

                            <c:if test="${application.paymentStatus eq 'WAITING_FOR_PAYMENT'}">
                                <td>
                                    <form action="controller" method="post">
                                        <input type="hidden" name="command" value="pay">
                                        <input type="hidden" name="applicationId" value="${application.id}">
                                    </form>
                                </td>
                            </c:if>

                            <%--FEEDBACK BUTTON--%>
                            <c:if test="${application.completionStatus eq 'DONE'}">
                                <td>
                                    <form action="controller" method="post">
                                        <input type="hidden" name="command" value="leaveFeedback">
                                        <input type="hidden" name="applicationId" value="${application.id}">
                                        <input type="submit" class="btn btn-outline-success" value="Review">
                                    </form>
                                </td>
                            </c:if>

                        </c:if>

                    </tr>

                    </c:forEach>

                </tbody>
            </table>
        </c:otherwise>

    </c:choose>

</div>

<nav aria-label="Navigation for applications">
    <ul class="pagination justify-content-end">
        <li class="page-item"><a href="#" class="page-link">&lsaquo;&lsaquo;</a></li>
        <li class="page-item active"><a href="#" class="page-link">1</a></li>
        <li class="page-item"><a href="#" class="page-link">2</a></li>
        <li class="page-item"><a href="#" class="page-link">3</a></li>
        <li class="page-item"><a href="#" class="page-link">&rsaquo;&rsaquo;</a></li>
    </ul>
</nav>

<%@include file="WEB-INF/includes/footer-links.jsp" %>
<%@include file="WEB-INF/includes/activeOptionInSelection.js" %>
</body>
</html>
