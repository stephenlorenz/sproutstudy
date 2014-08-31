'use strict';

describe('Controller: AppointmentCtrl', function () {

    // load the controller's module
    beforeEach(module('sproutStudyApp'));
    
    

    var AppointmentCtrl,
        scope,
        mockBackend;

    var $httpBackend;
    // Initialize the controller and a mock scope
    beforeEach(inject(function ($controller, $rootScope, $location, $routeParams, $http, $resource, localeModel, $injector) {
        scope = $rootScope.$new();
        $httpBackend = $injector.get('$httpBackend');

//        $httpBackend.whenJSONP(/http:\/\/localhost:8080\/study\/api\/command\/secure\/getAppointments.*/).passThrough();

//        $httpBackend.whenJSONP(/http:\/\/localhost:8080\/study\/api\/command\/secure\/getAppointments.*/).respond();

        $httpBackend.whenJSONP(/http:\/\/localhost:8080\/frontoffice\/api\/command\/secure\/getAppointments.*/).respond({"appointments": [
            {"mrn": "5203071", "patientName": "Jadiel Breton", "appointmentId": "32137719", "providerId": "103433", "providerName": "ERHABOR PHD,GILLIAN V", "duration": "240 min", "appointmentType": "ne4", "appointmentStatus": "PEN", "appointmentTime": 1365771600000, "iHealthSpaceStatus": "UNENROLLED", "forms": [
                {"formMarkup": null, "inboxStatus": "NEW", "instanceId": "FA7116EB-51EE-408C-9FC0-AB54C32F94A4", "model": null, "title": "One Question Form", "setFormMarkup": false, "setInboxStatus": true, "setInstanceId": true, "setModel": false, "setTitle": true}
            ], "appointmentDateTime": "Fri, Apr 12, 2013 9:00 AM"},
            {"mrn": "4380868", "patientName": "Seamus Murphy", "appointmentId": "32277460", "providerId": "12591", "providerName": "BAUMAN MD,MARGARET L", "duration": "45 min", "appointmentType": "fol", "appointmentStatus": "PEN", "appointmentTime": 1365771600000, "iHealthSpaceStatus": "UNENROLLED", "forms": [], "appointmentDateTime": "Fri, Apr 12, 2013 9:00 AM"},
            {"mrn": "4392752", "patientName": "Raul Perez", "appointmentId": "30059559", "providerId": "32518", "providerName": "ZIMMERMAN,ANDREW W", "duration": "45 min", "appointmentType": "fol", "appointmentStatus": "BMP", "appointmentTime": 1365773400000, "iHealthSpaceStatus": "UNENROLLED", "forms": [], "appointmentDateTime": "Fri, Apr 12, 2013 9:30 AM"},
            {"mrn": "5227735", "patientName": "Xavier Kelley", "appointmentId": "31822216", "providerId": "12591", "providerName": "BAUMAN MD,MARGARET L", "duration": "45 min", "appointmentType": "fol", "appointmentStatus": "PEN", "appointmentTime": 1365774300000, "iHealthSpaceStatus": "UNENROLLED", "forms": [], "appointmentDateTime": "Fri, Apr 12, 2013 9:45 AM"},
            {"mrn": "5175058", "patientName": "Benjamin Huskic", "appointmentId": "31826228", "providerId": "12591", "providerName": "BAUMAN MD,MARGARET L", "duration": "45 min", "appointmentType": "fol", "appointmentStatus": "PEN", "appointmentTime": 1365777000000, "iHealthSpaceStatus": "UNENROLLED", "forms": [], "appointmentDateTime": "Fri, Apr 12, 2013 10:30 AM"},
            {"mrn": "5224697", "patientName": "Vincent Aubin", "appointmentId": "31889226", "providerId": "12591", "providerName": "BAUMAN MD,MARGARET L", "duration": "45 min", "appointmentType": "fol", "appointmentStatus": "PEN", "appointmentTime": 1365779700000, "iHealthSpaceStatus": "UNENROLLED", "forms": [], "appointmentDateTime": "Fri, Apr 12, 2013 11:15 AM"},
            {"mrn": "5223437", "patientName": "Noah Book", "appointmentId": "31910108", "providerId": "12591", "providerName": "BAUMAN MD,MARGARET L", "duration": "45 min", "appointmentType": "fol", "appointmentStatus": "PEN", "appointmentTime": 1365786000000, "iHealthSpaceStatus": "ENROLLED_OTHER", "forms": [], "appointmentDateTime": "Fri, Apr 12, 2013 1:00 PM"},
            {"mrn": "5200093", "patientName": "Auden Ivester", "appointmentId": "31981872", "providerId": "12591", "providerName": "BAUMAN MD,MARGARET L", "duration": "45 min", "appointmentType": "fol", "appointmentStatus": "CAN", "appointmentTime": 1365788700000, "iHealthSpaceStatus": "ENROLLED_ACTIVE", "forms": [], "appointmentDateTime": "Fri, Apr 12, 2013 1:45 PM"},
            {"mrn": "4370974", "patientName": "James Barrie", "appointmentId": "32215684", "providerId": "12591", "providerName": "BAUMAN MD,MARGARET L", "duration": "45 min", "appointmentType": "fol", "appointmentStatus": "PEN", "appointmentTime": 1365788700000, "iHealthSpaceStatus": "UNENROLLED", "forms": [], "appointmentDateTime": "Fri, Apr 12, 2013 1:45 PM"},
            {"mrn": "5156222", "patientName": "Ryan Fletcher", "appointmentId": "32269638", "providerId": "12591", "providerName": "BAUMAN MD,MARGARET L", "duration": "45 min", "appointmentType": "fol", "appointmentStatus": "PEN", "appointmentTime": 1365791400000, "iHealthSpaceStatus": "UNENROLLED", "forms": [], "appointmentDateTime": "Fri, Apr 12, 2013 2:30 PM"},
            {"mrn": "3749936", "patientName": "Charles Staff", "appointmentId": "31864285", "providerId": "12591", "providerName": "BAUMAN MD,MARGARET L", "duration": "45 min", "appointmentType": "fol", "appointmentStatus": "PEN", "appointmentTime": 1365794100000, "iHealthSpaceStatus": "UNENROLLED", "forms": [], "appointmentDateTime": "Fri, Apr 12, 2013 3:15 PM"}
        ]});

        $httpBackend.whenJSONP(/http:\/\/localhost:8080\/frontoffice\/api\/command\/secure\/getProviders.*/).respond([{"name":"BAUMAN MD,MARGARET L","id":"12591","prettyName":"MARGARET L BAUMAN MD"},{"name":"ERHABOR PHD,GILLIAN V","id":"103433","prettyName":"GILLIAN V ERHABOR PHD"},{"name":"ZIMMERMAN,ANDREW W","id":"32518","prettyName":"ANDREW W ZIMMERMAN"}]);

        AppointmentCtrl = $controller('AppointmentCtrl', {
            $scope: scope
    });
})

);




    it('should have two locales', function () {
        expect(scope.locales.length).toBe(2);
    });

    it('should have two appointments', function () {
        console.log("scope.scheduleResult1: " + scope.scheduleResult);
//        expect(scope.scheduleResult.appointments).toBe(2);

        var data = scope.getSchedule.get(function (data) {
            //$scope.$broadcast('refreshAppointmentTable');
            console.log("getSchedule.get: " + data);
            return data;
        });

console.log("data: " + data.appointments);
        //scope.updateAppointments();

        //console.log("scope.providers: " + scope.providers);

    });


});
