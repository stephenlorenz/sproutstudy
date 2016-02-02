'use strict';

angular.module('sproutStudyApp')
    .factory('transformService', function ($http, networkService) {

        return {
            getTemplate: function (params, callback) {
                $http.get(networkService.generateUrl("getTemplate", params)).then(function (response) {
                    callback(response.data);
                });
             },
            saveTemplate: function (params, data, callback) {
                $http.post(networkService.generateUrl("saveTemplate", params), data).then(function (response) {
                    callback(response.data);
                });
             },
            getNarrativeText: function (params, data, callback) {
                $http.post(networkService.generateUrl("getNarrativeText", params), data).then(function (response) {
                    callback(response.data);
                });
             },
            saveNarrative: function (params, narrative, callback) {
                $http.post(networkService.generateUrl("saveNarrative", params), narrative).then(function (response) {
                    callback(response.data);
                });
             }
        };
    });
