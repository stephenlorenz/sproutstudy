'use strict';

angular.module('sproutStudyApp')
    .factory('userManagerService', function ($http, networkService) {

        return {
            getAuthorizedCohorts: function (params, callback) {
                $http.get(networkService.generateUrl("getAuthorizedCohorts", params)).then(function (response) {
                    callback(response.data);
                });
            },
        };
    });
