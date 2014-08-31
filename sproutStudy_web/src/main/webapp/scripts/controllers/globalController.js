'use strict';

angular.module('sproutStudyApp')
    .controller('globalController', function ($scope, $location, adminService) {

//        $scope.manager = adminService.isManager();
//        $scope.admin = adminService.isAdmin();
        $scope.manager = false;
        $scope.admin = false;

        $scope.path = $location.path();



    });
