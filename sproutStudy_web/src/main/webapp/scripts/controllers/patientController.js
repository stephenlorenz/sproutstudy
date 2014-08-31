'use strict';

angular.module('sproutStudyApp')
    .controller('patientController', function ($scope, patientService, formsService, practiceService, enrollmentService) {

        $scope.patientMatches = undefined;
        $scope.searchEnabled = true;

        $scope.query = "";
//        $scope.query = "5008268";

        $scope.patient = null;

        $scope.publicationsMaster = angular.copy(formsService.getPublications());

        $scope.publicationKey = null;

        $scope.inbox = 0;

        $scope.ihsMrn = false;
        $scope.practiceMrn = false;

        $scope.provider = null;
        $scope.expirationDate = null;

        $scope.viewName = "patient";

        $scope.minDeliveryDate = new Date();

        $scope.practices = practiceService.getPractices();
        $scope.statuses = null;

        $scope.formFilter = function (item){
            if ($scope.status !== undefined) {
                if (item.inboxStatus != $scope.status) return false;
            }
            return true;
        };

        $scope.onFilterByStatus = function(status) {
//            console.log("filtering by status: " + status);

            if (status !== undefined) {
                $scope.status = status;
            } else {
                $scope.status = undefined;
            }
        }

        $scope.getPatientInbox = function() {
            $scope.inbox = 1;
            patientService.getPatientInbox({mrn: $scope.patient.mrn, allInd: true}, function(data) {
                $scope.inbox = data;

                $scope.statuses = new Array();

                for (var i = 0; i < data.length; i++) {
                    var includeInd = true;
                    if ($scope.statuses !== undefined && $scope.statuses.length > 0) {
                        for (var i2=0;i2<$scope.statuses.length;i2++) {
                            if ($scope.statuses[i2] == data[i].inboxStatus) {
                                includeInd = false;
                            }
                        }
                    }

                    if (includeInd) $scope.statuses.push(data[i].inboxStatus);
                }
//                console.log("data: " + data.length);
            });
        }

        $scope.onDeliverForm = function(publication) {
            $scope.publication = publication;
            $scope.deliveringInd = true;
            $scope.deliveryError = null;

            var expirationDate = null;

            if (publication.expirationDate !== undefined && publication.expirationDate !== null) {
                try {
                    var yyyy = publication.expirationDate.getFullYear().toString();
                    var mm = (publication.expirationDate.getMonth()+1).toString(); // getMonth() is zero-based
                    var dd  = publication.expirationDate.getDate().toString();
                    expirationDate = (mm[1]?mm:"0"+mm[0]) + "/" + (dd[1]?dd:"0"+dd[0]) + "/" + yyyy; // padding
                } catch (e) {}
            }

            formsService.deliverForm({mrn: $scope.patient.mrn, publicationKey: publication.key, provider: publication.provider.id, expirationDate: expirationDate}, function(data) {
//                console.log("data.value: " + data.value);
//                console.log("data.message: " + data.message);
                if (data.value == 'false') {
                    $scope.deliveringInd = 'error';
                    $scope.deliveryError = data.message;
                } else {
                    $scope.getPatientInbox();
                    $scope.deliverFormModal = false;
                    $scope.deliveringInd = false;
                }
            });
        }

        $scope.onDeliverFormTest = function(publication) {
            $scope.publication = publication;
            $scope.deliveringInd = true;
            $scope.deliveryError = null;

            formsService.deliverForm({mrn: $scope.query, publicationKey: '8AEE3D03-E0AC-47DF-BB22-09EE0D6C652B'}, function(data) {
//                console.log("data.value: " + data.value);
//                console.log("data.message: " + data.message);
                if (data.value == 'false') {
                    $scope.deliveringInd = 'error';
                    $scope.deliveryError = data.message;
                } else {
                    $scope.getPatientInbox();
                    $scope.deliverFormModal = false;
                    $scope.deliveringInd = false;
                }
            });
        }

        $scope.openDeliverFormModal = function() {
            $scope.deliveringInd = false;
            $scope.deliverFormModal = true;

            $scope.publications = angular.copy($scope.publicationsMaster);


        }

        $scope.demoShowPubs = function() {
            $scope.publications = angular.copy($scope.publicationsMaster);
        }

        $scope.closeDeliverFormModal = function () {
            $scope.deliverFormModal = false;
        };

        $scope.onPatientLookup = function () {
            $scope.patientMatches = undefined;
            $scope.criteria = {};
            $scope.criteria["lastname"] = $scope.query;
            $scope.page = 0;
            $scope.patientMatches = patientService.patientLookup($scope.criteria, $scope.page);
        };


        $scope.choosePatient = function(patient) {
            $scope.patient = patient;
            $scope.patientMatches = undefined;
            $scope.searchEnabled = false;
            $scope.getPatientInbox();
//            patientService.mrnIHealthSpace({mrn: patient.mrn}, function(booleanTO) {
//                $scope.ihsMrn = booleanTO.value == 'true';
//            });
            $scope.mrnIhsAuthorization();
        }

        $scope.mrnIhsAuthorization = function() {

            $scope.ihsMrn = false;

            practiceService.getPracticesCallback(function(data) {
                if (data != null && data.length > 0) {
                    var practiceCodes = new Array()
                    for (var i = 0;i<data.length;i++) {
                        var practice = data[i];
                        var hasPracticeCode = false;
                        if (practiceCodes.length > 0) {
                            for (var i2 = 0;i2<practiceCodes.length;i2++) {
                                if (practiceCodes[i2] == practice.code) {
                                    hasPracticeCode = true;
                                    break;
                                }
                            }
                        }
                        if (hasPracticeCode == false) practiceCodes.push(practice.code);
                    }

                    for (var i = 0;i<practiceCodes.length;i++) {
                        var practiceCode = practiceCodes[i];

                        enrollmentService.getEnrollmentBadge({id: $scope.patient.mrn + "@mrn.mgh.harvard.edu", groupId: practiceCode}, function(data) {
                            var result = data.result;
                            if (result == '2') $scope.ihsMrn = true;
                        });
                        if ($scope.ihsMrn == true) break;
                    }
                }
            });

        }

        $scope.enableSearch = function() {
            $scope.searchEnabled = true;
            $scope.patient = null;
            $scope.ihsMrn = false;
            $scope.inbox = undefined;
        }

        $scope.onNextPage = function() {
            $scope.page++;
            $scope.patientMatches = patientService.patientLookup($scope.criteria, $scope.page);
        };

        $scope.onPreviousPage = function() {
            if ($scope.page > 0) $scope.page--;
            $scope.patientMatches = patientService.patientLookup($scope.criteria, $scope.page);
        };


        $scope.onViewForm = function (mrn, instanceId) {
            $scope.closeBtn = true;
            $scope.form = formsService.getForm({mrn: mrn, instanceId: instanceId});
            $scope.viewFormModal = true;
        };


        $scope.onPrintForm = function () {
            // NOTE: onPrintForm
            $('#printFormFrame').contents().find('html').html($('.sprout-form-content').html());
            $('#printFormFrame').contents().find('html').find(".sprout-forms-nav-btn").remove();
            $('#printFormFrame').contents().find('html').find(".sprout-page-relevant").show();
            $("#printFormFrame").get(0).contentWindow.print();
        };


        $scope.closeViewForm = function () {
            $scope.viewFormModal = false;
        };

        $scope.changeExpirationDate = function() {
            console.log("expirationDate changed: " + publication.expirationDate);
        }

        // Fixme: remove me...
//        $scope.patient = {mrn: "5008268"};
//        $scope.choosePatient($scope.patient);

    });
