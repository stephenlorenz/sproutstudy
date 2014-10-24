'use strict';

angular.module('sproutStudyApp')
    .controller('settingsController', function ($scope, $location, $routeParams, settingsService) {

    $scope.settings = null;
    $scope.user = null;

    settingsService.getUser(null, function(data) {
       $scope.user = data;
    });


});
