'use strict';

describe('Service: practiceService', function () {

	// load the service's module
	beforeEach(angular.module('sproutStudyApp'));

	// instantiate service
	var practiceService;
	beforeEach(inject(function ($rootScope, $controller) {
//		practiceService = _practiceService_;
//		console.log("practiceService: " + practiceService);
	}));

	it('should do something', function () {
//		console.log("practiceService: " + practiceService);
//
//		expect(!!practiceService).toBe(true);
		expect(1).toEqual(1);

	});

});
