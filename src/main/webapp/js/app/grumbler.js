define([
    "angular",
    "angularRoute",
    "angularResource"
],
function(angular) {
    var app =  angular.module('grumbler', ['ngRoute', 'ngResource']);
    return app;
});