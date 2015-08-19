'use strict';

angular.module('sproutStudyApp')
    .factory('formManagerService', function ($http, networkService) {

        var form = undefined;
        var cohort = undefined;

        function setCohort(cohortObject) {
            cohort = cohortObject;
        }
        function getCohort() {
            return cohort;
        }

        function setForm(formObject) {
            form = formObject;
        }
        function getForm() {
            return form;
        }

        return {
            setCohort: setCohort,
            getCohort: getCohort,
            setForm: setForm,
            getForm: getForm,
            saveFormPublicationKey: function (params, callback) {
                $http.get(networkService.generateUrl("saveFormPublicationKey", params)).then(function (response) {
                    callback(response.data);
                });
            },
            persistFormAttribute: function (params, callback) {
                $http.get(networkService.generateUrl("persistFormAttribute", params)).then(function (response) {
                    callback(response.data);
                });
            },
            toggleFormArchive: function (params, callback) {
                $http.get(networkService.generateUrl("toggleFormArchive", params)).then(function (response) {
                    callback(response.data);
                });
            },
            saveForm: function (params, callback) {
                $http.get(networkService.generateUrl("saveForm", params)).then(function (response) {
                    callback(response.data);
                });
            },
            deleteForm: function (params, callback) {
                $http.get(networkService.generateUrl("deleteForm", params)).then(function (response) {
                    callback(response.data);
                });
            }

        };
    });
