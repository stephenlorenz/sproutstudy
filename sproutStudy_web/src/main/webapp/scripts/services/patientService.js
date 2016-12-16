'use strict';

angular.module('sproutStudyApp')
    .factory('patientService', function ($http, networkService) {

        return {
            getPatientDetail: function (params, callback) {



                $http.get(networkService.generateUrl("getPatientDetail", params)).then(function (response) {
                    callback(response.data);
                });
            },
            getSproutInbox: function (params, callback) {
                $http.get(networkService.generateUrl("getSproutInbox", params)).then(function (response) {
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

                console.log("syncPatientIdentifiers.1");

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
