'use strict';

describe('Service: formsService', function () {

  // load the service's module
  beforeEach(module('sproutStudyApp'));

  // instantiate service
  var formsService;
  beforeEach(inject(function (_formsService_) {
    formsService = _formsService_;
  }));

  it('should do something', function () {
    expect(!!formsService).toBe(true);
  });

});
