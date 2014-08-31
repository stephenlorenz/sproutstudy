'use strict';

angular.module('sproutStudyApp')
    .directive('patientViewDirective', function () {
        return function (scope, element, attrs) {
            scope.$watch(scope.form.value, function() {
//                console.log("inside patientViewDirective...  scope.viewName: " + scope.viewName);

                if (scope.viewName !== undefined && scope.viewName == 'patient') {
                    $(".goto-step").attr("href", "#/patient");
                    $(".frontoffice-patient-view-form").bind("DOMSubtreeModified", function() {
//                    console.log("tree changed");
                        $(".goto-step").attr("href", "#/patient");
                    });
                }

            });
        };
    });
