/**
 * ShenWeiWei
 */

FolService.factory('CommonService', ['$http', 'promiseTracker', '$rootScope', '$timeout', '$q', function($http, promiseTracker, $rootScope, $timeout, $q) {
    var loadingTacker = promiseTracker();
    return {
        post: function(data, requestUrl, callback, errorCallback) {
            var canceller = $q.defer(),
                requestDone = false;

            var promise = $http({
                method: 'post',
                url: requestUrl,
                data: data,
                timeout: canceller.promise
            });

            promise.then(done, fail);

            function done(resp) {
                requestDone = true;
                if (resp.config.timeout.$$state.status === 1) {
                    resp.state = STATE.TIMEOUT
                    typeof errorCallback === 'function' && errorCallback(resp);
                }

                if (resp.data.length > 1) {
                    var data = resp.data[0];
                    var response_info = resp.data[1];
                    console.log(response_info);

                    if (response_info.state === STATE.SUCCESS) {
                        typeof callback === 'function' && callback(data, response_info);
                    } else {
                        typeof errorCallback === 'function' && errorCallback(data, response_info);
                    }
                } else {
                    var response_info = resp.data;

                    if (response_info.state === STATE.SUCCESS) {
                        typeof callback === 'function' && callback(response_info);
                    } else {
                        typeof errorCallback === 'function' && errorCallback(response_info);
                    }
                }
            }

            function fail(resp) {
                /*	var data = resp.data[0];
                	var response_info = resp.data[1];
                	typeof errorCallback === 'function' && errorCallback(data,response_info);*/
                requestDone = true;
                var data = null,
                    response_info = null;
                console.log(resp);

                if (!folUtil.isNull(resp.data)) {
                    if (resp.data.length > 1) {
                        data = resp.data[0];
                        response_info = resp.data[1];
                    } else {
                        response_info = resp.data;
                    }
                } else {
                    if (!folUtil.isNull(resp.config.timeout) && resp.config.timeout.$$state.value === 'timeout') {
                        response_info = { state: STATE.TIMEOUT };
                    }
                }

                typeof errorCallback === 'function' && errorCallback(response_info);
            }
            $timeout(function() {
                if (!requestDone) {
                    canceller.resolve('timeout');
                }
            }, ACTION_TIMEOUT);
        },
        query: function(requestUrl, callback, errorCallback) {
            var canceller = $q.defer(),
                requestDone = false;

            var promise = $http({
                method: 'post',
                url: requestUrl,
                timeout: canceller.promise
            });

            promise.then(done, fail);

            function done(resp) {
                requestDone = true;
                if (resp.data.length > 1) {
                    var data = resp.data[0];
                    var response_info = resp.data[1];
                    console.log(response_info);

                    if (response_info.state === STATE.SUCCESS) {
                        typeof callback === 'function' && callback(data, response_info);
                    } else {
                        typeof errorCallback === 'function' && errorCallback(data, response_info);
                    }
                } else {
                    var response_info = resp.data;

                    if (response_info.state === STATE.SUCCESS) {
                        typeof callback === 'function' && callback(response_info);
                    } else {
                        typeof errorCallback === 'function' && errorCallback(response_info);
                    }
                }
            }

            function fail(resp) {
                requestDone = true;
                var data = null,
                    response_info = null;

                if (!folUtil.isNull(resp.data)) {
                    if (resp.data.length > 1) {
                        data = resp.data[0];
                        response_info = resp.data[1];
                    } else {
                        response_info = resp.data;
                    }
                } else {
                    if (!folUtil.isNull(resp.config.timeout) && resp.config.timeout.$$state.value === 'timeout') {
                        response_info = { state: STATE.TIMEOUT };
                    }
                }

                typeof errorCallback === 'function' && errorCallback(response_info);
            }

            loadingTacker.addPromise(promise);

            $timeout(function() {
                if (!requestDone) {
                    canceller.resolve('timeout');
                }
            }, ACTION_TIMEOUT);
        },
        getJSON: function(requestUrl, callback, errorCallback) {
            var promise = $http({
                method: 'get',
                url: requestUrl
            });

            promise.then(done, fail);

            function done(resp) {
                var data = resp.data;
                typeof callback === 'function' && callback(data);
            }

            function fail(resp) {
                var data = resp.data;
                typeof errorCallback === 'function' && errorCallback(data);
            }
        },
        get: function(requestUrl, callback, errorCallback) {
			var canceller = $q.defer(),
                requestDone = false;
				
            var promise = $http({
                method: 'get',
                url: requestUrl,
                timeout: canceller.promise
            });

            promise.then(done, fail);

            function done(resp) {
                requestDone = true;
                if (resp.config.timeout.$$state.status === 1) {
                    resp.state = STATE.TIMEOUT
                    typeof errorCallback === 'function' && errorCallback(resp);
                }

                if (resp.data.length > 1) {
                    var data = resp.data[0];
                    var response_info = resp.data[1];
                    console.log(response_info);

                    if (response_info.state === STATE.SUCCESS) {
                        typeof callback === 'function' && callback(data, response_info);
                    } else {
                        typeof errorCallback === 'function' && errorCallback(data, response_info);
                    }
                } else {
                    var response_info = resp.data;

                    if (response_info.state === STATE.SUCCESS) {
                        typeof callback === 'function' && callback(response_info);
                    } else {
                        typeof errorCallback === 'function' && errorCallback(response_info);
                    }
                }
            }

            function fail(resp) {
                /*	var data = resp.data[0];
                	var response_info = resp.data[1];
                	typeof errorCallback === 'function' && errorCallback(data,response_info);*/
                requestDone = true;
                var data = null,
                    response_info = null;
                console.log(resp);

                if (!folUtil.isNull(resp.data)) {
                    if (resp.data.length > 1) {
                        data = resp.data[0];
                        response_info = resp.data[1];
                    } else {
                        response_info = resp.data;
                    }
                } else {
                    if (!folUtil.isNull(resp.config.timeout) && resp.config.timeout.$$state.value === 'timeout') {
                        response_info = { state: STATE.TIMEOUT };
                    }
                }

                typeof errorCallback === 'function' && errorCallback(response_info);
            }
            $timeout(function() {
                if (!requestDone) {
                    canceller.resolve('timeout');
                }
            }, ACTION_TIMEOUT);
        },
        crosPostQuery: function(requestUrl, callback, errorCallback) {
            var promise = $http({
                method: 'POST',
                url: requestUrl,
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                }
            });

            promise.then(done, fail);

            function done(resp) {
                var data = resp.data[0];
                var response_info = resp.data[1];
                console.log(response_info);

                if (response_info.state === STATE.SUCCESS) {
                    typeof callback === 'function' && callback(data, response_info);
                } else {
                    typeof errorCallback === 'function' && errorCallback(data, response_info);
                }
            }

            function fail(resp) {
                var data = resp.data[0];
                var response_info = resp.data[1];
                typeof errorCallback === 'function' && errorCallback(data, response_info);
            }
        },
        crosPost: function(data, requestUrl, callback, errorCallback) {
            var promise = $http({
                method: 'GET',
                url: requestUrl,
                params: {
                    jsonParams: data
                },
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                }
            });

            promise.then(done, fail);

            function done(resp) {
                var data = resp.data[0];
                var response_info = resp.data[1];
                console.log(response_info);

                if (response_info.state === STATE.SUCCESS) {
                    typeof callback === 'function' && callback(data, response_info);
                } else {
                    typeof errorCallback === 'function' && errorCallback(data, response_info);
                }
            }

            function fail(resp) {
                var data = resp.data[0];
                var response_info = resp.data[1];
                typeof errorCallback === 'function' && errorCallback(data, response_info);
            }
        },
    };
}]);