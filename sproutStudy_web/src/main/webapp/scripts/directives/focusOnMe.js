'use strict';

angular.module('sproutStudyApp')
    .directive('focusOnMe', ['$timeout', '$parse',
        function($timeout, $parse) {
            return {
                //scope: true,   // optionally create a child scope
                link: function(scope, element, attrs) {
                    var model = $parse(attrs.focusOnMe);
                    scope.$watch(model, function(value) {
                        // console.log('value=',value);
                        if(value === true) {
                            $timeout(function() {
                                element[0].focus();
                            });
                        }
                    });
                }
            };
        }
    ]);
