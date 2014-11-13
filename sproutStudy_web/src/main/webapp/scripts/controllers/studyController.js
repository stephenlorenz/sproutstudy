'use strict';

angular.module('sproutStudyApp')
    .controller('studyController', function ($log, $scope, $filter, $timeout, studyService, patientService, formsService, cohortService, sessionService) {


        $scope.cohortLoaded = false;

        $scope.patientMatches = undefined;
        $scope.recentCohortMembers = undefined;
        $scope.mutableForms = undefined;
        $scope.allForms = undefined;
        $scope.studyInbox = undefined;
        $scope.searchEnabled = true;

        $scope.searchReturned = false;
        $scope.searchInprogress = false;

//        $scope.query = "buster";
        $scope.sendMessageForm = null;
        $scope.sendMessageButtonText = "Send";
        $scope.sendingMessage = false;

        $scope.deleteFormButtonText = "Delete";
        $scope.deletingMessage = false;



        $scope.query = "";

        $scope.patient = null;

        $scope.cohortAuthorizations = null;

        $scope.publicationKey = null;

        $scope.inbox = 0;

        $scope.ihsMrn = false;

        $scope.provider = null;
        $scope.expirationDate = null;

        $scope.openingForm = false;

        $scope.viewName = "patient";

        $scope.tempId = null;

        $scope.addSubjectInd = false;
        $scope.demographicForm = false;
        $scope.demographicFormContent = null;

        $scope.minDeliveryDate = new Date();

        $scope.message = null;

        $scope.messageText = null;
        $scope.messageTo = null;

        $scope.session = null;

        $scope.modalSmallOpts = {
            backdropFade: true,
            dialogFade: true,
            dialogClass: 'modal modal-200-600'
        };

        $scope.cohort = function() {
            return cohortService.getCohort();
        }

        $scope.session = function() {
            return sessionService.getSession();
        }

        $scope.member = function() {
            return cohortService.getMember();
        }

        $scope.cohorts = null;

        $scope.statuses = null;
        $scope.formFilterRule = undefined;
        $scope.statusesIncomplete = null;
        $scope.allFormsFilterStatus = null;
        $scope.allFormsFilterForm = null;

        $scope.addPaneOrig = function(title, instanceId, nonce) {
            addPaneContent(title, instanceId, nonce);
        };

        $scope.addPaneForm = function(form, nonce) {
            addPaneContentForm(form, nonce);
        };

        $scope.formFilter = function (item){
            if ($scope.status !== undefined) {
                if (item.inboxStatus != $scope.status) return false;
            }
            if ($scope.formFilterRule !== undefined) {
                if (item.title != $scope.formFilterRule) return false;
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
        $scope.onFilterByForm = function(formFilterRule) {
//            console.log("filtering by status: " + status);
            if (formFilterRule !== undefined) {
                $scope.formFilterRule = formFilterRule;
            } else {
                $scope.formFilterRule = undefined;
            }
        }

        studyService.getSession({}, function(data) {
            sessionService.setSession(data);
        });



        $scope.getAuthorizedCohorts = function() {
            studyService.getAuthorizedCohorts({}, function(data) {
                $scope.cohorts = data;
            });
        }

        $scope.getCohortAuthorizations = function() {
            studyService.getCohortAuthorizations({}, function(data) {
                $scope.cohortAuthorizations = data;
            });
        }

        studyService.getAuthorizedCohorts({}, function(data) {
            $scope.cohorts = data;
        });

        studyService.getLastSelectedCohort({}, function(data) {
            cohortService.setCohort(data);
            $scope.getCohortAuthorizations();
            $scope.cohortLoaded = true;
        });

        $scope.onTest = function() {
            $scope.searchReturned = true;

        }

        $scope.findCohortMember = function() {
            $scope.patientMatches = undefined;
            $scope.searchReturned = true;
            $scope.searchInprogress = true;
            studyService.findCohortMember({cohortQueryURL: cohortService.getCohort().cohortQueryURL ,query: $scope.query}, function(data) {
                $scope.searchInprogress = false;
                $scope.patientMatches = data;
            });
        }

        $scope.getRecentCohortMembers = function() {
            $scope.recentCohortMembers = undefined;
            studyService.getRecentCohortMembers({}, function(data) {
                $scope.recentCohortMembers = data;
//                console.log("data: " + data.length);
            });
        }

        $scope.studyInboxSort = {
            column: 'activityDate',
            descending: true
        };

        $scope.studyInboxSortClass = function(column) {
            if (column == $scope.studyInboxSort.column) {
                if ($scope.studyInboxSort.descending) {
                    return 'icon-chevron-down';
                } else {
                    return 'icon-chevron-up';
                }
            }
        };
        $scope.studyInboxSortStyle = function(column) {
            if (column == $scope.studyInboxSort.column) {
                return '7px';
            } else {
                return '0px';
            }
        };

        $scope.studyInboxChangeSort = function(column) {
            var sort = $scope.studyInboxSort;
            if (sort.column == column) {
                sort.descending = !sort.descending;
            } else {
                sort.column = column;
                sort.descending = false;
            }
        };

        $scope.formsSort = {
            column: 'statusDate',
            descending: true
        };

        $scope.formsSortClass = function(column) {
            if (column == $scope.formsSort.column) {
                if ($scope.mutableFormsSort.descending) {
                    return 'icon-chevron-down';
                } else {
                    return 'icon-chevron-up';
                }
            }
        };
        $scope.formsSortStyle = function(column) {
            if (column == $scope.formsSort.column) {
                return '7px';
            } else {
                return '0px';
            }
        };

        $scope.formsChangeSort = function(column) {
            var sort = $scope.formsSort;
            if (sort.column == column) {
                sort.descending = !sort.descending;
            } else {
                sort.column = column;
                sort.descending = false;
            }
        };

        $scope.mutableFormsSort = {
            column: 'statusDate',
            descending: true
        };

        $scope.mutableFormsSortClass = function(column) {
            if (column == $scope.mutableFormsSort.column) {
                if ($scope.mutableFormsSort.descending) {
                    return 'icon-chevron-down';
                } else {
                    return 'icon-chevron-up';
                }
            }
        };
        $scope.mutableFormsSortStyle = function(column) {
            if (column == $scope.mutableFormsSort.column) {
                return '7px';
            } else {
                return '0px';
            }
        };

        $scope.mutableFormsChangeSort = function(column) {
            var sort = $scope.mutableFormsSort;
            if (sort.column == column) {
                sort.descending = !sort.descending;
            } else {
                sort.column = column;
                sort.descending = false;
            }
        };

//        $scope.getMutableForms = function() {
//            $scope.mutableForms = undefined;
//            studyService.getMutableForms({}, function(data) {
//                $scope.mutableForms = data;
//
//                $scope.statusesIncomplete = new Array();
//
//                for (var i = 0; i < data.length; i++) {
//                    var includeInd = true;
//                    if ($scope.statusesIncomplete !== undefined && $scope.statusesIncomplete.length > 0) {
//                        for (var i2=0;i2<$scope.statusesIncomplete.length;i2++) {
//                            if ($scope.statusesIncomplete[i2] == data[i].inboxStatus) {
//                                includeInd = false;
//                            }
//                        }
//
//                    }
//
//                    if (includeInd) $scope.statusesIncomplete.push(data[i].inboxStatus);
//                }
//
//
//            });
//        }

        $scope.getAllForms = function() {
            $scope.allForms = undefined;
            studyService.getAllForms({page: 1, rows: 5}, function(data) {
                $scope.allForms = data;

                $scope.allFormsFilterStatus = new Array();
                $scope.allFormsFilterForm = new Array();

                for (var i = 0; i < data.length; i++) {
                    var statusIncludeInd = true;
                    var formIncludeInd = true;
                    if ($scope.allFormsFilterStatus !== undefined && $scope.allFormsFilterStatus.length > 0) {
                        for (var i2=0;i2<$scope.allFormsFilterStatus.length;i2++) {
                            if ($scope.allFormsFilterStatus[i2] == data[i].inboxStatus) {
                                statusIncludeInd = false;
                            }
                        }

                    }
                    if ($scope.allFormsFilterForm !== undefined && $scope.allFormsFilterForm.length > 0) {
                        for (var i2=0;i2<$scope.allFormsFilterForm.length;i2++) {
                            if ($scope.allFormsFilterForm[i2] == data[i].title) {
                                formIncludeInd = false;
                            }
                        }

                    }

                    if (statusIncludeInd) $scope.allFormsFilterStatus.push(data[i].inboxStatus);
                    if (formIncludeInd) $scope.allFormsFilterForm.push(data[i].title);
                }


            });
        }

        $scope.getStudyInbox = function() {
            $scope.studyInbox = undefined;
            studyService.getStudyInbox({}, function(data) {
                $scope.studyInbox = data;
            });
        }

        $scope.getStudyInbox();
        $scope.getRecentCohortMembers();
//        $scope.getMutableForms();
        $scope.getAllForms();

        $scope.setNewSubject = function(id, instanceId) {
            studyService.findCohortMember({cohortQueryURL: cohortService.getCohort().cohortQueryURL, query: id}, function(data) {

                $scope.patientMatches = data;
                $scope.searchReturned = true;
                if ($scope.patientMatches.length == 1) {
                    $scope.subject = $scope.patientMatches[0];

                    if ($scope.subject != null) {
                        var formObject = $scope.newFormConstructor(instanceId, null, "New Subject", $scope.subject.fullName, $scope.subject.firstName, $scope.subject.lastName, $scope.subject.id);

                        $scope.onComposeMessage(formObject);

                        cohortService.setMember($scope.subject);

                        var params = [{identity: $scope.subject.id + "@mgh"}, {identity: $scope.tempId + "@SPROUT_STUDY_TEMP_ID"}];

                        $scope.getSubjectInbox(params);
                    }
                    $scope.searchEnabled = false;
                    $scope.patientMatches = undefined;
                } else {
                    $scope.searchEnabled = true;
                    $scope.searchReturned = false;
//                    $scope.getRecentCohortMembers();
                    $scope.patientMatches = undefined;
                    cohortService.clearMember();
                }
            });
        };

        $scope.onDeleteStudyInboxForm = function(message) {
            studyService.deleteInboxMessage({id: message.id}, function(data) {
                message.status = data.status;
            });

        };

        $scope.getSubjectInbox = function(params) {
            $scope.addSubjectInd = false;
            $scope.demographicFormContent = null;
            $scope.inbox = 1;
            if ($scope.subject != null) {
                cohortService.setMember($scope.subject);
                if (params == null || params == '') {
                    params = {identity: $scope.subject.id + "@mgh", allInd: true};
                }
                patientService.getSproutInbox(params, function(data) {
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
                    $scope.getRecentCohortMembers();
                    $scope.getStudyInbox();
//                console.log("data: " + data.length);
                });
            } else {
                cohortService.clearMember();
            }
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

            formsService.deliverForm({schema: "mgh", id: subject.id, publicationKey: form.publicationKey, provider: null, expirationDate: null}, function(data) {
//                console.log("data.instanceId: " + data.instanceId);
//                console.log("data.status: " + data.status);
                if (data.instanceId != null) {
                    $scope.getSubjectInbox();

                    var instanceId = data.instanceId;
                    var formObject = $scope.newFormConstructor(instanceId, form.publicationKey, form.name, subject.fullName, subject.firstName, subject.lastName, subject.id);

                    formsService.applyForNonce({instanceId: instanceId, subjectName: subject.fullName, subjectId: subject.id}, function(dataCallback) {
                        var nonce = dataCallback.nonce;
//                        var tabTitle = $scope.subject.prettyName + " (" + $scope.subject.id + ") - " + formObject.title;

                        $scope.addPaneForm(formObject, nonce);
                        setTimeout(sizeAppFrame, 1000);
                    });


//                    $scope.onOpenFormByInstanceId(data.instanceId, form.name);

                    $scope.deliverFormModal = false;
                    $scope.deliveringInd = false;
                } else {
                    $scope.deliveringInd = 'error';
                    $scope.deliveryError = data.message;
                }
            });

        }

        $scope.onSendForm = function(subject, form) {
            $scope.deliveringInd = true;
            $scope.deliveryError = null;

            var expirationDate = null;

            var formObject = $scope.newFormConstructor(null, form.publicationKey, form.name, subject.fullName, subject.firstName, subject.lastName, subject.id);
            $scope.onComposeMessage(formObject);

        };

        $scope.newFormConstructor = function(instanceId, publicationKey, title, fullName, firstName, lastName, subjectId) {
            return {instanceId: instanceId, title: title, identityFullName: fullName, identityFirstName: firstName, identityLastName: lastName, identityPrimaryId: subjectId, publicationKey: publicationKey};
        }

        $scope.addSubject = function() {
//            $log.log("addSubject....");

            $scope.deliveringInd = true;
            $scope.deliveryError = null;

            var form = null;

            $.each(cohortService.getCohort().forms, function(index, tmpForm) {
                if (tmpForm.demographic) form = tmpForm;
            });

//            $log.log("demographicForm: " + form.name);

            $scope.tempId = generateUUID();

            formsService.deliverForm({schema: "SPROUT_STUDY_TEMP_ID", id: $scope.tempId, publicationKey: form.publicationKey, provider: null, expirationDate: null}, function(data) {
//                console.log("data.instanceId: " + data.instanceId);
//                console.log("data.status: " + data.status);
                if (data.instanceId != null) {
                    $scope.onDemographicFormByInstanceId(data.instanceId);
                    $scope.deliverFormModal = false;
                    $scope.deliveringInd = false;
                    $scope.addSubjectInd = true;
                } else {
                    $scope.deliveringInd = 'error';
                    $scope.deliveryError = data.message;
                }
            });
        }


        $scope.applyForNonce = function(instanceId, subjectName, subjectId) {
            formsService.applyForNonce({instanceId: instanceId, subjectName: subjectName, subjectId: subjectId}, function(data) {
                var nonce = data.nonce;
//                console.log("nonce: " + nonce);
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

        $scope.onComposeMessage = function(form) {
//            $log.log("onComposeMessage.instanceId: " + form.instanceId)
//            $log.log("onComposeMessage.formTitle: " + form.title)
            $scope.messageText = "Please review this form.";
            $scope.sendMessageButtonText = "Send";
            $scope.sendingMessage = false;
            $scope.messageTo = null;
            $scope.sendMessageForm = form;
            $scope.sendMessageModal = true;
//            $scope.$apply();
        }
        $scope.onTest = function(instanceId) {

            $log.log("onTest: " + instanceId)

            $scope.onComposeMessage(instanceId);
        }

        $scope.onCloseSendMessageModal = function() {
            $scope.sendMessageModal = false;
        }
        $scope.onCloseDeleteFormModal = function() {
            $scope.deleteFormModal = false;
        }

        $scope.onDeleteForm = function() {
            $scope.deleteFormButtonText = "Deleting";
            $scope.deletingForm = true;
            $scope.deleteMessageText = "Deleting form...please wait...";

            studyService.deleteSubmission({instanceId: $scope.deleteFormInstance}, function(data) {
                if (data.value == 'false') {
                } else {
                    $scope.getSubjectInbox();
                }
                $scope.onCloseDeleteFormModal();
            });

        }

        $scope.onSendMessage = function() {

            $scope.sendMessageButtonText = "Sending...";
            $scope.sendingMessage = true;

            if ($scope.sendMessageForm.instanceId == null) {
                formsService.deliverForm({schema: "mgh", id: $scope.sendMessageForm.identityPrimaryId, publicationKey: $scope.sendMessageForm.publicationKey, provider: null, expirationDate: null}, function(data) {
                    if (data.instanceId != null) {
                        $scope.getSubjectInbox();

                        var instanceId = data.instanceId;
                        $scope.sendMessageForm.instanceId = instanceId;
                        $scope.deliverFormModal = false;
                        $scope.deliveringInd = false;


                        $.each($scope.messageTo, function(index, recipient) {
                            var username = recipient.user.username;
                            studyService.sendMessage({to: username, form: JSON.stringify($scope.sendMessageForm), message: encodeURIComponent($scope.messageText), instanceId: $scope.sendMessageForm.instanceId, formTitle: $scope.sendMessageForm.title, subjectId: cohortService.getMember().id, subjectName: cohortService.getMember().fullName}, function(data) {
                                $scope.sendMessageModal = false;
//                                $scope.sendMessageButtonText = "Send";
                                $scope.sendingMessage = false;
                                $scope.getStudyInbox();
                            });
                        });

                    } else {
                        $scope.deliveringInd = 'error';
                        $scope.deliveryError = data.message;
//                        $scope.sendMessageButtonText = "Send";
                        $scope.sendingMessage = false;
                    }
                });
            } else {
                $.each($scope.messageTo, function(index, recipient) {
                    var username = recipient.user.username;
                    studyService.sendMessage({to: username, form: JSON.stringify($scope.sendMessageForm), message: encodeURIComponent($scope.messageText), instanceId: $scope.sendMessageForm.instanceId, formTitle: $scope.sendMessageForm.title, subjectId: cohortService.getMember().id, subjectName: cohortService.getMember().fullName}, function(data) {
                        $scope.sendMessageModal = false;
//                        $scope.sendMessageButtonText = "Send";
                        $scope.sendingMessage = false;
                        $scope.getStudyInbox();
                    });
                });
            }

        }

        $scope.openDeliverFormModal = function() {
            $scope.deliveringInd = false;
            $scope.deliverFormModal = true;
        }

        $scope.demoShowPubs = function() {
//            $scope.publications = angular.copy($scope.publicationsMaster);
        }

        $scope.closeDeliverFormModal = function () {
            $scope.deliverFormModal = false;
        };

        $scope.onPatientLookup = function () {
            $scope.patientMatches = undefined;
            $scope.searchReturned = false;
            $scope.criteria = {};
            $scope.criteria["lastname"] = $scope.query;
            $scope.page = 0;
            $scope.patientMatches = patientService.patientLookup($scope.criteria, $scope.page);
        };

        $scope.chooseCohort = function(cohort) {
            $scope.changeCohort();
            cohortService.setCohort(cohort);
            $scope.enableSearch();
            studyService.setSessionCohort({cohortId: cohort.id}, function(data) {
                $scope.getCohortAuthorizations();
                $scope.getStudyInbox();
                $scope.getRecentCohortMembers();
                $scope.getAllForms();
            })
        }

        $scope.changeCohort = function() {
            cohortService.setCohort(null);
            $scope.studyInbox = undefined;
            $scope.recentCohortMembers = undefined;
            $scope.mutableForms = undefined;
            $scope.patientMatches = undefined;
        }

        $scope.chooseCohortMember = function(subject) {
            cohortService.setMember(subject);
            $scope.subject = subject;
            $scope.patientMatches = undefined;
            $scope.searchEnabled = false;
            $scope.getSubjectInbox();
        }

        $scope.enableSearch = function() {
            cohortService.clearMember();
            $scope.searchEnabled = true;
            $scope.searchReturned = false;
            $scope.addSubjectInd = false;
            $scope.patient = null;
            $scope.ihsMrn = false;
            $scope.inbox = undefined;
            clearAllFormTabs();
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

            $scope.openingForm = true;

            var subject = $scope.subject;
//            console.log("form: " + form);

            formsService.applyForNonce({instanceId: form.instanceId, subjectName: subject.fullName, subjectId: subject.id}, function(data) {
                var nonce = data.nonce;
//                var tabTitle = $scope.subject.prettyName + " (" + $scope.subject.id + ") - " + form.title;
//                var tabTitle = form.title;

                if ($scope.subject != null) form = $scope.addSubjectToForm(form);

                $scope.addPaneForm(form, nonce);
                setTimeout(sizeAppFrame, 1000);
                $scope.openingForm = false;
            });
        };
        $scope.onOpenMutableForm = function (form) {

            $scope.openingForm = true;

            var subject = $scope.getSubjectFromForm(form);

            cohortService.setMember(subject);
            $scope.subject = subject;
            $scope.patientMatches = undefined;
            $scope.searchEnabled = false;
            $scope.getSubjectInbox();

            formsService.applyForNonce({instanceId: form.instanceId, subjectName: subject.fullName, subjectId: subject.id}, function(data) {
                var nonce = data.nonce;
//                var tabTitle = $scope.subject.prettyName + " (" + $scope.subject.id + ") - " + form.title;
//                var tabTitle = form.title;

                if ($scope.subject != null) form = $scope.addSubjectToForm(form);

                $scope.addPaneForm(form, nonce);
                setTimeout(sizeAppFrame, 1000);
                $scope.openingForm = false;
            });
        };

        $scope.addSubjectToForm = function(form) {
            form.identityPrimarySchema = '';
            form.identityPrimaryId = $scope.subject.id;
            form.identityFirstName = $scope.subject.firstName;
            form.identityLastName = $scope.subject.lastName;
            form.identityFullName = $scope.subject.fullName;
            form.identityPrettyName = $scope.subject.fullName;
            return form;
        };

        $scope.onOpenMessage = function(message) {
            studyService.markInboxMessageAsRead({id: message.id}, function(data) {
                message.status = data.status;
            });
            $scope.message = message;
        };

        $scope.closeMessage = function() {
            $scope.message = null;
        }

        $scope.onOpenStudyInboxForm = function (message) {

            studyService.markInboxMessageAsRead({id: message.id}, function(data) {
                message.status = data.status;
            });

//            console.log("onOpenStudyInboxForm");
            $scope.openingForm = true;

            var form = jQuery.parseJSON(message.form);

//            $log.log("onOpenStudyInboxForm.form: " + form);
//            $log.log("onOpenStudyInboxForm.form.title: " + form.title);

            var subject = $scope.getSubjectFromForm(form);

            cohortService.setMember(subject);
            $scope.subject = subject;
            $scope.patientMatches = undefined;
            $scope.searchEnabled = false;
            $scope.getSubjectInbox();

            formsService.applyForNonce({instanceId: form.instanceId, subjectName: subject.fullName, subjectId: subject.id}, function(data) {
                var nonce = data.nonce;
                var tabTitle = form.title;

                $scope.addPaneForm(form, nonce);
                setTimeout(sizeAppFrame, 1000);
                $scope.openingForm = false;
            });
        };

        $scope.getSubjectFromForm = function(form) {
            return {fullName: form.identityFullName, prettyName: form.identityFullName, firstName: form.identityFirstName, lastName: form.identityLastName, id: form.identityPrimaryId};
        }

//        $scope.onOpenFormByInstanceId = function (instanceId, title) {
//
////            console.log("form: " + form);
//
//            formsService.applyForNonce({instanceId: instanceId}, function(data) {
//                var nonce = data.nonce;
//                var tabTitle = $scope.subject.prettyName + " (" + $scope.subject.id + ") - " + title;
//
//                $scope.addPane(tabTitle, instanceId, nonce);
//                setTimeout(sizeAppFrame, 1000);
//            });
//        };
        $scope.onDemographicFormByInstanceId = function (instanceId) {

            formsService.applyForNonce({instanceId: instanceId, subjectName: "New Subject", subjectId: "Unknown"}, function(data) {
                var nonce = data.nonce;
                var tabTitle = {fullName: "New Subject", id: 0};
                cohortService.setMember(tabTitle);

                var content = '<iframe id="iframe-' + instanceId + '" name="iframe-' + instanceId + '" instanceId="' + instanceId + '" src="/prompt/?instanceId=' + instanceId + '&nonce=' + nonce + '&debug=true&showThanks=false&disableSave=true" class="appFrame iframe-demographic-form-content" />';
                $scope.demographicFormContent = content;

//                $scope.addPane(tabTitle, instanceId, nonce);
                setTimeout(sizeAppFrame, 1000);
            });
        };

        $scope.addSubjectButton = "";
        $scope.expandAddSubjectButton =  function() {
            $scope.addSubjectButton = "  Add New Subject";
        }
        $scope.collapseAddSubjectButton =  function() {
            $scope.addSubjectButton = "";
        }

        $scope.searchButton = "  Search";
        $scope.expandSearchButton =  function() {
//            $scope.searchButton = "  Search";
        }
        $scope.collapseSearchButton =  function() {
//            $scope.searchButton = "";
        }

        $scope.newFormButton = "  New Form";
        $scope.expandNewFormButton =  function() {
//            $scope.newFormButton = "  New Form";
        }
        $scope.collapseNewFormButton =  function() {
//            $scope.newFormButton = "";
        }

        $scope.sendFormButton = "  Send Form";
        $scope.expandSendFormButton =  function() {
//            $scope.sendFormButton = "  Send Form";
        }
        $scope.collapseSendFormButton =  function() {
//            $scope.sendFormButton = "";
        }

        $scope.onDeleteSubmission = function (instanceId) {
            $scope.deleteFormInstance = instanceId;
            $scope.deleteMessageText = "Are you sure you want to delete this form?  This action cannot be undone!";
            $scope.deleteFormButtonText = "Delete";
            $scope.deletingForm = false;
            $scope.deleteFormModal = true;
        }

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

        $scope.testSendMessageModal = function() {
            $scope.messageText = null;
            $scope.messageTo = null;
            $scope.sendMessageModal = true;

        }


    });
