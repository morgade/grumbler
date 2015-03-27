define([
    'app/grumbler',
    'app/controller/TimelineController',
    'app/controller/HeaderController',
    'app/controller/FollowersController',
    'app/controller/FollowingController',
    'app/controller/UsersController'
], 
function(grumbler) {

    return grumbler.config(['$routeProvider', function($routeProvider) {
            $routeProvider.when('/timeline', {
                templateUrl: 'partial/timeline.html',
                controller: 'TimelineController'
            });
            $routeProvider.when('/followers', {
                templateUrl: 'partial/followers.html',
                controller: 'FollowersController'
            });
            $routeProvider.when('/following', {
                templateUrl: 'partial/following.html',
                controller: 'FollowingController'
            });
            $routeProvider.when('/users', {
                templateUrl: 'partial/users.html',
                controller: 'UsersController'
            });
            $routeProvider.otherwise('/timeline');
        }]);

});
