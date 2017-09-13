var app = angular.module('secondApplication', []);
var socket = new WebSocket("ws://localhost:8089/socket/tariff");

/*socket.onopen = function () {
 alert("Web Socket is connected!!");
 };
 socket.onmessage = function (evt) {
 var msg = evt.data;
 alert("Message received:" + msg);
 };
 socket.onclose = function () {
 alert("Connection is closed...");
 };*/

app.controller('TariffController', ['$http','$scope', function ($http, $scope) {
    var ctrl = this;
    ctrl.tariff = [];
    $http.get('/rest/tariff/all').success(function (data) {
        ctrl.tariff = data;
    });
    socket.onmessage = function (event) {
        //alert("Message received: " + event.data);
        // ctrl.tariff = [];
        // $scope.$apply();
        ctrl.tariff = JSON.parse(event.data);
        $scope.$apply();
        // location.reload();
/*        $http.get('/rest/tariff/all').success(function (data) {
            ctrl.tariff = data;
        });*/
    };
}]);
