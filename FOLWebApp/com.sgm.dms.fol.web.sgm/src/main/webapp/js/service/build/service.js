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
/**
 * 银行信息service
 */

FolService.factory('BankInfoService',['CommonService',function(CommonService){
	return {
		query:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},findAll:function(url,callback,errorCallback){
			CommonService.query(url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		}
	};
}]);
/**
 * 字典service
 */
FolService.factory('CodeService',['CommonService',function(CommonService){
	//查询字典值
	return {
		findCodeTypeNames:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		}
	};
}]);
/**
 * 文件上传的Service
 */
FolService.factory('uploadFileService',['CommonService',function(CommonService){
	return {
		openUploadFile:function(url,callback,errorCallback){
			CommonService.getJSON(url,function(data){
				typeof callback==='function' && callback(data);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},
		openDownloadFile:function(url,callback,errorCallback){
			CommonService.getJSON(url,function(data){
				typeof callback==='function' && callback(data);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},
		getToken:function(url,callback,errorCallback){
			CommonService.getJSON(url,function(data){
				typeof callback==='function' && callback(data);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},
		saveFile:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(reponse_info){
				typeof callback==='function' && callback(reponse_info);
			},function(reponse_info){
				typeof errorCallback==='function' && errorCallback(reponse_info);
			});
		}
	};
}]);
/**
 * 经销商service
 */
FolService.factory('DealerInfoService', ['CommonService', function(CommonService) {
    //查询字典值
    return {
        query: function(data, url, callback, errorCallback) {
            CommonService.post(data, url, function(data, response_info) {
                typeof callback === 'function' && callback(data, response_info);
            }, function(response_info) {
                typeof errorCallback === 'function' && errorCallback(response_info);
            });
        }
    };
}]);
/**
 *  银行银票额度维护service
 */
FolService.factory('BankSpecialDealerMaintainService',['CommonService',function(CommonService){
	return {
		query:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(data,response_info){
				typeof errorCallback==='function' && errorCallback(data,response_info);
			});
		},addBankSpecialDealer:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(data,response_info){
				typeof errorCallback==='function' && errorCallback(data,response_info);
			});
		},confirmAudit:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(data,response_info){
				typeof errorCallback==='function' && errorCallback(data,response_info);
			});
		},getBankInfoAllList:function(url,callback,errorCallback){
			CommonService.query(url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(data,response_info){
				typeof errorCallback==='function' && errorCallback(data,response_info);
			});
		},deleteSpecialDealer:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(data,response_info){
				typeof errorCallback==='function' && errorCallback(data,response_info);
			});
		}
	};
}]);
/**
 *  银行银票额度维护service
 */
FolService.factory('BankTicketDeadLineMaintainService',['CommonService',function(CommonService){
	return {
		query:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(data,response_info){
				typeof errorCallback==='function' && errorCallback(data,response_info);
			});
		},addBankTicketDeadLine:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(response_info){
				typeof callback==='function' && callback(response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},deleteBankTicketDeadLine:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(response_info){
				typeof callback==='function' && callback(response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},updateDeadLineWithBrand:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(response_info){
				typeof callback==='function' && callback(response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},updateDeadLineWithSpecialDealer:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(response_info){
				typeof callback==='function' && callback(response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		}
	};
}]);
/**
 * 银票限额Service
 */
FolService.factory('BankTicketLimitAmountMaintainService',['CommonService',function(CommonService){
	return {
		query:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(data,response_info){
				typeof errorCallback==='function' && errorCallback(data,response_info);
			});
		},addBankTicketLimitAmount:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(response_info){
				typeof callback==='function' && callback(response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},deleteBankTicketLimitAmount:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(response_info){
				typeof callback==='function' && callback(response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},updateBankTicketLimitAmount:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(response_info){
				typeof callback==='function' && callback(response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},updateBankTicketLimitAmounttWithSpecialDealer:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(response_info){
				typeof callback==='function' && callback(response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		}
	};
}]);
/**
 *  银行银票额度维护service
 */
FolService.factory('BankTicketLimitMaintainService',['CommonService',function(CommonService){
	return {
		query:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(data,response_info){
				typeof errorCallback==='function' && errorCallback(data,response_info);
			});
		},insert:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(data,response_info){
				typeof errorCallback==='function' && errorCallback(data,response_info);
			});
		},update:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(data,response_info){
				typeof errorCallback==='function' && errorCallback(data,response_info);
			});
		},addBankTicketLimit:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(response_info){
				typeof callback==='function' && callback(response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},updateBankTicketLimit:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(response_info){
				typeof callback==='function' && callback(response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		}
	};
}]);
/**
 *  票据贴息年化利率维护service
 */
