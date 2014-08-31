'use strict';

angular.module('sproutStudyApp')
    .controller('formSubmissionController', function ($scope, formsService, patientService, providerService) {

        $scope.appPath = "";

        $scope.idSelectedFormSubmission = null;

        $scope.viewName = "formSubmission";

        if (frontOfficeResourceBase !== undefined) {
        	$scope.appPath = frontOfficeResourceBase;	
        }

        $scope.pushResponse = undefined;

        $scope.pushEMROpts = {
            backdropFade: true,
            dialogFade: true,
            dialogClass: 'modal modal-small'
        };

        $scope.formName = undefined;
        $scope.formNames = undefined;

        $scope.assertionNames = undefined;
        $scope.assertionName = undefined;
        $scope.assertionValue = undefined;

        $scope.identifierSchemas = undefined;
        $scope.identifierSchema = undefined;
        $scope.identifierId = undefined;

        $scope.criteria = {};
        $scope.page = 0;

        $scope.verificationPage = 1;

        $scope.assertedIdentifier = undefined;

        $scope.verificationAssertions = undefined;

        $scope.freeformSearch = "";

        formsService.getFormSubmissions(function (data) {
            $scope.formSubmissions = data;
        });

        $scope.ihsProviders = null;

        providerService.getProvidersBySession({}, function(data) {
//                console.log("getProvidersByIhsGroupCode: " + data);
            $scope.ihsProviders = data;
        });

        $scope.onFilterByFormName = function (value) {
            if (value !== undefined) {
                $scope.formName = value;
            } else {
                $scope.formName = undefined;
            }
        };

        $scope.formSubmissionFilter = function (formSubmission) {
            if ($scope.formName !== undefined) {
                if (formSubmission.formTitle != $scope.formName) return false;
            }
            if ($scope.assertionName !== undefined && $scope.assertionValue !== undefined) {
                var passedInd = false;
                if (formSubmission.assertions.length > 0) {
                    $.each(formSubmission.assertions, function (index, assertion) {
                        if (assertion.name == $scope.assertionName) {
//                           var regex = new RegExp($scope.assertionValue.toLowerCase(), 'g');
                            var regex = new RegExp("^" + $scope.assertionValue.toLowerCase());
                            if (assertion.value.toLowerCase().match(regex)) {
                                passedInd = true;
                            } else {
                                return false;
                            }
                        }
                    });
                }
                if (!passedInd) return false;
            }
            if ($scope.identifierSchema !== undefined && $scope.identifierId !== undefined) {
                var passedInd = false;
                if (formSubmission.identifiers.length > 0) {
                    $.each(formSubmission.identifiers, function (index, identifier) {
                        if (identifier.schema == $scope.identifierSchema) {
//                           var regex = new RegExp($scope.assertionValue.toLowerCase(), 'g');
                            var regex = new RegExp("^" + $scope.identifierId.toLowerCase());
                            if (identifier.identifier.toLowerCase().match(regex)) {
                                passedInd = true;
                            } else {
                                return false;
                            }
                        }
                    });
                }
                if (!passedInd) return false;
            }


            return true;
        };

        $scope.onViewForm = function (identifiers, instanceId) {
            if (identifiers !== undefined && identifiers.length > 0) {
                $scope.form = formsService.getForm({schema: identifiers[0].schema, mrn: identifiers[0].identifier, instanceId: instanceId});
                $scope.viewFormModal = true;
            }
        };



        $scope.closeViewForm = function () {
            $scope.viewFormModal = false;
        };

        $scope.onViewPushEMRModal = function (formSubmission) {
            $scope.formSubmission = formSubmission;
            $scope.pushResponse = undefined;
            $scope.pushEMRModal = true;
        };

        $scope.closePushEMRModal = function () {
            $scope.pushEMRModal = false;
        };

        $scope.onPrintForm = function () {
            // NOTE: onPrintForm
            $('#printFormFrame').contents().find('html').html($('.sprout-form-content').html());
            $('#printFormFrame').contents().find('html').find(".sprout-forms-nav-btn").remove();
            $('#printFormFrame').contents().find('html').find(".sprout-page-relevant").show();
            $("#printFormFrame").get(0).contentWindow.print();
        };

        $scope.refreshIdentifierSchemas = function() {
            $scope.identifierSchemas = [];
            $.each($scope.formSubmissions.submissions, function (index, formSubmission) {
                $.each(formSubmission.identifiers, function (index, identifier) {
                    if ($scope.identifierSchemas.indexOf(identifier.schema) < 0) $scope.identifierSchemas.push(identifier.schema);
                });
            });
        };

        $scope.$watch("formSubmissions.submissions", function (value) {
            $scope.formNames = [];
            $scope.assertionNames = [];
            $scope.identifierSchemas = [];
            if (value !== undefined && value !== null) {
                $.each(value, function (index, formSubmission) {
                    if ($scope.formNames.indexOf(formSubmission.formTitle) < 0) $scope.formNames.push(formSubmission.formTitle);

                    $.each(formSubmission.assertions, function (index, assertion) {
                        if ($scope.assertionNames.indexOf(assertion.name) < 0) $scope.assertionNames.push(assertion.name);
                    });
                    $.each(formSubmission.identifiers, function (index, identifier) {
                        if ($scope.identifierSchemas.indexOf(identifier.schema) < 0) $scope.identifierSchemas.push(identifier.schema);
                    });
                });
            }
        });


        $scope.closePatientVerificationModal = function () {
            $scope.patientVerificationModal = false;
        };

        $scope.onVerificationMatch = function (patient) {
            $scope.syncPatientIdentifiersRunning = true;

            patientService.syncPatientIdentifiers($scope.verifiedIdentifiers, patient, $scope.verificationAssertions, $scope.instanceId, function (data) {
                $scope.syncPatientIdentifiersRunning = false;
                if (data.instanceId == $scope.instanceId) {
                    $.each($scope.formSubmissions.submissions, function(index, formSubmission) {
                        if (formSubmission.instanceId == data.instanceId) {
                            $scope.formSubmissions.submissions[index] = data;
                            return false;
                        }
                    });
                    $scope.refreshIdentifierSchemas();
                    $scope.patientVerificationModal = false;
                } else {
                    $scope.syncPatientIdentifiersError = true;
                }
            });
        };

        $scope.onVerificationNoMatch = function (patient) {
            $scope.verificationPage = 2;
            $scope.onPatientLookup();

        };

        $scope.resetVerificationAssertions = function () {
            $scope.freeformSearch = "";
            $scope.verificationAssertions = angular.copy($scope.verificationAssertionsOrig);
            $scope.onPatientLookup();
        };

        $scope.clearVerificationAssertions = function () {
            $scope.freeformSearch = "";
//            $scope.verificationAssertions = angular.copy($scope.verificationAssertionsOrig);
            $.each($scope.verificationAssertions, function (index, assertion) {
                 assertion.lookupValue = "";
            });

            $scope.onPatientLookup();
        };

        $scope.onVerificationChoice = function(patient) {
        	$scope.selectedPatient = patient;
            $scope.verificationPage = 3;
        };            
            
        $scope.onVerificationModal = function (assertions, identifiers, instanceId) {

            $scope.instanceId = instanceId;
            $scope.verifiedIdentifiers = identifiers;
            $scope.syncPatientIdentifiersError = false;
            $scope.verificationPage = 1;

            if (assertions !== undefined && assertions.length > 0) {
//                $scope.form = formsService.getForm({schema: identifiers[0].schema, mrn: identifiers[0].identifier, instanceId: instanceId});

                $scope.assertedIdentifier = undefined;
                $scope.hasSearchOn = false;
                $.each(assertions, function (index, assertion) {
                    assertion.lookupValue = assertion.value;
                    if (assertion.identifierInd) {
                        $scope.assertedIdentifier = assertion;
                        assertion.searchOn = true;
                        //return false;
                    } else {
                    	if (assertion.searchField == 'firstName' || assertion.searchField == 'lastName') {
                    		assertion.searchOn = true;
                            $scope.hasSearchOn = true;
                    	} else {
                    		assertion.searchOn = false;
                    	}
                    }
                });

                
                $scope.verificationAssertions = assertions;

                $scope.verificationAssertionsOrig = {};
                $scope.verificationAssertionsOrig = angular.copy($scope.verificationAssertions);

                if ($scope.assertedIdentifier !== undefined) {
                    $scope.criteria.mrn = $scope.assertedIdentifier.value;
                    $scope.patientMatches = patientService.patientLookup({mrn: $scope.criteria.mrn});
                } else {
                    $scope.patientMatches = new Array();
                }

                $scope.patientVerificationModal = true;
            }
        };

        $scope.onPatientLookup = function () {
            $scope.patientMatches = undefined;

            $scope.criteria = {};

            if ($scope.hasSearchOn) {
                $.each($scope.verificationAssertions, function (index, assertion) {
                    if (assertion.searchOn || assertion.identifierInd) {
                        if (assertion.searchField == null && assertion.identifierInd) {
                            $scope.criteria["mrn"] = assertion.lookupValue;
                        } else {
                            $scope.criteria[assertion.searchField] = assertion.lookupValue;
                        }
                    }
                });
            } else {
                $scope.criteria["lastName"] = $scope.freeformSearch;
            }

            $scope.page = 0;

            $scope.patientMatches = patientService.patientLookup($scope.criteria, $scope.page);
        };

        $scope.onNextPage = function() {
            $scope.page++;
            $scope.patientMatches = patientService.patientLookup($scope.criteria, $scope.page);
        };

        $scope.onPreviousPage = function() {
            if ($scope.page > 0) $scope.page--;
            $scope.patientMatches = patientService.patientLookup($scope.criteria, $scope.page);
        };

        $scope.enableSearch = function (assertion) {
            assertion.searchOn = true;
        };

        $scope.toggleSearch = function (assertion) {

            if (assertion.searchOn !== undefined) {
                assertion.searchOn = assertion.searchOn ? false : true;
            } else {
                assertion.searchOn = true;
            }
        };


        $('.dropdown-menu-extended').click(function (e) {
            e.stopPropagation();
        });

        $scope.onFilterAssertion = function (name, value) {
            $(".dropdown-toggle-assertions").trigger('click');
        };

        $scope.clearFilterAssertion = function () {
            $(".dropdown-toggle-assertions").trigger('click');
            $scope.assertionName = undefined;
            $scope.assertionValue = undefined;
        };

        $scope.acceptAssertionFilter = function ($event) {
            $(".dropdown-toggle-assertions").trigger('click');
            $event.preventDefault();
        };

        $scope.cancelAssertionFilter = function ($event) {
            $(".dropdown-toggle-assertions").trigger('click');
            $scope.assertionName = undefined;
            $scope.assertionValue = undefined;
            $event.preventDefault();
        };

        $scope.onFilterIdentifiers = function (name, value) {
            $(".dropdown-toggle-identifiers").trigger('click');
        };

        $scope.clearFilterIdentifiers = function () {
            $(".dropdown-toggle-identifiers").trigger('click');
            $scope.identifierSchema = undefined;
            $scope.identifierId = undefined;
        };

        $scope.acceptIdentifierFilter = function ($event) {
            $(".dropdown-toggle-identifiers").trigger('click');
            $event.preventDefault();
        };

        $scope.cancelIdentifierFilter = function ($event) {
            $(".dropdown-toggle-identifiers").trigger('click');
            $scope.identifierSchema = undefined;
            $scope.identifierId = undefined;
            $event.preventDefault();
        };

        $scope.onPush = function (formSubmission) {

            $scope.pushResponse = -1;
            formsService.appendProviderAsParameter({instanceId: formSubmission.instanceId, pun: formSubmission.ihsProvider.id}, function(data) {
                if (data !== undefined && data.value !== undefined && data.value == 'true') {
                    formsService.pushForm({mrn: null, instanceId: formSubmission.instanceId}, function(data) {
//                      console.log("data.value: " + data.value);
                        if (data !== undefined && data.value !== undefined && data.value == 'PUSHED') {
                            $scope.updateFormAdminStatus(formSubmission.instanceId, data.value, $scope.formSubmissions.submissions);
                            $scope.pushResponse = 1;
                        } else {
                            $scope.pushResponse = 0;
                        }
                    });
                } else {
                    $scope.pushResponse = 0;
                }

            });

        };

        $scope.onReturnToSender = function (formSubmission) {
            $scope.idSelectedFormSubmission = formSubmission.instanceId;

            setTimeout(function(){
                var answer = confirm("Are you sure you want to delete this form?  This action cannot be undone!");
                if (answer == true) {
                    var promise = formsService.returnToSender({mrn: null, instanceId: formSubmission.instanceId});
                    promise.then(function (data) {
                        $scope.updateFormAdminStatus(formSubmission.instanceId, data.value);
                        //console.log('returnToSender failed: ' + reason);
                    }, function (reason) {
                    });
                }
                $scope.idSelectedFormSubmission = null;
                $scope.$apply();
            }, 50);

//            $scope.idSelectedFormSubmission = null;

        };

        $scope.updateFormAdminStatus = function (instanceId, adminStatus) {
            $.each($scope.formSubmissions.submissions, function (index, value) {
                if (value.instanceId == instanceId) {
                    //$scope.formSubmissions.submissions[index].status = adminStatus;
                    $scope.formSubmissions.submissions.splice(index, 1);
                    return false;
                }
            });
        };

    });
