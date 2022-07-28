<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>Login</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            crossorigin="anonymous"></script>
</head>
<body>
<c:if test="${not empty errorMessge}">
    <div style="color:red; font-weight: bold; margin: 30px 0px;">
            ${errorMessge}
    </div>
</c:if>
<div class="container pt-4">
    <div class="row">
        <div class="card">
            <div class="card-header">
                Авторизация
            </div>
            <div class="card-body">
                <form name='login' action="<c:url value='/login'/>" method='POST'>
                    <table>
                        <tr>
                            <td>UserName:</td>
                            <td><input type='text' name='username'></td>
                        </tr>
                        <tr>
                            <td>Password:</td>
                            <td><input type='password' name='password'/></td>
                        </tr>
                        <tr>
                            <td colspan='2'>
                                <input class="btn btn-primary" name="submit" type="submit" value="Войти"/>
                            </td>
                        </tr>
                    </table>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>