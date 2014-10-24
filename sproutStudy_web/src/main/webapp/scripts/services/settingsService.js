'use strict';

angular.module('sproutStudyApp')
    .factory('settingsService', function ($http, networkService) {

        return {
            getUser: function (params, callback) {
                $http.get(networkService.generateUrl("getUser", params)).then(function (response) {
                    callback(response.data);
                });
            }

        };
    });
