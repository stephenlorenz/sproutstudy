'use strict';

describe('Directive: enrollmentLetterDirective', function () {
  beforeEach(module('sproutStudyApp'));

  var element;

  it('should make hidden element visible', inject(function ($rootScope, $compile) {
    element = angular.element('<enrollment-letter-directive></enrollment-letter-directive>');
    element = $compile(element)($rootScope);
    expect(element.text()).toBe('this is the enrollmentLetterDirective directive');
  }));
});
