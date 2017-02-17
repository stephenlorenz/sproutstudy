'use strict';

// var sproutStudyApp = angular.module('sproutStudyApp', ['ui', 'ngRoute', 'ngResource', 'ui.bootstrap', 'bgDirectives', 'ui.ace', 'ui.bootstrap.contextMenu', 'ngWebsocket', '$strap.directives'])
var sproutStudyApp = angular.module('sproutStudyApp', ['ui', 'ngResource', 'ui.bootstrap', 'bgDirectives', 'ui.ace', 'ui.bootstrap.contextMenu', 'ngWebsocket', '$strap.directives'])
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
            .when('/feedback', {
                templateUrl: 'views/feedbackView.html',
                controller: 'feedbackController'
            })
            .when('/forms', {
                templateUrl: 'views/formManagerView.html',
                controller: 'formManagerController'
            })
            .when('/forms/add', {
                templateUrl: 'views/formAddView.html',
                controller: 'formManagerController'
            })
            .when('/lists', {
                templateUrl: 'views/lists/listManagerView.html',
                controller: 'listManagerController'
            })
            .when('/lists/add', {
                templateUrl: 'views/lists/listAddView.html',
                controller: 'listManagerController'
            })
            .when('/lists/data', {
                templateUrl: 'views/lists/listDataView.html',
                controller: 'listManagerController'
            })
            .when('/lists/edit', {
                templateUrl: 'views/lists/listEditView.html',
                controller: 'listManagerController'
            })
            .when('/transform', {
                templateUrl: 'views/transformDesignerView.html',
                controller: 'transformManagerController'
            })
            .when('/cohorts', {
                templateUrl: 'views/cohortManagerView.html',
                controller: 'cohortManagerController'
            })
            .when('/cohorts/add', {
                templateUrl: 'views/cohortAddView.html',
                controller: 'cohortManagerController'
            })
            .when('/cohorts/edit', {
                templateUrl: 'views/cohortEditView.html',
                controller: 'cohortManagerController'
            })
            .when('/cohorts/authorizations', {
                templateUrl: 'views/cohortAuthView.html',
                controller: 'cohortManagerController'
            })
            .when('/cohorts/authorizations/add', {
                templateUrl: 'views/cohortAuthAddView.html',
                controller: 'cohortManagerController'
            })
            .otherwise({
                redirectTo: '/'
            });
    }]).config(['$httpProvider', function($httpProvider) {
        $httpProvider.defaults.useXDomain = true;
        delete $httpProvider.defaults.headers.common['X-Requested-With'];
    }
]);
