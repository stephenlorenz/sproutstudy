'use strict';

angular.module('sproutStudyApp')
    .factory('formsService', function ($http, networkService, sproutStudyProperties) {

        return {
            getForm: function (params) {
                return $http.get(networkService.generateUrl("getForm", params)).then(function (response) {
                    return response.data;
                });
            },
            getForms: function (params, callback) {
                return $http.get(networkService.generateUrl("getForms", params)).then(function (response) {
                    callback(response.data);
                });
            },
            pushForm: function (params, callback) {
                return $http.get(networkService.generateUrl("pushForm", params)).then(function (response) {
                    callback(response.data);
                });
            },
            appendProviderAsParameter: function (params, callback) {
                return $http.get(networkService.generateUrl("appendProviderAsParameter", params)).then(function (response) {
                    callback(response.data);
                });
            },
            returnToSender: function (params) {
                return $http.get(networkService.generateUrl("returnToSender", params)).then(function (response) {
                    return response.data;
                });
            },
            getPublications: function (params) {
                return $http.get(networkService.generateUrl("getPublications", params)).then(function (response) {
                    return response.data;
                });
            },
            deliverForm: function (params, callback) {
                return $http.get(networkService.generateUrl("deliverForm", params)).then(function (response) {
                    callback(response.data);
                });
            },
            applyForNonce: function (params, callback) {
                return $http.get(networkService.generateUrl("applyForNonce", params)).then(function (response) {
                    callback(response.data);
                });
            },
            getFormSubmissions: function (callback) {
                     $http({method: 'get', url: networkService.generateUrl("getFormSubmissions"), isArray: false}).
                     success(function (data) {callback(data);});
            },
            isPatientVerified: function (params, callback) {
                     $http({method: 'get', url: networkService.generateUrl("isPatientVerified", params), isArray: false}).
                     success(function (data) {callback(data);});
            },
            isPatientAssertive: function (params, callback) {
                     $http({method: 'get', url: networkService.generateUrl("isPatientAssertive", params), isArray: false}).
                     success(function (data) {callback(data);});
            }



        };
    });
