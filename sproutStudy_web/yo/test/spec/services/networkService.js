'use strict';

describe('Service: networkService', function () {

  // load the service's module
  beforeEach(module('sproutStudyApp'));

  // instantiate service
  var networkService;
  beforeEach(inject(function (_networkService_) {
    networkService = _networkService_;
  }));

  it('should do something', function () {
    expect(!!networkService).toBe(true);
  });

});
