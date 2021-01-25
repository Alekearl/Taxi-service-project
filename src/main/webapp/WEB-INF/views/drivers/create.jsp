<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create driver</title>
</head>
<body>
<h1>Insert please</h1>
<h4 style="color: green">${notification}</h4>
<form method="post" action="${pageContext.request.contextPath}/drivers/create">
    Insert driver name<br>
    <input type="text" name="name" required><br>
    Insert license number<br>
    <input type="text" name="licence_number" required><br>
    Insert driver login<br>
    <input type="text" name="login" required><br>
    Insert driver password<br>
    <input type="password" name="password" required><br>
    <button type="submit">Create</button>
</form>
<a href="${pageContext.request.contextPath}/">Go to the main page</a>
</body>
</html>
