'use strict';

angular.module('sproutStudyApp')
    .controller('cohortController', function ($log, $scope, $location, cohortService, studyService, sessionService, cohortManagerService) {

        $scope.managedCohorts = undefined;

        cohortManagerService.getManagedCohorts({}, function(data) {
            $scope.managedCohorts = data;
        });

        $scope.isAdmin = function() {
            return studyService.isAdmin();
        }

        $scope.isManager = function() {
           return studyService.isManager();
        }

        $scope.isCohortManager = function() {
            return (studyService.isManager() || studyService.isAdmin());
        }

        $scope.cohort = function() {
            return cohortService.getCohort();
        }

        $scope.session = function() {
            return sessionService.getSession();
        }

        $scope.changeCohort = function() {
            cohortService.setCohort(null);
        }

        $scope.member = function() {
            return cohortService.getMember();
        }

        $scope.onClearForms = function (destination) {
            clearAllFormTabs();
            cohortService.clearMember();
        }

        $scope.isManagerOfCohort = function() {
            var manager = false;
            var cohortCurrent = cohortService.getCohort();

            if (cohortCurrent !== undefined && cohortCurrent !== null) {
                if ($scope.managedCohorts !== undefined) {
                    $.each($scope.managedCohorts, function(index, tmpCohort) {
                        if (tmpCohort.name == cohortCurrent.name) {
                            manager = true;
                        }
                    });
                }
            }
            return manager;
        }

    });
