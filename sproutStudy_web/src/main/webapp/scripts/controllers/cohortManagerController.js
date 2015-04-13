'use strict';

angular.module('sproutStudyApp')
    .controller('cohortManagerController', function ($scope, $location, $routeParams, $window, studyService, cohortService, cohortManagerService) {

    $scope.cohorts = undefined;
    $scope.cohort = cohortManagerService.getCohort();
    $scope.managedCohorts = undefined;

    cohortService.setMember({fullName: "Cohort Manager", id: 0, url: "cohorts"});

    if ($scope.cohort !== undefined && $scope.cohort !== null) {
        $window.sessionStorage.setItem("sproutStudyCohort", JSON.stringify($scope.cohort));
    } else {
        try {
            cohortManagerService.setCohort(JSON.parse($window.sessionStorage.getItem("sproutStudyCohort")));
        } catch (e) {
            $scope.cohort = undefined;
        }
    }

    $scope.saveCohortMessage = undefined;
    $scope.saveCohortAuthMessage = undefined;
    $scope.cohortManagerMessage = undefined;

    $scope.cohortAuthorizations = undefined;

    $scope.sproutGroups = undefined;
    $scope.user = undefined;

    $scope.cn = undefined;
    $scope.manager = false;

    $scope.submittedInd = undefined;

    $scope.isAdmin = function() {
       return studyService.isAdmin();
    }

    $scope.isManager = function() {
       return studyService.isManager();
    }

    $scope.isManagerOfCohort = function() {
        var manager = false;
        var cohortCurrent = $scope.cohort;
        if ($scope.managedCohorts !== undefined) {
            $.each($scope.managedCohorts, function(index, tmpCohort) {
                if (tmpCohort.name == cohortCurrent.name) manager = true;
            });
        }
        return manager;
    }

    cohortManagerService.getManagedCohorts({}, function(data) {
        $scope.managedCohorts = data;
    });

    $scope.getCohorts = function() {
        cohortManagerService.getManagedCohorts({}, function(data) {
            $scope.cohorts = data;
        });
    };

    $scope.onSaveCohort = function() {
        cohortManagerService.saveCohort({"cohortKey": $scope.cohort.cohortKey, "name": $scope.cohort.name, "description": $scope.cohort.description, "group": $scope.cohort.group}, function(data) {
            if (data.value == 'true') {
                cohortManagerService.setCohort(null);
                $scope.saveNewCohortMessage = undefined;
                $location.path("/cohorts");
            } else {
                $scope.saveCohortMessage = data.message;
            }
        });
    }

    $scope.onEditCohort = function(cohort) {
        cohortManagerService.setCohort(cohort);
        $location.path("/cohorts/edit");
    }

    $scope.onAddCohort = function() {
        $scope.cohort = undefined;
        cohortManagerService.setCohort(null);
        $window.sessionStorage.setItem("sproutStudyCohort", undefined);
        $location.path("/cohorts/add");
    }

    $scope.onAddCohortAuthorization = function(cohort) {
        cohortManagerService.setCohort(cohort);
        $scope.submittedInd = undefined;
        $scope.user = undefined;
        $scope.manager = undefined;
        $scope.saveCohortAuthMessage = undefined;
        $location.path("/cohorts/authorizations/add");
    }

    $scope.onAuthCohort = function(cohort) {
        cohortManagerService.setCohort(cohort);
        $location.path("/cohorts/authorizations");
    }

    $scope.getCohortAuthorizations = function() {
        if ($scope.cohort != undefined && $scope.cohort != null) {
            cohortManagerService.getCohortAuthorizationsByKey({"cohortKey": $scope.cohort.cohortKey}, function(data) {
                $scope.cohortAuthorizations = data;
            });
        }
    }

    $scope.onDeleteCohort = function(cohort) {
        $scope.saveCohortMessage = undefined;
        cohortManagerService.deleteCohort({"cohortKey": cohort.cohortKey}, function(data) {
            if (data.value == 'true') {
                $scope.getCohorts();
            } else {
                $scope.saveCohortMessage = data.message;
            }
        });
    }

    $scope.getUser = function() {
        cohortManagerService.getUser({cn: $scope.cn}, function(data) {
            $scope.user = data;
        });
    };

    $scope.onSubmitNewAuth = function() {
        $scope.submittedInd = true;
        if ($scope.cohort != undefined && $scope.cohort != null) {
            cohortManagerService.grantCohortAuthorization({"cohortKey": $scope.cohort.cohortKey, "username": $scope.user.cn, "firstName": $scope.user.givenName, "lastName": $scope.user.sn, "email": $scope.user.email, "manager": $scope.manager}, function(data) {
                if (data.value == 'true') {
                    $scope.saveCohortAuthMessage = undefined;
                    $location.path("/cohorts/authorizations");
                } else {
                    $scope.saveCohortAuthMessage = data.message;
                }
            });
        }
    }

    $scope.onManagerUpdate = function(cohort, authorization) {
        cohortManagerService.updateCohortAuthorization({"cohortKey": cohort.cohortKey, "username": authorization.user.username, "manager": authorization.manager}, function(data) {
            if (data.value == 'true') {
                $scope.saveCohortAuthMessage = undefined;
                $scope.getCohortAuthorizations();
            } else {
                $scope.saveCohortAuthMessage = data.message;
            }
        });
    }

    $scope.onRevokeAuthorization = function(cohort, authorization) {
        cohortManagerService.revokeCohortAuthorization({"cohortKey": cohort.cohortKey, "username": authorization.user.username}, function(data) {
            if (data.value == 'true') {
                $scope.saveCohortAuthMessage = undefined;
                $scope.getCohortAuthorizations();
            } else {
                $scope.saveCohortAuthMessage = data.message;
            }
        });
    }

    $scope.getCohorts();

    $scope.cohort = cohortManagerService.getCohort();

    if ($scope.cohort != null) {
        $scope.getCohortAuthorizations();
    }

});
