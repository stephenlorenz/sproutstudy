'use strict';

angular.module('sproutStudyApp')
    .factory('transformService', function ($http, networkService) {

        return {
            getTemplate: function (params, callback) {
                $http.get(networkService.generateUrl("getTemplate", params)).then(function (response) {
                    callback(response.data);
                }, function errorCallback(response) {
                    alert("ERROR: " + response.status + ": transformService.getTemplate. Please contact your system administrator.\n\nFailed to retrieve narrative.");
                    response.value = 'false';
                    callback(response);
                });
             },
            saveTemplate: function (params, data, callback) {
                $http.post(networkService.generateUrl("saveTemplate", params), data).then(function (response) {
                    callback(response.data);
                }, function errorCallback(response) {
                    alert("ERROR: " + response.status + ": transformService.saveTemplate. Please contact your system administrator.\n\nFailed to save narrative template.");
                    // alert("Failed to save narrative template. Please contact your system administrator.");
                    response.value = 'false';
                    callback(response);
                });
             },
            getNarrativeText: function (params, data, callback) {
                $http.post(networkService.generateUrl("getNarrativeText", params), data).then(function (response) {
                    callback(response.data);
                }, function errorCallback(response) {
                    alert("ERROR: " + response.status + ": transformService.getNarrativeText. Please contact your system administrator.\n\nFailed to retrieve narrative text.");
                    response.value = 'false';
                    callback(response);
                });
             },
            getNarrativePDF: function (params, data, callback) {
                $http.post(networkService.generateUrl("getNarrativePDF", params), data).then(function (response) {
                    callback(response.data);
                }, function errorCallback(response) {
                    alert("ERROR: " + response.status + ": transformService.getNarrativePDF. Please contact your system administrator.\n\nFailed to retrieve narrative PDF.");
                    response.value = 'false';
                    callback(response);
                });
             },
            getNarrativeServer: function (params, data, callback) {
                $http.post(networkService.generateUrl("getNarrativeServer", params), data).then(function (response) {
                    callback(response.data);
                }, function errorCallback(response) {
                    alert("ERROR: " + response.status + ": transformService.getNarrativeServer. Please contact your system administrator.\n\nFailed to retrieve narrative server.");
                    response.value = 'false';
                    callback(response);
                });
             },
            saveNarrative: function (params, narrative, callback) {
                $http.post(networkService.generateUrl("saveNarrative", params), narrative).then(function (response) {
                    callback(response.data);
                }, function errorCallback(response) {
                    alert("ERROR: " + response.status + ": transformService.saveNarrative. Please contact your system administrator.\n\nFailed to save narrative.");
                    response.value = 'false';
                    callback(response);
                });
             }
        };
    });
