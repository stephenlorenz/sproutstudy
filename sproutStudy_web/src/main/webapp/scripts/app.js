'use strict';

var sproutStudyApp = angular.module('sproutStudyApp', ['ui', 'ngResource', 'ui.bootstrap', '$strap.directives'])
    .value("sproutStudyProperties", {
//    serverUrl: 'http://localhost:8080/study/api/command/secure'
    serverUrl: '/sproutstudy/api/command/secure'
        , enrollmentUrl: '/saEnrollmentModule/rest/json'
//    	serverUrl: 'http://localhost:3000'
//    	serverUrl: 'http://lcsihsdev.partners.org:3000'
    }).config(['$routeProvider', function ($routeProvider) {

        $routeProvider
            .when('/', {
                templateUrl: 'views/studyView.html',
                controller: 'studyController'
            })
            .when('/study', {
                templateUrl: 'views/studyView.html',
                controller: 'studyController'
            })
            .when('/settings', {
                templateUrl: 'views/settingsView.html',
                controller: 'settingsController'
            })
            .otherwise({
                redirectTo: '/'
            });
    }]).config(['$httpProvider', function($httpProvider) {
        $httpProvider.defaults.useXDomain = true;
        delete $httpProvider.defaults.headers.common['X-Requested-With'];
    }
]);
