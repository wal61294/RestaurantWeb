<%-- 
    Document   : OrganiseOrders
    Created on : 09.07.2015, 2:52:05
    Author     : huz
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="properties/language" />
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Organise user orders | Restaurant </title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet"/>
	<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet"/>
	<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
    </head>
    <body>
        <style>
            body {
                background-image: url(images/big_6593_oboi_mjagkij_zhjoltyj_svet.jpg); /* Путь к фоновому изображению */
            }
        </style>
        <div style="text-align: right">
            <a class="link-accept" href="/Restaurant/view-products"><fmt:message key="view_food"/></a>
        </div>
        <div style="text-align: right">
            <a class="link-cancel" href="/Restaurant/logout"><fmt:message key="button.logout"/></a>
        </div>
        <table class="table table-striped">
            <caption><fmt:message key="unwatched_orders"/></caption>
            <tr>
                <td><fmt:message key="client_id"/></td>
                <td><fmt:message key="price"/></td>
                <td colspan=2><fmt:message key="actions"/></td>
            </tr>
            <c:forEach items="${uOrders}" var="uOrder">
                <tr>

                    <td>${uOrder.getClientId()}</td>
                    <td>${uOrder.getCost()}</td>
                    <td><a href="Controller?action=accept&orderId=<c:out value="${uOrder.getId()}"/>"><fmt:message key="button.accept_order"/></a></td>
                    <td><a href="Controller?action=refuse&orderId=<c:out value="${uOrder.getId()}"/>"><fmt:message key="button.refuse_order"/></a></td>
                </tr>

            </c:forEach>

        </table>

        <table class="table table-striped">
            <caption><fmt:message key="accepted_orders"/></caption>
            <tr>
                <td><fmt:message key="client_id"/></td>
                <td><fmt:message key="price"/></td>
            </tr>

            <c:forEach items="${aOrders}" var="aOrder">
                <tr>
                    <td>${aOrder.getClientId()}</td>
                    <td>${aOrder.getCost()}</td>
                </tr>

            </c:forEach>

        </table>
    </table>

    <table class="table table-striped">
        <caption><fmt:message key="refused_orders"/></caption>
        <tr>
            <td><fmt:message key="client_id"/></td>
            <td><fmt:message key="price"/></td>
        </tr>

        <c:forEach items="${rOrders}" var="rOrder">
            <tr>
                <td>${rOrder.clientId}</td>
                <td>${rOrder.cost}</td>
            </tr>

        </c:forEach>

    </table>
        
</body>
</html>
