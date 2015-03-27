define([
    "app/grumbler",
    "app/resource/Account",
    "app/service/UserService"
],
function(grumbler) {

    return grumbler.controller("FollowersController", ["$scope", "Account", "UserService",
        function($scope, Account, UserService) {
            $scope.loadFollowers = function () {
                if (UserService.account) {
                    Account.followers({id:UserService.account.id}).$promise.then(function(accounts){
                        $scope.followers = accounts;
                    });
                }
            };
            
            $scope.loadFollowers();
            $scope.$on(UserService.CHANGE_BROADCAST, $scope.loadFollowers);
        }]);
});

