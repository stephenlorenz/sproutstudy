'use strict';

angular.module('sproutStudyApp')
    .controller('adminController', function ($scope, $location, $routeParams, adminService) {

        $scope.applicationAuthName = "";

        $scope.grantedAuthId = $routeParams.grantedAuthId;

        $scope.users = adminService.getUsers();
        $scope.applicationAuthorities = adminService.getApplicationAuthorities();

        $scope.applicationAuthority = null;
        $scope.cn = null;
        $scope.manager = false;
        $scope.submittedInd = false;

        $scope.onFilterByPractice = function(applicationAuth) {
            if (applicationAuth !== undefined) {
                $scope.applicationAuthId = applicationAuth.id;
                $scope.applicationAuthName = applicationAuth.description;
            } else {
                $scope.applicationAuthId = undefined;
                $scope.applicationAuthName = undefined;
            }

        }

        $scope.applicationAuthFilter = function (item){
            if ($scope.applicationAuthId > 0) {
                if (item.applicationAuthId != $scope.applicationAuthId) return false;
            }
            return true;
        };

        $scope.edit = function(user) {
            $location.path( "/admin/editauth/" + user.principal  );
        };

        $scope.delete = function(user) {
            var answer = confirm("Are you sure you want to delete this authorization?  This action cannot be undone!")
            if (answer == true) {
                $scope.users = adminService.deleteUser({id: user.id});
            }
        };

        $scope.submit = function() {
           $scope.submittedInd = true;
           adminService.saveGrantedAuthority({id: $scope.applicationAuthority.id, username: $scope.cn, manager: $scope.manager}, function(data) {
                $scope.submittedInd = false;
                $location.path( "/admin" );
            });
        }

        $scope.getUser = function() {
            $scope.user = adminService.getUser({cn: $scope.cn});
        };

        $scope.updateManager = function(user) {
            adminService.updateManager({grantedAuthId: user.id, manager: user.manager}, function(data) {});
        }


        if ($scope.grantedAuthId != null && $scope.grantedAuthId.length > 0) {
            adminService.getFrontOfficeUser({grantedAuthId: $scope.grantedAuthId}, function(data) {
                $scope.cn = data.cn;

                $scope.getUser();
            });
        }


    });
