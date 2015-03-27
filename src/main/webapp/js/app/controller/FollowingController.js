define([
    "app/grumbler",
    "app/resource/Account",
    "app/resource/Status",
    "app/service/UserService"
],
function(grumbler) {

    return grumbler.controller("FollowingController", ["$scope", "Account", "UserService",
        function($scope, Account, UserService) {
            $scope.unfollow = function (accountId) {
                Account.unfollow(accountId).$promise.then(function () {
                    $scope.loadFollowings();
                });
            };
            
            $scope.loadFollowings = function () {
                if (UserService.account) {
                    Account.following({id:UserService.account.id}).$promise.then(function(accounts){
                        $scope.following = accounts;
                    });
                }
            };
            
            $scope.loadFollowings();
            $scope.$on(UserService.CHANGE_BROADCAST, $scope.loadFollowings);
        }]);
});

