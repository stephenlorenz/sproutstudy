<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page session="true" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
<head>
    <title>Sprout Study</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta http-equiv="CACHE-CONTROL" content="NO-CACHE">
    <meta http-equiv="PRAGMA" content="NO-CACHE">

    <link rel="icon" type="image/png" href="favicon.png">
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <!-- Place favicon.ico and apple-touch-icon.png in the root directory -->
    <!-- build:css styles/main.css -->
    <link rel="stylesheet" href="styles/main.css">
    <!-- endbuild -->

    <style type="text/css">
        [ng\:cloak], [ng-cloak], .ng-cloak {
            display: none !important;
        }
    </style>

	<!--[if lt IE 9]>
		<script type="text/javascript" src="/sproutassets/scripts/html5shiv/html5shiv.js"></script>
		<script src="/sproutassets/components/es5-shim/es5-shim.js"></script>
		<script src="/sproutassets/components/json3/lib/json3.min.js"></script>
	<![endif]-->
	
	<!--[if lte IE 8]>
	    <script>
	      document.createElement('ng-include');
	      document.createElement('ng-pluralize');
	      document.createElement('ng-view');
	      document.createElement('forms');
	
	      // Optionally these for CSS
	      document.createElement('ng:include');
	      document.createElement('ng:pluralize');
	      document.createElement('forms');
	    </script>
	<![endif]-->
    
    <link rel="icon" href="/sproutassets/images/favicon.png" type="image/png" />
    
    <link type="text/css" rel="stylesheet" href="/sproutassets/stylesheets/bootstrap/css/bootstrap.css" />
    <%--<link type="text/css" rel="stylesheet" href="/sproutassets/stylesheets/bootstrap/css/bootstrap.icon-large.min.css" />--%>
	<link type="text/css" rel="stylesheet" href="/sproutassets/stylesheets/lcs/css/lcs.css" />
	<link type="text/css" rel="stylesheet" href="/sproutassets/stylesheets/lcs/css/login.css" />
	<link type="text/css" rel="stylesheet" href="/sproutassets/stylesheets/bootstrap/css/responsive.css" />
	<link type="text/css" rel="stylesheet" href="assets/components/font-awesome/css/font-awesome.min.css">
	<link type="text/css" rel="stylesheet" href="assets/components/icomoon99838/style.css">
	<link type="text/css" rel="stylesheet" href="assets/scripts/external/splitter/css/style.css">
	<!--[if IE]><link rel="stylesheet" type="text/css" href="/sproutassets/stylesheets/ie/ie.css"></link><![endif]-->
   	<!--[if lt IE 9]><link rel="stylesheet" type="text/css" href="/sproutassets/stylesheets/ie/ie8.css"></link><![endif]-->
   	<!--[if lt IE 7]><link rel="stylesheet" type="text/css" href="/sproutassets/stylesheets/ie/ie6.css"></link><![endif]-->
    
    
</head>
<body xmlns:ng="http://angularjs.org" id="ng-app" class="ng-app:sproutStudyApp" xmlns:forms="http://angularjs.org" ng-app="sproutStudyApp" >

<!--[if lt IE 7]>
<p class="chromeframe">You are using an outdated browser. <a href="http://browsehappy.com/">Upgrade your browser
    today</a> or <a href="http://www.google.com/chromeframe/?redirect=true">install Google Chrome Frame</a> to better
    experience this site.</p>
<![endif]-->

<!-- Add your site or application content here -->

<script type="text/javascript">
    var sproutStudyResourceBase = "";
</script>

