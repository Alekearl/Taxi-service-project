<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create car</title>
</head>
<body>
<h1>Insert please</h1>
<h4 style="color: green">${notification}</h4>
<form method="post" action="${pageContext.request.contextPath}/cars/create">
    Insert car model<br>
    <input type="text" name="model" required><br>
    Insert manufacturer<br>
    <input type="number" name="manufacturer_id" required><br>
    <button type="submit">Create</button>
</form>
<a href="${pageContext.request.contextPath}/">Go to the main page</a>
</body>
</html>
