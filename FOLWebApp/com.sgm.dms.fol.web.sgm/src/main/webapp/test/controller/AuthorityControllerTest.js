describe('authorityCtrl', function(){
	//我们会在测试中使用这个scope
	var scope,authorityService,httpBackend;

	//模拟我们的FolController模块并注入我们自己的依赖
	beforeEach(angular.mock.module('FolController'));
	beforeEach(angular.mock.module('FolService'));

	//模拟Controller，并且包含 $rootScope 和 $controller
	beforeEach(inject(function(AuthorityService,CommonService,_$httpBackend_, $rootScope, $controller) {
        httpBackend = _$httpBackend_;
        scope = $rootScope.$new();
        authorityService= AuthorityService;
 
        createController = $controller('authorityCtrl', {
                '$scope': scope,
                'AuthorityService':authorityService
        });

        httpBackend.expectPOST(SERVER_URL+'authority/authorityList')
            .respond({
                "result": true
            });

        authorityService.systemRoRiMain({},SERVER_URL+'authority/authorityList',function(data){
			scope.data=data;
		},function(data){
			scope.data=data;
		});

    }));
 
	// 测试从这里开始
	it('post url authority/authorityList xhr', function(){
		httpBackend.flush();
		expect(scope.data.result).toBe(true);
//		expect(scope.data.availAmount).toBe(true);
//		expect(scope.data.dealType).toBe(true);
//		expect(scope.data.dealerCode).toBe(true);
//		expect(scope.data.dealerName).toBe(true);
//		expect(scope.data.frozenAmount).toBe(true);
	});
});