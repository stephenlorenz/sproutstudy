'use strict';

angular.module('sproutStudyApp')
  .directive('custodialAgreementDirective', function () {
    return {
      templateUrl: frontOfficeResourceBase + 'views/custodialAgreement.html',
      restrict: 'E',
      replace: true
    };
  });
