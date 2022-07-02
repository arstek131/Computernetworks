<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<c:set var="title" scope="page" value="TITLE" />
	<c:set var="ip" scope="page" value="${param.ip}" />
	<c:set var="n" scope="page" value="${param.n}" />
	<c:catch var ="catchException">
		<fmt:parseNumber var="i" integerOnly="true" type="number" value="${n}" />
	</c:catch>
	<jsp:useBean id="netCalc" class="Esercizi.NetCalc"/>
	<jsp:setProperty property="ip" name="netCalc" value="${ip}"/>
	<jsp:setProperty property="n" name="netCalc" value="${n}"/>
	<c:url var="url" scope="page" value="${pageContext.request.requestURL}" />
	<title><c:out value="${title}" /></title>
</head>
<body>
	<h1><c:out value="${title}"></c:out></h1>
	<form action="<c:out value="${url}" />" method=GET>
		Address IP: <input type="text" name="ip"><br><br>
		Netmask: <input type="text" name="n"><br><br>
		<input type="submit" value="go">
	</form>
	<br><br>
	<c:if test="${ip != null && n != null}">
		Netmask = <c:out value="${netCalc.netmask}"></c:out><br>
		Netid = <c:out value="${netCalc.netId}"></c:out><br>
		Numero di host = <c:out value="${netCalc.numHost}"></c:out><br>
	</c:if>
</body>
</html>
