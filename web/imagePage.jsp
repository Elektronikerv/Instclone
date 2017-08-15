
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="stylesheets/imagePage.css">
    <title>InstClone</title>
</head>
<body>
<table>
  <tr>
    <td><img src="${image.path}" height="600" width="600"></td>
    <td class="desc" valign="top">${image.description}</td>
  </tr>
</table>

  <form method="get" action="/imagePage">
    <input type="hidden" name="path" value="${image.path}">
    <input class="delete" type="submit" name="delete" value="X">
  </form>

</body>
</html>
