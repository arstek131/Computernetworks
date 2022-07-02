<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:useBean id="jb" class="esame.org.FABIO_ROMAGNOLO_BEAN" />

<c:set var="title" scope="page" value="Netcalc JSTL" />
<c:set var="ip" scope="page" value="${param.ip}" />
<c:set var="n" scope="page" value="${param.n}" />
<c:url var="url" scope="page" value="${pageContext.request.requestURL}" />


<title> <c:out value="${title}"/> </title>
</head>
<body>

<h1> <c:out value="${title}"/> </h1>

<form method="GET" action="<c:out value="${url}" />">
		IP: <input type=text name="ip" value="<c:out value="${ip}" />" />
		/ <input type=text name="n" value="<c:out value="${n}" />" />	
		
		<input type=submit name="go" value="Calcola"></input>	
</form>

<c:choose>
	 <c:when test="${ip == null or n == null}">
    	Riempire i campi!
   	 </c:when>
   	 <c:when test="${ip != null and n != null}">
    	
		<c:set var="intip" scope="page" value="${jb.parseIp(ip)}" />
		
		<c:choose>
	 		<c:when test="${intip == null}">
    			Scrivere un indirizzo IP valido!
   	 		</c:when>
   	 		<c:when test="${n < 0 or n > 32 }">
    			Verificare che il valore della netmask sia compreso tra 0 e 32
   	 		</c:when>
   	 		
   	 		 <c:otherwise>
    			<c:set var="netmask" scope="page" value="${jb.calcolaNetmask(n)}" />
				<c:set var="netid" scope="page" value="${jb.calcolaNetid(intip,netmask)}" />
				<c:set var="numhost" scope="page" value="${jb.calcolaNumHost(n)}" />

				<c:out value="Netmask" />: <c:out value="${netmask[0]}" />. 
				<c:out value="${netmask[1]}" />. 
				<c:out value="${netmask[2]}" />. 
				<c:out value="${netmask[3]}" /> <br>
				
				<c:out value="NetId" />: <c:out value="${netid[0]}" />.
				<c:out value="${netid[1]}" />.
				<c:out value="${netid[2]}" />.
				<c:out value="${netid[3]}" />	<br>
				
				<c:out value="NumHost" />: <c:out value="${numhost}" />	<br>
  			</c:otherwise>
		</c:choose>
		
   	 </c:when>
   	 
  	<c:otherwise>
    	Errore interno.
  	</c:otherwise>
</c:choose>



</body>
</html>