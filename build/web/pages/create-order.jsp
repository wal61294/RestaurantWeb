<%-- 
    Document   : CreateOrder
    Created on : 08.07.2015, 23:34:56
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
        <title>Create new Order | Restaurant</title>
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
        <h1> <fmt:message key="order_creation"/></h1>
        <form action="/Restaurant/do-order" method="post">
            <table class="table table-striped">
                <tr>

                    <td><fmt:message key="FoodName"/></td>
                    <td><fmt:message key="price"/></td>
                </tr>
                <c:forEach items="${foods}" var="food">
                    <tr>
                        <td>${food.getFoodName()}</td>
                        <td>${food.getPrice()}</td>
                        <td><input type="checkbox" class="checkbox" name="chooseFood" value="${food.getFoodId()}"></td>
                    </tr>
                </c:forEach>

            </table>
            <input
                class="btn btn-lg btn-primary" type="submit" value=<fmt:message key="create_order"/> />

        </form>
        <form action="/Restaurant/cancel-order" method="post">
            <input type="submit" class="btn btn-lg" value=<fmt:message key="button.cancel_order"/> />
        </form>

    </body>
</html>
