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
                }, function errorCallback(response) {
                    response.value = 'false';
                    callback(response);
                })
             },
            getNarrativeText: function (params, data, callback) {
                $http.post(networkService.generateUrl("getNarrativeText", params), data).then(function (response) {
                    callback(response.data);
                });
             },
            getNarrativePDF: function (params, data, callback) {
                $http.post(networkService.generateUrl("getNarrativePDF", params), data).then(function (response) {
                    callback(response.data);
                });
             },
            getNarrativeServer: function (params, data, callback) {
                $http.post(networkService.generateUrl("getNarrativeServer", params), data).then(function (response) {
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