FolService.factory('TicketInterestRateMaintainService',['CommonService',function(CommonService){
	return {
		queryCur:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(data,response_info);
			});
		},
		queryHis:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(data,response_info);
			});
		},
		confirm:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(data,response_info);
			});
		},update:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(data,response_info);
			});
		},
		checkdata:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(data,response_info);
			});
		},
	}
}]);
//银行银票审核
FolService.factory('BankBankTicketLimitVerifyService',['CommonService',function(CommonService){
	return {
		query:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},
		reject:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},
		auditSuccess:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},
		findCodeTypeNames:function(url,callback,errorCallback){
			CommonService.query(url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},
	}
}]);
//银行特殊经销商审核
FolService.factory('BankSpecialDealerVerifyService',['CommonService',function(CommonService){
	
	return {
		query:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},
		reject:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},
		auditSuccess:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},
		findCodeTypeNames:function(url,callback,errorCallback){
			CommonService.query(url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},
	}
}]);
FolService.factory('BankTicketDeadLineVerifyService',['CommonService',function(CommonService){
	return {
		query:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},
		reject:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},
		auditSuccess:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},
		rejectBrand:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},
		auditSuccessBrand:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},
		findCodeTypeNames:function(url,callback,errorCallback){
			CommonService.query(url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},
	}
}]);
FolService.factory('BankTicketLimitService',['CommonService',function(CommonService){
	return {
		query:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},
		reject:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},
		auditSuccess:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},
		rejectBrand:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},
		auditSuccessBrand:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(data,response_info){
				typeof errorCallback==='function' && errorCallback(data,response_info);
			});
		},
		findCodeTypeNames:function(url,callback,errorCallback){
			CommonService.query(url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(data,response_info){
				typeof errorCallback==='function' && errorCallback(data,response_info);
			});
		},
	}
}]);
/**
 * 票据贴息年化利率维审核service
 */
FolService.factory('TicketInterestRateAuditService',['CommonService',function(CommonService){
	return {
		query:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(data,response_info);
			});
		},
		reject:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(data,response_info);
			});
		},
		auditSuccess:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},
		checkdata:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},
	}
}]);
/**
 * 
 */

FolService.factory('BankTicketDeatilService',['CommonService',function(CommonService){
	return {
		query:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		}
	};
}]);
/**
 * 
 */

FolService.factory('BankTicketInterestService',['CommonService',function(CommonService){
	return {
		query:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		}
	};
}]);
//银票贴息明细
FolService.factory('BankTicketInterestDeatilService',['CommonService',function(CommonService){
	
	return {
		query:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},
	}
}]);
/**
 * 
 */

FolService.factory('BankTicketInterestInventoryService',['CommonService',function(CommonService){
	return {
		query:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},InterestInventoryUpload:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},exportFile:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},importFile:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},getToken:function(url,callback,errorCallback){
			CommonService.getJSON(url,function(data){
				typeof callback==='function' && callback(data);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},issue:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(response_info){
				typeof callback==='function' && callback(response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		}
	};
}]);
/**
 * 银票异常清单Service
 * shenweiwei
 */
