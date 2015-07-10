'use strict';

angular.module('sproutStudyApp')
    .controller('studyController', function ($log, $scope, $filter, $timeout, $window, $websocket, studyService, patientService, formsService, cohortService, sessionService, transformService) {

        $scope.defaultTab = 'inbox';

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

        $scope.allFormsCurrentPage = 1;
        $scope.allFormsPageCount = 1;
        $scope.allFormsMetadata = undefined;
        $scope.allFormsRowsPerPage = 15;
        $scope.allFormsOrderBy = "date_of_status";
        $scope.allFormsOrderDirection = "DESC"
        $scope.allFormsFilterFormPublicationKey = null;
        $scope.allFormsFilterFormTitle = null;

        $scope.filterChanged = false;

        $scope.template = undefined;

        $scope.activeSproutInboxStatuses = null;
        $scope.assignments = null;

        $scope.targetDateProxy = undefined;
        $scope.targetDate = undefined;

        $scope.query = "";

        $scope.patient = null;

        $scope.cohortAuthorizations = null;

        $scope.publicationKey = null;

        $scope.hasNarrative = undefined;
        $scope.formLoaded = undefined;
        $scope.activeInstanceId = undefined;

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

        $scope.isAdmin = function() {
            return studyService.isAdmin();
        }

        $scope.isManager = function() {
           return studyService.isManager();
        }

        $scope.modalSmallOpts = {
//            backdropFade: true, // These two settings
//            dialogFade: true,
            dialogClass: 'modal modal-200-600'
        };

        $scope.cohort = function() {
            return cohortService.getCohort();
        }

        $scope.hasDemographicForm = function() {
            return cohortService.hasDemographicForm();
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
        $scope.allFormsFilterAssignment = undefined;
        $scope.allFormsFilterTargetDate = undefined;
        $scope.allFormsFilterTargetDateProxy = undefined;
        $scope.allFormsFilterForm = null;

        $scope.$watch('allFormsFilterTargetDateProxy', function() {
            $scope.applyAllFormsFilterTargetDateProxy();
        });

        $scope.$watch('targetDateProxy', function() {
            $scope.setTargetDate();
        });

        $scope.getTodayAsDateString = function () {
            var tmpDate = new Date();
            var year = tmpDate.getFullYear();
            var month = (1 + tmpDate.getMonth()).toString();
            month = month.length > 1 ? month : '0' + month;
            var day = tmpDate.getDate().toString();
            day = day.length > 1 ? day : '0' + day;

            return month + '/' + day + '/' + year;
        }

        $scope.applyAllFormsFilterTargetDateProxy = function() {
            //console.log("allFormsFilterTargetDateProxy changed: " + $scope.allFormsFilterTargetDateProxy);
            if ($scope.allFormsFilterTargetDateProxy !== undefined) {
                var year = $scope.allFormsFilterTargetDateProxy.getFullYear();
                var month = (1 + $scope.allFormsFilterTargetDateProxy.getMonth()).toString();
                month = month.length > 1 ? month : '0' + month;
                var day = $scope.allFormsFilterTargetDateProxy.getDate().toString();
                day = day.length > 1 ? day : '0' + day;

                $scope.allFormsFilterTargetDate = month + '/' + day + '/' + year;

                var tmpDate = new Date();
                var year = tmpDate.getFullYear();
                var month = (1 + tmpDate.getMonth()).toString();
                month = month.length > 1 ? month : '0' + month;
                var day = tmpDate.getDate().toString();
                day = day.length > 1 ? day : '0' + day;

                var tmpDateString = month + '/' + day + '/' + year;

                if (tmpDateString == $scope.allFormsFilterTargetDate) {
                    $scope.allFormsFilterTargetDate = 'today';
                }
                $scope.filterChanged = true;

            } else {
                if ($scope.allFormsFilterTargetDate !== undefined) {
                    $scope.filterChanged = true;
                }
                $scope.allFormsFilterTargetDate = undefined;
            }
            $scope.getAllForms();
        }

        $scope.clearFilterTargetDate = function() {
            $scope.allFormsFilterTargetDateProxy = undefined;
            $scope.filterChanged = true;
            $scope.applyAllFormsFilterTargetDateProxy();
        }

        $scope.clearFilterAssignment = function() {
            $scope.allFormsFilterAssignment = undefined;
            $scope.filterChanged = true;
            $scope.getAllForms();
        };

        $scope.onResetFilters = function () {
            $scope.allFormsFilterStatus = null;
            $scope.allFormsFilterAssignment = undefined;
            $scope.allFormsFilterTargetDate = undefined;
            $scope.allFormsFilterTargetDateProxy = undefined;
            $scope.allFormsFilterForm = null;
            $scope.allFormsFilterFormTitle = null;
            $scope.filterChanged = false;
            $scope.getAllForms();
        };

        $scope.onSaveDefaultFilter = function () {
            $scope.setSessionFormFilter();
        };

        $scope.clearFilterForm = function() {
            $scope.allFormsFilterForm = null;
            $scope.allFormsFilterFormTitle = null;
            $scope.getAllForms();
        }

        $scope.clearFilterStatus = function() {
            $scope.allFormsFilterStatus = null;
            $scope.getAllForms();
        }

        $scope.applyIfPossible = function() {
            $timeout(function() {});
        }

        $scope.allFormsFirstPage = function() {
            if ($scope.allFormsCurrentPage > 1) {
                $scope.allFormsCurrentPage = 1;
                $scope.getAllForms();
            }
        }

        $scope.allFormsNextPage = function() {
            if ($scope.allFormsCurrentPage < $scope.allFormsPageCount) {
                $scope.allFormsCurrentPage++;
                $scope.getAllForms();
            }
        }

        $scope.allFormsLastPage = function() {
            if ($scope.allFormsCurrentPage < $scope.allFormsPageCount) {
                $scope.allFormsCurrentPage = $scope.allFormsPageCount;
                $scope.getAllForms();
            }
        }

        $scope.allFormsPreviousPage = function() {
            if ($scope.allFormsCurrentPage > 1) {
                $scope.allFormsCurrentPage--;
                $scope.getAllForms();
            }
        };

        $scope.getUserPreferences = function () {
            studyService.getUserPreferences({}, function(data) {
                var updatedFilter = false;
                $.each(data, function(index, preference) {
                    if (preference.name == 'FORM_FILTER_FORM') {
                        if (preference.value !== undefined && preference.value !== null && preference.value != 'null' && preference.value != 'undefined' && preference.value != '') {
                            //$scope.allFormsFilterForm = preference.value;

                            var formParts = preference.value.split(":");

                            if (formParts !== null && formParts.length == 2) {
                                $scope.allFormsFilterFormPublicationKey = formParts[0];
                                $scope.allFormsFilterFormTitle = formParts[1];
                                updatedFilter = true;
                            }
                        }
                    } else if (preference.name == 'FORM_FILTER_STATUS') {
                        if (preference.value !== undefined && preference.value !== null && preference.value != 'null' && preference.value != 'undefined' && preference.value != '') {
                            $scope.allFormsFilterStatus = preference.value;
                            updatedFilter = true;
                        }
                    } else if (preference.name == 'FORM_FILTER_ASSIGNED_TO') {
                        if (preference.value !== undefined && preference.value !== null && preference.value != 'null' && preference.value != 'undefined' && preference.value != '') {
                            $scope.allFormsFilterAssignment = preference.value;


                            if ($scope.assignments !== null) {
                                $.each($scope.assignments, function(index, assignment) {
                                    if (assignment.value == preference.value) {
                                        $scope.allFormsFilterAssignment = assignment;
                                    }
                                });
                                updatedFilter = true;

                            } else {
                                studyService.getAssignments({"status": $scope.allFormsFilterStatus, "targetDate": $scope.targetDate}, function(assignments) {
                                    $.each(assignments, function(index, assignment) {
                                        if (assignment.value == preference.value) {
                                            $scope.allFormsFilterAssignment = assignment;
                                            $scope.getAllForms();
                                        }
                                    });
                                });
                            }

                        }
                    } else if (preference.name == 'FORM_FILTER_STUDY_DATE') {
                        if (preference.value !== undefined && preference.value !== null && preference.value != 'null' && preference.value != 'undefined' && preference.value != '') {
                            $scope.allFormsFilterTargetDate = preference.value;
                            updatedFilter = true;
                        }
                    } else if (preference.name == 'USER_PREFERENCE_DEFAULT_TAB') {
                        if (preference.value !== undefined && preference.value !== null && preference.value != 'null' && preference.value != 'undefined' && preference.value != '') {
                            $scope.defaultTab = preference.value;
                        }
                    }
                });
                if (updatedFilter) {
                    $scope.getAllForms();
                }
            });
        };

        $scope.setInitialTab = function (tab) {
            //$('.nav-tabs a[href="#' + tab + '"]').tab('show');

        }

        $scope.getUserPreferences();

        $scope.setSessionFormFilter = function () {
            studyService.setSessionFormFilter({"formFilter": ($scope.allFormsFilterFormPublicationKey !== null && $scope.allFormsFilterForm !== undefined && $scope.allFormsFilterForm.length > 0) ? $scope.allFormsFilterFormPublicationKey + ":" + $scope.allFormsFilterForm : null, "assignmentFilter": $scope.allFormsFilterAssignment !== undefined ? $scope.allFormsFilterAssignment.value : $scope.allFormsFilterAssignment, "statusFilter": $scope.allFormsFilterStatus, "targetDateFilter": $scope.allFormsFilterTargetDate}, function(data) {
                if (data.value == false) {
                    $scope.errorMessageText = "Failed to save filter.";
                    $scope.errorFormModal = true;
                } else {
                    $scope.filterChanged = false;
                }
            });
        };

        $scope.addPaneOrig = function(title, instanceId, nonce) {
            addPaneContent(title, instanceId, nonce);
        };

        $scope.addPaneForm = function(form, nonce) {
            addPaneContentForm(form, nonce);
        };

        $scope.formFilter = function (item){


            if ($scope.status !== undefined) {
                if (item.inboxProxies !== undefined && item.inboxProxies.length > 0) {
                    var hasMatch = false;
                    $.each(item.inboxProxies, function (index, proxy) {
                       if (proxy.status == $scope.status.name) hasMatch = true;
                    });
                    if (!hasMatch) return false;
                } else {
                    if (item.inboxStatus != $scope.status.name) return false;
                }
            }
            if ($scope.targetDate !== undefined) {
                try {
                    var tmpDate = new Date(item.targetDate);

                    var year = tmpDate.getFullYear();
                    var month = (1 + tmpDate.getMonth()).toString();
                    month = month.length > 1 ? month : '0' + month;
                    var day = tmpDate.getDate().toString();
                    day = day.length > 1 ? day : '0' + day;

                    var targetDateTmp = month + '/' + day + '/' + year;
                    if ($scope.targetDate != targetDateTmp) return false;
                } catch (e) {
                    return false;
                }
            }
            if ($scope.assignment !== undefined) {
                if (item.inboxProxies !== undefined && item.inboxProxies.length > 0) {
                    var hasMatch = false;
                    $.each(item.inboxProxies, function (index, proxy) {
                        if (proxy.assignedToDisplayName == $scope.assignment.name) hasMatch = true;
                    });
                    if (!hasMatch) return false;
                } else {
                    return false;
                }
            }
            if ($scope.formFilterRule !== undefined) {
                if (item.title != $scope.formFilterRule) return false;
            }
            return true;
        };
        $scope.allFormsFilter = function (item){

            //console.log("allFormsFilter...");
            //console.log("$scope.targetDate: " + $scope.allFormsFilterTargetDate);

            if (item.inboxStatus !== undefined && item.inboxStatus == 'REVOKED') return false;


            if ($scope.status !== undefined) {
                if (item.inboxProxies !== undefined && item.inboxProxies.length > 0) {
                    var hasMatch = false;
                    $.each(item.inboxProxies, function (index, proxy) {
                       if (proxy.status == $scope.status.name) hasMatch = true;
                    });
                    if (!hasMatch) return false;
                } else {
                    if (item.inboxStatus != $scope.status.name) return false;
                }
            }
            if ($scope.allFormsFilterTargetDate !== undefined) {
                try {
                    var tmpDate = new Date(item.targetDate);

                    var year = tmpDate.getFullYear();
                    var month = (1 + tmpDate.getMonth()).toString();
                    month = month.length > 1 ? month : '0' + month;
                    var day = tmpDate.getDate().toString();
                    day = day.length > 1 ? day : '0' + day;

                    var targetDateTmp = month + '/' + day + '/' + year;

                    if ($scope.allFormsFilterTargetDate != targetDateTmp) return false;
                } catch (e) {
                    return false;
                }
            }
            if ($scope.assignment !== undefined) {
                if (item.inboxProxies !== undefined && item.inboxProxies.length > 0) {
                    var hasMatch = false;
                    $.each(item.inboxProxies, function (index, proxy) {
                        if (proxy.assignedToDisplayName == $scope.assignment.name) hasMatch = true;
                    });
                    if (!hasMatch) return false;
                } else {
                    return false;
                }
            }
            if ($scope.formFilterRule !== undefined) {
                if (item.title != $scope.formFilterRule) return false;
            }
            return true;
        };



        $scope.clearTargetDate = function(targetDate) {
            console.log("$scope.targetDate set to undefined 2");

            $scope.targetDate = undefined;
            $scope.filterChanged = true;
        }

        $scope.setTargetDate = function(targetDate) {

            //console.log("setTargetDate");

            if (targetDate !== undefined) {
                $scope.targetDate = targetDate;
            } else {
                if ($scope.targetDateProxy !== undefined) {
                    var year = $scope.targetDateProxy.getFullYear();
                    var month = (1 + $scope.targetDateProxy.getMonth()).toString();
                    month = month.length > 1 ? month : '0' + month;
                    var day = $scope.targetDateProxy.getDate().toString();
                    day = day.length > 1 ? day : '0' + day;

                    $scope.targetDate = month + '/' + day + '/' + year;

                    //console.log("$scope.targetDate: " + $scope.targetDate);

                    //var tmpDate = new Date();
                    //var year = tmpDate.getFullYear();
                    //var month = (1 + tmpDate.getMonth()).toString();
                    //month = month.length > 1 ? month : '0' + month;
                    //var day = tmpDate.getDate().toString();
                    //day = day.length > 1 ? day : '0' + day;
                    //
                    //var tmpDateString = month + '/' + day + '/' + year;
                    //
                    //if (tmpDateString == $scope.targetDate) {
                    //    $scope.targetDate = 'today';
                    //}
                    $scope.filterChanged = true;

                } else {
                    if (targetDate !== undefined) {
                        $scope.filterChanged = true;
                        $scope.targetDate = undefined;
                        console.log("$scope.targetDate set to undefined 1");
                    }
                }
            }
        }
        $scope.onFilterByStatus = function(status) {
            if (status !== undefined) {
                $scope.status = status;
            } else {
                $scope.status = undefined;
            }
            $scope.filterChanged = true;
        }
        $scope.onFilterByAssignment = function(assignment) {
            if (assignment !== undefined) {
                $scope.assignment = assignment;
            } else {
                $scope.assignment = undefined;
            }
            $scope.filterChanged = true;
        }
        $scope.onFilterByForm = function(formFilterRule) {
//            console.log("filtering by status: " + status);
            if (formFilterRule !== undefined) {
                $scope.formFilterRule = formFilterRule;
            } else {
                $scope.formFilterRule = undefined;
            }
            $scope.filterChanged = true;
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
            $scope.bootWebsockets(data);
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
            column: 'deliveryDate',
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
                if ($scope.formsSort.descending) {
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
        $scope.allFormsSortClass = function(column) {
            if (column == $scope.allFormsOrderBy) {
                if ($scope.allFormsOrderDirection == 'DESC') {
                    return 'icon-chevron-down';
                } else {
                    return 'icon-chevron-up';
                }
            }
        };
        $scope.mutableFormsSortStyle = function(column) {
            if (column == $scope.allFormsOrderBy) {
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
        $scope.allFormsChangeSort = function(column) {
            $scope.allFormsCurrentPage = 1;

            var tmpOrderByColumn = null;

            if (column == 'date_of_entry') {
                tmpOrderByColumn = "date_of_entry";
            } else if (column == 'date_of_status') {
                tmpOrderByColumn = "date_of_status";
            } else if (column == 'instance_key') {
                tmpOrderByColumn = "instance_key";
            }

            if (tmpOrderByColumn != null) {
                $scope.allFormsOrderBy = column;
                if (tmpOrderByColumn == $scope.allFormsOrderBy) {
                    if ($scope.allFormsOrderDirection == 'ASC') {
                        $scope.allFormsOrderDirection = 'DESC';
                    } else {
                        $scope.allFormsOrderDirection = 'ASC';
                    }

                } else {
                    $scope.allFormsOrderDirection = 'DESC';
                }
                $scope.getAllForms();
            }

        };

        $scope.onFilterByFormAll = function(form) {
            $scope.allFormsCurrentPage = 1;
            if (form !== undefined) {
                $scope.allFormsFilterFormPublicationKey = form.publicationKey;
                $scope.allFormsFilterFormTitle = form.name;
            } else {
                $scope.allFormsFilterFormPublicationKey = null;
                $scope.allFormsFilterFormTitle = null;
            }
            $scope.filterChanged = true;
            $scope.getAllForms();
        };

        $scope.onFilterByStatusAll = function(status) {
            $scope.allFormsCurrentPage = 1;
            if (status !== undefined) {
                $scope.allFormsFilterStatus = status.name;
            } else {
                $scope.allFormsFilterStatus = null;
            }
            $scope.filterChanged = true;
            $scope.getAllForms();
        };

        $scope.onFilterByAssignmentAll = function(assignment) {
            $scope.allFormsCurrentPage = 1;
            if (assignment !== undefined) {
                $scope.allFormsFilterAssignment = assignment;
            } else {
                $scope.allFormsFilterAssignment = undefined;
            }
            $scope.filterChanged = true;
            $scope.getAllForms();
        };

        studyService.getActiveSproutInboxStatuses({}, function(data) {
            $scope.activeSproutInboxStatuses = data;
        });

        $scope.getAssignments = function() {
            studyService.getAssignments({"status": $scope.allFormsFilterStatus, "targetDate": $scope.targetDate}, function(data) {
                $scope.assignments = data;
            });
        };

        $scope.getAssignments();

        $scope.getAllForms = function() {
            $scope.allForms = undefined;

            //studyService.getAllFormsPageCount({rows: $scope.allFormsRowsPerPage, form: $scope.allFormsFilterFormPublicationKey, status: $scope.allFormsFilterStatus, expirationDate: $scope.allFormsMetadata.hasStudyDates $scope.allFormsFilterTargetDate !== undefined && $scope.allFormsFilterTargetDate == 'today' ? $scope.getTodayAsDateString() : $scope.allFormsFilterTargetDate, assignment: $scope.allFormsFilterAssignment !== undefined && $scope.allFormsFilterAssignment.value !== undefined ? $scope.allFormsFilterAssignment.value : null}, function(data) {
            //    $scope.allFormsPageCount = data;
            //});

            studyService.getAllFormsMetadata({rows: $scope.allFormsRowsPerPage, form: $scope.allFormsFilterFormPublicationKey, status: $scope.allFormsFilterStatus, expirationDate: $scope.allFormsFilterTargetDate !== undefined && $scope.allFormsFilterTargetDate == 'today' ? $scope.getTodayAsDateString() : $scope.allFormsFilterTargetDate, assignment: $scope.allFormsFilterAssignment !== undefined && $scope.allFormsFilterAssignment.value !== undefined ? $scope.allFormsFilterAssignment.value : null}, function(data) {
                $scope.allFormsMetadata = data;
                $scope.allFormsPageCount = $scope.allFormsMetadata.count;

                studyService.getAllForms({page: $scope.allFormsCurrentPage, rows: $scope.allFormsRowsPerPage, orderBy: $scope.allFormsOrderBy, orderDirection: $scope.allFormsOrderDirection, form: $scope.allFormsFilterFormPublicationKey, status: $scope.allFormsFilterStatus, targetDate: $scope.allFormsMetadata.hasStudyDates ? $scope.allFormsFilterTargetDate !== undefined && $scope.allFormsFilterTargetDate == 'today' ? $scope.getTodayAsDateString() : $scope.allFormsFilterTargetDate : null, assignment: $scope.allFormsFilterAssignment !== undefined && $scope.allFormsFilterAssignment.value !== undefined ? $scope.allFormsFilterAssignment.value : null}, function(data) {
//                $scope.allFormsFilterFormPublicationKey = null;

                    $scope.allForms = data;

                    $scope.allFormsFilterForm = new Array();

//                $log.log("getAllForms.data.length: " + data.length);

                    for (var i = 0; i < data.length; i++) {
                        var statusIncludeInd = true;
                        var formIncludeInd = true;

                        var publicationKey = data[i].publicationKey;

                        $.each(cohortService.getCohort().forms, function(index, tmpForm) {
                            if (tmpForm.publicationKey == publicationKey) data[i].title = tmpForm.name;
                        });

                        if ($scope.allFormsFilterForm !== undefined && $scope.allFormsFilterForm.length > 0) {
                            for (var i2=0;i2<$scope.allFormsFilterForm.length;i2++) {
                                if ($scope.allFormsFilterForm[i2] == data[i].title) {
                                    formIncludeInd = false;
                                }
                            }

                        }

                        if (formIncludeInd) $scope.allFormsFilterForm.push(data[i].title);
                    }

                    $scope.allFormsFilterForm.sort();

                });


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

//                        var params = [{identity: $scope.subject.id + "@mgh"}, {identity: $scope.tempId + "@SPROUT_STUDY_TEMP_ID"}];
                        var params = [{identity: $scope.subject.id + "@mgh"}];

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

        $scope.isAdmin = function() {
            return studyService.isAdmin();
        }

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

                        var publicationKey = data[i].publicationKey;

                        $.each(cohortService.getCohort().forms, function(index, tmpForm) {
                            if (tmpForm.publicationKey == publicationKey) data[i].title = tmpForm.name;
                        });

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
            $scope.deliveringInd = true;
            $scope.deliveryError = null;

            var form = null;

            $.each(cohortService.getCohort().forms, function(index, tmpForm) {
                if (tmpForm.demographic && tmpForm.active) {
                    form = tmpForm;
                }
            });

            $scope.tempId = generateUUID();

            formsService.deliverForm({schema: "SPROUT_STUDY_TEMP_ID", id: $scope.tempId, publicationKey: form.publicationKey, provider: null, expirationDate: null}, function(data) {
//                console.log("data.instanceId: " + data.instanceId);
//                console.log("data.status: " + data.status);
                if (data.instanceId != null) {
                    $scope.onDemographicFormByInstanceId(data.instanceId, form);
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
                if ($scope.sendMessageForm.title == 'New Subject') {
                    $.each(cohortService.getCohort().forms, function(index, tmpForm) {
                        if (tmpForm.demographic && tmpForm.active) $scope.sendMessageForm.title = tmpForm.name;
                    });
                }

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
            $scope.subject = undefined;
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
//            $scope.form = formsService.getForm({schema: "mgh", mrn: $scope.subject.id, instanceId: instanceId});
            $scope.viewFormModal = true;
//            $scope.deleteFormModal = true;
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
        $scope.onDemographicFormByInstanceId = function (instanceId, form) {

            formsService.applyForNonce({instanceId: instanceId, subjectName: "New Subject", subjectId: "Unknown"}, function(data) {
                var nonce = data.nonce;
                var tabTitle = {fullName: form === undefined || form.name === undefined ? "New Subject" : form.name, id: 0};
                cohortService.setMember(tabTitle);
                setActiveTabData("form", form);

                $scope.getTemplate(form.publicationKey, instanceId, function(template) {
                    setActiveTabData("template", template);
                });

                setActiveTabData("demographicInd", true);

                var uneditable = form.uneditable;

                //var content = '<iframe id="iframe-' + instanceId + '" name="iframe-' + instanceId + '" instanceId="' + instanceId + '" src="/prompt/?instanceId=' + instanceId + '&nonce=' + nonce + '&debug=true&showThanks=false&disableSave=true" class="appFrame sproutStudyFrame iframe-demographic-form-content" />';
                var content = '<div class="sprout-study-form-narrative-split-frame sprout-study-form-narrative-split-frame-' + instanceId + '"><div class="sproutstudy-content sproutstudy-content-form sproutstudy-content-' + instanceId + '" id="' + instanceId + '"><iframe id="iframe-' + instanceId + '" name="iframe-' + instanceId + '" instanceId="' + instanceId + '" src="/prompt/?instanceId=' + instanceId + '&nonce=' + nonce + '&debug=true&showThanks=false&disableSave=trueshowThanks=false&uneditable=' + uneditable + '" class="appFrame sproutStudyFrame iframe-demographic-form-content" /></div></div>';
                $scope.demographicFormContent = content;

//                $scope.addPane(tabTitle, instanceId, nonce);
                setTimeout(sizeAppFrame, 1000);
            });
        };

        $scope.onClearForms = function (destination) {
            //console.log("onClearForms.destination: " + destination);
            cohortService.clearMember();
            clearAllFormTabs();
            $location.path("#/" + destination);
        }

        $scope.addSubjectButton = "";
        $scope.expandAddSubjectButton =  function() {
            $scope.addSubjectButton = "  Add New Subject";
        }
        $scope.collapseAddSubjectButton =  function() {
            $scope.addSubjectButton = "";
        }

        $scope.searchButton = "  Search";
        $scope.narrativeButton = "  Narrative";
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

        $scope.onViewNarrative = function() {
            $scope.model = getNarrativeModel();
            var form = getActiveForm();
            var template = getActiveTemplate();
            if (form !== undefined && template !== undefined) {
                var publicationKey = form.publicationKey;
                var instanceId = form.instanceId;
                enableSplitNarrativeFrame(instanceId);
                setSproutTransformTemplate(template.template, instanceId);
                //$scope.setTemplate(publicationKey, instanceId);
            }
        }

        $scope.onSyncNarrative = function(callback) {
            var form = getActiveForm();
            if (form !== undefined) {
                var publicationKey = form.publicationKey;
                var instanceId = form.instanceId;

                var narrative = getNarrativeHtml(instanceId);
                syncNarrativeTemplate(instanceId);
                var template = stripNarrativeTextEditable(instanceId);

                if (narrative !== undefined) {
                    transformService.saveTemplate({publicationKey: publicationKey, instanceId: instanceId, templateKey: null, masterInd: false}, syncNarrativeTemplate(instanceId), function(data) {
                        if (data.value == 'false') {
                            callback(false, "Failed to save narrative template.");
                        } else {
                            transformService.saveNarrative({instanceId: instanceId, narrative: narrative, format: "HTML"}, function(data) {
                                if (data.value == 'false') {
                                    callback(false, "Failed to save narrative.");
                                } else {
                                    setActiveTemplate(template);
                                    callback(true);

                                }
                            });
                        }
                    });
                }
            } else {
                callback(false, "Failed to save narrative template.");
            }

        }

        $scope.onCloseErrorModal = function() {
            $scope.errorFormModal = false;
        }

        $scope.setTemplate = function(publicationKey, instanceId) {
            $scope.templateKey = undefined;
            $scope.template = undefined;

            transformService.getTemplate({publicationKey: publicationKey, instanceId: instanceId}, function(data) {
                $scope.templateKey = data.key;
                $scope.template = data.template;
                setSproutTransformTemplate($scope.template, instanceId);
            });
        }

        $scope.getTemplate = function(publicationKey, instanceId, callback) {
            transformService.getTemplate({publicationKey: publicationKey, instanceId: instanceId}, function(template) {
                if (template !== undefined && template.key !== undefined) {
                    callback(template);
                } else {
                    return callback(null);
                }
            });
        }

        $scope.showNarrativeButton = function(showInd, instanceId) {
            if (showInd !== undefined) {
                $scope.hasNarrative = showInd;
            }
            if (instanceId !== undefined) {
                $scope.formLoaded = isFormLoaded(instanceId);
            }
        }

        $scope.formLoadUpdate = function(instanceId) {
            var form = getActiveForm();
            if (form !== undefined && form.instanceId == instanceId) $scope.formLoaded = true;
        }

        //$scope.makeDefaultTab = function (tabName) {
        //    $scope.defaultTab = tabName;
        //    studyService.setDefaultTab({defaultTab: tabName}, function(data) {
        //        if (data.value == false) {
        //            $scope.errorMessageText = "Failed to save default tab.";
        //            $scope.errorFormModal = true;
        //        } else {
        //            $scope.filterChanged = false;
        //        }
        //    });
        //}

        $scope.applicationTabContextMenu = function (tab) {
            var tabName = tab;
            return [
                [function ($itemScope) { return 'Make Default Tab'; }, function ($itemScope) {
                    $scope.defaultTab = tabName;
                    studyService.setDefaultTab({defaultTab: tabName}, function(data) {
                        if (data.value == false) {
                            $scope.errorMessageText = "Failed to save default tab.";
                            $scope.errorFormModal = true;
                        } else {
                            $scope.filterChanged = false;
                        }
                    });

                }]
            ];
        };

        $scope.unlockForm = function (instanceId) {
            if (instanceId !== undefined) {
                studyService.unlock({instanceId: instanceId}, function(data) {
                    if (data.value == false) {
                        $scope.errorMessageText = "Failed to unlock form.";
                        $scope.errorFormModal = true;
                    } else {
                        $scope.filterChanged = false;
                    }
                })
            }
        };

        $scope.bootWebsockets = function (cohort) {

            //console.log("$scope.bootWebsockets.cohort.cohortKey: " + cohort.cohortKey);

            //var ws = $websocket.$new('wss://scl30.partners.org:8443/sproutstudy/sproutStudyFormState/' + cohort.cohortKey); // instance of ngWebsocket, handled by $websocket service
            var ws = $websocket.$new(cohort.websocketURL); // instance of ngWebsocket, handled by $websocket service

            ws.$on('$open', function () {});
            ws.$on('$close', function () {});

            ws.$on('$message', function(data) {
                //console.log("data: " + data);

                if (data !== undefined && data !== null && data.indexOf("|") > 0) {
                    try {
                        var dataJSON = data.substring(data.indexOf("|") + 1);

                        var message = JSON.parse(dataJSON);

                        //console.log("data: " + data);

                        var instanceId = message.instanceId;
                        var publicationKey = message.publicationKey;
                        var lockInd = message.locked;
                        //console.log("instanceId: " + instanceId);
                        //console.log("publicationKey: " + publicationKey);

                        if (instanceId !== undefined && publicationKey !== undefined && instanceId !== null && publicationKey !== null) {

                            //console.log("Considering message....");

                            var inboxRecordIndex = undefined;
                            var allFormsRecordIndex = undefined;
                            var allFormsRecordAction = 'ADD';

                            if ($scope.inbox !== undefined) {
                                $.each($scope.inbox, function (index, data) {
                                    //console.log("inbox.instanceId: " + data.instanceId);
                                    if (instanceId == data.instanceId) inboxRecordIndex = index;
                                });
                            }
                            if ($scope.allForms !== undefined) {
                                $.each($scope.allForms, function (index, data) {
                                    //console.log("allForms.instanceId: " + data.instanceId);
                                    if (instanceId == data.instanceId) {
                                        allFormsRecordIndex = index;
                                        if (data.inboxStatus != 'REVOKED' && data.inboxStatus != 'EXPIRED') {
                                            allFormsRecordAction = 'UPDATE';
                                        } else {
                                            allFormsRecordAction = 'DELETE';
                                        }
                                    }
                                });
                            }

                            var formIncludeInd = true;

                            $.each(cohortService.getCohort().forms, function(index, tmpForm) {
                                if (tmpForm.publicationKey == publicationKey) message.title = tmpForm.name;
                            });

                            if ($scope.allFormsFilterForm !== undefined && $scope.allFormsFilterForm.length > 0) {
                                for (var i2=0;i2<$scope.allFormsFilterForm.length;i2++) {
                                    if ($scope.allFormsFilterForm[i2] == message.title) {
                                        formIncludeInd = false;
                                    }
                                }
                            }

                            if (formIncludeInd) $scope.allFormsFilterForm.push(data[i].title);

                            $scope.allFormsFilterForm.sort();


                            //console.log("inboxRecordIndex: " + inboxRecordIndex);
                            //console.log("allFormsRecordIndex: " + allFormsRecordIndex);
                            //console.log("allFormsRecordAction: " + allFormsRecordAction);

                            var applyInd = false;

                            if (inboxRecordIndex !== undefined) {
                                if (lockInd) {
                                    $scope.inbox[inboxRecordIndex].locked = true;
                                } else {
                                    $scope.inbox[inboxRecordIndex].locked = false;
                                }
                                applyInd = true;
                            }
                            if (allFormsRecordAction == 'ADD') {
                                $scope.allForms.push(message);
                            } else if (allFormsRecordAction == 'UPDATE') {
                                $scope.allForms[allFormsRecordIndex] = message;
                            } else if (allFormsRecordAction == 'DELETE') {
                                $scope.allForms.splice(allFormsRecordIndex, 1);
                            }

                            //console.log("applying changes...");
                            $scope.applyIfPossible();
                            $scope.getAssignments();
                        }

                    } catch (e) {
                        //console.log("e: " + e);
                    }
                }
            });

        }







    });
