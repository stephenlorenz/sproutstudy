'use strict';

angular.module('sproutStudyApp')
    .directive('formDeliveryExpDateDirective', function () {
        return function (scope, element, attrs) {

            element.bind("change", function(e) {
                if (scope.publication !== undefined) scope.publication.expirationDateValid = true;

                if (element.val() !== undefined && element.val().length > 0) {
                    if (element.val().length < 10) {
                        scope.publication.expirationDateValid = false;
                        scope.$apply();
                        return false;
                    }

                    if (element.val().match(/^\d\d?\/\d\d?\/\d\d\d\d$/) == false) {
                        scope.publication.expirationDateValid = false;
                        scope.$apply();
                        return false;
                    }

                    var dateParts = element.val().split("/");
                    var testDate = new Date(dateParts[2], dateParts[0] - 1, dateParts[1]);

                    if (testDate instanceof Date) {
                        var today = new Date();
                        today.setHours(0,0,0,0);
                        if (testDate < today) {
                            scope.publication.expirationDateValid = false;
                            scope.$apply();
                        } else {
                            scope.publication.expirationDateValid = true;
                            scope.$apply();
                        }
                    } else {
//                        console.log("element is not a date...");
                    }
                } else {
                    scope.publication.expirationDateValid = true;
                    scope.$apply();
                }


            });

            element.bind("keyup", function(e) {
                if (scope.publication !== undefined) scope.publication.expirationDateValid = true;

                if (element.val() !== undefined && element.val().length > 0) {
                    if (element.val().length < 10) {
                        scope.publication.expirationDateValid = false;
                        scope.$apply();
                        return false;
                    }

                    if (element.val().match(/^\d\d?\/\d\d?\/\d\d\d\d$/) == false) {
                        scope.publication.expirationDateValid = false;
                        scope.$apply();
                        return false;
                    }

                    var dateParts = element.val().split("/");
                    var testDate = new Date(dateParts[2], dateParts[0] - 1, dateParts[1]);

                    if (testDate instanceof Date) {
                        var today = new Date();
                        today.setHours(0,0,0,0);
                        if (testDate < today) {
                            scope.publication.expirationDateValid = false;
                            scope.$apply();
                        } else {
                            scope.publication.expirationDateValid = true;
                            scope.$apply();
                        }
                    } else {
//                        console.log("element is not a date...");
                    }
                } else {
                    scope.publication.expirationDateValid = true;
                    scope.$apply();
                }

            });

        };
    });
