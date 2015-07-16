<%-- 
    Document   : login
    Created on : 03.07.2015, 23:16:50
    Author     : huz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="properties/language" />
<html lang="${language}">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>Login | Restaurant</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet"/>
        <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet"/>
        <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>

    </head>
    <style>
        body {
            background-image: url(images/desktopwallpapers.org.ua_6136.jpg); /* Путь к фоновому изображению */
        }
    </style>
    <body>

        <form>
            <select id="language" name="language" onchange="submit()">
                <option value="ru" ${language == 'ru' ? 'selected' : ''}>RU</option>
                <option value="en" ${language == 'en' ? 'selected' : ''}>EN</option>
            </select>
        </form>
        
            <h1><fmt:message key="welcome"/></h1>

            <form form class="form-signin" action="/Restaurant/check-login" method="post">
                <table style="margin: auto">
                    <td><input name="login" type="login" size="35" class="form-control" placeholder=<fmt:message key="login"/>
                               maxlength="35" required/></td>
                    <td><input name="password" type="password" size="35"  class="form-control" placeholder=<fmt:message key="password"/>
                               maxlength="35" required /></td>
                    <td><input  class="btn btn-lg btn-primary btn-block" type="submit" class="button-accept"
                                name="run"
                                value=<fmt:message key="button.login"/> /></td>
                </table>      
            </form>

            <form action="/Restaurant/registration" method="get">
                <input class="btn btn-default btn-md" type="submit" class="button-register" 
                       name="register" value=<fmt:message key="button.register"/> />
            </form>
                      
        
    </body>
</html>
