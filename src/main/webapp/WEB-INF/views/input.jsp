<%@include file="/common/taglibs.jsp"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title><spring:message code="page.heading.input"/></title>
		<%@include file="/common/scriptCss.jsp"%>
	</head>
	<body>
	<div align="left" class="container col-lg-6 col-lg-offset-3">
			<h1><spring:message code="page.heading.input"/></h1>
	        <form:form action="javascript:saveOrUpdateContact();" method="post" modelAttribute="addressBookInput" class="form-group">
				<%@include file="/common/messages.jsp"%>
				<div class="panel panel-primary">
					<div class="panel-heading"><spring:message code="heading.person.details"/></div>
					<div class="panel-body">
						<form:hidden path="person.personId"/>
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
							<form:input path="person.emailId" size="30" cssClass="form-control"/>
						</div>
						<div class="form-group">
							<spring:message code="label.dob.with.format"/>
							<form:input path="person.dateOfBirth" cssClass="form-control"/>
						</div>
						<div class="form-group">
							<spring:message code="label.phone"/>
							<form:input path="person.phoneNo" cssClass="form-control"/>
						</div>
					</div>
					<div class="panel-footer">
						<div class="form-group">
							<input type="button" class="btn btn-success" value="<spring:message code="button.save"/>" onclick="javascript:saveOrUpdateContact();">
							<input type="button" class="btn btn-warning" value="<spring:message code="button.quit"/>" onclick="javascript:navigateToHome();">
						</div>
					</div>
				</div>
				<h2><spring:message code="heading.address.list"/></h2>
				<hr>
				<table class="table table-striped table-bordered">
					<thead>
						<tr class="bg-primary">
							<th><spring:message code="label.address.line.one"/></th>
							<th><spring:message code="label.address.line.two"/></th>
							<th><spring:message code="label.city"/></th>
							<th><spring:message code="label.state"/></th>
							<th><spring:message code="label.country"/></th>
							<th>&nbsp;</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach var="addressVar" items="${addressBookInput.addresses}" varStatus="row">
					    <tr>
							<td>
								<form:hidden path="addresses[${row.index}].addrId"/>
								<c:out value="${addressVar.addrLineOne}"/>
								<form:hidden path="addresses[${row.index}].addrLineOne"/>
							</td>
							<td>
								<c:out value="${addressVar.addrLineTwo}"/>
								<form:hidden path="addresses[${row.index}].addrLineTwo"/>
							</td>
							<td>
								<c:out value="${addressVar.city}"/>
								<form:hidden path="addresses[${row.index}].city"/>
							</td>
							<td>
								<c:out value="${addressVar.state}"/>
								<form:hidden path="addresses[${row.index}].state"/>
							</td>
							<td>
								<c:out value="${addressVar.country}"/>
								<form:hidden path="addresses[${row.index}].country"/>
							</td>
							<td>
								<a class="btn btn-xs btn-success" href="javascript:editAddress('<c:out value="${addressVar.addrId}"/>')"><spring:message code="button.edit"/></a>
							</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
				<div class="panel panel-primary">
					<div class="panel-heading"><spring:message code="heading.address.add.edit"/></div>
					<div class="panel-body">
						<form:hidden path="address.addrId"/>
						<div class="form-group">
							<spring:message code="label.address.line.one"/>
							<form:input path="address.addrLineOne" cssClass="form-control"/>
						</div>
						<div class="form-group">
							<spring:message code="label.address.line.two"/>
							<form:input path="address.addrLineTwo" cssClass="form-control"/>
						</div>
						<div class="form-group">
							<spring:message code="label.city"/>
							<form:input path="address.city" cssClass="form-control"/>
						</div>
						<div class="form-group">
							<spring:message code="label.state"/>
							<form:input path="address.state" cssClass="form-control"/>
						</div>
						<div class="form-group">
							<spring:message code="label.country"/>
							<form:input path="address.country" cssClass="form-control"/>
						</div>
						<div class="form-group">
							<input type="button" class="btn btn-primary" value="<spring:message code="button.addOrUpdate"/>" onclick="javascript:addOrUpdateAddress();">
						</div>
					</div>
				</div>
	        </form:form>
	    </div>
	<script>
		function addOrUpdateAddress(){
			document.forms[0].action = '<c:url value="/addUpdateAddress/"/>';
			document.forms[0].submit();
		}
		function saveOrUpdateContact(){
			document.forms[0].action = '<c:url value="/saveDetails/"/>';
			document.forms[0].submit();
		}
		function navigateToHome(){
			document.forms[0].action = '<c:url value="/home/"/>';
			document.forms[0].submit();
		}
		function editAddress(addrId){
			document.forms[0].action = '<c:url value="/editAddress/"/>' + addrId;
			document.forms[0].submit();
		}
	</script>
	</body>
</html>