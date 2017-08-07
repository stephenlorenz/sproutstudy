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
                }, function errorCallback(response) {
                    alert("ERROR: " + response.status + ": studyService.getAdmin. Please contact your system administrator.");
                });
            },
            getManager: function (params, callback) {
                $http.get(networkService.generateUrl("isManager", params)).then(function (response) {
                    callback(response.data);
                }, function errorCallback(response) {
                    alert("ERROR: " + response.status + ": studyService.getManager. Please contact your system administrator.");
                });
            },
            getAuthorizedCohorts: function (params, callback) {
                $http.get(networkService.generateUrl("getAuthorizedCohorts", params)).then(function (response) {
                    callback(response.data);
                }, function errorCallback(response) {
                    alert("ERROR: " + response.status + ": studyService.getAuthorizedCohorts. Please contact your system administrator.");
                });
            },
            findCohortMember: function (params, callback) {
                $http.get(networkService.generateUrl("findCohortMember", params)).then(function (response) {
                    callback(response.data);
                }, function errorCallback(response) {
                    alert("ERROR: " + response.status + ": studyService.findCohortMember. Please contact your system administrator.");
                });
            },
            getRecentCohortMembers: function (params, callback) {
                $http.get(networkService.generateUrl("getRecentCohortMembers", params)).then(function (response) {
                    callback(response.data);
                }, function errorCallback(response) {
                    alert("ERROR: " + response.status + ": studyService.getRecentCohortMembers. Please contact your system administrator.");
                });
            },
            getCohortByKey: function (params, callback) {
                $http.get(networkService.generateUrl("getCohortByKey", params)).then(function (response) {
                    callback(response.data);
                }, function errorCallback(response) {
                    alert("ERROR: " + response.status + ": studyService.getCohortByKey. Please contact your system administrator.");
                });
            },
            getMutableForms: function (params, callback) {
                $http.get(networkService.generateUrl("getMutableForms", params)).then(function (response) {
                    callback(response.data);
                }, function errorCallback(response) {
                    alert("ERROR: " + response.status + ": studyService.getMutableForms. Please contact your system administrator.");
                });
            },
            getActiveSproutInboxStatuses: function (params, callback) {
                $http.get(networkService.generateUrl("getActiveSproutInboxStatuses", params)).then(function (response) {
                    callback(response.data);
                }, function errorCallback(response) {
                    alert("ERROR: " + response.status + ": studyService.getActiveSproutInboxStatuses. Please contact your system administrator.");
                });
            },
            getActiveSproutInboxLocations: function (params, callback) {
                $http.get(networkService.generateUrl("getActiveSproutInboxLocations", params)).then(function (response) {
                    callback(response.data);
                }, function errorCallback(response) {
                    alert("ERROR: " + response.status + ": studyService.getActiveSproutInboxLocations. Please contact your system administrator.");
                });
            },
            getAssignments: function (params, callback) {
                $http.get(networkService.generateUrl("getAssignments", params)).then(function (response) {
                    callback(response.data);
                }, function errorCallback(response) {
                    alert("ERROR: " + response.status + ": studyService.getAssignments. Please contact your system administrator.");
                });
            },
            getAllForms: function (params, callback) {
                $http.get(networkService.generateUrl("getAllForms", params)).then(function (response) {
                    callback(response.data);
                }, function errorCallback(response) {
                    alert("ERROR: " + response.status + ": studyService.getAllForms. Please contact your system administrator.");
                });
            },
            getAllFormsPageCount: function (params, callback) {
                $http.get(networkService.generateUrl("getAllFormsPageCount", params)).then(function (response) {
                    callback(response.data);
                }, function errorCallback(response) {
                    alert("ERROR: " + response.status + ": studyService.getAllFormsPageCount. Please contact your system administrator.");
                });
            },
            getAllFormsMetadata: function (params, callback) {
                $http.get(networkService.generateUrl("getAllFormsMetadata", params)).then(function (response) {
                    callback(response.data);
                }, function errorCallback(response) {
                    alert("ERROR: " + response.status + ": studyService.getAllFormsMetadata. Please contact your system administrator.");
                });
            },
            getCohortAuthorizations: function (params, callback) {
                $http.get(networkService.generateUrl("getCohortAuthorizations", params)).then(function (response) {
                    callback(response.data);
                }, function errorCallback(response) {
                    alert("ERROR: " + response.status + ": studyService.getCohortAuthorizations. Please contact your system administrator.");
                });
            },
            getStudyInbox: function (params, callback) {
                $http.get(networkService.generateUrl("getStudyInbox", params)).then(function (response) {
                    callback(response.data);
                }, function errorCallback(response) {
                    alert("ERROR: " + response.status + ": studyService.getStudyInbox. Please contact your system administrator.");
                });
            },
            unlock: function (params, callback) {
                $http.get(networkService.generateUrl("unlock", params)).then(function (response) {
                    callback(response.data);
                }, function errorCallback(response) {
                    alert("ERROR: " + response.status + ": studyService.unlock. Please contact your system administrator.");
                });
            },
            deleteInboxMessage: function (params, callback) {
                $http.get(networkService.generateUrl("deleteInboxMessage", params)).then(function (response) {
                    callback(response.data);
                }, function errorCallback(response) {
                    alert("ERROR: " + response.status + ": studyService.deleteInboxMessage. Please contact your system administrator.");
                });
            },
            markInboxMessageAsRead: function (params, callback) {
                $http.get(networkService.generateUrl("markInboxMessageAsRead", params)).then(function (response) {
                    callback(response.data);
                }, function errorCallback(response) {
                    alert("ERROR: " + response.status + ": studyService.markInboxMessageAsRead. Please contact your system administrator.");
                });
            },
            changeInboxMessageStatus: function (params, callback) {
                $http.get(networkService.generateUrl("changeInboxMessageStatus", params)).then(function (response) {
                    callback(response.data);
                }, function errorCallback(response) {
                    alert("ERROR: " + response.status + ": studyService.changeInboxMessageStatus. Please contact your system administrator.");
                });
            },
            sendMessage: function (params, callback) {
                $http.get(networkService.generateUrl("sendMessage", params)).then(function (response) {
                    callback(response.data);
                }, function errorCallback(response) {
                    alert("ERROR: " + response.status + ": studyService.sendMessage. Please contact your system administrator.");
                });
            },
            getLastSelectedCohort: function (params, callback) {
                $http.get(networkService.generateUrl("getLastSelectedCohort", params)).then(function (response) {
                    callback(response.data);
                }, function errorCallback(response) {
                    alert("ERROR: " + response.status + ": studyService.getLastSelectedCohort. Please contact your system administrator.");
                });
            },
            deleteSubmission: function (params, callback) {
                $http.get(networkService.generateUrl("deleteSubmission", params)).then(function (response) {
                    callback(response.data);
                }, function errorCallback(response) {
                    alert("ERROR: " + response.status + ": studyService.deleteSubmission. Please contact your system administrator.");
                });
            },
            setSessionCohort: function (params, callback) {
                $http.get(networkService.generateUrl("setSessionCohort", params)).then(function (response) {
                    callback(response.data);
                }, function errorCallback(response) {
                    alert("ERROR: " + response.status + ": studyService.setSessionCohort. Please contact your system administrator.");
                });
            },
            setSessionFormFilter: function (params, callback) {
                $http.get(networkService.generateUrl("setSessionFormFilter", params)).then(function (response) {
                    callback(response.data);
                }, function errorCallback(response) {
                    alert("ERROR: " + response.status + ": studyService.setSessionFormFilter. Please contact your system administrator.");
                });
            },
            getUserPreferences: function (params, callback) {
                $http.get(networkService.generateUrl("getUserPreferences", params)).then(function (response) {
                    callback(response.data);
                }, function errorCallback(response) {
                    alert("ERROR: " + response.status + ": studyService.getUserPreferences. Please contact your system administrator.");
                });
            },
            setDefaultTab: function (params, callback) {
                $http.get(networkService.generateUrl("setDefaultTab", params)).then(function (response) {
                    callback(response.data);
                }, function errorCallback(response) {
                    alert("ERROR: " + response.status + ": studyService.setDefaultTab. Please contact your system administrator.");
                });
            },
            getPatientInbox: function (params, callback) {
                $http.get(networkService.generateUrl("getPatientInbox", params)).then(function (response) {
                    callback(response.data);
                }, function errorCallback(response) {
                    alert("ERROR: " + response.status + ": studyService.getPatientInbox. Please contact your system administrator.");
                });
            },
            getPollEvents: function (params, callback) {
                $http.get(networkService.generateUrl("getPollEvents", params)).then(function (response) {
                    callback(response.data);
                }, function errorCallback(response) {
                    console.error("ERROR: " + response.status + ": studyService.getPollEvents. Please contact your system administrator.");
                });
            },
            getSession: function (params, callback) {
                $http.get(networkService.generateUrl("getSession", params)).then(function (response) {
                    callback(response.data);
                }, function errorCallback(response) {
                    alert("ERROR: " + response.status + ": studyService.getSession. Please contact your system administrator.");
                });
            },
            mrnIHealthSpace: function (params, callback) {
                $http.get(networkService.generateUrl("mrnIHealthSpace", params)).then(function (response) {
                    callback(response.data);
                }, function errorCallback(response) {
                    alert("ERROR: " + response.status + ": studyService.mrnIHealthSpace. Please contact your system administrator.");
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

                // console.log("8885: studyService.syncPatientIdentifiers");
            	
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
