<!DOCTYPE html>
<html lang="en">

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
                <h1 class="page-header">All Owners table</h1>
            </div>
        </div>
        <div class="row" ng-controller="ownerCtrl">
            <div class="col-lg-6">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <div class="panel-body">
                            <div class="table-responsive">
                                <table ng-table="tableParams" class="table" show-filter="true">
                                    <tr ng-repeat="owner in $data">
                                        <td title="'Name'" filter="{ fullName: 'text'}" sortable="'name'">
                                            {{owner.fullName}}</td>
                                        <td title="'Address'" filter="{ address: 'text'}" sortable="'address'">
                                            {{owner.address}}</td>
                                        <td title="'City'" filter="{ city: 'text'}" sortable="'city'">
                                            {{owner.city}}</td>
                                        <td title="'Telephone'" filter="{ telephone: 'number'}" sortable="'telephone'">
                                            {{owner.telephone}}</td>
                                    </tr>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- /.panel -->
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
