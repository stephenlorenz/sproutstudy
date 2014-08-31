'use strict';

describe('Service: enrollmentService', function () {

  // load the service's module
  beforeEach(module('sproutStudyApp'));

  // instantiate service
  var enrollmentService;
  beforeEach(inject(function (_enrollmentService_) {
    enrollmentService = _enrollmentService_;
  }));

  it('should do something', function () {
    expect(!!enrollmentService).toBe(true);
  });

});
