<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Hello World!</h1>
<a href="${pageContext.request.contextPath}/drivers/create">Create driver</a><br>
<a href="${pageContext.request.contextPath}/cars/create">Create car</a><br>
<a href="${pageContext.request.contextPath}/manufacturers/create">Create Manufacturer</a><br>
<a href="${pageContext.request.contextPath}/drivers/">Display all drivers</a><br>
<a href="${pageContext.request.contextPath}/cars/drivers/insert">Add driver</a><br>
</body>
</html>