<div class="container container-sproutstudy" ng-controller="cohortController">
	<div class="navbar navbar-fixed-top" ng-cloak>
		<div class="navbar-inner">
			<ul class="nav sproutstudy-tab-container-reset" style="margin: 0px 0px 0px 0px;">
                <li class="sproutstudy-tab-reset" ><a href="/sproutstudy" class="sproutstudy-tab-button" instance="reset"><i class="icon-home"></i></a></li>
                <%--<li class="sproutstudy-tab-reset" ><a href="/sproutstudy" class="sproutstudy-tab-button" instance="reset"><img style="width: 20px; height: 20px; " src="/sproutstudy/images/sprout-study-60-2.png" alt="Sprout Study Logo" /></a></li>--%>
		    </ul>
			<ul class="nav" id="sproutstudy-tab-container">
                <li class="sproutstudy-tab-li sproutstudy-tab-home" instance="home"><a ng-href="#/{{member().url}}" class="sproutstudy-tab-button" instance="home">{{member().fullName}}<span ng-show="member().id > 0"> ({{member().id}})</span></a></li>
		    </ul>

            <ul class="nav pull-right">
                <li><a>{{session().firstName}}</a></li>
                <li ng-show="cohort() != null && cohort().name.length > 0"><a>{{cohort().name}} Cohort</a></li>
                <li class="dropdown sproutstudy-tab-cohort">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <i class="fa fa-bars"></i>
                    </a>
                    <ul class="dropdown-menu" style="min-width: 150px;">
                        <li><a href="#/" ng-click="changeCohort()" class="sproutstudy-tab-button">Change Cohort</a></li>
                        <li><a href="#/cohorts" class="sproutstudy-tab-button" ng-show="isCohortManager()">Cohort Admin</a></li>
                        <li><a href="#/forms" class="sproutstudy-tab-button" ng-show="isManagerOfCohort() || isAdmin()  ">Form Admin</a></li>
                        <li><a href="#/lists" class="sproutstudy-tab-button" ng-show="isManagerOfCohort() || isAdmin()  ">List Admin</a></li>
                        <li><a id="btn_logout" href="logout">Logout</a></li>
                    <%--<li><a href="#/settings">Account Settings</a></li>--%>
                    </ul>
                </li>
                <%--<li>--%>
                    <%--<a id="btn_logout" href="logout">Logout</a>--%>
                <%--</li>--%>
            </ul>


		</div>
	</div>

</div>

<script type="text/javascript" src="/sproutassets/scripts/jquery/jquery.js" ></script>
<script type="text/javascript" src="assets/scripts/jquery-migrate-1.2.1.min.js" ></script>
<script>
    var jQuerySprout = jQuery.noConflict();
    if (typeof $ == 'undefined') $ = jQuerySprout;
</script>
<script type="text/javascript" src="/sproutassets/scripts/jquery-ui/jquery-ui-1.10.1.custom.min.js" ></script>

<script type="text/javascript" src="assets/components/ace-builds/src-min-noconflict/ace.js"></script>
<script type="text/javascript" src="assets/components/ace-builds/src-min-noconflict/ext-language_tools.js"></script>
<script type="text/javascript" src="/sproutassets/scripts/bootstrap/bootstrap.js" ></script>
<script type="text/javascript" src="/sproutassets/scripts/lcs/lcs.js"></script>

<script src="/sproutassets/components/angular/angular.js"></script>
<script src="/sproutassets/components/angular-resource/angular-resource.js"></script>
<script src="/sproutassets/components/angular-cookies/angular-cookies.js"></script>
<script src="/sproutassets/components/angular-sanitize/angular-sanitize.js"></script>
<script src="/sproutassets/components/angular-ui/build/angular-ui.min.js"></script>
<script src="/sproutassets/components/angular-ui-bootstrap-bower/ui-bootstrap-tpls.js"></script>
<script src="assets/scripts/jquery.contenteditable.js"></script>

<script src="/sproutassets/components/angular-strap/angular-strap.js"></script>
<script src="/sproutassets/components/angular-strap/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="assets/components/angular-ui-ace/ui-ace.min.js"></script>

<script src="assets/scripts/external/splitter/js/splitter.js"></script>
<script src="assets/scripts/external/splitterJQuery/splitter.js"></script>
<script src="assets/scripts/external/handlebars/handlebars-v3.0.1.js"></script>

<!-- build:js scripts/scripts.js -->
<script src="scripts/app.js"></script>
<script src="scripts/controllers/appointmentsController.js"></script>
<script src="scripts/controllers/badgeController.js"></script>
<script src="scripts/controllers/formSubmissionController.js"></script>
<script src="scripts/controllers/patientController.js"></script>
<script src="scripts/controllers/studyController.js"></script>
<script src="scripts/controllers/globalController.js"></script>
<script src="scripts/controllers/cohortController.js"></script>
<script src="scripts/controllers/settingsController.js"></script>
<script src="scripts/controllers/formManagerController.js"></script>
<script src="scripts/controllers/transformManagerController.js"></script>
<script src="scripts/controllers/cohortManagerController.js"></script>
<script src="scripts/filters/filters.js"></script>
<script src="scripts/directives/forms.js"></script>
<script src="scripts/directives/preventDefault.js"></script>
<script src="scripts/directives/patientViewDirective.js"></script>
<script src="scripts/directives/sproutNarrativeDirective.js"></script>
<script src="scripts/models/localeModel.js"></script>
<script src="scripts/services/appointmentsService.js"></script>
<script src="scripts/services/cohortService.js"></script>
<script src="scripts/services/sessionService.js"></script>
<script src="scripts/services/enrollmentService.js"></script>
<script src="scripts/services/providerService.js"></script>
<script src="scripts/services/patientService.js"></script>
<script src="scripts/services/settingsService.js"></script>
<script src="scripts/services/formManagerService.js"></script>
<script src="scripts/services/cohortManagerService.js"></script>
<script src="scripts/services/userManagerService.js"></script>
<script src="scripts/services/formsService.js"></script>
<script src="scripts/services/practiceService.js"></script>
<script src="scripts/services/studyService.js"></script>
<script src="scripts/services/transformService.js"></script>
<script src="scripts/services/networkService.js"></script>
<!-- endbuild -->

