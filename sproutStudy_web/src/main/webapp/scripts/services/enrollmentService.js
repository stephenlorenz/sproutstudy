'use strict';

angular.module('sproutStudyApp')
    .factory('enrollmentService', function ($http, networkService) {

        return {
            updateOptOutStatus: function (mrn, status, callback) {
    			$http.get(networkService.generateUrl("updateEnrollmentOptOut", {mrn: mrn, optOutInd: status})).then(function (response) {
    				callback(response.data.value == "true" ? true : false);
    			});
            },
            createEnrollment: function (params, callback) {
    			$http.get(networkService.generateUrl("createEnrollment", params)).then(function (response) {
    				callback(response.data);
    			});
            },
            reenroll: function (params, callback) {
    			$http.get(networkService.generateUrl("reenroll", params)).then(function (response) {
    				callback(response.data);
    			});
            },
            getIhsEnrollmentDetail: function (params, callback) {
    			$http.get(networkService.generateUrl("getEnrollmentDetail", params)).then(function (response) {
    				callback(response.data);
    			});
            },
            getOncallEnrollmentInfo: function (params, callback) {
                $http.get(networkService.generateUrl("getOncallEnrollmentInfo", params)).then(function (response) {
                    callback(response.data);
                });
            },
            getEnrollmentBadge: function (params, callback) {
                $http.get(networkService.generateEnrollmentUrl("getPatientStatus", params)).then(function (response) {
                    callback(response.data);
                });
            },
            activateIhsEnrollment: function (params, callback) {
                $http.get(networkService.generateUrl("activateIhsEnrollment", params)).then(function (response) {
                    callback(response.data);
                });
            }
        };
    });
