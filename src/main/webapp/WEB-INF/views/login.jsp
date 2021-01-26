<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h1>Login page</h1>
<h4 style="color: orangered">${errorMsg}</h4>
<form action="${pageContext.request.contextPath}/login" method="post">
    Please provide your login: <input type="text" name="login"><br>
    Please provide your password: <input type="password" name="password"><br>
    <button type="submit">Sign in</button>
</form>
</body>
</html>
