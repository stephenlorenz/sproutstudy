'use strict';

angular.module('sproutStudyApp')
    .controller('feedbackController', function ($scope, $window, $location, $routeParams, feedbackService, cohortService, cohortManagerService) {

        $scope.feedback = null;
        $scope.feedbackSent = false;

        $scope.sendFeedbackMessage = undefined;

        $scope.cohort = feedbackService.getCohort();

        if ($scope.cohort == undefined) {
            feedbackService.setCohort(cohortService.getCohort());
            $scope.cohort = feedbackService.getCohort();
        }

        if ($scope.cohort !== undefined && $scope.cohort !== null) {
            $window.sessionStorage.setItem("sproutStudyCohort", JSON.stringify($scope.cohort));
        } else {
            try {
                feedbackService.setCohort(JSON.parse($window.sessionStorage.getItem("sproutStudyCohort")));
                $scope.cohort = feedbackService.getCohort();
            } catch (e) {
                $scope.cohort = undefined;
            }
        }

        $scope.onSendFeedbackMessage = function() {

            var cohortKey = "";

            if ($scope.cohort && $scope.cohort.cohortKey) cohortKey = $scope.cohort.cohortKey;

            feedbackService.sendFeedback({"cohortKey": cohortKey}, $scope.feedback, function(data){
                if (data.value == 'true') {
                    $scope.feedbackSent = true;
                } else {
                    $scope.sendFeedbackMessage = data.message;
                }
            });

        }


    //settingsService.getUser(null, function(data) {
    //   $scope.user = data;
    //});


});
