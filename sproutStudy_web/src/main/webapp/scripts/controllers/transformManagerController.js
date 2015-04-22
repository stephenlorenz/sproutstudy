'use strict';

angular.module('sproutStudyApp')
    .controller('transformManagerController', function ($scope, $location, $routeParams, $window, formManagerService, studyService, cohortService, hotkeys) {

    $scope.managedForms = undefined;

    cohortService.setMember({fullName: "Transform Manager", id: 0, url: "forms"});



    hotkeys.add({
        combo: 'command+enter',
        description: 'Execute Query',
        allowIn: ['INPUT', 'SELECT', 'TEXTAREA'],
        callback: function() {
            if ($scope.executeQuery !== undefined) {
                $scope.executeQuery();
            }
        }
    });

    hotkeys.add({
        combo: 'command+e',
        description: 'Execute Query',
        allowIn: ['INPUT', 'SELECT', 'TEXTAREA'],
        callback: function() {
            if ($scope.executeQuery !== undefined) {
                $scope.executeQuery();
            }
        }
    });
});