<link rel="stylesheet" href="/sproutassets/components/angular-strap/bootstrap-datepicker.css"/>
<link rel="stylesheet" href="/sproutassets/components/angular-ui/build/angular-ui.min.css"/>

<link rel="stylesheet" href="styles/sproutstudy.css">


<div class="container container-sproutstudy" style="margin-top: 50px;" ng-view></div>

<script type="text/javascript">
    var sproutFormsDoneInd = false;

    jQuerySprout(document).ready(function() {
//        jQuerySprout(".study-tab:not('.study-tab-default')").hide();

        sizeTransformPane();
        sizeAppFrame();

        var app = jQuerySprout(location).attr('hash').toString();
        app = app.substring(app.indexOf("/") + 1);

        if (app.length == 0) {
            app = "appointments";
        } else if (app == "addauth") {
            app = "admin";
        }

        jQuerySprout(".sproutstudy-tab-home").addClass("active");
        updateTransformButton(jQuerySprout(".sproutstudy-tab-home"));

        jQuerySprout("#sproutstudy-tab-container").on('click', '.sproutstudy-tab-button', function(event) {
//            event.preventDefault();

            var demographicInd = getActiveTabData("demographicInd");

            if (!jQuerySprout(this).closest(".sproutstudy-tab-li").hasClass("active")) {
                var instance = jQuerySprout(this).attr("instance");

                jQuerySprout(".sproutstudy-tab-li").removeClass("active");
                jQuerySprout(this).parent().addClass("active");
                updateTransformButton(jQuerySprout(this).parent());

                jQuerySprout(".sproutstudy-content").hide();
                jQuerySprout(".sprout-study-form-narrative-split-frame").hide();
                jQuerySprout(".sprout-study-form-narrative-split-frame-" + instance).show();
                jQuerySprout(".sproutstudy-content-" + instance).show();
            }

//            return false;
        });
        jQuerySprout("#sproutstudy-tab-container").on('click', '.sproutstudy-close-tab', function(event) {
            var instanceId = jQuerySprout(this).attr("instance");
            deleteTab(instanceId);
            return false;
        });

        Handlebars.registerHelper('compare', function (lvalue, operator, rvalue, options) {

            var operators, result;

            if (arguments.length < 3) {
                throw new Error("Handlerbars Helper 'compare' needs 2 parameters");
            }

            if (options === undefined) {
                options = rvalue;
                rvalue = operator;
                operator = "===";
            }

            operators = {
                '==': function (l, r) { return l == r; },
                '===': function (l, r) { return l === r; },
                '!=': function (l, r) { return l != r; },
                '!==': function (l, r) { return l !== r; },
                '<': function (l, r) { return l < r; },
                '>': function (l, r) { return l > r; },
                '<=': function (l, r) { return l <= r; },
                '>=': function (l, r) { return l >= r; },
                'typeof': function (l, r) { return typeof l == r; }
            };

            if (!operators[operator]) {
                throw new Error("Handlerbars Helper 'compare' doesn't know the operator " + operator);
            }

            result = operators[operator](lvalue, rvalue);

            if (result) {
                return options.fn(this);
            } else {
                return options.inverse(this);
            }

        });

    });

    function setActiveTabData(key, value) {
        var activeTab = jQuerySprout(".sproutstudy-tab-li.active");
        if (activeTab !== undefined) activeTab.data(key, value);
    }

    function getActiveTabData(key) {
        var activeTab = jQuerySprout(".sproutstudy-tab-li.active");
        if (activeTab !== undefined) return activeTab.data(key);
    }

    function removeActiveTabData(key) {
        var activeTab = jQuerySprout(".sproutstudy-tab-li.active");
        if (activeTab !== undefined) activeTab.removeData(key);
    }

    function addPaneContentForm(form, nonce) {
        var title = form.title;
        var instanceId = form.instanceId;

        jQuerySprout(".form-iframe-container").hide();

        jQuerySprout(".sproutstudy-content").hide();
        jQuerySprout(".sprout-study-form-narrative-split-frame").hide();

        if (jQuerySprout(".sproutstudy-tab-" + instanceId).length > 0) {
            jQuerySprout(".sprout-study-form-narrative-split-frame-" + instanceId).show();
        } else {
            var content = '<div class="sprout-study-form-narrative-split-frame sprout-study-form-narrative-split-frame-' + instanceId + '"><div class="sproutstudy-content sproutstudy-split-frame-content sprout-study-drag-dimme sproutstudy-content-form sproutstudy-content-' + instanceId + '" id="' + instanceId + '"><iframe id="iframe-' + instanceId + '" name="iframe-' + instanceId + '" src="/prompt/?instanceId=' + instanceId + '&nonce=' + nonce + '&debug=true&showThanks=false" class="appFrame sproutStudyFrame" /></div><div class="sproutstudy-split-frame-content sproutstudy-split-frame-content-narrative sproutstudy-split-frame-content-narrative-' + instanceId + '"><legend>Narrative <button class="btn btn-danger sprout-study-narrative-content-save-button sprout-study-narrative-content-save-button-' + instanceId + '" style="display: none;" instanceId="' + instanceId + '"><i class="fa fa-save"></i> Save Narrative Changes</button></legend><div class="sprout-study-narrative-content sprout-study-narrative-content-' + instanceId + '"></div><div class="sprout-study-template-content sprout-study-template-content-' + instanceId + '" style="display: none;" /><div class="sprout-study-template-scratch sprout-study-template-scratch-' + instanceId + '" style="display: none;"/></div></div>';
            var tab = '<li class="sproutstudy-tab-li sproutstudy-tab-form sproutstudy-tab-' + instanceId + '" title="' + title + '" instance="' + instanceId + '"><a href="#/" class="sproutstudy-tab-button"  title="' + title + '" instance="' + instanceId + '">' + title + '<span class="sproutstudy-close-tab" instance="' + instanceId + '"><i class="icon-remove-sign"></i></span></a></li>';
            jQuerySprout("#sproutstudy-tab-container").append(tab);
            jQuerySprout("#sproutStudyFormContent").append(content);

            var instanceTab = jQuerySprout('.sproutstudy-tab-' + instanceId);
            instanceTab.data("form", form);
            instanceTab.data("loaded", false);

            if (angular.element(jQuerySprout("#studyControllerDiv")).scope() !== undefined) {
                angular.element(jQuerySprout("#studyControllerDiv")).scope().getTemplate(form.publicationKey, instanceId, function(template) {
                    if (template !== undefined && template !== null && template.key !== undefined) {
                        instanceTab.data("template", template);
                        updateTransformButton(jQuerySprout(".sproutstudy-tab-" + instanceId));
                    }
                });
            }
        }

        jQuerySprout(".sproutstudy-tab-li").removeClass("active");
        jQuerySprout(".sproutstudy-tab-" + instanceId).addClass("active");

    }

    function enableSplitNarrativeFrame(instanceId) {
        var splitter = jQuerySprout(".sprout-study-form-narrative-split-frame-" + instanceId).splitter({
            "type": "v",
            "outline": false,
            "minLeft": 100,
            "minRight": 0,
            "anchorToWindow": true,
            "resizeToWidth": true,
            "dock": "right",
            "dockSpeed": 200});
        //splitter.trigger("dock");
        jQuerySprout(".sproutstudy-split-frame-content-narrative-" + instanceId).show();
    }

    function addTransformAdminContentForm(form, nonce) {
        var title = form.title;
        var instanceId = form.instanceId;

        var content = '<div class="sprout-transform-content sprout-transform-content-form sprout-transform-content-' + instanceId + '" id="' + instanceId + '"><iframe id="iframe-' + instanceId + '" name="iframe-' + instanceId + '" src="/prompt/?instanceId=' + instanceId + '&nonce=' + nonce + '&debug=true&showThanks=false" class="appFrame sproutTransformFrame" /></div>';
        jQuerySprout("#sproutTransformFormContent").append(content);

    }

    function sproutFormsDoneCallback() {
        sproutFormsDoneInd = true;
    }

    function deleteTab(instanceId) {
        if (instanceId != null && instanceId != 'home') {

            var mutableInd = jQuerySprout("#iframe-" + instanceId).contents().find(".sprout-form-mutable-ind").val();

            if (mutableInd == 'true') {
                jQuerySprout("#modal-wait-title").html("Saving and Closing form");
                jQuerySprout("#modal-wait-message").html("Saving and closing form....please wait...");
                jQuerySprout('#modal-wait').modal({
                    keyboard: false
                });

                jQuerySprout("#iframe-" + instanceId)[0].contentWindow.remoteSave(function(data) {
                    jQuerySprout(".sproutstudy-tab-li.active").removeClass("active");
                    jQuerySprout(".sprout-study-form-narrative-split-frame").hide();

                    var sourceTab = jQuerySprout(".sproutstudy-tab-" + instanceId);
                    var targetTab = jQuerySprout(".sproutstudy-tab-home");

                    jQuerySprout(".sprout-study-form-narrative-split-frame-" + instanceId).remove();

                    sourceTab.remove();
                    targetTab.addClass("active");
                    updateTransformButton(targetTab, true);

                    jQuerySprout(".sproutstudy-content-home").show();
                    jQuerySprout('#modal-wait').modal('hide');
                });
            } else {
                jQuerySprout(".sproutstudy-tab-li.active").removeClass("active");
                jQuerySprout("sprout-study-form-narrative-split-frame").hide();

                var sourceTab = jQuerySprout(".sproutstudy-tab-" + instanceId);
                var targetTab = jQuerySprout(".sproutstudy-tab-home");

                jQuerySprout(".sprout-study-form-narrative-split-frame-" + instanceId).remove();

                sourceTab.remove();
                targetTab.addClass("active");
                updateTransformButton(targetTab, true);

                jQuerySprout(".sproutstudy-content-home").show();
                jQuerySprout('#modal-wait').modal('hide');
            }
        }

        angular.element(jQuerySprout("#studyControllerDiv")).scope().getAllForms();
        angular.element(jQuerySprout("#studyControllerDiv")).scope().$apply();

        sproutFormsDoneInd = false;
    }
    function deletePaneContent(id) {
        var instanceId = jQuerySprout(".sproutstudy-tab-li.active").attr("instance");
        var form = jQuerySprout(".sproutstudy-tab-li.active").data("form");

        if (instanceId != null && instanceId != 'home') {
            var sourceTab = jQuerySprout(".sproutstudy-tab-" + instanceId);
            var targetTab = jQuerySprout(".sproutstudy-tab-" + instanceId).prev();

            jQuerySprout(".sprout-study-form-narrative-split-frame-" + instanceId).remove();

            sourceTab.remove();
            targetTab.addClass("active");
            updateTransformButton(targetTab, true);

            var targetInstanceId = targetTab.attr("instance");
            jQuerySprout(".sprout-study-form-narrative-split-frame-" + targetInstanceId).show();
            angular.element(jQuerySprout("#studyControllerDiv")).scope().getSubjectInbox();

            if (!sproutFormsDoneInd) angular.element(jQuerySprout("#studyControllerDiv")).scope().onComposeMessage(form);
        } else {
            // demographic form was just submitted
            instanceId = jQuerySprout(".iframe-demographic-form-content").attr("instanceId");
            angular.element(jQuerySprout("#studyControllerDiv")).scope().setNewSubject(id, instanceId);
        }

        angular.element(jQuerySprout("#studyControllerDiv")).scope().getAllForms();
        angular.element(jQuerySprout("#studyControllerDiv")).scope().$apply();

        sproutFormsDoneInd = false;
    }

    function stripNarrativeTextEditable(instanceId) {
        var scratch = jQuerySprout(".sprout-study-template-scratch-" + instanceId);

        scratch.html(jQuerySprout(".sprout-study-template-content-" + instanceId).html());

        scratch.find('[contenteditable]').contents().unwrap();
        scratch.find('handlebar').contents().unwrap();

        return scratch.html();
    }


    function updateTransformButton(targetTab, apply) {
        var instanceId = targetTab.attr("instance");
        var template = targetTab.data("template");

        if (angular.element(jQuerySprout("#studyControllerDiv")).scope() !== undefined) {
            angular.element(jQuerySprout("#studyControllerDiv")).scope().showNarrativeButton(template !== undefined && template.key !== undefined, instanceId);
            if (apply) angular.element(jQuerySprout("#studyControllerDiv")).scope().$apply();
        }
    }

    function formLoadedCallback(instanceId) {
        if (angular.element(jQuerySprout("#studyControllerDiv")).scope() !== undefined) {
            var instanceTab = jQuerySprout('.sproutstudy-tab-' + instanceId);
            instanceTab.data("loaded", true);
            angular.element(jQuerySprout("#studyControllerDiv")).scope().formLoadUpdate(instanceId);
            angular.element(jQuerySprout("#studyControllerDiv")).scope().$apply();
        } else if (angular.element(jQuerySprout("#transformControllerDiv")).scope() !== undefined) {
            angular.element(jQuerySprout("#transformControllerDiv")).scope().formLoadUpdate(instanceId);
            angular.element(jQuerySprout("#transformControllerDiv")).scope().onReloadModel();
            angular.element(jQuerySprout("#transformControllerDiv")).scope().$apply();
        }
    }

    function formSyncCallback(instanceId) {
        if (angular.element(jQuerySprout("#studyControllerDiv")).scope() !== undefined) {
            setSproutTransformTemplate(null, instanceId);
        } else if (angular.element(jQuerySprout("#transformControllerDiv")).scope() !== undefined) {
            angular.element(jQuerySprout("#transformControllerDiv")).scope().onSyncModel();
        }
    }

    function getActiveForm() {
        return jQuerySprout(".sproutstudy-tab-li.active").data("form");
    }

    function getActiveTemplate() {
        return jQuerySprout(".sproutstudy-tab-li.active").data("template");
    }

    function setActiveTemplate(template) {
        return jQuerySprout(".sproutstudy-tab-li.active").data("template", template);
    }

    function isFormLoaded(instanceId) {
        var instanceTab = jQuerySprout('.sproutstudy-tab-' + instanceId);
        if (instanceTab !== undefined) {
            return instanceTab.data("loaded");
        } else {
            return false;
        }
    }

    function clearAllFormTabs() {
        jQuerySprout(".sproutstudy-tab-li.active").removeClass("active");
        var homeTab = jQuerySprout(".sproutstudy-tab-home");
        homeTab.addClass("active");
        updateTransformButton(homeTab);
        jQuerySprout(".sprout-study-form-narrative-split-frame").remove();
        jQuerySprout(".sproutstudy-tab-form").remove();
    }

    function generateUUID(){
        var d = new Date().getTime();
        var uuid = 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
            var r = (d + Math.random()*16)%16 | 0;
            d = Math.floor(d/16);
            return (c=='x' ? r : (r&0x7|0x8)).toString(16);
        });
        return uuid;
    };

    // resize the iframe if the user resized the browser
    // the delay will keep the two scrollbars from flipping
    // back and forth quickly
    $(window).resize(function() {
        setTimeout(sizeAppFrame, 500);
        setTimeout(sizeTransformPane, 500);
    });


    function sizeAppFrame() {
        var tNavBarHeight = $(".navbar-fixed-top").height();
        var footerHeight = $(".footer").height();
        var wHeight = $(window).height();
        var dHeight = $(document).height();
        var aHeight = tNavBarHeight + footerHeight + 80;
        $(".appFrame").height(wHeight - aHeight - 2);
        //$(".sproutstudy-split-frame").height(wHeight - aHeight - 2);
        $(".sprout-study-form-narrative-split-frame").height(wHeight - aHeight);
    }

    function sizeTransformPane() {
        var modelToolbarHeight = $(".sprout-transform-model-toolbar").height();
        var narrativeToolbarHeight = $(".sprout-transform-narrative-toolbar").height();

        $(".sproutTransformFrame").height($(".sprout-transform-form-pane").height());
        $(".sprout-transform-template-editor").height($(".sprout-transform-template-pane").height());
        $("#sproutTransformNarrativeContent").height($(".sprout-transform-narrative-pane").height() - narrativeToolbarHeight - 40);
        $("#sproutTransformModelContent").height($(".sprout-transform-model-pane").height() - modelToolbarHeight - 50);
    }

    var formCallbackCatalog = {};

    function getSerializedArray(getSerializedArray, narrativeUpdate, paths, instanceId) {
        //console.log("sproutStudy.getSerializedArray.instanceId: " + instanceId);
        var callbackItem = {};
        if (formCallbackCatalog[instanceId]) {
            callbackItem = formCallbackCatalog[instanceId];
        }
        callbackItem["getSerializedArray"] = getSerializedArray;
        callbackItem["narrativeUpdate"] = narrativeUpdate;
        callbackItem["paths"] = paths;
        formCallbackCatalog[instanceId] = callbackItem;
    }

    function getNarrativeModel(instanceId) {

        if (instanceId == undefined || instanceId == null) {
            var activeTab = jQuerySprout(".sproutstudy-tab-li.active");
            instanceId = activeTab.attr("instance");
        }

        if (formCallbackCatalog[instanceId]) {
            var callbackItem = formCallbackCatalog[instanceId];
            var narrativeModel = callbackItem.getSerializedArray();
            var paths = callbackItem.paths();

            if (angular.element(jQuerySprout("#transformControllerDiv")).scope() !== undefined && paths !== undefined) {
                angular.element(jQuerySprout("#transformControllerDiv")).scope().setPaths(paths);
                angular.element(jQuerySprout("#transformControllerDiv")).scope().applyIfPossible();
            }

            //console.log("model: " + JSON.stringify(narrativeModel, null, 4));
            return narrativeModel;
        }
    }

    function compileTemplate() {
        var source = angular.element(jQuerySprout("#transformControllerDiv")).scope().getTemplateFromEditor();
        var model = angular.element(jQuerySprout("#transformControllerDiv")).scope().getModel();
        var template = Handlebars.compile(source);
        try {
            var narrative = template(model);
            jQuerySprout("#sproutTransformNarrativeContent").html(narrative);
            angular.element(jQuerySprout("#transformControllerDiv")).scope().setNarrative(narrative);
            angular.element(jQuerySprout("#transformControllerDiv")).scope().setTemplateError(undefined);
            angular.element(jQuerySprout("#transformControllerDiv")).scope().applyIfPossible();
        } catch (exception) {
            //console.log("compileTemplate.exception: " + exception);
            angular.element(jQuerySprout("#transformControllerDiv")).scope().setTemplateError(exception);
            angular.element(jQuerySprout("#transformControllerDiv")).scope().applyIfPossible();
        }
    }

    function updateSproutTransformModelView(model) {
        jQuerySprout("#sproutTransformModelContent").html(syntaxHighlight(model));
    }

    function syntaxHighlight(json) {
        if (typeof json != 'string') {
             json = JSON.stringify(json, undefined, 2);
        }
        if (json !== undefined) {
            json = json.replace(/&/g, '&amp;').replace(/</g, '&lt;').replace(/>/g, '&gt;');

            return json.replace(/("(\\u[a-zA-Z0-9]{4}|\\[^u]|[^\\"])*"(\s*:)?|\b(true|false|null)\b|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?)/g, function (match) {
                var cls = 'sprout-transform-model-type-number';
                if (/^"/.test(match)) {
                    if (/:$/.test(match)) {
                        cls = 'sprout-transform-model-type-key';
                    } else {
                        cls = 'sprout-transform-model-type-string';
                    }
                } else if (/true|false/.test(match)) {
                    cls = 'sprout-transform-model-type-boolean';
                } else if (/null/.test(match)) {
                    cls = 'sprout-transform-model-type-null';
                }
                return '<span class="' + cls + ' sprout-transform-model-handle">' + match + '</span>';
            });
        }
    }

    function makeNarrativeTextEditable(instanceId) {
        makeTextEditable(jQuerySprout(".sprout-study-template-content-" + instanceId));
    }

    function makeTextEditable(element) {
        element.find('*').each(function() {
            $('*', element)
                    .andSelf()
                    .contents()
                    .filter(function(){
                        return this.nodeType === 3;
                    })
                    .filter(function(){
                        return !this.nodeValue.match( /\{\{(.*?)\}\}/g );
                    })
                    .filter(function(){
                        return $(this).parent().attr("contenteditable") === undefined;
                    })
                    .each(function(){
                        $(this).wrap('<span contenteditable="true" sproutNarrativeEditable="true" />');
                    });

            var tagName = jQuerySprout(this).prop("tagName");

            if ((tagName === undefined || tagName !== 'HANDLEBAR') && jQuerySprout(this).find("handlebar").length > 0) {
                makeTextEditable(jQuerySprout(this));
            }
        });
    }

    function setSproutTransformTemplate(source, instanceId) {

        if (source === undefined || source === null) {
            source = $(".sprout-study-template-content-" + instanceId).html();
        } else {
            source = source.replace( /\{\{(.*?)\}\}/g, "<handlebar>{{$1}}</handlebar>");

            $(".sprout-study-template-content-" + instanceId).html(source);
            makeNarrativeTextEditable(instanceId);
            //console.log("new template: " + jQuerySprout(".sprout-study-template-content-" + instanceId).html());
        }

        var model = getNarrativeModel(instanceId)
        var template = Handlebars.compile($(".sprout-study-template-content-" + instanceId).html());
        try {
            var narrative = template(model);
            jQuerySprout("#sproutTransformNarrativeContent").html(narrative);
            jQuerySprout(".sprout-study-narrative-content-" + instanceId).html(narrative);

            jQuerySprout(".sprout-study-narrative-content-" + instanceId).off('focus blur keyup paste change', '[contenteditable]');

            jQuerySprout(".sprout-study-narrative-content-" + instanceId).on('focus', '[contenteditable]', function() {
                var $this = $(this);
                $this.data('before', $this.html());
                return $this;
            }).on('blur keyup paste', '[contenteditable]', function() {
                var $this = $(this);
                if ($this.data('before') !== $this.html()) {
                    $this.data('before', $this.html());
                    $this.data('changed', true);
                    $this.trigger('change');
//                } else if ($this.data('changed')) {
//                    jQuerySprout(".sprout-study-narrative-content-save-button-" + instanceId).hide();
//                    $this.data('changed', false);
                }
                return $this;
            }).on('change', '[contenteditable]', function() {
                jQuerySprout(".sprout-study-narrative-content-save-button-" + instanceId).show();
            });

            jQuerySprout(".sproutstudy-split-frame-content-narrative-" + instanceId).off('click', '.sprout-study-narrative-content-save-button').on('click', '.sprout-study-narrative-content-save-button', function(event) {
                var instanceId = jQuerySprout(this).attr("instanceId");
                if (angular.element(jQuerySprout("#studyControllerDiv")).scope() !== undefined) {
                    angular.element(jQuerySprout("#studyControllerDiv")).scope().onSyncNarrative(function(result, message) {
                        if (result) {
                            if (formCallbackCatalog[instanceId]) {
                                var callbackItem = formCallbackCatalog[instanceId];
                                callbackItem.narrativeUpdate();
                            }
                            jQuerySprout(".sprout-study-narrative-content-save-button-" + instanceId).hide();
                        } else {
                            console.log(message);
                        }
                    });
                }
            });

        } catch (exception) {
            console.log("compileTemplate.exception: " + exception);
        }
    }

    function syncNarrativeTemplate(instanceId) {
        var narrativeParts = getNarrativeParts(instanceId);
        if (narrativeParts.length > 0) {
            $(".sprout-study-template-content-" + instanceId).find("[contenteditable]").each(function(index, element) {
                if (jQuerySprout(this).find("[sproutnarrativeeditable='true']").length == 0) {
                    $(this).html(narrativeParts[index]);
                }
            });
        }
        return $(".sprout-study-template-content-" + instanceId).html();
    }

    function getNarrativeParts(instanceId) {
        var narrativeParts = [];
        $(".sprout-study-narrative-content-" + instanceId).find("[sproutnarrativeeditable='true']").each(function(index, element) {
            var part = $(this).html();
            narrativeParts.push(part);
        });
        return narrativeParts;
    }

    function getNarrativeHtml(instanceId) {
        if (instanceId !== undefined) {
            return $(".sprout-study-narrative-content-" + instanceId).html();
        }
        return null;
    }
