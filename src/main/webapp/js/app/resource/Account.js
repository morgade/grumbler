define(['app/grumbler'],
        function(grumbler) {

            grumbler.factory('Account', ['$resource', function($resource) {
                    var Usuario = $resource('/grumbler/account/:id/:verb', {}, {
                        accounts: {method: 'GET', params: { }, isArray: true},
                        statuses: {method: 'GET', params: { verb: "status" }, isArray: true},
                        get: {method: 'GET', params: { id: 'id' }, isArray: false},
                        follow: {method: 'POST', params: { verb: 'follow' }},
                        unfollow: {method: 'POST', params: { verb: 'unfollow' }},
                        followers: {method: 'GET', params: { verb: 'followers' }, isArray: true},
                        following: {method: 'GET', params: { verb: 'following' }, isArray: true},
                        timeline: {method: 'GET', params: { verb: 'timeline' }, isArray: true}
                    });
                    return Usuario;
                }]);

        });