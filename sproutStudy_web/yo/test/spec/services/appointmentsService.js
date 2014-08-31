'use strict';

describe('Service: appointmentsService', function () {

  // load the service's module
  beforeEach(module('sproutStudyApp'));

  // instantiate service
  var appointmentsService;
  beforeEach(inject(function (_appointmentsService_) {
    appointmentsService = _appointmentsService_;
  }));

  it('should do something', function () {
    expect(!!appointmentsService).toBe(true);
  });

});
