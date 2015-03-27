define(['app/grumbler'],
function(grumbler) {
    grumbler.factory('Status', ['$resource', function($resource) {
            var Status = $resource('/grumbler/status', {}, {
                post: {method: 'POST', params: { }}
//                        get: {method: 'GET', params: { id: 'id' }, isArray: false}
            });
            return Status;
        }]);
});