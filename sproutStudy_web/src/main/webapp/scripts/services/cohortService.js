'use strict';

angular.module('sproutStudyApp')
    .factory('cohortService', function () {

        var memberDefault = {fullName: "Search", id: 0, url: ""};
        var member = memberDefault;
        var cohort = null;
        var cohortService = {};

        cohortService.setCohort = function (cohortTmp) {
            cohort = cohortTmp;
        };
        cohortService.getCohort = function () {
            if (cohort != null && cohort.length == 0) return null;

            return cohort;
        };
        cohortService.getMember = function () {
            return member;
        };
        cohortService.setMember = function (memberTmp) {

//            console.log("cohortService.setMember: " + memberTmp.lastName);

            if (memberTmp == null || memberTmp.length == 0) {
                member = memberDefault;
            } else {
                member = memberTmp;
            }
        };
        cohortService.clearMember = function () {
            member = memberDefault;
        };
        cohortService.hasDemographicForm = function() {
            var demographicFound = false;
            if (cohort !== undefined && cohort !== null && cohort.forms !== undefined) {
                $.each(cohort.forms, function(index, tmpForm) {
                    if (demographicFound == false) {
                        if (tmpForm.demographic && tmpForm.active) {
                            demographicFound = true;
                        }
                    }
                });
            }
            return demographicFound;
        }

        return cohortService;
    });


