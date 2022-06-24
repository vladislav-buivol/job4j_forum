<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html lang="en">
<ul class="nav">
    <li class="nav-item">
        <a class="nav-link" href="/logout">
            Login as : ${user.username}
            | Выйти
        </a>
    </li>
    <li>
        <a class="nav-link" href="/create">
            Добавить тему
        </a>
    </li>
</ul>
</html>