describe('systemCtrl', function(){
	//我们会在测试中使用这个scope
	var scope,systemService,httpBackend;

	//模拟我们的FolController模块并注入我们自己的依赖
	beforeEach(angular.mock.module('FolController'));
	beforeEach(angular.mock.module('FolService'));

	//模拟Controller，并且包含 $rootScope 和 $controller
	beforeEach(inject(function(SystemService,CommonService,_$httpBackend_, $rootScope, $controller) {
        httpBackend = _$httpBackend_;
        scope = $rootScope.$new();
        systemService= SystemService;
 
        createController = $controller('systemCtrl', {
                '$scope': scope,
                'SystemService':systemService
        });

        httpBackend.expectPOST(SERVER_URL+'system/systemList')
            .respond({
                "result": true
            });

        systemService.systemPoRoMain({},SERVER_URL+'system/systemList',function(data){
			scope.data=data;
		},function(data){
			scope.data=data;
		});

    }));
 
	// 测试从这里开始
	it('post url system/systemList xhr', function(){
		httpBackend.flush();
		expect(scope.data.result).toBe(true);
//		expect(scope.data.availAmount).toBe(true);
//		expect(scope.data.dealType).toBe(true);
//		expect(scope.data.dealerCode).toBe(true);
//		expect(scope.data.dealerName).toBe(true);
//		expect(scope.data.frozenAmount).toBe(true);
	});
});