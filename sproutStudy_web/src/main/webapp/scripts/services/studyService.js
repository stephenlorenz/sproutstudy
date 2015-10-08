'use strict';

angular.module('sproutStudyApp')
    .factory('studyService', function ($http, networkService) {

        var admin = false;
        var manager = false;

        function setAdmin(adminObject) {
            admin = adminObject;
        }
        function isAdmin() {
            return admin;
        }

        function setManager(managerObject) {
            manager = managerObject;
        }
        function isManager() {
            return manager;
        }

        $http.get(networkService.generateUrl("isAdmin", {})).then(function (response) {
            admin = response.data.value;
        });

        $http.get(networkService.generateUrl("isManager", {})).then(function (response) {
            manager = response.data.value;
        });

        return {
            setAdmin: setAdmin,
            setManager: setManager,
            isAdmin: isAdmin,
            isManager: isManager,
            getAdmin: function (params, callback) {
                $http.get(networkService.generateUrl("isAdmin", params)).then(function (response) {
                    callback(response.data);
                });
            },
            getManager: function (params, callback) {
                $http.get(networkService.generateUrl("isManager", params)).then(function (response) {
                    callback(response.data);
                });
            },
            getAuthorizedCohorts: function (params, callback) {
                $http.get(networkService.generateUrl("getAuthorizedCohorts", params)).then(function (response) {
                    callback(response.data);
                });
            },
            findCohortMember: function (params, callback) {
                $http.get(networkService.generateUrl("findCohortMember", params)).then(function (response) {
                    callback(response.data);
                });
            },
            getRecentCohortMembers: function (params, callback) {
                $http.get(networkService.generateUrl("getRecentCohortMembers", params)).then(function (response) {
                    callback(response.data);
                });
            },
            getCohortByKey: function (params, callback) {
                $http.get(networkService.generateUrl("getCohortByKey", params)).then(function (response) {
                    callback(response.data);
                });
            },
            getMutableForms: function (params, callback) {
                $http.get(networkService.generateUrl("getMutableForms", params)).then(function (response) {
                    callback(response.data);
                });
            },
            getActiveSproutInboxStatuses: function (params, callback) {
                $http.get(networkService.generateUrl("getActiveSproutInboxStatuses", params)).then(function (response) {
                    callback(response.data);
                });
            },
            getAssignments: function (params, callback) {
                $http.get(networkService.generateUrl("getAssignments", params)).then(function (response) {
                    callback(response.data);
                });
            },
            getAllForms: function (params, callback) {
                $http.get(networkService.generateUrl("getAllForms", params)).then(function (response) {
                    callback(response.data);
                });
            },
            getAllFormsPageCount: function (params, callback) {
                $http.get(networkService.generateUrl("getAllFormsPageCount", params)).then(function (response) {
                    callback(response.data);
                });
            },
            getAllFormsMetadata: function (params, callback) {
                $http.get(networkService.generateUrl("getAllFormsMetadata", params)).then(function (response) {
                    callback(response.data);
                });
            },
            getCohortAuthorizations: function (params, callback) {
                $http.get(networkService.generateUrl("getCohortAuthorizations", params)).then(function (response) {
                    callback(response.data);
                });
            },
            getStudyInbox: function (params, callback) {
                $http.get(networkService.generateUrl("getStudyInbox", params)).then(function (response) {
                    callback(response.data);
                });
            },
            unlock: function (params, callback) {
                $http.get(networkService.generateUrl("unlock", params)).then(function (response) {
                    callback(response.data);
                });
            },
            deleteInboxMessage: function (params, callback) {
                $http.get(networkService.generateUrl("deleteInboxMessage", params)).then(function (response) {
                    callback(response.data);
                });
            },
            markInboxMessageAsRead: function (params, callback) {
                $http.get(networkService.generateUrl("markInboxMessageAsRead", params)).then(function (response) {
                    callback(response.data);
                });
            },
            changeInboxMessageStatus: function (params, callback) {
                $http.get(networkService.generateUrl("changeInboxMessageStatus", params)).then(function (response) {
                    callback(response.data);
                });
            },
            sendMessage: function (params, callback) {
                $http.get(networkService.generateUrl("sendMessage", params)).then(function (response) {
                    callback(response.data);
                });
            },
            getLastSelectedCohort: function (params, callback) {
                $http.get(networkService.generateUrl("getLastSelectedCohort", params)).then(function (response) {
                    callback(response.data);
                });
            },
            deleteSubmission: function (params, callback) {
                $http.get(networkService.generateUrl("deleteSubmission", params)).then(function (response) {
                    callback(response.data);
                });
            },
            setSessionCohort: function (params, callback) {
                $http.get(networkService.generateUrl("setSessionCohort", params)).then(function (response) {
                    callback(response.data);
                });
            },
            setSessionFormFilter: function (params, callback) {
                $http.get(networkService.generateUrl("setSessionFormFilter", params)).then(function (response) {
                    callback(response.data);
                });
            },
            getUserPreferences: function (params, callback) {
                $http.get(networkService.generateUrl("getUserPreferences", params)).then(function (response) {
                    callback(response.data);
                });
            },
            setDefaultTab: function (params, callback) {
                $http.get(networkService.generateUrl("setDefaultTab", params)).then(function (response) {
                    callback(response.data);
                });
            },
            getPatientInbox: function (params, callback) {
                $http.get(networkService.generateUrl("getPatientInbox", params)).then(function (response) {
                    callback(response.data);
                });
            },
            getPollEvents: function (params, callback) {
                $http.get(networkService.generateUrl("getPollEvents", params)).then(function (response) {
                    callback(response.data);
                });
            },
            getSession: function (params, callback) {
                $http.get(networkService.generateUrl("getSession", params)).then(function (response) {
                    callback(response.data);
                });
            },
            mrnIHealthSpace: function (params, callback) {
                $http.get(networkService.generateUrl("mrnIHealthSpace", params)).then(function (response) {
                    callback(response.data);
                });
            },
            patientLookup: function (params, page) {
                params['page'] = page == undefined ? 0 : page;
                params['limit'] = 10;
                return $http.get(networkService.generateUrl("patientLookup", params)).then(function (response) {
                    return response.data;
                });
            },
            syncPatientIdentifiers: function (verifiedIdentifiers, patient, assertions, instanceId, callback) {
            	
            	var matchedIdentifiers = patient.identities;

                var params = [];
                params.push({instanceId: instanceId});
                
                if ($.isArray(verifiedIdentifiers)) {
                    $.each(verifiedIdentifiers, function(index, verifiedIdentifier){
                        params.push({verifiedIdentifier: verifiedIdentifier.identifier + "@" + verifiedIdentifier.schema});
                    });
                } else {
                    params.push({verifiedIdentifier: verifiedIdentifiers.identifier + "@" + verifiedIdentifiers.schema});
                }

                $.each(matchedIdentifiers, function(index, matchedIdentifier){
                    params.push({matchedIdentifier: matchedIdentifier.identifier + "@" + matchedIdentifier.schema});
                });

                if ($.isArray(assertions)) {
                    $.each(assertions, function(index, assertion){
                    	var partnersValue = patient[assertion.searchField];
                        assertion.value = "";
                    	if (partnersValue !== undefined && partnersValue !== null && assertion.value !== null) {
                        	if (partnersValue.toUpperCase() != assertion.value.toUpperCase()) assertion.value = partnersValue;
                    	}
                        params.push({assertion: assertion.value + "@" + assertion.variableName});
                    });
                } else {
                	var partnersValue = patient[assertions.searchField];
                    assertion.value = "";
                	if (partnersValue !== undefined && partnersValue !== null && assertion.value !== null) {
                    	if (partnersValue.toUpperCase() != assertions.value.toUpperCase()) assertions.value = partnersValue;
                	}
                    params.push({assertion: assertions.value + "@" + assertions.variableName});
                }
                
                $http({method: 'GET', url: networkService.generateUrl("syncSproutPatientIdentifiers", params), isArray: false}).
                    success(function (data, status) {
                        if (data.value == 'true') {
                            $http({method: 'GET', url: networkService.generateUrl("syncFormSubmission", params), isArray: false}).
                                success(function (data, status) {
                                    if (data.value == 'true') {
                                        $http({method: 'GET', url: networkService.generateUrl("getFormSubmission", params), isArray: false}).
                                            success(function (data, status) {
                                                callback(data);
                                            }).
                                            error(function (data, status) {
                                                callback(data);
                                            });
                                    } else {
                                        callback(data);
                                    }
                                }).
                                error(function (data, status) {
                                    callback(data);
                                });
                        } else {
                            callback(data);
                        }
                    }).
                    error(function (data, status) {
                        callback(data);
                    });
            	}

        };
    });
