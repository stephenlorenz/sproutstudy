'use strict';

describe('Directive: custodialAgreementDirective', function () {
  beforeEach(module('sproutStudyApp'));

  var element;

  it('should make hidden element visible', inject(function ($rootScope, $compile) {
    element = angular.element('<custodial-agreement-directive></custodial-agreement-directive>');
    element = $compile(element)($rootScope);
    expect(element.text()).toBe('this is the custodialAgreementDirective directive');
  }));
});
