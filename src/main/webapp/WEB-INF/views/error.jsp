<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<%@include file="/common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spring:message code="page.heading.home" /></title>
<%@include file="/common/scriptCss.jsp"%>
</head>
<body>
	<div class="container">
	<div class="jumbotron">
		<h1 class="display-3">Error Page</h1>
		<p class="lead">Application has encountered an error.</p>
		<hr class="m-y-2">
		<p class="lead">
			<a class="btn btn-primary btn-lg" href="<c:url value="/home/"/>" role="button">Home</a>
		</p>

	</div>
	</div>
	<!--
    Failed URL: ${url}
    Exception:  ${exception.message}
        <c:forEach items="${exception.stackTrace}" var="ste">
        	${ste} 
    	</c:forEach>
  -->
</body>
</html>