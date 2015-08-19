'use strict';

angular.module('sproutStudyApp')
    .factory('cohortManagerService', function ($http, networkService) {
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
            getCohorts: function (params, callback) {
                $http.get(networkService.generateUrl("getAuthorizedCohorts", params)).then(function (response) {
                    callback(response.data);
                });
            },
            isAdmin: function (params, callback) {
                $http.get(networkService.generateUrl("isAdmin", params)).then(function (response) {
                    callback(response.data);
                });
            },
            isManager: function (params, callback) {
                $http.get(networkService.generateUrl("isManager", params)).then(function (response) {
                    callback(response.data);
                });
            },
            saveCohort: function (params, callback) {
                $http.get(networkService.generateUrl("saveCohort", params)).then(function (response) {
                    callback(response.data);
                });
            },
            deleteCohort: function (params, callback) {
                $http.get(networkService.generateUrl("deleteCohort", params)).then(function (response) {
                    callback(response.data);
                });
            },
            getManagedCohorts: function (params, callback) {
                $http.get(networkService.generateUrl("getManagedCohorts", params)).then(function (response) {
                    callback(response.data);
                });
            },
            getCohortAuthorizations: function (params, callback) {
                $http.get(networkService.generateUrl("getCohortAuthorizations", params)).then(function (response) {
                    callback(response.data);
                });
            },
            getCohortAuthorizationsByKey: function (params, callback) {
                $http.get(networkService.generateUrl("getCohortAuthorizationsByKey", params)).then(function (response) {
                    callback(response.data);
                });
            },
            grantCohortAuthorization: function (params, callback) {
                $http.get(networkService.generateUrl("grantCohortAuthorization", params)).then(function (response) {
                    callback(response.data);
                });
            },
            revokeCohortAuthorization: function (params, callback) {
                $http.get(networkService.generateUrl("revokeCohortAuthorization", params)).then(function (response) {
                    callback(response.data);
                });
            },
            updateCohortAuthorization: function (params, callback) {
                $http.get(networkService.generateUrl("updateCohortAuthorization", params)).then(function (response) {
                    callback(response.data);
                });
            },
            getUser: function (params, callback) {
                $http.get(networkService.generateUrl("getDomainUser", params)).then(function (response) {
                    callback(response.data);
                });
            },
            refreshCohorts: function (params, callback) {
                $http.get(networkService.generateUrl("refreshAuthorizedCohorts", params)).then(function (response) {
                    callback(response.data);
                });
            }
        };
    });
