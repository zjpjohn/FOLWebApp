describe('statementCtrl', function(){
	//我们会在测试中使用这个scope
	var scope,statementService,httpBackend;

	//模拟我们的FolController模块并注入我们自己的依赖
	beforeEach(angular.mock.module('FolController'));
	beforeEach(angular.mock.module('FolService'));

	//模拟Controller，并且包含 $rootScope 和 $controller
	beforeEach(inject(function(StatementService,CommonService,_$httpBackend_, $rootScope, $controller) {
        httpBackend = _$httpBackend_;
        scope = $rootScope.$new();
        statementService= StatementService;
 
        createController = $controller('statementCtrl', {
                '$scope': scope,
                'StatementService':statementService
        });

        httpBackend.expectPOST(SERVER_URL+'statement/statementList')
            .respond({
                "result": true
            });

        statementService.StatementAmount({},SERVER_URL+'statement/statementList',function(data){
			scope.data=data;
		},function(data){
			scope.data=data;
		});

    }));
 
	// 测试从这里开始
	it('post url statement/statementList xhr', function(){
		httpBackend.flush();
		expect(scope.data.result).toBe(true);
//		expect(scope.data.availAmount).toBe(true);
//		expect(scope.data.dealType).toBe(true);
//		expect(scope.data.dealerCode).toBe(true);
//		expect(scope.data.dealerName).toBe(true);
//		expect(scope.data.frozenAmount).toBe(true);
	});
});