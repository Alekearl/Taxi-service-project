<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Manufacturer create</title>
</head>
<body>
<h1>Insert please</h1>
<h4 style="color: green">${notification}</h4>
<form method="post" action="${pageContext.request.contextPath}/manufacturers/create">
    Insert name<br>
    <input type="text" name="name" required><br>
    Insert country<br>
    <input type="text" name="country" required><br>
    <button type="submit">Create</button>
</form>
<a href="${pageContext.request.contextPath}/">Go to the main page</a>
</body>
</html>
