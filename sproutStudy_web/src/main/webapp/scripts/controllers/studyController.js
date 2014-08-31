'use strict';

angular.module('sproutStudyApp')
    .controller('studyController', function ($scope, $filter, studyService, patientService, formsService) {

        $scope.patientMatches = undefined;
        $scope.searchEnabled = true;

        $scope.query = "";
//        $scope.query = "5008268";

        $scope.patient = null;

//        $scope.publicationsMaster = angular.copy(formsService.getPublications());

        $scope.publicationKey = null;

        $scope.inbox = 0;

        $scope.ihsMrn = false;

        $scope.provider = null;
        $scope.expirationDate = null;

        $scope.viewName = "patient";

        $scope.minDeliveryDate = new Date();

        $scope.cohort = null;
        $scope.cohorts = null;

        $scope.statuses = null;

        $scope.panes = [
            { id: "0", title: "Search", content:"", active: true }
        ];

        $scope.test = function() {
            alert("test");
        }

        $scope.active = function() {
            return $scope.panes.filter(function(pane){
                return pane.active;
            })[0];
        };

        $scope.addPane = function(title, instanceId, nonce) {
//            var id = $scope.panes.length;
            var id = generateUUID();

            console.log("guid: " + id);

            $scope.panes.push({
                id: id,
                title: title,
                content: '<iframe id="iframe-' + id + '" name="iframe-' + id + '" src="/prompt/?instanceId=' + instanceId + '&nonce=' + nonce + '&debug=true" class="appFrame" />',
                active: true
            });
        };

//        $scope.closePane = function (pane) {
//            console.log("close pane: " + pane.id);
//            pane.active = false;
//            $scope.panes[0].active = true;
////            $scope.panes.splice($scope.panes.indexOf(pane), 1);
//            $scope.panes = $filter('filter')($scope.panes, {id: '!'+pane.id});
//        };

        $scope.closePane = function () {

            var pane = $scope.active();

            console.log("close pane: " + pane.id);
            pane.active = false;
            $scope.panes[0].active = true;
//            $scope.panes.splice($scope.panes.indexOf(pane), 1);

//            $scope.panes = $filter($scope.panes, {id: pane.id})
            $scope.panes = $filter('filter')($scope.panes, {id: '!'+pane.id});
//            $scope.panes = $filter('filter')($scope.panes, { id: pane.id}, function(paneItem, pane){
//                return (paneItem.id !== pane.id);
//            });




//            mycollection = $filter('filter')(myCollection, { id: theId }, function (obj, test) {
//                return obj !== test;
        };

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

        $scope.getCohortAuthorizations = function() {
            studyService.getCohortAuthorizations({}, function(data) {
                $scope.cohorts = data;
            });
        }

        studyService.getCohortAuthorizations({}, function(data) {
            $scope.cohorts = data;
        });

        studyService.getLastSelectedCohort({}, function(data) {
           $scope.cohort = data;
        });

        $scope.findCohortMember = function() {
            $scope.patientMatches = undefined;
            studyService.findCohortMember({cohortQueryURL: $scope.cohort.cohortQueryURL ,query: $scope.query}, function(data) {
                $scope.patientMatches = data;
//                console.log("data: " + data.length);
            });
        }

        $scope.getSubjectInbox = function() {
            $scope.inbox = 1;
            patientService.getSproutInbox({id: $scope.subject.id, allInd: true}, function(data) {
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

        $scope.onDeliverForm = function(subject, form) {
            $scope.deliveringInd = true;
            $scope.deliveryError = null;

            var expirationDate = null;

//            if (publication.expirationDate !== undefined && publication.expirationDate !== null) {
//                try {
//                    var yyyy = publication.expirationDate.getFullYear().toString();
//                    var mm = (publication.expirationDate.getMonth()+1).toString(); // getMonth() is zero-based
//                    var dd  = publication.expirationDate.getDate().toString();
//                    expirationDate = (mm[1]?mm:"0"+mm[0]) + "/" + (dd[1]?dd:"0"+dd[0]) + "/" + yyyy; // padding
//                } catch (e) {}
//            }

            formsService.deliverForm({mrn: subject.id, publicationKey: form.publicationKey, provider: null, expirationDate: null}, function(data) {
                console.log("data.instanceId: " + data.instanceId);
                console.log("data.status: " + data.status);
                if (data.instanceId != null) {
                    $scope.getSubjectInbox();
                    $scope.applyForNonce(data.instanceId);
                    $scope.deliverFormModal = false;
                    $scope.deliveringInd = false;
                } else {
                    $scope.deliveringInd = 'error';
                    $scope.deliveryError = data.message;
                }
            });

        };

        $scope.applyForNonce = function(instanceId) {
            formsService.applyForNonce({instanceId: instanceId}, function(data) {
                var nonce = data.nonce;
                console.log("nonce: " + nonce);
            });
        };

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

//            $scope.publications = angular.copy($scope.publicationsMaster);


        }

        $scope.demoShowPubs = function() {
//            $scope.publications = angular.copy($scope.publicationsMaster);
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

        $scope.chooseCohort = function(cohort) {
            $scope.cohort = cohort;
            $scope.patientMatches = undefined;
            $scope.searchEnabled = true;
            studyService.setSessionCohort({cohortId: cohort.id})
        }

        $scope.changeCohort = function() {
            $scope.cohort = null;
        }

        $scope.chooseCohortMember = function(subject) {
            $scope.subject = subject;
            $scope.patientMatches = undefined;
            $scope.searchEnabled = false;
            $scope.getSubjectInbox();
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
            $scope.form = formsService.getForm({schema: "mgh", mrn: $scope.subject.id, instanceId: instanceId});
            $scope.viewFormModal = true;
        };

        $scope.onOpenForm = function (form) {

            console.log("form: " + form);

            formsService.applyForNonce({instanceId: form.instanceId}, function(data) {
                var nonce = data.nonce;
                var tabTitle = $scope.subject.prettyName + " (" + $scope.subject.id + ") - " + form.title;

                $scope.addPane(tabTitle, form.instanceId, nonce);
                setTimeout(sizeAppFrame, 1000);
            });
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
