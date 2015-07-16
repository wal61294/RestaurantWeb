
<%-- 
    Document   : client
    Created on : 04.07.2015, 0:37:14
    Author     : huz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/tag/customTag.tld" prefix="ct" %>
<!DOCTYPE html>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="properties/language" />
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Client Panel | Restaurant</title>
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
        <h1><fmt:message key="hello_client"/></h1>
        <p><fmt:message key="you_have"/></p>
        <p>${money}</p>
        <div style="text-align: right ">
            <a class="link-accept" href="/Restaurant/create-order"><fmt:message key="create_order"/></a>
        </div>
        <div style="text-align: right ">
            <a class="link-accept" href="/Restaurant/view-client-orders"><fmt:message key="view_orders"/></a>
        </div>
        <div style="text-align: right">
            <a class="link-cancel" href="/Restaurant/logout" ><fmt:message key="button.logout"/></a>
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
         <footer ><ct:custom/></footer>
    </body>
</html>
