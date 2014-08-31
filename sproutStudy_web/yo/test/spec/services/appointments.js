'use strict';

describe('Service: appointments', function () {

  // load the service's module
  beforeEach(module('sproutStudyApp'));

  // instantiate service
  var appointments;
  beforeEach(inject(function (_appointments_) {
    appointments = _appointments_;
  }));

  it('should do something', function () {
    expect(!!appointments).toBe(true);
  });

});
