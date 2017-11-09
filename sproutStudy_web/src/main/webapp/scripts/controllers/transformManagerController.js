'use strict';

angular.module('sproutStudyApp')
    .controller('transformManagerController', function ($scope, $timeout, $compile, $location, $routeParams, $window, formManagerService, studyService, formsService, cohortService, transformService) {

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
    $scope.templateLoaded = false;

        $scope.autosave = true;

    $scope.editor;
    $scope.syncNarrative = true;
    $scope.syncModel = true;

    $scope.paths = [];
    $scope.model = "";
    $scope.template = "";

    $scope.reloadNarrativeQueue = false;

        $scope.checkReloadNarrativeQueue = function () {
            // console.log("checkReloadNarrativeQueue");
            if ($scope.reloadNarrativeQueue) {
                $scope.onReloadNarrative();
                $scope.reloadNarrativeQueue = false;
            }
            $timeout(function() {$scope.checkReloadNarrativeQueue()}, 1000);
        };

        $timeout(function() {$scope.checkReloadNarrativeQueue()}, 1);

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
            if ($scope.translations && $scope.translations.length > 0) {
                $scope.updateTranslations();
            }

            $scope.updateSubmissionDate();

            updateSproutTransformModelView($scope.modelVerbose);
            // $scope.onReloadNarrative();
            $scope.reloadNarrativeQueue = true;
        };

        $scope.updateLocale = function () {
            if ($scope.model && $scope.model.sprout) {
                $scope.model.sprout.locale = $scope.locale;
            }
            if ($scope.modelVerbose && $scope.modelVerbose.sprout) {
                $scope.modelVerbose.sprout['sprout%locale'] = $scope.locale;
            }
        };

        $scope.updateSubmissionDate = function () {
            if ($scope.model && $scope.model.sprout) {
                $scope.model.sprout.submissionDate = "" + new Date().getTime();
            }
            if ($scope.modelVerbose && $scope.modelVerbose.sprout) {
                $scope.modelVerbose.sprout['sprout%submissionDate'] = "" + new Date().getTime();
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

        $scope.updateTranslations = function () {
            if ($scope.translations && $scope.translations.length > 0) {
                var translations = {};

                angular.forEach($scope.translations, function(translation) {
                    var locales = {};
                    angular.forEach(translation.locales, function(locale) {
                        locales[locale.locale.key] = locale.message;
                    });
                    translations[translation.key] = locales;
                });

                if ($scope.model && $scope.model.sprout) {
                    $scope.model['translations'] = translations;
                }
                if ($scope.modelVerbose && $scope.modelVerbose.sprout) {
                    $scope.modelVerbose['translations'] = translations;
                }
            } else if ($scope.model.sprout.translations) {
                delete $scope.model.translations;
            }
        };
        // $scope.updateTranslations = function () {
        //     if ($scope.translations && $scope.translations.length > 0) {
        //         var translations = {};
        //
        //         angular.forEach($scope.translations, function(translation) {
        //             var locales = {};
        //             angular.forEach(translation.locales, function(locale) {
        //                 locales[locale.locale.key] = locale.message;
        //             });
        //             translations[translation.key] = locales;
        //         });
        //
        //         if ($scope.model && $scope.model.sprout) {
        //             $scope.model.sprout['translations'] = translations;
        //         }
        //         if ($scope.modelVerbose && $scope.modelVerbose.sprout) {
        //             $scope.modelVerbose.sprout['sprout%translations'] = translations;
        //         }
        //     } else if ($scope.model.sprout.translations) {
        //         delete $scope.model.sprout.translations;
        //     }
        // };

    $scope.changeLocale = function(locale) {
        $scope.locale = locale;
        $scope.onReloadModel();
    };

    $scope.onCloseCustomAttributeModal = function () {
        $scope.customAttributeModal = false;
        $scope.onReloadModel();
    };

    $scope.onCloseTranslationsModal = function () {
        $scope.translationsModal = false;
        $scope.onSaveTemplate();
        $scope.onReloadModel();
    };

    $scope.onAddCustomAttribute = function () {
        if (!$scope.customAttributes) $scope.customAttributes = [];
        $scope.customAttributes.push({})
    };

        $scope.onOpenTranslationsModal = function () {
            if (!$scope.translations) {
                $scope.translations = [];
                // $scope.translations.push({})
            }
            $scope.translationsModal = true;
            $scope.translationsModalMode = 'list';
            $scope.translationsFilter = undefined;
            $scope.translationsPage = [];
            $scope.translationsPageNumber = 0;


            $scope.fillTranslationPage();

        };

        $scope.nextTranslationPage = function () {
            $scope.translationsPageNumber++;
            $scope.fillTranslationPage();

        };

        $scope.previousTranslationPage = function () {
            $scope.translationsPageNumber--;
            $scope.fillTranslationPage();

        };

        $scope.fillTranslationPage = function () {
            var itemsPerPage = 10;

            var startNumber = $scope.translationsPageNumber * itemsPerPage;
            var endNumber = startNumber + itemsPerPage;

            if ($scope.translations && $scope.translations.length > 0) {
                var counter = 0;
                angular.forEach($scope.translations, function (translation) {
                    var countMe = true;
                    if ($scope.translationsFilter && $scope.translationsFilter.length > 0) {
                        if (JSON.stringify(translation).toLowerCase().indexOf($scope.translationsFilter.toLowerCase()) < 0) countMe = false;
                    }

                    if (countMe) {
                        if (counter >= startNumber && counter < endNumber) {
                            $scope.translationsPage.push(translation);
                        }
                        counter++
                    }

                });
            }

        };

        $scope.onAddTranslation = function () {
            if (!$scope.translations) $scope.translations = [];

            var translation = {};
            translation.key = "";

            var locales = [];
            locales.push({"locale": {"key": "en", "name": "English"}, "message": ""});
            locales.push({"locale": {"key": "es", "name": "Spanish"}, "message": ""});
            translation.locales = locales;

            $scope.translations.push(translation);
            $scope.translationsPage.push(translation);
            $scope.translationsFilter = undefined;

            // var objDiv = document.getElementById("translationsModalMapper");
            // objDiv.scrollTop = objDiv.scrollHeight;


            $('#translationsModalMapper').stop().animate({
                scrollTop: $('#translationsModalMapper')[0].scrollHeight
            }, 800);
        };

        $scope.onDeleteTranslation = function ($index, translation) {

            // console.log("onDeleteTranslation");
            // console.dir(translation);
            
            $scope.translations.splice($index, 1);
            // $scope.translations.push(translation);
            $scope.translationsPage.splice($index, 1);

        };

        $scope.filterTranslationList = function () {
            // console.log("filterTranslationList.translationsFilter: " + $scope.translationsFilter);
            $scope.translationsPageNumber = 0;
            $scope.translationsPage = [];
            $scope.fillTranslationPage();
        };

        $scope.onExportTranslations = function () {
            if ($scope.translations) {

                var data = [["Key", "English", "Español"]];
                angular.forEach($scope.translations, function (translation) {
                    var row = [];
                    row.push(translation.key);
                    var en = "";
                    var es = "";
                    angular.forEach(translation.locales, function(locale) {
                        if (locale.message) {
                            var quotesInd = locale.message.indexOf("\"") >= 0 || locale.message.indexOf(",") >= 0;
                            // var quotesInd = true;
                            var message = (quotesInd ? "\"" : "") + locale.message.replace(/"/g, "\"\"") + (quotesInd ? "\"" : "");
                            if (locale.locale.key === 'en') en = message;
                            if (locale.locale.key === 'es') es = message;
                        }
                    });

                    row.push(en);
                    row.push(es);
                    data.push(row);
                });

                var csvContent = "data:text/csv;charset=utf-8,";
                data.forEach(function(infoArray, index){
                    var dataString = infoArray.join(",");
                    csvContent += index < data.length ? dataString+ "\n" : dataString;
                });

                var encodedUri = encodeURI(csvContent);
                var link = document.createElement("a");
                link.setAttribute("href", encodedUri);
                link.setAttribute("download", "SproutTranslations.csv");
                document.body.appendChild(link); // Required for FF

                link.click();
            }
        };

        function loadHandler(event) {
            var csv = event.target.result;
            processTranslationsImportData(csv);
        }

        function parseCSV(str) {
            var arr = [];
            var quote = false;  // true means we're inside a quoted field

            var col, c;

            // iterate over each character, keep track of current row and column (of the returned array)
            for (var row = col = c = 0; c < str.length; c++) {
                var cc = str[c], nc = str[c+1];        // current character, next character
                arr[row] = arr[row] || [];             // create a new row if necessary
                arr[row][col] = arr[row][col] || '';   // create a new column (start with empty string) if necessary

                // If the current character is a quotation mark, and we're inside a
                // quoted field, and the next character is also a quotation mark,
                // add a quotation mark to the current column and skip the next character
                if (cc == '"' && quote && nc == '"') { arr[row][col] += cc; ++c; continue; }

                // If it's just one quotation mark, begin/end quoted field
                if (cc == '"') { quote = !quote; continue; }

                // If it's a comma and we're not in a quoted field, move on to the next column
                if (cc == ',' && !quote) { ++col; continue; }

                // If it's a newline and we're not in a quoted field, move on to the next
                // row and move to column 0 of that new row
                if ((cc == '\n' || cc == '\r') && !quote) { ++row; col = 0; continue; }

                // Otherwise, append the current character to the current column
                arr[row][col] += cc;
            }
            return arr;
        }

        function processTranslationsImportData(csv) {

            $scope.translationsModalMode = 'importing';

            var translationArray = parseCSV(csv);

            if (translationArray && translationArray.length > 0) {
                var translations = [];

                var rowCounter = 0;

                angular.forEach(translationArray, function(row) {

                    if (rowCounter++ > 0 && Array.isArray(row)) {
                        var key = row[0];

                        var en = row[1];
                        var es = row[2];


                        // console.log("*****************");
                        // console.log("es1: [" + es + "]");
                        // console.log("es1.length: " + es.length);
                        // es.replace(new RegExp('\\r', 'g'), '')
                        // console.log("es2: " + es.length);
                        //
                        // if (row[1] && row[1].indexOf('"') === 0) {
                        //     en = row[1].substring(1, row[1].length - 1).replace(/\"\"/g, '\"');
                        // }
                        // if (row[2] && row[2].indexOf('"') === 0) {
                        //     es = row[2].substring(1, row[2].length - 1).replace(/\"\"/g, '\"');
                        // }

                        // es = 'test123';


                        if (key) {
                            var translation = {};
                            translation.key = key;

                            var locales = [];
                            locales.push({"locale": {"key": "en", "name": "English"}, "message": en});
                            locales.push({"locale": {"key": "es", "name": "Spanish"}, "message": es});
                            translation.locales = locales;

                            translations.push(translation);
                        }
                    }
                });

                $scope.translations = translations;
                $scope.onSaveTemplate();

                $scope.translationsFilter = undefined;
                $scope.filterTranslationList();

            }

            $scope.translationsModalMode = 'list';

        }
        // function processTranslationsImportData(csv) {
        //     var allTextLines = csv.split(/\r\n|\n/);
        //     var lines = [];
        //     while (allTextLines.length) {
        //         lines.push(allTextLines.shift().split(','));
        //     }
        //     // console.log(lines);
        //
        //     if (lines && lines.length > 0) {
        //         var translations = [];
        //
        //         var rowCounter = 0;
        //
        //         angular.forEach(lines, function(row) {
        //
        //             if (rowCounter++ > 0 && Array.isArray(row)) {
        //                 var key = row[0];
        //
        //                 var en = row[1];
        //                 var es = row[2];
        //
        //                 if (row[1] && row[1].indexOf('"') === 0) {
        //                     en = row[1].substring(1, row[1].length - 1).replace(/\"\"/g, '\"');
        //                 }
        //                 if (row[2] && row[2].indexOf('"') === 0) {
        //                     es = row[2].substring(1, row[2].length - 1).replace(/\"\"/g, '\"');
        //                 }
        //
        //                 if (key) {
        //                     var translation = {};
        //                     translation.key = key;
        //
        //                     var locales = [];
        //                     locales.push({"locale": {"key": "en", "name": "English"}, "message": en});
        //                     locales.push({"locale": {"key": "es", "name": "Spanish"}, "message": es});
        //                     translation.locales = locales;
        //
        //                     translations.push(translation);
        //                 }
        //             }
        //         });
        //
        //         $scope.translations = translations;
        //         $scope.onSaveTemplate();
        //     }
        //
        // }

        function errorHandler(evt) {
            if(evt.target.error.name == "NotReadableError") {
                alert("Error: invalid CSV file.");
            }
        }

        $scope.onImportTranslations = function () {
            // console.log("onImportTranslations");
            $scope.translationsModalMode = 'importPrompt';
            $("#csvFileInput").click();
        };

    $scope.onDeleteCustomAttribute = function ($index) {
        $scope.customAttributes.splice([$index],1);

        if ($scope.customAttributes.length == 0) {
            $scope.customAttributes.push({})
        }
    };

    $scope.onReloadNarrative = function() {
        compileTemplate();
    };

    $scope.onToggleSyncNarrative = function() {
        $scope.syncNarrative = !$scope.syncNarrative;
        if ($scope.syncNarrative) {
            // $scope.onReloadNarrative();
            $scope.reloadNarrativeQueue = true;
        }
    };

    $scope.onToggleSyncModel = function() {
        $scope.syncModel = !$scope.syncModel;
        if ($scope.syncModel) $scope.onReloadModel();
    };

    $scope.onSyncModel = function() {
        if ($scope.syncModel) {
            $scope.onReloadModel();
            if ($scope.syncNarrative) {
                // $scope.onReloadNarrative();
                $scope.reloadNarrativeQueue = true;
            }
        }
    };

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


                console.log("response: " + response);
                console.dir(response);

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

        var model = {};
        model.template = $scope.template;
        model.translations = JSON.stringify($scope.translations);

        transformService.saveTemplate({publicationKey: $scope.form.publicationKey, instanceId: null, templateKey: $scope.templateKey, masterInd: true}, model, function(data) {
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
    };

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

            if (data.translations && typeof data.translations === 'string') {
                $scope.translations = JSON.parse(data.translations);
                $scope.onReloadModel();
            }

            $scope.templateLoaded = true;
        });
    };

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
            if ($scope.syncNarrative) {
                // $scope.onReloadNarrative();
                $scope.reloadNarrativeQueue = true;
            }
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


                var model = {};
                model.template = $scope.template;
                model.translations = JSON.stringify($scope.translations);

                transformService.saveTemplate({publicationKey: $scope.form.publicationKey, instanceId: null, templateKey: $scope.templateKey, masterInd: true}, model, function(data) {
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
        };

        var autosaveIntervalID = window.setInterval(triggerAutosave, 1500);

        $scope.getFile = function () {
            var reader = new FileReader();
            // Handle errors load
            reader.onload = loadHandler;
            reader.onerror = errorHandler;
            // Read file into memory as UTF-8
            reader.readAsText($scope.file);
        };

}).directive("ngFileSelect",function(){

    return {
        link: function($scope,el){

            el.bind("change", function(e){

                $scope.file = (e.srcElement || e.target).files[0];
                $scope.getFile();
            })

        }

    }


});
