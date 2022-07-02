<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>Create</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>

<div class="container pt-4">
    <div class="row">
        <div class="card">
            <div class="card-header">
                ${post.name}
            </div>
            <div class="card-body">
                <div class="row">
                    <b>Описание:</b> ${post.description}
                </div>
                <div class="row">
                    <table class="table">
                        <thead>
                        <tr>
                            <th scope="col"><i>Комментарии:</i></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${post.comments}" var="comment">
                            <tr>
                                <td>
                                    <div class="row">
                                        <i>Автор: ${comment.author} (${comment.date})</i>
                                    </div>
                                    <div class="row">
                                            ${comment.text}
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <form method="post" action="/comment">
                        <div class="row">
                            <label for="comment">Добавить комментарии</label>
                            <textarea name="comment" id="comment" required></textarea>
                        </div>
                        <input type="hidden" name="id" value="${post.id}"/>
                        <td colspan='2'><input name="submit" type="submit" value="Сохранить"/></td>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>