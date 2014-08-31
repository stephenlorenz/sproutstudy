'use strict';

describe('Controller: TestCtrl', function () {

    // load the controller's module
    beforeEach(module('sproutStudyApp'));

    var TestCtrl,
        scope,
        $httpBackend;

    // Initialize the controller and a mock scope
    beforeEach(inject(function ($controller, $rootScope, $injector) {
        scope = $rootScope.$new();

        $httpBackend = $injector.get('$httpBackend');


        // backend definition common for all tests
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

//        $httpBackend.whenJSONP('http://localhost:8080/study/api/command/secure/getAppointments?callback=JSON_CALLBACK&clientToken=01fe65ba-d3cb-4ff3-9f80-0ea7817b54b3&serverToken=admin').respond({"appointments": [
//            {"mrn": "5203071", "patientName": "Jadiel Breton", "appointmentId": "32137719", "providerId": "103433", "providerName": "ERHABOR PHD,GILLIAN V", "duration": "240 min", "appointmentType": "ne4", "appointmentStatus": "PEN", "appointmentTime": 1365771600000, "iHealthSpaceStatus": "UNENROLLED", "forms": [
//                {"formMarkup": null, "inboxStatus": "NEW", "instanceId": "FA7116EB-51EE-408C-9FC0-AB54C32F94A4", "model": null, "title": "One Question Form", "setFormMarkup": false, "setInboxStatus": true, "setInstanceId": true, "setModel": false, "setTitle": true}
//            ], "appointmentDateTime": "Fri, Apr 12, 2013 9:00 AM"},
//            {"mrn": "4380868", "patientName": "Seamus Murphy", "appointmentId": "32277460", "providerId": "12591", "providerName": "BAUMAN MD,MARGARET L", "duration": "45 min", "appointmentType": "fol", "appointmentStatus": "PEN", "appointmentTime": 1365771600000, "iHealthSpaceStatus": "UNENROLLED", "forms": [], "appointmentDateTime": "Fri, Apr 12, 2013 9:00 AM"},
//            {"mrn": "4392752", "patientName": "Raul Perez", "appointmentId": "30059559", "providerId": "32518", "providerName": "ZIMMERMAN,ANDREW W", "duration": "45 min", "appointmentType": "fol", "appointmentStatus": "BMP", "appointmentTime": 1365773400000, "iHealthSpaceStatus": "UNENROLLED", "forms": [], "appointmentDateTime": "Fri, Apr 12, 2013 9:30 AM"},
//            {"mrn": "5227735", "patientName": "Xavier Kelley", "appointmentId": "31822216", "providerId": "12591", "providerName": "BAUMAN MD,MARGARET L", "duration": "45 min", "appointmentType": "fol", "appointmentStatus": "PEN", "appointmentTime": 1365774300000, "iHealthSpaceStatus": "UNENROLLED", "forms": [], "appointmentDateTime": "Fri, Apr 12, 2013 9:45 AM"},
//            {"mrn": "5175058", "patientName": "Benjamin Huskic", "appointmentId": "31826228", "providerId": "12591", "providerName": "BAUMAN MD,MARGARET L", "duration": "45 min", "appointmentType": "fol", "appointmentStatus": "PEN", "appointmentTime": 1365777000000, "iHealthSpaceStatus": "UNENROLLED", "forms": [], "appointmentDateTime": "Fri, Apr 12, 2013 10:30 AM"},
//            {"mrn": "5224697", "patientName": "Vincent Aubin", "appointmentId": "31889226", "providerId": "12591", "providerName": "BAUMAN MD,MARGARET L", "duration": "45 min", "appointmentType": "fol", "appointmentStatus": "PEN", "appointmentTime": 1365779700000, "iHealthSpaceStatus": "UNENROLLED", "forms": [], "appointmentDateTime": "Fri, Apr 12, 2013 11:15 AM"},
//            {"mrn": "5223437", "patientName": "Noah Book", "appointmentId": "31910108", "providerId": "12591", "providerName": "BAUMAN MD,MARGARET L", "duration": "45 min", "appointmentType": "fol", "appointmentStatus": "PEN", "appointmentTime": 1365786000000, "iHealthSpaceStatus": "ENROLLED_OTHER", "forms": [], "appointmentDateTime": "Fri, Apr 12, 2013 1:00 PM"},
//            {"mrn": "5200093", "patientName": "Auden Ivester", "appointmentId": "31981872", "providerId": "12591", "providerName": "BAUMAN MD,MARGARET L", "duration": "45 min", "appointmentType": "fol", "appointmentStatus": "CAN", "appointmentTime": 1365788700000, "iHealthSpaceStatus": "ENROLLED_ACTIVE", "forms": [], "appointmentDateTime": "Fri, Apr 12, 2013 1:45 PM"},
//            {"mrn": "4370974", "patientName": "James Barrie", "appointmentId": "32215684", "providerId": "12591", "providerName": "BAUMAN MD,MARGARET L", "duration": "45 min", "appointmentType": "fol", "appointmentStatus": "PEN", "appointmentTime": 1365788700000, "iHealthSpaceStatus": "UNENROLLED", "forms": [], "appointmentDateTime": "Fri, Apr 12, 2013 1:45 PM"},
//            {"mrn": "5156222", "patientName": "Ryan Fletcher", "appointmentId": "32269638", "providerId": "12591", "providerName": "BAUMAN MD,MARGARET L", "duration": "45 min", "appointmentType": "fol", "appointmentStatus": "PEN", "appointmentTime": 1365791400000, "iHealthSpaceStatus": "UNENROLLED", "forms": [], "appointmentDateTime": "Fri, Apr 12, 2013 2:30 PM"},
//            {"mrn": "3749936", "patientName": "Charles Staff", "appointmentId": "31864285", "providerId": "12591", "providerName": "BAUMAN MD,MARGARET L", "duration": "45 min", "appointmentType": "fol", "appointmentStatus": "PEN", "appointmentTime": 1365794100000, "iHealthSpaceStatus": "UNENROLLED", "forms": [], "appointmentDateTime": "Fri, Apr 12, 2013 3:15 PM"}
//        ]});


        $httpBackend.whenGET('/appointments').respond({"appointments": [
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



        TestCtrl = $controller('TestCtrl', {
            $scope: scope
        });
    }));

    it('should attach a list of awesomeThings to the scope', function () {
        expect(scope.awesomeThings.length).toBe(3);
    });
});
