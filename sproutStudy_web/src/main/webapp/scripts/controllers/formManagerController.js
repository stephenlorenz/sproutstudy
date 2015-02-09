'use strict';

angular.module('sproutStudyApp')
    .controller('formManagerController', function ($scope, $location, $routeParams, formManagerService, studyService) {

    $scope.forms = null;
    $scope.cohort = null;

    studyService.getLastSelectedCohort({}, function(data) {
        $scope.cohort = data;
    });

    $scope.onSaveForm = function(form) {
        formManagerService.saveFormPublicationKey({id: form.id, publicationKey: form.publicationKey}, function(data) {
            if (data.value == 'true') {
//                alert("Form Publication Key was successfully saved.");
                form.activityDate = data.timestamp;
                form.modified = false;
            } else {
                alert("Failed to save Form Publication Key.  Perhaps you used a duplicate key?");
                form.modified = false;
            }
        });

    };

    $scope.onPubIdModified = function(form) {
        form.modified = true;
    };


    $scope.onDeleteForm = function(form) {
        console.log("onDeleteForm...");
    };

});
