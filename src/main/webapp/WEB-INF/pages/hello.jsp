<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="false"%>
<html>
<body>
	<h1>Title : ${title}</h1>	
	<h1>Message : ${message}</h1>	
	<h2>For Admins : <a href="<c:url value="/admin" />" > Login</a></h2>  
</body>
</html>