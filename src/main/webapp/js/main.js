requirejs.config({
    waitSeconds: 0,
    baseUrl: "js/",
    paths: {
        'angular': 'lib/ajax.googleapis.com/ajax/libs/angularjs/1.3.14/angular.min',
        'angularRoute': 'lib/ajax.googleapis.com/ajax/libs/angularjs/1.3.14/angular-route.min',
        'angularResource': 'lib/ajax.googleapis.com/ajax/libs/angularjs/1.3.14/angular-resource.min'
    },
    shim: {
        'angular': {exports: 'angular'},
        'angularRoute': {exports: 'angularRoute', deps: ['angular']},
        'angularResource': {exports: 'angularResource', deps: ['angular']}
    }
});

requirejs([
    "angular",
    "app/routes",
    "app/grumbler"
],
function(angular) {
    angular.bootstrap(window.document, ['grumbler']);
});