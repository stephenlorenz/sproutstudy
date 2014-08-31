'use strict';

angular.module('sproutStudyApp')
    .directive('enrollmentLetterDirective', function () {
        return {
            template: frontOfficeResourceBase + 'views/enrollmentLetter.html',
            restrict: 'E',
            replace: true
        };
    });
