<%-- 
    Document   : create-food
    Created on : 10.07.2015, 0:36:44
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
        <title>Add Food | Restaurant</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet"/>
	<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet"/>
	<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
    </head>
</head>
<body>
    <style>
            body {
                background-image: url(images/big_6593_oboi_mjagkij_zhjoltyj_svet.jpg); /* Путь к фоновому изображению */
            }
        </style>
    <form action="/Restaurant/add-food" method="post">
        <fmt:message key="FoodName"/>:<input class="form-control-static"
            type="text" name="Name"
            /> <br /> 
        <fmt:message key="price"/>: <input class="form-control-static"
            type="number" name="cost"
            /> <br /> 
        <input
            class="btn btn-lg" type="submit" value=<fmt:message key="add_food"/> />
    </form>
</body>
</html>