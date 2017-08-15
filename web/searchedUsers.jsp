<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <link rel="stylesheet" href="stylesheets/page.css">
    <title>Searched users</title>
</head>
<body>
    <p><b>  Result for: ${search}</b></p>
  <c:forEach items="${searchedUsers}" var="visitedUser">
    <table>
      <tr>

        <td><img class="img-circle" src="${visitedUser.avatar}" height="100" width="100" hspace="20"></td>
        <td> <a href="/userPage?visitedUser=${visitedUser.login}">${visitedUser.login}</a></td>
      </tr>
    </table>
  </c:forEach>
</body>
</html>
