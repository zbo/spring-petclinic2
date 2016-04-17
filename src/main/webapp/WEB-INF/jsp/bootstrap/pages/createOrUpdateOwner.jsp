<%@ page session="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<form:form modelAttribute="owner" class="form-horizontal">
    <petclinic:inputField label="First Name" name="firstName"/>
    <petclinic:inputField label="Last Name" name="lastName"/>
    <petclinic:inputField label="Address" name="address"/>
    <petclinic:inputField label="City" name="city"/>
    <petclinic:inputField label="Telephone" name="telephone"/>
    <button type="submit" class="btn btn-default">Submit</button>
    <a class="btn btn-default" href="../owners">Cancel</a>
</form:form>