</script>

<script src="/sproutassets/scripts/idletimer/jquery.idletimer.js" type="text/javascript"></script>
<script src="/sproutassets/scripts/idletimer/jquery.idletimeout.js" type="text/javascript"></script>
<script type="text/javascript">
    var contextPath = "<%=request.getContextPath()%>";
    var timeoutSeconds = 600;

    var keepAlive = function(app) {
        $.idleTimer('keepAlive', {timeout: (timeoutSeconds * 1000)});
    }

    $(document).ready(function() {
        $.idleTimeout('#modal-timeout', '#idletimeout-resume', {
            idleAfter: timeoutSeconds,
            pollingInterval: 60,
            keepAliveURL: '<%=request.getContextPath()%>/public/keepalive.jsp',
            serverResponseEquals: 'OK',
            onTimeout: function() {
                $('#modal-timeout').modal('hide');
                window.location = "<%=request.getContextPath()%>/logout";
            },
            onIdle: function () {
                $('#modal-timeout').modal({
                    keyboard: true
                });
            },
            onCountdown: function (counter) {
                $(".timeoutCountdown").html(counter); // update the counter
            },
            onResume: function () {
                $('#modal-timeout').modal('hide');
            }
        });

    });

</script>

<div class="modal modal-timeout hide fade in" id="modal-timeout" style="display: none;" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-header">
        <h3>Session Timeout</h3>
    </div>
    <div class="modal-body-short">
        <p>
        <h4>You will be logged out in <span class="timeoutCountdown" style="color: #8b0031;"></span>&nbsp;seconds due to inactivity.</h4>
        </p>
    </div>
    <div class="modal-footer">
        <a id="idletimeout-resume" href="#" class="btn btn-primary">Stay Logged In</a><a href="<%=request.getContextPath()%>/logout" class="btn">Logout</a>
    </div>
</div>

<div class="modal modal-wait hide fade in" id="modal-wait" style="display: none;" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" options="modalSmallOpts" aria-hidden="true">
    <div class="modal-header">
        <h3><span id="modal-wait-title"/>></h3>
    </div>
    <div class="modal-body-short">
        <p>
        <h4><span id="modal-wait-message"/></h4>
        </p>
    </div>
</div>

<div id="sproutStudyFormContent" />

<div id="sproutTransformTemplate" style="display: none;" />

</body>
</html>
