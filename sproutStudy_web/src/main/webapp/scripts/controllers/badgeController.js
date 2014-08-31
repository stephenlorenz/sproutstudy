'use strict';

angular.module('sproutStudyApp')
    .controller('badgeCtrl', function ($scope, enrollmentService, formsService) {
        enrollmentService.getEnrollmentBadge({id: $scope.appointment.mrn + "@mrn.mgh.harvard.edu", groupId: $scope.appointment.ihsGroupCode}, function(data) {
            var badge = data.result;

//            console.log("enrollmentService.getEnrollmentBadge...");

            $scope.appointment.activateInd = false;
            $scope.appointment.verifyInd = false;

            var enableAccountActivation = false;

            var minutesBeforeAppointment = 60;

            var date = new Date($scope.appointment.appointmentTime - (minutesBeforeAppointment * 60 * 1000));
            if (date < new Date()) enableAccountActivation = true;

//            console.log("badge: " + badge);
            $scope.appointment.badge = "images/";
            switch (badge) {
                case null:
                    $scope.appointment.badge += "ihs_gray.gif";
                    break;
                case '0':
                    formsService.isPatientVerified({mrn: $scope.appointment.mrn}, function(data) {
                        if (data.value == 'true') {
                            if (enableAccountActivation) $scope.appointment.activateInd = true;
                        } else {
                            formsService.isPatientAssertive({mrn: $scope.appointment.mrn}, function(data) {
                                if (data.value == 'true') $scope.appointment.verifyInd = true;
                            });
                        }
                    });
                    $scope.appointment.badge += "ihs_gray.gif";
                    break;
                case '1':
                    formsService.isPatientVerified({mrn: $scope.appointment.mrn}, function(data) {
                        if (data.value == 'true') {
                            if (enableAccountActivation) $scope.appointment.activateInd = true;
                        } else {
                            formsService.isPatientAssertive({mrn: $scope.appointment.mrn}, function(data) {
                                if (data.value == 'true') $scope.appointment.verifyInd = true;
                            });
                        }
                    });
                    $scope.appointment.badge += "ihs_yellow.gif";
                    break;
                case '2':
                    $scope.appointment.badge += "ihs_green.gif";
                    break;
                default:
                    $scope.appointment.badge += "ihs_gray.gif";
                    break;
            }
        });

        formsService.getForms({schema: "MGH", mrn: $scope.appointment.mrn, practiceId: $scope.appointment.practiceId}, function(data) {
            $scope.appointment.formCount = -1;
            if (data != null && data.length > 0) {
                $scope.appointment.formCount = data.length;
                $scope.appointment.forms = data;
            } else {
                $scope.appointment.formCount = 0;
            }
        });
    });
