'use strict';

angular.module('sproutStudyApp')
    .factory('appointmentsService', function ($http, networkService) {

        return {
            getAppointments: function (params) {
    			return $http.get(networkService.generateUrl("getAppointments", params)).then(function (response) {
                    return response.data;
                });
            }
        };
    });
