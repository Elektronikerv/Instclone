
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="stylesheets/auth.css" rel="stylesheet">
    <title>Change avatar</title>
</head>
<body>
<div class="center">
  <div class="container">
    <h3>Change avatar</h3>
<form action="/changeAvatar" method="post" enctype="multipart/form-data">
  <input type="file" name="avatar" accept="image/*" value="Choose avatar">
  <input type="submit" value="Change avatar">
</form>
  </div>
  </div>
</body>
</html>
