'use strict';

angular.module('sproutStudyApp')
    .controller('cohortController', function ($log, $scope, $location, cohortService, sessionService) {

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

    });
