'use strict';

angular.module('sproutStudyApp')
    .factory('sessionService', function () {

        var session = null;

        var sessionService = {};

        sessionService.setSession = function (sessionTmp) {
            session = sessionTmp;
        };
        sessionService.getSession = function () {
            return session;
        };

        return sessionService;
    });