FolService.factory('BankTicketExceptionInventoryService',['CommonService',function(CommonService){
	return {
		query:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},
		getToken:function(url,callback,errorCallback){
			CommonService.getJSON(url,function(data){
				typeof callback==='function' && callback(data);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		}
	};
}]);
/**
 * 奖金定义service
 */
FolService.factory('BonusDefinitionSerivce',['CommonService',function(CommonService){
	return {
		queryBonusDefinition:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},
		addBonusDefinition:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},
		updateBonusDefinition:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		}
	};
}]);
/**
 * 奖金发放版本上传service
 */
FolService.factory('BonusIssueService',['CommonService',function(CommonService){
	return {
		query:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},issue:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},back:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},bonusIssueQuery:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},getToken:function(url,callback,errorCallback){
			CommonService.getJSON(url,function(data){
				typeof callback==='function' && callback(data);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		}
	}
}]);
/**
 * 奖金发放版本上传service
 */
FolService.factory('BonusIssueVersionFileAuditService',['CommonService',function(CommonService){
	return {
		audit:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(response_info){
				typeof callback==='function' && callback(response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},reject:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},findCodeTypeNames:function(url,callback,errorCallback){
			CommonService.query(url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},query:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},getToken:function(url,callback,errorCallback){
			CommonService.getJSON(url,function(data){
				typeof callback==='function' && callback(data);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		}
	}
}]);
/**
 * 奖金发放版本上传service
 */
FolService.factory('BonusIssueVersionUploadService',['CommonService',function(CommonService){
	return {
		issueVersionQuery:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},cancel:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},findCodeTypeNames:function(url,callback,errorCallback){
			CommonService.query(url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},confirmAudit:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},getToken:function(url,callback,errorCallback){
			CommonService.getJSON(url,function(data){
				typeof callback==='function' && callback(data);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		}
	}
}]);
/**
 * 奖金重处理Service
 */
FolService.factory('BonusReastDisposeService',['CommonService',function(CommonService){
	return {
		query:function(data,url, callback, errorCallback) {
			CommonService.post(data, url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},
		reastDispose:function(data,url, callback, errorCallback) {
			CommonService.post(data, url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},reject:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},getToken:function(url,callback,errorCallback){
			CommonService.getJSON(url,function(data){
				typeof callback==='function' && callback(data);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		}
	};
}]);
/*
 * 奖金对账管理service
 */
FolService.factory('BonusService',['CommonService',function(CommonService){
	return {
		findBonusAmount:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},
		findCodeTypeNames:function(url,callback,errorCallback){
			CommonService.query(url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},
		findBonusMonthDeatil:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},
		findbonusAmountReconcile:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},
		findBonusAmountCollect:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},
		getToken:function(url,callback,errorCallback){
			CommonService.getJSON(url,function(data){
				typeof callback==='function' && callback(data);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},
	};
	
}]);
FolService.factory('AuthorityService',['CommonService',function(CommonService){
	var roleData = null;
	//系统角色维护
	return {
		//获取
		get: function(url, callback, errorCallback) {
			CommonService.get(url,function(data){
				typeof callback==='function' && callback(data);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},
		//新增
		post: function(data, url, callback, errorCallback) {
			CommonService.post(data, url,function(data){
				typeof callback==='function' && callback(data);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},
		//修改--CommmonService中没有PUT METHOD
		put: function(data, url, callback, errorCallback) {
			CommonService.post(data, url,function(data){
				typeof callback==='function' && callback(data);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},
		//删除--CommmonService中没有DELETE METHOD
		del: function(data, url, callback, errorCallback) {
			CommonService.post(data, url,function(data){
				typeof callback==='function' && callback(data);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},
		setRoleData:function(request) {
			if(request!=null){
				roleData={
					roleId :request.roleId,
					roleName:request.roleName,
					roleDesc:request.roleDesc,
					roleStatus:request.roleStatus,
					roleType:request.roleType
				};
			}else{
				positionData=null;
			}
		},
		getRoleData:function() {
			return roleData;
		},
		systemRoRiMain:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data){
				typeof callback==='function' && callback(data);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},
	systemRoRiMains:function(data,url,callback,errorCallback){
		CommonService.post(data,url,function(data){
			typeof callback==='function' && callback(data);
		},function(response_info){
			typeof errorCallback==='function' && errorCallback(response_info);
		});
	},
	systemRoRiMainss:function(url,callback,errorCallback){
		CommonService.query(url,function(data){
			typeof callback==='function' && callback(data);
		},function(response_info){
			typeof errorCallback==='function' && errorCallback(response_info);
		});
	},
	systemPoRoMainA:function(data,url,callback,errorCallback){
		CommonService.post(data,url,function(data){
			typeof callback==='function' && callback(data);
		},function(response_info){
			typeof errorCallback==='function' && errorCallback(response_info);
		});
	},
	systemRoRiMainsAd:function(data,url,callback,errorCallback){
		CommonService.post(data,url,function(data){
			typeof callback==='function' && callback(data);
		},function(response_info){
			typeof errorCallback==='function' && errorCallback(response_info);
		});
	},
	systemPoRoMainAdd:function(data,url,callback,errorCallback){
		CommonService.post(data,url,function(data){
			typeof callback==='function' && callback(data);
		},function(response_info){
			typeof errorCallback==='function' && errorCallback(response_info);
		});
	},
	delsystem:function(data,url,callback,errorCallback){
		CommonService.post(data,url,function(data){
			typeof callback==='function' && callback(data);
		},function(response_info){
			typeof errorCallback==='function' && errorCallback(response_info);
		});
	},
	findMenu:function(data,url,callback,errorCallback){
		CommonService.post(data,url,function(data){
			typeof callback==='function' && callback(data);
		},function(response_info){
			typeof errorCallback==='function' && errorCallback(response_info);
		});
	},
	checkdata:function(data,url,callback,errorCallback){
		CommonService.post(data,url,function(data){
			typeof callback==='function' && callback(data);
		},function(response_info){
			typeof errorCallback==='function' && errorCallback(response_info);
		});
	}
	};
}]);
FolService.factory('PersonRoleService',['CommonService',function(CommonService){
	var personData = null;
	//系统角色维护
	return {
		//获取
		get: function(url, callback, errorCallback) {
			CommonService.get(url,function(data){
				typeof callback==='function' && callback(data);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},
		//新增
		post: function(data, url, callback, errorCallback) {
			CommonService.post(data, url,function(data){
				typeof callback==='function' && callback(data);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},
		//修改--CommmonService中没有PUT METHOD
		put: function(data, url, callback, errorCallback) {
			CommonService.post(data, url,function(data){
				typeof callback==='function' && callback(data);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},
		//删除--CommmonService中没有DELETE METHOD
		del: function(data, url, callback, errorCallback) {
			CommonService.post(data, url,function(data){
				typeof callback==='function' && callback(data);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},
		setPersonData: function(request) {
			if(request!=null){
				personData={
					userAccount: request.userAccount,
					userName: request.userName,
					userType: request.userType,
					gender: request.gender,
					handPhone: request.handPhone,
					birthday: request.birthday,
					zipCode: request.zipCode,
					addr: request.addr,
					email: request.email,
					userStatus: request.userStatus
				};
			}else{
				personData=null;
			}
		},
		getPersonData:function() {
			return personData;
		},
		
		
		systemRoRiMainss:function(url,callback,errorCallback){
			CommonService.query(url,function(data){
				typeof callback==='function' && callback(data);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},
		findPersonRole:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data){
				typeof callback==='function' && callback(data);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},
		updataPersonRoles:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data){
				typeof callback==='function' && callback(data);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},
		updataPersonRole:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data){
				typeof callback==='function' && callback(data);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},
		checkdata:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data){
				typeof callback==='function' && callback(data);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		}
	};
}]);
FolService.factory('SystemService',['CommonService',function(CommonService){
	var positionData = null;
	//系统岗位角色维护
	return {
		//获取
		get: function(url, callback, errorCallback) {
			CommonService.get(url,function(data){
				typeof callback==='function' && callback(data);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},
		//新增
		post: function(data, url, callback, errorCallback) {
			CommonService.post(data, url,function(data){
				typeof callback==='function' && callback(data);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},
		//修改--CommmonService中没有PUT METHOD
		put: function(data, url, callback, errorCallback) {
			CommonService.post(data, url,function(data){
				typeof callback==='function' && callback(data);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},
		//删除--CommmonService中没有DELETE METHOD
		del: function(data, url, callback, errorCallback) {
			CommonService.post(data, url,function(data){
				typeof callback==='function' && callback(data);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},
		//
		setPositionData:function(request) {
			if(request!=null){
				positionData={
					positionId :request.positionId,
					positionName:request.positionName,
					positionDesc:request.positionDesc,
					status:request.status,
					positionType:request.positionType,
					dpPositionCode:request.dpPositionCode
				};
			}else{
				positionData=null;
			}
		},
		getPositionData:function() {
			return positionData;
		},
		
		
		systemPoRoMain:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data){
				typeof callback==='function' && callback(data);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		}, 
	//系统岗位角色设定
		systemPoRoMains:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data){
				typeof callback==='function' && callback(data);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},
		//系统岗位对应的角色修改
		systemPoRoMainA:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data){
				typeof callback==='function' && callback(data);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},
		/*test:function(url,callback,errorCallback){
			CommonService.crosPostQuery(url,function(data){
				typeof callback==='function' && callback(data);
			},function(data){
				typeof errorCallback==='function' && errorCallback(data);
			});
		}*/
		
		checkdata:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data){
				typeof callback==='function' && callback(data);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		}
	};
}]);
/**
 * UserService
 */
FolService.factory('UserService',['CommonService',function(CommonService){
	return {
		logout:function(url,callback,errorCallback){
			CommonService.post(url,function(data){
				typeof callback==='function' && callback(data);
			},function(resoponse){
				typeof errorCallback==='function' && errorCallback(resoponse);
			});
		}
	};
}]);
FolService.factory('RoleAuthorityService',['CommonService',function(CommonService){
	var roleData = null;
	//系统角色维护
	return {
		//获取
		get: function(url, callback, errorCallback) {
			CommonService.get(url,function(data){
				typeof callback==='function' && callback(data);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},
		//新增
		post: function(data, url, callback, errorCallback) {
			CommonService.post(data, url,function(data){
				typeof callback==='function' && callback(data);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},
		//修改--CommmonService中没有PUT METHOD
		put: function(data, url, callback, errorCallback) {
			CommonService.post(data, url,function(data){
				typeof callback==='function' && callback(data);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},
		//删除--CommmonService中没有DELETE METHOD
		del: function(data, url, callback, errorCallback) {
			CommonService.post(data, url,function(data){
				typeof callback==='function' && callback(data);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},
		setRoleData:function(request) {
			if(request!=null){
				roleData={
					methodId :request.methodId,
					clazz:request.clazz,
					method:request.method,
					url:request.url,
				};
			}else{
				positionData=null;
			}
		},
		getRoleData:function() {
			return roleData;
		},
		systemRoRiMain:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data){
				typeof callback==='function' && callback(data);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},
	systemRoRiMains:function(data,url,callback,errorCallback){
		CommonService.post(data,url,function(data){
			typeof callback==='function' && callback(data);
		},function(response_info){
			typeof errorCallback==='function' && errorCallback(response_info);
		});
	},
	systemRoRiMainss:function(url,callback,errorCallback){
		CommonService.query(url,function(data){
			typeof callback==='function' && callback(data);
		},function(response_info){
			typeof errorCallback==='function' && errorCallback(response_info);
		});
	},
	systemPoRoMainA:function(data,url,callback,errorCallback){
		CommonService.post(data,url,function(data){
			typeof callback==='function' && callback(data);
		},function(response_info){
			typeof errorCallback==='function' && errorCallback(response_info);
		});
	},
	systemRoRiMainsAd:function(data,url,callback,errorCallback){
		CommonService.post(data,url,function(data){
			typeof callback==='function' && callback(data);
		},function(response_info){
			typeof errorCallback==='function' && errorCallback(response_info);
		});
	},
	systemPoRoMainAdd:function(data,url,callback,errorCallback){
		CommonService.post(data,url,function(data){
			typeof callback==='function' && callback(data);
		},function(response_info){
			typeof errorCallback==='function' && errorCallback(response_info);
		});
	},
	delsystem:function(data,url,callback,errorCallback){
		CommonService.post(data,url,function(data){
			typeof callback==='function' && callback(data);
		},function(response_info){
			typeof errorCallback==='function' && errorCallback(response_info);
		});
	},
	findMenu:function(data,url,callback,errorCallback){
		CommonService.post(data,url,function(data){
			typeof callback==='function' && callback(data);
		},function(response_info){
			typeof errorCallback==='function' && errorCallback(response_info);
		});
	},
	checkdata:function(data,url,callback,errorCallback){
		CommonService.post(data,url,function(data){
			typeof callback==='function' && callback(data);
		},function(response_info){
			typeof errorCallback==='function' && errorCallback(response_info);
		});
	}
	};
}]);
FolService.factory('ReserveService',['CommonService',function(CommonService){
	return {
		findReserveAmount:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},
		findCodeTypeNames:function(url,callback,errorCallback){
			CommonService.query(url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},
		findChangeReserveAmount:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},
		findFreezeReserveAmount:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},
		findReserveMonthDeatil:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},
		findReserveAmountReconcile:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},
		getToken:function(url,callback,errorCallback){
			CommonService.getJSON(url,function(data){
				typeof callback==='function' && callback(data);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		}
	};
	
}]);
/**
 * 资金余额报表
 */
FolService.factory('FundBalanceStatementService',['CommonService',function(CommonService){
	return {
		query:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},
		exportFile:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},
		findCodeTypeNames:function(url,callback,errorCallback){
			CommonService.query(url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},
		getToken:function(url,callback,errorCallback){
			CommonService.getJSON(url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},
	}
}]);
/**
 * 客户对账单倒入service
 */

FolService.factory('DealerReconciliationImportService',['CommonService',function(CommonService){
	return {
		//查询对账单
		query:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},
		checkOut:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(response_info){
				typeof callback==='function' && callback(response_info);
			});
		},
		getToken:function(url,callback,errorCallback){
			CommonService.getJSON(url,function(data){
				typeof callback==='function' && callback(data);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},reconciliationStatusUpdate:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},
		reconciliationStatusUpdateWithPublish:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},reconciliationStatusUpdateWithCancelPublish:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},
//		明细查询
		ReconciliationSignQuery:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		}
	};
}]);
/**
 * 索赔发票状态查询
 */
FolService.factory('ChaimInvoiceStatusService',['CommonService',function(CommonService){
		return {
			query:function(data,url,callback,errorCallback){
				CommonService.post(data,url,function(data,response_info){
					typeof callback==='function' && callback(data,response_info);
				},function(response_info){
					typeof errorCallback==='function' && errorCallback(response_info);
				});
			},
			//重处理
			anew: function(data,url,callback,errorCallback){
				CommonService.post(data,url,function(data,response_info){
					typeof callback==='function' && callback(data,response_info);
				},function(response_info){
					typeof errorCallback==='function' && errorCallback(response_info);
				});
			},
			//保存
			save: function(data,url,callback,errorCallback){
				CommonService.post(data,url,function(data,response_info){
					typeof callback==='function' && callback(data,response_info);
				},function(response_info){
					typeof errorCallback==='function' && errorCallback(response_info);
				});
			},
			//同意
			agree: function(data,url,callback,errorCallback){
				CommonService.post(data,url,function(data,response_info){
					typeof callback==='function' && callback(data,response_info);
				},function(response_info){
					typeof errorCallback==='function' && errorCallback(response_info);
				});
			},
			//退回
			back: function(data,url,callback,errorCallback){
				CommonService.post(data,url,function(data,response_info){
					typeof callback==='function' && callback(data,response_info);
				},function(response_info){
					typeof errorCallback==='function' && errorCallback(response_info);
				});
			},
			//查询明细
			queryDeatil: function(data,url,callback,errorCallback){
				CommonService.post(data,url,function(data,response_info){
					typeof callback==='function' && callback(data,response_info);
				},function(response_info){
					typeof errorCallback==='function' && errorCallback(response_info);
				});
			},
			checkdata:function(data,url,callback,errorCallback){
				CommonService.post(data,url,function(data,response_info){
					typeof callback==='function' && callback(data,response_info);
				},function(response_info){
					typeof errorCallback==='function' && errorCallback(data,response_info);
				});
			},
		};
	}]);
/**
 * 快递单号扫描发票
 */
FolService.factory('ExpressScanInvoiceService',['CommonService',function(CommonService){
		return {
			query:function(data,url,callback,errorCallback){
				CommonService.post(data,url,function(data,response_info){
					typeof callback==='function' && callback(data,response_info);
				},function(response_info){
					typeof errorCallback==='function' && errorCallback(response_info);
				});
			},
			//重做
			agree:function(data,url,callback,errorCallback){
				CommonService.post(data,url,function(data,response_info){
					typeof callback==='function' && callback(data,response_info);
				},function(response_info){
					typeof errorCallback==='function' && errorCallback(response_info);
				});
			},
			//退回
			back: function(data,url,callback,errorCallback){
				CommonService.post(data,url,function(data,response_info){
					typeof callback==='function' && callback(data,response_info);
				},function(response_info){
					typeof errorCallback==='function' && errorCallback(response_info);
				});
			},

		};
	}]);
/**
 * SAP每日财务凭证查询
 */
FolService.factory('FinancialCertificateService',['CommonService',function(CommonService){
	return {
		query:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},
		getToken:function(url,callback,errorCallback){
			CommonService.getJSON(url,function(data){
				typeof callback==='function' && callback(data);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		}
	};
	
}]);
/**
 * FOL-SAP接口状态查询
 */

FolService.factory('InterfaceStatusService',['CommonService',function(CommonService){
	return {
		query:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		}
	};
}]);
/**
 * 经销商提交超时未处理发票查询
 */
FolService.factory('OvertimeInvoiceService',['CommonService',function(CommonService){
	return {
		query:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},
	};
	
}]);
FolService.factory('ReturnAllowanceManagerService', ['CommonService', function(CommonService) {
    //折让单扫描
    return {
        query: function(data, url, callback, errorCallback) {
            CommonService.post(data, url, function(data) {
                typeof callback === 'function' && callback(data);
            }, function(response_info) {
                typeof errorCallback === 'function' && errorCallback(response_info);
            });
        },
        report: function(data, url, callback, errorCallback) {
            CommonService.post(data, url, function(data) {
                typeof callback === 'function' && callback(data);
            }, function(response_info) {
                typeof errorCallback === 'function' && errorCallback(response_info);
            });
        },
        reject: function(data, url, callback, errorCallback) {
            CommonService.post(data, url, function(data) {
                typeof callback === 'function' && callback(data);
            }, function(response_info) {
                typeof errorCallback === 'function' && errorCallback(response_info);
            });
        },
        getToken: function(url, callback, errorCallback) {
            CommonService.getJSON(url, function(data) {
                typeof callback === 'function' && callback(data);
            }, function(response_info) {
                typeof errorCallback === 'function' && errorCallback(response_info);
            });
        },
        deatilQuery: function(data, url, callback, errorCallback) {
            CommonService.post(data, url, function(data) {
                typeof callback === 'function' && callback(data);
            }, function(response_info) {
                typeof errorCallback === 'function' && errorCallback(response_info);
            });
        },
        downlandXmlInfo: function(url, callback, errorCallback) {
            CommonService.get(url, function(data, response_info) {
                typeof callback === 'function' && callback(data, response_info);
            }, function(data, response_info) {
                typeof errorCallback === 'function' && errorCallback(data, response_info);
            });
        }
    };
}]);