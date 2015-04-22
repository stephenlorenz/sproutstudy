'use strict';

angular.module('sproutStudyApp')
    .factory('transformService', function ($http, networkService) {

        return {
            getTemplate: function (params, callback) {
                $http.get(networkService.generateUrl("getTemplate", params)).then(function (response) {
                    callback(response.data);
                });
             },
            saveTemplate: function (params, callback) {
                $http.get(networkService.generateUrl("saveTemplate", params)).then(function (response) {
                    callback(response.data);
                });
             },
            saveNarrative: function (params, callback) {
                $http.get(networkService.generateUrl("saveNarrative", params)).then(function (response) {
                    callback(response.data);
                });
             }
        };
    });
