define([
    "app/grumbler",
    "app/resource/Account",
    "app/resource/Status",
    "app/service/UserService"
],
function(grumbler) {

    return grumbler.controller("TimelineController", ["$scope", "Account", "Status", "UserService",
        function($scope, Account, Status, UserService) {
            $scope.status = { 
                body: ""
            };
            
            $scope.post = function () {
                Status.post($scope.status).$promise.then(function () {
                    $scope.status.body = "";
                    $scope.loadTimeline();
                });
            };
            
            $scope.loadTimeline = function () {
                $scope.timeline = [];
                if (UserService.account) {
                    Account.timeline({id:UserService.account.id}).$promise.then(function(statuses){
                        $scope.timeline = statuses;
                    });
                }
            };
            
            $scope.loadTimeline();
            $scope.$on(UserService.CHANGE_BROADCAST, $scope.loadTimeline);
            
        }]);
});

