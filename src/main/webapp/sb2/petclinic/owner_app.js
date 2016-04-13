/**
 * Created by zbo on 4/11/16.
 */
var app = angular.module('owner', ['ngTable']);
app.controller('firstCtrl', function($scope, NgTableParams) {
    $scope.firstName = "John";
    $scope.lastName = "Doe";
    var data = [{name: "Moroni", age: 50},{name: "Moroni", age: 51},{name: "Moroni", age: 52} /*,*/];
    $scope.tableParams = new NgTableParams({}, { dataset: data});
});



