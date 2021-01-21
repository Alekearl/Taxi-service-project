<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Insert driver</title>
</head>
<body>
<h1>Please provide your car details</h1>
<h4 style="color: green">${notification}</h4>
<form method="post" action="${pageContext.request.contextPath}/cars/drivers/insert">
    Please provide car id<br>
    <input type="number" name="id" required><br>
    Please provide driver id<br>
    <input type="number" name="id" required><br>
    <button type="submit">Insert</button>
</form>
<a href="${pageContext.request.contextPath}/">Go to the main page</a>
</body>
</html>
