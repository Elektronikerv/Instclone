
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="/stylesheets/auth.css" rel="stylesheet" type="text/css">
    <title>Sign in</title>
</head>
<body >
    <div class="center">
        <div class="container">
            <h3>Sign in</h3>
            <form method="POST" action="/auth">
                Login
                <br>
                <input type="text" name="login" required>
                <br>
                Password
                <br>
                <input type="password" name="password" required>
                <br>
                <input type="submit" value="Sign in">
                <br>
                Do not have the account? <a href="signUp.jsp">Sign Up</a>
            </form>
        </div>
    </div>
</body>
</html>
