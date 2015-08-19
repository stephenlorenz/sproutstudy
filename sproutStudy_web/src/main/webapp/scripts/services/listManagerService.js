'use strict';

angular.module('sproutStudyApp')
    .factory('listManagerService', function ($http, networkService) {

        var list = undefined;
        var cohort = undefined;

        function setCohort(cohortObject) {
            cohort = cohortObject;
        }
        function getCohort() {
            return cohort;
        }

        function setList(listObject) {
            list = listObject;
        }
        function getList() {
            return list;
        }

        return {
            setCohort: setCohort,
            getCohort: getCohort,
            setList: setList,
            getList: getList,
            persistListAttribute: function (params, callback) {
                $http.get(networkService.generateUrl("persistListAttribute", params)).then(function (response) {
                    callback(response.data);
                });
            },
            saveList: function (params, callback) {
                $http.get(networkService.generateUrl("saveList", params)).then(function (response) {
                    callback(response.data);
                });
            },
            saveListData: function (params, data, callback) {
                $http.post(networkService.generateUrl("saveListData", params), data).then(function (response) {
                    callback(response.data);
                });
            },
            refreshList: function (params, callback) {
                $http.get(networkService.generateUrl("refreshList", params)).then(function (response) {
                    callback(response.data);
                });
            },
            deleteList: function (params, callback) {
                $http.get(networkService.generateUrl("deleteList", params)).then(function (response) {
                    callback(response.data);
                });
            }

        };
    });
