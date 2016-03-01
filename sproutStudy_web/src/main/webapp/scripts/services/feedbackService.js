'use strict';

angular.module('sproutStudyApp')
    .factory('feedbackService', function ($http, networkService) {

        var cohort = undefined;

        function setCohort(cohortObject) {
            cohort = cohortObject;
        }
        function getCohort() {
            return cohort;
        }

        return {
            setCohort: setCohort,
            getCohort: getCohort,
            sendFeedback: function (params, data, callback) {
                $http.post(networkService.generateUrl("sendFeedback", params), data).then(function (response) {
                    callback(response.data);
                });
            }

        };
    });
