define(['app/grumbler'],
function(grumbler) {
    grumbler.factory('Authentication', ['$resource', function($resource) {
            var Authentication = $resource('/grumbler/auth', {}, {
                get: {method: 'GET', params: { }, isArray: false}
            });
            return Authentication;
        }]);
});