'use strict';

angular.module('sproutStudyApp')
    .controller('AppointmentCtrl', function ($scope, localeModel, appointmentsService, enrollmentService, providerService, patientService, formsService, practiceService) {

        $scope.formatDate = function (date, log) {
            var curr_date = date.getDate();
            var curr_month = date.getMonth() + 1; //Months are zero based
            var curr_year = date.getFullYear();

            return curr_month + "/" + curr_date + "/" + curr_year;
        };
        $scope.makePrettyDate = function (date, log) {
            var curr_date = date.getDate();
            var curr_month = date.getMonth() + 1; //Months are zero based
            var curr_year = date.getFullYear();

            var weekday = new Array(7);
            weekday[0]=  "Sunday";
            weekday[1] = "Monday";
            weekday[2] = "Tuesday";
            weekday[3] = "Wednesday";
            weekday[4] = "Thursday";
            weekday[5] = "Friday";
            weekday[6] = "Saturday";

            var monthNames = [ "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" ];

            var day = weekday[date.getDay()];
            return day + ", " + monthNames[date.getMonth()] + " " + curr_date + ", " + curr_year;
        };

        $scope.viewName = "appointment";

        $scope.appPath = "";
        
        if (frontOfficeResourceBase !== undefined) {
        	$scope.appPath = frontOfficeResourceBase;	
        }

        // TODO: get the brand property from host application (e.g. ihealthspace bridge)
        $scope.brand = "IMA";

        $scope.locales = localeModel.getLocales();
        $scope.patient = undefined;
        $scope.locale = undefined;
        $scope.hasProvider = false;
        $scope.optOutRaw = undefined;

        $scope.today = new Date();
        // FIXME: Set the following line to today when done development
//        $scope.startDate = new Date(2014, 2, 25);
        $scope.startDate = $scope.today;

        $scope.prettyDate = $scope.makePrettyDate($scope.startDate);

        //console.log("appointmentController.$scope.startDate: " + $scope.startDate);
        
        $scope.scheduleResult = appointmentsService.getAppointments({startDate: $scope.formatDate($scope.startDate, "1")});
        $scope.providers = providerService.getAppointmentProviders({startDate: $scope.formatDate($scope.startDate, "2")});
        $scope.practices = practiceService.getPractices();

        $scope.providerSearch = function(term) {
            return providerService.providerSearch({term: term});
        };

        $scope.onFilterByPractice = function(value) {
            if (value !== undefined) {
                $scope.practice = value.practiceId;
                $scope.practiceName = value.name;
            } else {
                $scope.practice = undefined;
                $scope.practiceName = undefined;
            }
        };

        $scope.onFilterByProvider = function(value) {
            if (value !== undefined) {
                $scope.provider = value.id;
                $scope.providerName = value.name;
            } else {
                $scope.provider = undefined;
                $scope.providerName = undefined;
            }
        };

        $scope.appointmentFilter = function (item){
            if (($scope.practice !== undefined && $scope.practice.length > 0 ) || $scope.provider > 0) {
                if ($scope.practice !== undefined && $scope.practice.length > 0 ) {
                    if (item.practiceId != $scope.practice) return false;
                }
                if ($scope.provider > 0) {
                    if (item.providerId != $scope.provider) return false;
                }
            }
            return true;
        };

        $scope.hideFormDetails = function() {$(".form-detail").hide();};
        $scope.$watch('practice', function() {$scope.hideFormDetails();});
        $scope.$watch('provider', function() {$scope.hideFormDetails();});
        $scope.$watch('startDate', function() {$scope.hideFormDetails();});

        $scope.updateOptOutStatus = function (mrn, status) {
        	enrollmentService.updateOptOutStatus(mrn, status, function(optOutStatus) {
            	$scope.oncallEnrollmentInfo.optOut = optOutStatus;	
            });
        };

        $scope.validateProvider = function (value) {
            return value == undefined ? false : true;
        };

        $scope.onCreateEnrollment = function () {
            // NOTE: createEnrollment
            enrollmentService.createEnrollment({mrn: $scope.patient.mrn, firstname: $scope.patient.firstName, middleinitial: $scope.patient.middleName, lastname: $scope.patient.lastName, sex: $scope.patient.sex, dateofbirth: $scope.patient.dobPretty, patientUsername: $scope.patient.username, patientEmail: $scope.patient.email, custodianUsername: $scope.patient.custodianUsername, custodianEmail: $scope.patient.custodianEmail, pin: $scope.oncallEnrollmentInfo.ihealthspacePin, providerId: $scope.patient.provider, groupOwner: $scope.patient.groupOwner, address1: $scope.patient.address1, address2: $scope.patient.address2, city: $scope.patient.city, state: $scope.patient.state, zip: $scope.patient.zip, medicalRecordSystem: 'MGH Medical Record System', role: $scope.patient.userType, patientPortalEnroll: true, createNewUser: true, localeId: $scope.locale}, function(data) {
            	$scope.enrollmentDataResponse = data;
            });
        };
        
        $scope.onReenroll = function (membershipEnrollmentId, userType) {
            // NOTE: reenroll
            $scope.patient.userType = userType.toLowerCase();
            enrollmentService.reenroll({membershipId: membershipEnrollmentId}, function(data) {
            	$scope.enrollmentDataResponse = data;
            });
        };

        $scope.printCaregiverAgreement = function () {
            $('#custodianAgreementFrame').contents().find('html').html($('#custodianAgreementTemplate').html());
            $("#custodianAgreementFrame").get(0).contentWindow.print();
        };

        $scope.printEnrollmentLetter = function () {
            $('#enrollmentLetterFrame').contents().find('html').html($('#enrollmentLetterTemplate').html());
            $("#enrollmentLetterFrame").get(0).contentWindow.print();
        };

        $scope.onViewForm = function (mrn, instanceId) {
            $scope.form = formsService.getForm({mrn: mrn, instanceId: instanceId});
            $scope.viewFormModal = true;
        };

        $scope.onViewForms = function (appointment) {
            $scope.forms = appointment.forms;
            $scope.appointment = appointment;
            $scope.viewFormsModal = true;
        };

        $scope.onPrintForm = function () {
            // NOTE: onPrintForm
            $('#printFormFrame').contents().find('html').html($('.sprout-form-content-div').html());
            $('#printFormFrame').contents().find('html').find(".sprout-forms-nav-btn").remove();
            $('#printFormFrame').contents().find('html').find(".sprout-page-relevant").show();
            $("#printFormFrame").get(0).contentWindow.print();
        };

        $scope.onEnrollment = function (mrn, practice) {
            $scope.enrollmentDataResponse = undefined;
            $scope.enrollmentDataResponse = undefined;
            $scope.patient = undefined;
            $scope.ihsEnrollmentDetail = undefined;
            $scope.oncallEnrollmentInfo = undefined;
            patientService.getPatientDetail({mrn: mrn}, function(data) {
            	$scope.patient = data;
            });
            
            enrollmentService.getIhsEnrollmentDetail({mrn: mrn}, function(data) {
            	$scope.ihsEnrollmentDetail = data;
        	});
            	
        	enrollmentService.getOncallEnrollmentInfo({mrn: mrn}, function(data) {
        		$scope.oncallEnrollmentInfo = data;
        	});
            $scope.ihsEnrollentModal = true;
        };

        $scope.closeIhsEnrollment = function () {
            $scope.ihsEnrollentModal = false;
        };

        $scope.closeViewForm = function () {
            $scope.viewFormModal = false;
        };

        $scope.closeViewForms = function () {
            $scope.viewFormsModal = false;
        };

        $scope.iHSEnrollmentOpts = {
            backdropFade: true,
            dialogFade: true
        };

        $scope.updateAppointments = function () {
            $(".form-detail").remove();
            $scope.prettyDate = $scope.makePrettyDate($scope.startDate);

            $scope.scheduleResult = appointmentsService.getAppointments({provider: $scope.provider, startDate: $scope.formatDate($scope.startDate, "4")});

            $scope.providers = providerService.getAppointmentProviders({startDate: $scope.formatDate($scope.startDate, "5")});
        };

        $scope.resetProvider = function () {
            $scope.provider = "";
            $scope.providers = providerService.getAppointmentProviders({startDate: $scope.formatDate($scope.startDate, "6")});
        };

        $scope.pickNewDate = function() {
            $scope.practice = "";
            $scope.practiceName = "";
            $scope.provider = "";
            $scope.providerName = "";
            $scope.updateAppointments();
        };
        
        $scope.resetAppointments = function () {
//            $(".dropdown-toggle-datepicker").trigger('click');
            $scope.pickNewDate();
        };

        $scope.onPush = function(index, appointment, instanceId) {
            var promise = formsService.pushForm({mrn: appointment.mrn, instanceId: instanceId});
            promise.then(function(data) {
                $scope.updateFormAdminStatus(appointment, index, data.value);
            }, function(reason) {
                //console.log('returnToSender failed: ' + reason);
            });
        };

        $scope.onReturnToSender = function(index, appointment, instanceId) {
            var promise = formsService.returnToSender({mrn: appointment.mrn, instanceId: instanceId});
            promise.then(function(data) {
                $scope.updateFormAdminStatus(appointment, index, data.value);
            }, function(reason) {
                //console.log('returnToSender failed: ' + reason);
            });
        };

        $scope.updateFormAdminStatus = function(appointment, formIndex, adminStatus) {
            $.each($scope.scheduleResult.appointments, function(index, value) {
                if (value.appointmentId == appointment.appointmentId) {
                    $scope.scheduleResult.appointments[index].forms[formIndex].adminStatus = adminStatus;
                    return false;
                }
            });
        };

        $scope.onActivateIhs = function(appointment) {
//            $scope.ihsProviders = formsService.getForm({mrn: mrn, instanceId: instanceId});
            $scope.enrollmentDataResponse = undefined;
            $scope.enrollmentDataResponse = undefined;
            $scope.patient = undefined;
            $scope.ihsEnrollmentDetail = undefined;
            $scope.oncallEnrollmentInfo = undefined;

            $scope.ihsProviders = undefined;

            $scope.appointment = appointment;

//            console.log("appointment.mrn: " + appointment.mrn);

            providerService.getProvidersByIhsGroupCode({ihsGroupCode: appointment.ihsGroupCode}, function(data) {
//                console.log("getProvidersByIhsGroupCode: " + data);
                $scope.ihsProviders = data;
            });

            patientService.getPatientDetail({mrn: appointment.mrn}, function(data) {
                $scope.patient = data;
            });

            enrollmentService.getIhsEnrollmentDetail({mrn: appointment.mrn}, function(data) {
                $scope.ihsEnrollmentDetail = data;
            });

            enrollmentService.getOncallEnrollmentInfo({mrn: appointment.mrn}, function(data) {
                $scope.oncallEnrollmentInfo = data;
            });

            $scope.activationResponse = undefined;
            $scope.activateIhsModal = true;
        }

        $scope.closeActivateIhsModal = function() {
            $scope.activateIhsModal = false;
        }

        $scope.activateIhsAccount = function(appointment) {
//            console.log("activating account: " + appointment.mrn + " in " + appointment.ihsProvider.id);
            $scope.activationResponse = -1;
            enrollmentService.activateIhsEnrollment({mrn: appointment.mrn, groupCode: appointment.ihsProvider.id}, function(data) {
                
//                console.log("enrollmentService.activateIhsEnrollment.data.value: " + data.value);
                
                if (data.value == 'true') {
                    appointment.activateInd = false;
                    $scope.activationResponse = 1;
                    appointment.badge = "images/ihs_green.gif";
                    $scope.closeActivateIhsModal();
                } else {
                    $scope.activationResponse = 0;
//                    $scope.closeActivateIhsModal();
                }
            });


        }

    });
