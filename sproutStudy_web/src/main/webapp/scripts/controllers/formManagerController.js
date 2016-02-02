'use strict';

angular.module('sproutStudyApp')
    .controller('formManagerController', function ($scope, $location, $routeParams, $window, formManagerService, studyService, cohortService, cohortManagerService) {

    $scope.forms = null;
    $scope.form = null;
    $scope.managedForms = undefined;

    $scope.managedCohorts = undefined;

    cohortManagerService.getManagedCohorts({}, function(data) {
        $scope.managedCohorts = data;
    });

    $scope.formListFilter = { active: true };
    $scope.focusedForm = undefined;

    cohortService.setMember({fullName: "Form Manager", id: 0, url: "forms"});

    $scope.saveFormMessage = undefined;

    $scope.cohort = formManagerService.getCohort();

    if ($scope.cohort == undefined) {
        formManagerService.setCohort(cohortService.getCohort());
        $scope.cohort = formManagerService.getCohort();
    }

    if ($scope.cohort !== undefined && $scope.cohort !== null) {
        $window.sessionStorage.setItem("sproutStudyCohort", JSON.stringify($scope.cohort));
    } else {
        try {
            formManagerService.setCohort(JSON.parse($window.sessionStorage.getItem("sproutStudyCohort")));
            $scope.cohort = formManagerService.getCohort();
        } catch (e) {
            $scope.cohort = undefined;
        }
    }

    $scope.session = function() {
        return sessionService.getSession();
    }

    $scope.member = function() {
        return cohortService.getMember();
    }

    $scope.isAdmin = function() {
       return studyService.isAdmin();
    }

    $scope.isManager = function() {
       return studyService.isManager();
    }

    $scope.isManagerOfCohort = function() {
        var manager = false;
        var cohortCurrent = $scope.cohort;

        if (cohortCurrent !== undefined && cohortCurrent !== null) {
            if ($scope.managedCohorts !== undefined) {
                $.each($scope.managedCohorts, function(index, tmpCohort) {
                    if (tmpCohort.name == cohortCurrent.name) {
                        manager = true;
                    }
                });
            }
        }
        return manager;
    }
        
    $scope.isManagerOfForm = function() {
        var manager = false;
        var formCurrent = $scope.form;
        if (formCurrent !== undefined && formCurrent !== null) {
            if ($scope.managedForms !== undefined) {
                $.each($scope.managedForms, function(index, tmpForm) {
                    if (tmpForm.name == formCurrent.name) manager = true;
                });
            }
        }
        return manager;
    }

    $scope.onAddForm = function() {
        $scope.form = undefined;
        formManagerService.setForm(null);
        $window.sessionStorage.setItem("sproutStudyForm", undefined);
        $location.path("/forms/add");
    }

//    formManagerService.getManagedForms({}, function(data) {
//        $scope.managedForms = data;
//    });

//    studyService.getLastSelectedForm({}, function(data) {
//        $scope.form = data;
//    });

    $scope.onSaveForm = function(cohort, form) {
        formManagerService.saveFormPublicationKey({id: form.id, publicationKey: form.publicationKey}, function(data) {
            if (data.id !== undefined && data.id.length > 0) {
//                alert("Form Publication Key was successfully saved.");
                form.activityDate = data.timestamp;
                form.modified = false;
                cohort.forms.push(data);
            } else {
                alert("Failed to save Form Publication Key.  Perhaps you used a duplicate key?");
                form.modified = false;
            }
        });

    };

    $scope.onReturnToHomeUpdate = function (form) {
        var returnToHome = '';

        if (form.returnToHome) returnToHome = 'HOME';

        formManagerService.persistFormAttribute({"formKey": form.formKey, "publicationKey": form.publicationKey, "cohort": $scope.cohort.cohortKey, "attributeKey": 'DESTINATION', "attributeValue": returnToHome}, function(data) {
            if (data.value != 'true') {
                $scope.saveFormMessage = data.message;
            } else {
                studyService.getCohortByKey({"cohortKey": $scope.cohort.cohortKey}, function(cohort) {
                    $scope.cohort = cohort;
                    cohortService.setCohort(cohort);
                    formManagerService.setCohort(cohortService.getCohort());
                    $window.sessionStorage.setItem("sproutStudyCohort", JSON.stringify($scope.cohort));
                    $location.path("/forms");
                });

            }
        });

    };

    $scope.onArchiveUpdate = function (form) {
        var archiveInd = form.archive;

        formManagerService.toggleFormArchive({"formKey": form.formKey, "publicationKey": form.publicationKey, "cohortKey": $scope.cohort.cohortKey, "archiveInd": archiveInd}, function(data) {
            if (data.value != 'true') {
                $scope.saveFormMessage = data.message;
            }
        });

    };

    $scope.onSaveNewForm = function() {
        formManagerService.saveForm({"formKey": $scope.form.formKey, "publicationKey": $scope.form.publicationKey, "name": $scope.form.name, "demographicInd": $scope.form.demographicInd, "cohort": $scope.cohort.cohortKey}, function(data) {
            if (data.value == 'true') {
                formManagerService.setForm(null);
                $scope.saveNewFormMessage = undefined;

                studyService.getCohortByKey({"cohortKey": $scope.cohort.cohortKey}, function(cohort) {
                    $scope.cohort = cohort;
                    cohortService.setCohort(cohort);
                    formManagerService.setCohort(cohortService.getCohort());
                    $window.sessionStorage.setItem("sproutStudyCohort", JSON.stringify($scope.cohort));
                    $location.path("/forms");
                });

            } else {
                $scope.saveFormMessage = data.message;
            }
        });
    }

    $scope.onDeleteForm = function(formKey) {
        formManagerService.deleteForm({"formKey": formKey, "cohort": $scope.cohort.cohortKey}, function(data) {
            if (data.value == 'true') {
                formManagerService.setForm(null);
                $scope.saveNewFormMessage = undefined;

                studyService.getCohortByKey({"cohortKey": $scope.cohort.cohortKey}, function(cohort) {
                    $scope.cohort = cohort;
                    cohortService.setCohort(cohort);
                    formManagerService.setCohort(cohortService.getCohort());
                    $window.sessionStorage.setItem("sproutStudyCohort", JSON.stringify($scope.cohort));
                });
            } else {
                $scope.saveFormMessage = data.message;
            }
        });
    }

    $scope.onPubIdModified = function(form) {
        form.modified = true;
    };

});
