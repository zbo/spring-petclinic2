<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ attribute name="name" required="true" rtexprvalue="true"
              description="Name of corresponding property in bean object" %>
<%@ attribute name="label" required="true" rtexprvalue="true"
              description="Label appears in red color if input is considered as invalid after submission" %>

<%--<spring:bind path="${name}">--%>
    <%--<c:set var="cssGroup" value="control-group ${status.error ? 'error' : '' }"/>--%>
    <%--<div class="${cssGroup}">--%>
        <%--<label class="control-label">${label}</label>--%>

        <%--<div class="controls">--%>
            <%--<form:input path="${name}"/>--%>
            <%--<span class="help-inline">${status.errorMessage}</span>--%>
        <%--</div>--%>
    <%--</div>--%>
<%--</spring:bind>--%>
<div class="form-group has-success">
    <c:set var="cssGroup" value="control-group ${status.error ? 'error' : '' }"/>
    <spring:bind path="${name}">
        <div class="${cssGroup}">
            <label class="control-label" for="${name}">${label}</label>
            <form:input type="text" class="form-control" path="${name}"/>
            <span class="help-inline" style="color: red">${status.errorMessage}</span>
        </div>
    </spring:bind>
</div>
