'use strict';

angular.module('sproutStudyApp')
    .directive('preventDefault', function () {
        return {
        	controller: function (scope, element, attrs) {
                $(element).click(function (event) {
                    event.preventDefault();
                });
        	},
            restrict: 'E',
            replace: true

        }
        
    }
);
