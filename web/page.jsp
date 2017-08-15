<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
  <head>
      <link rel="stylesheet" href="stylesheets/page.css" type="text/css">
    <title>InstClone | ${user.login}</title>
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
      <td><a href="/changeAvatar.jsp">
          <img class="img-circle" src="${user.avatar}" title="change avatar" height="150" width="150"  hspace="20" vspace="20">
      </a></td>
      <td><h2>${user.login}</h2></td>
      <td><h3 class="info">${posts} posts</h3></td>
      <td><h3 class="info">
          <a href="/followersList?followers=${user.login}">${followers}</a> followers</h3>
      </td>
      <td><h3 class="info">
          <a href="/followersList?following=${user.login}">${following}</a> following</h3>
      </td>
  </tr>
  </table>
  <br>
  <form method="get" action="/page">
      <input class="upload" type="submit" name="upload" accept="image/*" value="Upload image">
  </form>
 <c:forEach var="image" items="${list}">
     <a href="/imagePage?path=${image.path}&description=${image.description}">
     <img src=${image.path} height="300" width="300" hspace="20" vspace="20">
     </a>
 </c:forEach>
  </body>
</html>
