<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome to my security page</title>
</head>
<body>
		<h2>Welcome !!</h2>
		<br>
		<!--  Add a logout -->
		
		<form:form action="${pageContext.request.contextPath}/logout" 
							   method="POST" >
							   
		 <input type="submit" value="Logout" />					   
		</form:form>
</body>
</html>