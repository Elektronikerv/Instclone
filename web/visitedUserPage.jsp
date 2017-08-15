<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="stylesheets/page.css">
    <title>InstClone | ${visitedUser.login}</title>
</head>
<body>
<header>
    <table style="background: #e6e6e6">
        <tr>
            <td><h1>InstClone</h1></td>
            <form method="get" action="/page">
                <td align="center" width="80%">
                    <input type="text" placeholder="Search" name="search">
                </td></form>
            <form method="get" action="page">
                <td  width="5%">
                    <h5 class="name">${user.login}</h5>
                </td>
                <td width="5%">
                    <a href="/page">
                        <img class="img-circle" src="${user.avatar}" height="75" width="75">
                    </a>
                </td>
                <td width="5%"><input class="logout" type="submit" value="log out" name="logOut"> </td>
            </form>
        </tr>
    </table>
</header>

<table>
  <tr>
    <td>
    <img class="img-circle" src="${visitedUser.avatar}" height="150" width="150"  hspace="20" vspace="20"></td>
      <td><h2>${visitedUser.login}</h2></td>
      <td><h3 class="info">${posts} posts</h3></td>
      <td><h3 class="info">
          <a href="/followersList?followers=${visitedUser.login}">${followers}</a> followers</h3>
      </td>
      <td><h3 class="info">
          <a href="/followersList?following=${visitedUser.login}">${following}</a> following</h3>
      </td>
      <td><form method="get" action="/userPage">
          <input type="hidden" value="${visitedUser.login}" name="visitedUser">
          <input type="submit" value="${followMode}" name="followMode">
      </form> </td>
  </tr>
</table>
    <br>
<c:forEach var="image" items="${list}">

    <img  src=${image.path} height="300" width="300" hspace="20" vspace="20">

</c:forEach>
</body>
</html>
