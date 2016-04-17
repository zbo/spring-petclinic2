<!DOCTYPE html>
<html lang="en">
<%@ page session="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>SB Admin 2 - Bootstrap Admin Theme</title>

    <jsp:include page="csses.jsp"/>
    <link rel="stylesheet" href="/petclinic/sb2/bower_components/ng-table/dist/ng-table.min.css">

</head>

<body>

<div id="wrapper" ng-app="owner">

    <jsp:include page="nav.jsp"/>

    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">One Owner Detail: ${owner.firstName} ${owner.lastName}</h1>
            </div>
        </div>
        <div class="row" ng-controller="ownerDetailCtrl">
            <div class="col-lg-6">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <div class="panel-body">
                            <jsp:include page="createOrUpdateOwner.jsp"/>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>
<!-- /#page-wrapper -->

</div>

<!-- /#wrapper -->

<jsp:include page="scripts.jsp"/>
<script src="/petclinic/sb2/bower_components/ng-table/dist/ng-table.min.js"></script>
<script src="/petclinic/sb2/petclinic/owner_app.js"></script>
</body>

</html>
