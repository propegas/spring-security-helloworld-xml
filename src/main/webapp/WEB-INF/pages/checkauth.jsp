<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="false"%>
{
"message": "${message}",	
<c:choose>
<c:when test="${pageContext.request.userPrincipal.authenticated}">
"login": "true',
"user": "${pageContext.request.userPrincipal.name}", 
"role": "${roles}"
</c:when>
<c:otherwise>
"login": "false",
"user": "",
"role": ""
</c:otherwise>
</c:choose>
}