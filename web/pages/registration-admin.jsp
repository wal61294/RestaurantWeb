<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="properties/language" />
<html>
   <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>Registration Administrator | Restaurant</title>
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
        <form action="/Restaurant/check-registration" method="post">
            <table style="margin: auto; text-align: left">
                <tr>
                    <td><fmt:message key="name"/>:</td>
                    <td><input class="form-control" name="name" value="" type="name"
                               size="35" required /></td>
                </tr>
            </table>
            <table style="margin: auto">
                <tr>
                    <td><input type="submit" class="btn-lg btn-primary" name="create"
                               value=<fmt:message key="button.accept"/> /></td>
                    <td><a class="link-cancel" href="/Restaurant/registration">
                            <fmt:message key="button.login"/></a></td>
                </tr>
            </table>
        </form>
    </div>
</body>
</html>
