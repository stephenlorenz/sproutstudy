'use strict';

angular.module('sproutStudyApp')
    .factory('providerService', function ($http, networkService) {

        return {
            getAppointmentProviders: function (params) {
                return $http.get(networkService.generateUrl("getProviders", params)).then(function (response) {
                    return response.data;
                });
            },
            providerSearch: function (params) {
                return $http.get(networkService.generateUrl("providerSearch", params)).then(function (response) {
                    return response.data;
                });
            },
            getProvidersByIhsGroupCode: function (params, callback) {
                return $http.get(networkService.generateUrl("getProvidersByIhsGroupCode", params)).then(function (response) {
                    callback(response.data);
                });
            },
            getProvidersBySession: function (params, callback) {
                return $http.get(networkService.generateUrl("getProvidersBySession", params)).then(function (response) {
                    callback(response.data);
                });
            }


        };
    });
