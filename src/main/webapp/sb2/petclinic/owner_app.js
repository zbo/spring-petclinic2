/**
 * Created by zbo on 4/11/16.
 */
var app = angular.module('owner', ['ngTable']);

app.service('ownerService', function() {
    this.getAllOwners = function ($scope, $http, NgTableParams) {
        //var data = [{name: "Bob", age: 50},{name: "Tan", age: 51},{name: "You", age: 52} /*,*/];
        $http.get("/petclinic/owners.json").success(function (response) {
            console.log(response)
            var data = response.owners;
            angular.forEach(data, function(da){
                da.fullName=da.firstName + " " + da.lastName;
            });
            $scope.tableParams = new NgTableParams({}, { dataset: data});
        });
    }
});

app.controller('ownerCtrl', function($scope, NgTableParams, ownerService, $http) {
    $scope.firstName = "John";
    $scope.lastName = "Doe";
    ownerService.getAllOwners($scope, $http, NgTableParams);

});



