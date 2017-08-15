
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <link href="stylesheets/signUp.css" rel="stylesheet">
    <title>Sign up</title>
</head>
<body>
<div class="center">
    <div class="container">
        <h3>Sign Up</h3>
        <form method="POST" action="/signUp" encType="multipart/form-data">
            Login
            <input type="text" name="login" required>
            Password
            <input type="text" name="password" required>
            E-mail
            <input type="text" name="email" required>
            Choose avatar:
            <br>
            <input type="file" name="file" accept="image/*"/>
            <input type="submit" name="createAccount" value="Create">
        </form>
    </div>
</div>
</body>
</html>
