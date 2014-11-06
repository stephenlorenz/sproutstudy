'use strict';

angular.module('sproutStudyApp')
    .factory('formManagerService', function ($http, networkService) {

        return {
            saveFormPublicationKey: function (params, callback) {
                $http.get(networkService.generateUrl("saveFormPublicationKey", params)).then(function (response) {
                    callback(response.data);
                });
            }

        };
    });
