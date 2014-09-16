'use strict';

angular.module('sproutStudyApp')
    .controller('cohortController', function ($log, $scope, $location, cohortService) {

        $scope.cohort = function() {
            return cohortService.getCohort();
        }

        $scope.changeCohort = function() {
            cohortService.setCohort(null);
        }

        $scope.member = function() {
            return cohortService.getMember();
        }

    });
