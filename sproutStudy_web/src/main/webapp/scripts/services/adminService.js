'use strict';

angular.module('sproutStudyApp')
    .factory('adminService', function ($http, networkService, sproutStudyProperties) {

        return {
            isAdmin: function (params) {
                return $http.get(networkService.generateUrl("isAdmin", params)).then(function (response) {
                    return response.data;
                });
            },
            isManager: function (params) {
                return $http.get(networkService.generateUrl("isManager", params)).then(function (response) {
                    return response.data;
                });
            },
            getUsers: function (params) {
                return $http.get(networkService.generateUrl("getFrontOfficeUsers", params)).then(function (response) {
                    return response.data;
                });
            },
            deleteUser: function (params) {
                return $http.get(networkService.generateUrl("deleteFrontOfficeUserAuth", params)).then(function (response) {
                    return response.data;
                });
            },
            getUser: function (params) {
                return $http.get(networkService.generateUrl("getUser", params)).then(function (response) {
                    return response.data;
                });
            },
            getApplicationAuthorities: function (params) {
                return $http.get(networkService.generateUrl("getApplicationAuthorities", params)).then(function (response) {
                    return response.data;
                });
            },
            getFrontOfficeUser: function (params, callback) {
                return $http.get(networkService.generateUrl("getFrontOfficeUser", params)).then(function (response) {
                    callback(response.data);
                });
            },
            saveGrantedAuthority: function (params, callback) {
                return $http.get(networkService.generateUrl("saveGrantedAuthority", params)).then(function (response) {
                    callback(response.data);
                });
            },
            updateManager: function (params, callback) {
                return $http.get(networkService.generateUrl("updateManager", params)).then(function (response) {
                    callback(response.data);
                });
            }
        };
    });


