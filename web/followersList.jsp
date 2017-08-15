<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <link href="stylesheets/page.css" rel="stylesheet">
  <title>${user.login} ${followType}</title>
</head>
<body>
<p><b>${user.login} ${followType}</b></p>
<c:forEach items="${followers}" var="follower">
  <table>
    <tr>
      <td><img class="img-circle" src="${follower.avatar}" height="100" width="100" hspace="20"></td>
      <td> <a href="/userPage?visitedUser=${follower.login}">${follower.login}</a></td>
    </tr>
  </table>
</c:forEach>
</body>
</html>
