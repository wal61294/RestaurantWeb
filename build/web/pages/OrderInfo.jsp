<%-- 
    Document   : OrderInfo
    Created on : 13.07.2015, 10:33:56
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
        <title> Order-Info | Restaurant </title>
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
            <a class="link-accept" href="/Restaurant/view-client-orders"><fmt:message key="button.back"/></a>
        </div>
        <table class="table table-striped">
            <tr>
                <td><fmt:message key="FoodName"/></td>
                <td><fmt:message key="price"/></td>
            </tr>
            <c:forEach items="${foods}" var="food">
                <tr>
                    <td>${food.getFoodName()}</td>
                    <td>${food.getPrice()}</td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
