'use strict';

angular.module('sproutStudyApp')
    .controller('listManagerController', function ($rootScope, $scope, $location, $routeParams, $window, listManagerService, studyService, cohortService, cohortManagerService) {

    $scope.lists = null;
    $scope.list = null;
    $scope.managedLists = undefined;

    $scope.managedCohorts = undefined;

    if ($rootScope.list !== undefined) $scope.list = $rootScope.list;

    cohortManagerService.getManagedCohorts({}, function(data) {
        $scope.managedCohorts = data;
    });

    $scope.listListFilter = { active: true };
    $scope.focusedForm = undefined;

    cohortService.setMember({fullName: "List Manager", id: 0, url: "lists"});

    $scope.saveListMessage = undefined;

    $scope.cohort = listManagerService.getCohort();

    if ($scope.cohort == undefined) {
        listManagerService.setCohort(cohortService.getCohort());
        $scope.cohort = listManagerService.getCohort();
    }

    if ($scope.cohort !== undefined && $scope.cohort !== null) {
        $window.sessionStorage.setItem("sproutStudyCohort", JSON.stringify($scope.cohort));
    } else {
        try {
            listManagerService.setCohort(JSON.parse($window.sessionStorage.getItem("sproutStudyCohort")));
            $scope.cohort = listManagerService.getCohort();
        } catch (e) {
            $scope.cohort = undefined;
        }
    }

    $scope.onFocusList = function (list) {
        $scope.listListFilter = { listKey: list.listKey };
        $scope.focusedList = list;
    }

    $scope.onUnFocusList = function () {
        $scope.listListFilter = { active: true };
        $scope.focusedList = undefined;
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

    $scope.isManagerOfList = function() {
        var manager = false;
        var listCurrent = $scope.list;
        if (listCurrent !== undefined && listCurrent !== null) {
            if ($scope.managedLists !== undefined) {
                $.each($scope.managedLists, function(index, tmpForm) {
                    if (tmpList.name == listCurrent.name) manager = true;
                });
            }
        }
        return manager;
    }

    $scope.onAddList = function() {
        $scope.list = {};
        $scope.list.active = true;
        listManagerService.setList(null);
        $window.sessionStorage.setItem("sproutStudyForm", undefined);
        $rootScope.list = $scope.list;
        $location.path("/lists/add");
    }

    $scope.onDeleteListDetail = function(detail) {
        if ($scope.list != null && $scope.list.cohortListDetail !== undefined) {
            var index = $scope.list.cohortListDetail.indexOf(detail);
            $scope.list.cohortListDetail.splice(index, 1);
        }
    };

    $scope.onDeleteListData = function() {
        if ($scope.list != null && $scope.list.data !== undefined && $scope.deleteListData !== undefined) {
            var index = $scope.list.data.indexOf($scope.deleteListData);
            $scope.list.data.splice(index, 1);
            $scope.deleteListData = undefined;
            $scope.deleteListDataModal = false;
        }
    };

    $scope.onDeleteEditListDetail = function() {
        if ($scope.list != null && $scope.list.detail !== undefined && $scope.deleteListDetail !== undefined) {
            var index = $scope.list.detail.indexOf($scope.deleteListDetail);
            $scope.list.detail.splice(index, 1);
            $scope.deleteListDetail = undefined;
            $scope.deleteListDetailModal = false;
        }
    };

    $scope.onAddListDetail = function(list) {
        var item = {"id": "", "cohortListKey": "", "name": "", "description": "", "required": false, "activityDate": "", "active": true, "key": generateUUID()};

        if ($scope.list === null) {
            $scope.list = {};
            $scope.list.active = true;
        }

        if ($scope.list.cohortListDetail === undefined || $scope.list.cohortListDetail === null) $scope.list.cohortListDetail = [];
        if ($scope.list.detail === undefined || $scope.list.detail === null) {
            $scope.list.detail = [];
        }

        $scope.list.cohortListDetail.push(item);
        $scope.list.detail.push(item);

    }

    $scope.onAddDataRow = function(list, data) {

        var detailsStub = new Array();

        if (list !== undefined && list.detail !== undefined && list.detail !== null) {
            $.each(list.detail, function(index, tmpDetail) {
                if (tmpDetail.active == true) {
                    var detail = {};
                    detail[tmpDetail.name] = "";
                    detail["required"] = tmpDetail.required;
                    detail["detailKey"] = tmpDetail.key;
                    detailsStub.push(detail);
                }
            });
        }

        var row = {"id": "9999999999", "discriminators": [], "name": "", "value": "", "activityDate": "", "active": true, "key": generateUUID(), "details": detailsStub};

        if ($scope.list === null) {
            $scope.list = {};
            $scope.list.active = true;
        }

        if ($scope.list.data === undefined || $scope.list.data === null) {
            $scope.list.data = [];
        }

        $scope.list.data.push(row);

    }

    $scope.onLinkView = function(list) {
        //alert("URL: list://" + list.key);
        jQuerySprout("#modal-wait-title").html("SproutList Link");
        jQuerySprout("#modal-wait-message").html("<span style='color: blue; font-family: Courier, monospace'>sproutlist://" + list.key + "</span>");
        jQuerySprout('#modal-wait').modal({
            keyboard: false
        });

    }

    $scope.onSaveListData = function(list) {
        var data = JSON.stringify(list.data);
        listManagerService.saveListData({"cohortKey": $scope.cohort.cohortKey, "listKey": $scope.list.key}, data, function(data) {
            if (data.value == 'true') {
                listManagerService.setList(null);
                $scope.saveNewListMessage = undefined;

                studyService.getCohortByKey({"cohortKey": $scope.cohort.cohortKey}, function(cohort) {
                    $scope.cohort = cohort;
                    cohortService.setCohort(cohort);
                    listManagerService.setCohort(cohortService.getCohort());
                    $window.sessionStorage.setItem("sproutStudyCohort", JSON.stringify($scope.cohort));
                    $location.path("/lists");
                });
            } else {
                $scope.saveListMessage = data.message;
            }
        });
    };

    $scope.onEditList = function(list) {
        $scope.list = list;
        listManagerService.setList(list);
        $rootScope.list = list;
        $window.sessionStorage.setItem("sproutStudyForm", undefined);
        $location.path("/lists/edit");
    };

    $scope.onDataView = function(list) {
        $scope.list = list;
        listManagerService.setList(list);
        $rootScope.list = list;
        $window.sessionStorage.setItem("sproutStudyForm", undefined);
        $location.path("/lists/data");
    };

    $scope.onSaveList = function() {
        var details = JSON.stringify($scope.list.cohortListDetail);
        listManagerService.saveList({"listKey": $scope.list.key, "listKeyFormer": "", "name": $scope.list.name, "description": $scope.list.description, "nameColumnTitle": $scope.list.nameColumnTitle, "valueColumnTitle": $scope.list.valueColumnTitle, "cohortKey": $scope.cohort.cohortKey, "publicInd": $scope.list.publicInd, "active": $scope.list.active, "details": details}, function(data) {
            if (data.value == 'true') {
                listManagerService.setList(null);
                $scope.saveNewListMessage = undefined;

                studyService.getCohortByKey({"cohortKey": $scope.cohort.cohortKey}, function(cohort) {
                    $scope.cohort = cohort;
                    cohortService.setCohort(cohort);
                    listManagerService.setCohort(cohortService.getCohort());
                    $window.sessionStorage.setItem("sproutStudyCohort", JSON.stringify($scope.cohort));
                    $location.path("/lists");
                });
            } else {
                $scope.saveListMessage = data.message;
            }
        });
    }

    $scope.onSaveEditList = function() {
        var details = JSON.stringify($scope.list.detail);
        listManagerService.saveList({"listKey": $scope.list.key, "listKeyFormer": $scope.list.keyFormer, "name": $scope.list.name, "description": $scope.list.description, "nameColumnTitle": $scope.list.nameColumnTitle, "valueColumnTitle": $scope.list.valueColumnTitle, "cohortKey": $scope.cohort.cohortKey, "publicInd": $scope.list.publicInd, "active": $scope.list.active, "details": details}, function(data) {
            if (data.value == 'true') {
                listManagerService.setList(null);
                $scope.saveNewListMessage = undefined;

                studyService.getCohortByKey({"cohortKey": $scope.cohort.cohortKey}, function(cohort) {
                    $scope.cohort = cohort;
                    cohortService.setCohort(cohort);
                    listManagerService.setCohort(cohortService.getCohort());
                    $window.sessionStorage.setItem("sproutStudyCohort", JSON.stringify($scope.cohort));
                    $location.path("/lists");
                });
            } else {
                $scope.saveListMessage = data.message;
            }
        });
    }

        $scope.modalSmallOpts = {
//            backdropFade: true, // These two settings
//            dialogFade: true,
            dialogClass: 'modal modal-200-600'
        };

        $scope.onOpenDeleteListModal = function(listKey) {
            $scope.deleteListKey = listKey;
            $scope.deleteMessageText = "Are you sure you want to delete this list?  This action cannot be undone!";
            $scope.deleteListButtonText = "Delete";
            $scope.deletingList = false;
            $scope.deleteListModal = true;
        }

        $scope.onCloseDeleteListModal = function(listKey) {
            $scope.deleteListKey = undefined;
            $scope.deleteListModal = false;
        };

        $scope.onOpenDeleteListDataModal = function(data) {
            $scope.deleteListData = data;
            $scope.deleteMessageText = "Are you sure you want to delete this record?  This action cannot be undone!";
            $scope.deleteListDataButtonText = "Delete";
            $scope.deletingListData = false;
            $scope.deleteListDataModal = true;
        }

        $scope.onCloseDeleteListDataModal = function(listKey) {
            $scope.deleteListData = undefined;
            $scope.deleteListDataModal = false;
        };

        $scope.onOpenDeleteListDetailModal = function(detail) {
            $scope.deleteListDetail = detail;
            $scope.deleteMessageText = "Are you sure you want to delete this supplemental field?  This action cannot be undone!";
            $scope.deleteListDetailButtonText = "Delete";
            $scope.deletingListDetail = false;
            $scope.deleteListDetailModal = true;
        }

        $scope.onCloseDeleteListDetailModal = function(listKey) {
            $scope.deleteListDetail = undefined;
            $scope.deleteListDetailModal = false;
        };

    $scope.onDeleteList = function() {
        listManagerService.deleteList({"listKey": $scope.deleteListKey, "cohort": $scope.cohort.cohortKey}, function(data) {
            if (data.value == 'true') {
                listManagerService.setList(null);
                $scope.saveNewListMessage = undefined;

                studyService.getCohortByKey({"cohortKey": $scope.cohort.cohortKey}, function(cohort) {
                    $scope.cohort = cohort;
                    cohortService.setCohort(cohort);
                    listManagerService.setCohort(cohortService.getCohort());
                    $window.sessionStorage.setItem("sproutStudyCohort", JSON.stringify($scope.cohort));
                });

                $scope.deleteListKey = undefined;
                $scope.deleteListModal = false;
            } else {
                $scope.saveListMessage = data.message;
            }
        });
    }

    $scope.onListModified = function(list) {
        list.modified = true;
    };

        $scope.detailOrderById = function(data){
            return parseInt(data.id);
        };
        $scope.detailOrderByDetailId = function(data){
            return parseInt(data.detailId);
        };
});
