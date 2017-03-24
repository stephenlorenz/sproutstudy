'use strict';

angular.module('sproutStudyApp')
    .controller('transformManagerController', function ($scope, $timeout, $location, $routeParams, $window, formManagerService, studyService, formsService, cohortService, transformService) {

    $scope.managedForms = undefined;
    $scope.instanceId = undefined;

    $scope.deliveringInd = undefined;
    $scope.deliveryError = null;

    $scope.templateKey = null;
    $scope.template = undefined;
    $scope.templateError = undefined;

    $scope.templateHasChanges = false;
    $scope.templateSavedSuccessfully = false;
        $scope.templateAutosavedSuccessfully = false;

    $scope.aceCursorPosition = undefined;

    $scope.formLoaded = false;

        $scope.autosave = true;

    $scope.editor;
    $scope.syncNarrative = true;
    $scope.syncModel = true;

    $scope.paths = [];
    $scope.model = "";
    $scope.template = "";


        $scope.modalSmallOpts = {
//            backdropFade: true, // These two settings
//            dialogFade: true,
            dialogClass: 'modal modal-200-600'
        };


        cohortService.setMember({fullName: "Transform Manager", id: 0, url: "forms", click: "closeForm"});

    $scope.form = formManagerService.getForm();
    $scope.subject = {"id": 1, "schema": "sprouttransform", "firstName": "User", "lastName": "SproutTransform", "fullName": "SproutTransform User"};

    if ($scope.form !== undefined && $scope.form !== null) {
        $window.sessionStorage.setItem("sproutStudyForm", JSON.stringify($scope.form));
    } else {
        try {
            formManagerService.setForm(JSON.parse($window.sessionStorage.getItem("sproutStudyForm")));
            $scope.form = formManagerService.getForm();
        } catch (e) {
            $scope.form = undefined;
        }
    }

        $scope.toggleAutoSave = function() {
            $scope.autosave = !$scope.autosave;
        }

    $scope.closeForm = function () {}

    $scope.applyIfPossible = function() {
        $timeout(function() {});
    }

    $scope.formLoadUpdate = function(instanceId) {
        $scope.setInstanceId(instanceId);
        $scope.formLoaded = true;
    }

    $scope.setInstanceId = function(instanceId) {
        $scope.instanceId = instanceId;
    }

    $scope.setPaths = function(paths) {
        $scope.paths = paths;
    }

    $scope.setTemplateError = function(templateError) {
        $scope.templateError = templateError;
    }

    $scope.showTemplateError = function() {
        alert($scope.templateError);
    }

        $scope.onReloadModel = function () {
            $scope.model = getNarrativeModel($scope.instanceId);
            $scope.modelVerbose = getNarrativeModelVerbose($scope.instanceId);

            if ($scope.locale) {
                $scope.updateLocale();
            }
            if ($scope.customAttributes && $scope.customAttributes.length > 0) {
                $scope.updateCustomAttributes();
            }

            updateSproutTransformModelView($scope.modelVerbose);
            $scope.onReloadNarrative();
        };

        $scope.updateLocale = function () {
            if ($scope.model && $scope.model.sprout) {
                $scope.model.sprout.locale = $scope.locale;
            }
            if ($scope.modelVerbose && $scope.modelVerbose.sprout) {
                $scope.modelVerbose.sprout['sprout%locale'] = $scope.locale;
            }
        };

        $scope.updateCustomAttributes = function () {

            var customAttributes = {};

            angular.forEach($scope.customAttributes, function(customAttribute) {
                customAttributes[customAttribute.name] = customAttribute.value;
            });

            if ($scope.model && $scope.model.sprout) {
                $scope.model.sprout['custom'] = customAttributes;
            }
            if ($scope.modelVerbose && $scope.modelVerbose.sprout) {
                $scope.modelVerbose.sprout['sprout%custom'] = customAttributes;
            }
        };

    $scope.changeLocale = function(locale) {
        $scope.locale = locale;
        $scope.onReloadModel();
    };

    $scope.onCloseCustomAttributeModal = function () {
        $scope.customAttributeModal = false;
        $scope.onReloadModel();
    };

    $scope.onAddCustomAttribute = function () {
        if (!$scope.customAttributes) $scope.customAttributes = [];
        $scope.customAttributes.push({})
    };

    $scope.onDeleteCustomAttribute = function ($index) {
        $scope.customAttributes.splice([$index],1);

        if ($scope.customAttributes.length == 0) {
            $scope.customAttributes.push({})
        }
    };

    $scope.onReloadNarrative = function() {
        compileTemplate();
    }

    $scope.onToggleSyncNarrative = function() {
        $scope.syncNarrative = !$scope.syncNarrative;
        if ($scope.syncNarrative) $scope.onReloadNarrative();
    }

    $scope.onToggleSyncModel = function() {
        $scope.syncModel = !$scope.syncModel;
        if ($scope.syncModel) $scope.onReloadModel();
    }

    $scope.onSyncModel = function() {
        if ($scope.syncModel) {
            $scope.onReloadModel();
            if ($scope.syncNarrative) $scope.onReloadNarrative();
        }
    }

    $scope.onViewAsText = function() {
        compileTemplate();
        $scope.narrativeText = "";
        transformService.getNarrativeText({}, $scope.narrative, function(narrativeText) {
            // console.log("narrativeText: " + narrativeText);
            $scope.narrativeText = narrativeText;
            $scope.textViewModal = true;
        });
    };

        $scope.waitForModal = function (data) {
            $timeout(function() {
                var pdfViewContainer = jQuery('#pdfViewContainer').attr("src");

                if (pdfViewContainer == undefined) {
                    //Wait some more...
                    $scope.waitForModal(data);
                } else {
                    try {
                        jQuery('#pdfViewContainer').attr("src", data);
                    } catch (e) {}
                }
            }, 200);
        };

    $scope.onViewAsPDF = function() {
        $scope.pdfLoadingError = undefined;
        $scope.pdfViewModal = true;
        $scope.pdfLoading = true;

        transformService.getNarrativePDF({}, $scope.narrative, function(response) {
            $scope.pdfLoading = false;

            if (response) {

                var content = response;

                if (typeof response !== 'object') {
                    content = JSON.parse(response);
                }

                if (content.success) {
                    $scope.waitForModal("data:application/pdf;base64," + escape(content.data));
                } else {
                    $scope.pdfLoadingError = content.message;
                }


            }

        });
    };

    $scope.onViewFromServer = function() {
        // console.log("onViewFromServer");
        var model = angular.element(jQuerySprout("#transformControllerDiv")).scope().getModel();

        //if (typeof model != 'string') {
        //    json = JSON.stringify(model, undefined, 2);
        //    console.log("onViewFromServer.model1: " + json);
        //} else {
        //    console.log("onViewFromServer.model2: " + model);
        //}
        $scope.narrativeHtmlServer = "";
        transformService.getNarrativeServer({publicationKey: $scope.form.publicationKey, instanceId: null}, model, function(narrativeHtmlServer) {
            // console.log("narrativeHtmlServer: " + narrativeHtmlServer);
            $scope.narrativeHtmlServer = narrativeHtmlServer;
            $scope.narrativeHtmlClient = $("#sproutTransformNarrativeContent").html();
            //$scope.textViewModal = true;
            $scope.narrativeServerViewModal = true;
        });
    };

    $scope.onOpenCustomAttributeModal = function () {
        if (!$scope.customAttributes) {
            $scope.customAttributes = [];
            $scope.customAttributes.push({})
        }
        $scope.customAttributeModal = true;
    };

    $scope.onCloseTextViewModal = function() {
        $scope.textViewModal = false;
    }

    $scope.onClosePDFViewModal = function() {
        $scope.pdfViewModal = false;
    }

    $scope.onCloseNarrativeServerViewModal = function() {
        $scope.narrativeServerViewModal = false;
    }

    $scope.setNarrative = function(narrative) {
        $scope.narrative = narrative;
    }

    $scope.onSaveTemplate = function() {
        $scope.aceCursorPosition = $scope.editor.getCursorPosition();
        $scope.getTemplateFromEditor();
        transformService.saveTemplate({publicationKey: $scope.form.publicationKey, instanceId: null, templateKey: $scope.templateKey, masterInd: true}, $scope.template, function(data) {
            if (data.value == 'false') {
                alert("Failed to save narrative template.");
            } else {
                if ($scope.templateKey !== null && data.message !== undefined && data.message !== null) $scope.templateKey = data.message;
                $scope.templateSavedSuccessfully = true;
                $scope.templateHasChanges = false;

                setTimeout(function(){$scope.templateSavedSuccessfully = false; $scope.applyIfPossible();}, 3000);
                $scope.focusOnEditor();
            }
        });
    }

    $scope.focusOnEditor = function() {
        if ($scope.aceCursorPosition !== undefined) {
            $scope.editor.navigateTo($scope.aceCursorPosition.row, $scope.aceCursorPosition.column);
        } else {
            $scope.editor.navigateFileEnd();
        }
        $scope.editor.focus();
    }

    $scope.setTemplate = function(publicationKey) {
        $scope.templateKey = undefined;
        $scope.template = undefined;

        transformService.getTemplate({publicationKey: publicationKey, instanceId: null}, function(data) {
            $scope.templateKey = data.key;
            $scope.template = data.template;
        });
    }

    $scope.onDeliverForm = function(subject, form) {
        $scope.deliveringInd = true;
        $scope.deliveryError = null;

        formsService.deliverOrOpenForm({schema: subject.schema, id: subject.id, publicationKey: form.publicationKey, provider: null, expirationDate: null,}, function(data) {
            if (data.instanceId != null) {
                var instanceId = data.instanceId;
                var formObject = $scope.newFormConstructor(instanceId, form.publicationKey, form.name, subject.fullName, subject.firstName, subject.lastName, subject.id);

                formsService.applyForNonce({instanceId: instanceId, subjectName: subject.fullName, subjectId: subject.id}, function(dataCallback) {
                    var nonce = dataCallback.nonce;
                    addTransformAdminContentForm(formObject, nonce);
                    setTimeout(sizeTransformPane, 1000);
                });

                $scope.deliverFormModal = false;
                $scope.deliveringInd = false;
            } else {
                $scope.deliveringInd = 'error';
                $scope.deliveryError = data.message;
            }
        });
    }

    $scope.newFormConstructor = function(instanceId, publicationKey, title, fullName, firstName, lastName, subjectId) {
        return {instanceId: instanceId, title: title, identityFullName: fullName, identityFirstName: firstName, identityLastName: lastName, identityPrimaryId: subjectId, publicationKey: publicationKey};
    }

    if ($scope.form !== undefined && $scope.subject !== undefined) {
        $scope.onDeliverForm($scope.subject, $scope.form);

    }

    $scope.getTemplateFromEditor = function() {
        $scope.template = $scope.editor.getSession().getValue();
        return $scope.template;
    }

    $scope.getModel = function() {
        return $scope.model;
    }

    $scope.everythingIsDone = function() {
        $scope.templateHasChanges = false;
    }

    $scope.aceLoaded = function(_editor) {

        $scope.editor = _editor;
        // Options
        //_editor.setReadOnly(true);

//    var langTools = ace.require("ace/ext/language_tools");


        // Editor part
        var _session = _editor.getSession();
        //var _renderer = _editor.renderer;

        // Options
        //_editor.setReadOnly(true);
        _session.setUndoManager(new ace.UndoManager());
        //_renderer.setShowGutter(false);

        // Events
        //_editor.on("changeSession", function(){  });
        _session.on("change", function(){
            $scope.template = _session.getValue();

            $scope.templateHasChanges = true;

            $scope.templateSavedSuccessfully = false;
            if ($scope.syncNarrative) $scope.onReloadNarrative();
        });

        _editor.commands.on("afterExec", function(e){
//            if (e.command.name == "insertstring" && /^[\<_]$/.test(e.args)) {
            if (e.command.name == "insertstring" && /^[\{][\{]$/.test(e.args)) {
                _editor.execCommand("startAutocomplete");
            }
        });


        _session.setUseWrapMode(true);
        _session.setWrapLimitRange();

        $scope.setTemplate($scope.form.publicationKey);
    };
    $scope.aceChanged = function(e) {
//        console.log("aceChanged");
//        $scope.templateHasChanges = false;
    };


    var langTools = ace.require("ace/ext/language_tools");

    var sproutTransformSyntaxCompleter = {
        getCompletions: function(editor, session, pos, prefix, callback) {

//            console.log("sproutTransformSyntaxCompleter.getCompletions: " + prefix);
//            console.log("pos: " + pos.column + ":" + pos.row);
//
//{ callback(null, []); return }

            if (prefix.length === 0) { callback(null, []); return }



//            var regexTest = new RegExp('^' + prefix, "i");
            var regexTest = new RegExp('^' + prefix);

//                var keywords = ["SELECT","INSERT","UPDATE","DELETE","FROM","WHERE","AND","OR","GROUP","BY","ORDER","LIMIT","OFFSET","HAVING","AS","CASE","WHEN","ELSE","END","TYPE","LEFT","RIGHT","JOIN","ON","OUTER","DESC","ASC","UNION"];
            var keywords = $scope.paths;

            var matches = [];
            var i = 0;

            for (i=0;i<keywords.length;i++) {
                if (keywords[i].match(regexTest)) {
                    matches.push({"name": keywords[i], "value": keywords[i]});
                }
            }
            callback(null, matches);
            return;
        }
    }
    langTools.setCompleters();
    langTools.addCompleter(sproutTransformSyntaxCompleter);

        var triggerAutosave = function() {
            if ($scope.autosave && $scope.templateHasChanges) {
                $scope.getTemplateFromEditor();
                transformService.saveTemplate({publicationKey: $scope.form.publicationKey, instanceId: null, templateKey: $scope.templateKey, masterInd: true}, $scope.template, function(data) {
                    if (data.value == 'false') {
                        alert("Failed to save narrative template.");
                    } else {
                        if ($scope.templateKey !== null && data.message !== undefined && data.message !== null) $scope.templateKey = data.message;
                        $scope.templateAutosavedSuccessfully = true;
                        $scope.templateHasChanges = false;

                        setTimeout(function(){$scope.templateAutosavedSuccessfully = false; $scope.applyIfPossible();}, 2000);

                    }
                });


            }
        }

        var autosaveIntervalID = window.setInterval(triggerAutosave, 1500);

});
