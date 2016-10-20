<%@include file="taglibs.jsp"%>
<c:set var="formErrors">
	<form:errors path="*" cssClass="text-danger" />
</c:set>
<c:if test="${not empty formErrors}">
	<div class="alert alert-danger ">${formErrors}</div>
</c:if>
<c:if test="${not empty message}">
	<c:set var="messageCssClass" value="info"/>
	<c:choose>
		<c:when test="${message.type.cssClass == 'error'}">
			<c:set var="messageCssClass" value="danger"/>
		</c:when>
		<c:otherwise>
			<c:set var="messageCssClass" value="${message.type.cssClass}"/>
		</c:otherwise>
	</c:choose>
	<div class="alert alert-${messageCssClass} alert-dismissible">
		<button type="button" class="close" data-dismiss="alert" aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
		${message.text}
	</div>
</c:if>