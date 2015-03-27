define([
    "app/grumbler",
    "app/resource/Account",
    "app/resource/Status",
    "app/service/UserService"
],
function(grumbler) {

    return grumbler.controller("UsersController", ["$scope", "Account", "UserService",
        function($scope, Account, UserService) {
            $scope.loadUsers = function () {
                Account.accounts().$promise.then(function(accounts){
                    $scope.accounts = accounts;
                });
            };
            
            $scope.unfollow = function (account) {
                Account.unfollow(account.id).$promise.then(function () {
                    account.follows = false;
                });
            };
            
            $scope.follow = function (account) {
                Account.follow(account.id).$promise.then(function () {
                    account.follows = true;
                });
            };
            
            $scope.loadUsers();
            $scope.$on(UserService.CHANGE_BROADCAST, $scope.loadUsers);
        }]);
});

