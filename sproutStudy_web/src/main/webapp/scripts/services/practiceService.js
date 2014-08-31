'use strict';

angular.module('sproutStudyApp')
.factory('practiceService', function ($http, networkService) {

	return {
        getPractices: function () {
            return $http.get(networkService.generateUrl("getPractices")).then(function (response) {
                return response.data;
            });
        },
        getPracticesCallback: function (callback) {
            return $http.get(networkService.generateUrl("getPractices")).then(function (response) {
                callback(response.data);
            });
        }
	};
});
