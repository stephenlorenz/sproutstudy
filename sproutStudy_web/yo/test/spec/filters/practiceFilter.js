'use strict';

describe('Filter: practiceFilter', function () {

  // load the filter's module
  beforeEach(module('sproutStudyApp'));

  // initialize a new instance of the filter before each test
  var practiceFilter;
  beforeEach(inject(function ($filter) {
    practiceFilter = $filter('practiceFilter');
  }));

  it('should return the input prefixed with "practiceFilter filter:"', function () {
    var text = 'angularjs';
    expect(practiceFilter(text)).toBe('practiceFilter filter: ' + text);
  });

});
