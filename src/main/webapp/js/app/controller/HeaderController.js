define([
    "app/grumbler",
    "app/resource/Authentication",
    "app/service/UserService"
],
function(grumbler) {

    return grumbler.controller("HeaderController", ["$scope", "Authentication", "UserService",
        function($scope, Authentication, UserService) {
            $scope.UserService = UserService;
            
            Authentication.get().$promise.then(function (auth) {
                UserService.set(auth);
            });
        }]);
});

