<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<%@include file="/common/taglibs.jsp"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title><spring:message code="page.heading.home"/></title>
		<%@include file="/common/scriptCss.jsp"%>
	</head>
	<body>
	<div align="left" class="container col-lg-6 col-lg-offset-3">
			<h1><spring:message code="page.heading.home"/></h1>
			<form:form action="home" method="post" modelAttribute="addressBook">
			<%@include file="/common/messages.jsp"%>
			<div class="panel panel-primary">
				<div class="panel-heading"><spring:message code="label.search"/></div>
				<div class="panel-body">
					<div class="form-group">
						<spring:message code="label.first.name"/>
						<form:input path="person.firstName" cssClass="form-control"/>
					</div>
					<div class="form-group">
						<spring:message code="label.last.name"/>
						<form:input path="person.lastName" cssClass="form-control"/>
					</div>
					<div class="form-group">
						<spring:message code="label.email"/>
						<form:input path="person.emailId" cssClass="form-control"/>
					</div>
				</div>
				<div class="panel-footer">
					<input type="button" value="<spring:message code="button.search"/>" 
							   onclick="javascript:searchContact();" class="btn btn-primary" >
					<input type="button" value="<spring:message code="button.refresh"/>" 
							   onclick="javascript:resetSearchCriteria();" class="btn btn-warning" >
				</div>	
			</div>	
	        </form:form>
					
			<c:if test="${fn:length(addressBooks) gt 0}">
				<h1><spring:message code="label.address.book"/></h1>
				<hr>
				<table class="table table-striped table-bordered">
					<thead>
						<tr class="bg-success text-info">
							<th><a href="javascript:sortBy('FIRST_NAME')"><spring:message code="label.first.name"/></a></th>
							<th><a href="javascript:sortBy('LAST_NAME')"><spring:message code="label.last.name"/></a></th>
							<th><a href="javascript:sortBy('EMAIL')"><spring:message code="label.email"/></a></th>
							<th><a href="javascript:sortBy('DOB')"><spring:message code="label.dob"/></a></th>
							<th><a href="javascript:sortBy('PHONE_NO')"><spring:message code="label.phone"/></a></th>
							<th>&nbsp;</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach var="addressBooksVar" items="${addressBooks}" varStatus="row">
					    <tr>
							<td>
								<c:out value="${addressBooksVar.person.firstName}"/>
							</td>
							<td>
								<c:out value="${addressBooksVar.person.lastName}"/>
							</td>
							<td>
								<c:out value="${addressBooksVar.person.emailId}"/>
							</td>
							<td>
								<fmt:formatDate pattern="dd/MM/yyyy" value="${addressBooksVar.person.dateOfBirth}" />
							</td>
							<td>
								<c:out value="${addressBooksVar.person.phoneNo}"/>
							</td>
							<td>
								<a class="btn btn-xs btn-success" href="<c:url value="/edit/"/><c:out value="${addressBooksVar.person.personId}"/>"><spring:message code="button.edit"/></a>
							</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
				<hr>
					<input type="button" value="<spring:message code="button.add.new.contact"/>" 
						   onclick="javascript:addContact();" class="btn btn-primary" >
					   <input type="button" value="<spring:message code="button.export.csv"/>" 	
					   		  onclick="javascript:exportToCsv();" class="btn btn-warning" >
					
				<hr>
			</c:if>
	    </div>
	<script>
		function addContact(){
			document.forms[0].action = '<c:url value="/addContact/"/>';
			document.forms[0].submit();
		}
		function sortBy(value){
			document.forms[0].action = '<c:url value="/sortBy/"/>' + value;
			document.forms[0].submit();
		}
		function searchContact(){
			document.forms[0].action = '<c:url value="/searchContact/"/>';
			document.forms[0].submit();
		}
		function exportToCsv(){
			document.forms[0].action = '<c:url value="/exportToCSV/"/>';
			document.forms[0].submit();
		}
		function resetSearchCriteria(){
			document.forms[0].action = '<c:url value="/resetSearchCriteria/"/>';
			document.forms[0].submit();
		}
	</script>
	</body>
</html>