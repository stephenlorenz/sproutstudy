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
                        <i class="icon-list"></i>
                    </a>
                    <ul class="dropdown-menu" style="min-width: 150px;">
                        <li><a href="#/" ng-click="changeCohort()" class="sproutstudy-tab-button">Change Cohort</a></li>
                        <li><a href="#/cohorts" class="sproutstudy-tab-button" ng-show="isCohortManager()">Cohort Admin</a></li>
                        <li><a href="#/forms" class="sproutstudy-tab-button" ng-show="isManagerOfCohort() || isAdmin()  ">Form Admin</a></li>
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
<script>
    var jQuerySprout = jQuery.noConflict();
    if (typeof $ == 'undefined') $ = jQuerySprout;
</script>
<script type="text/javascript" src="/sproutassets/scripts/jquery-ui/jquery-ui-1.10.1.custom.min.js" ></script>

<script type="text/javascript" src="/sproutassets/scripts/bootstrap/bootstrap.js" ></script>
<script type="text/javascript" src="/sproutassets/scripts/lcs/lcs.js"></script>

<script src="/sproutassets/components/angular/angular.js"></script>
<script src="/sproutassets/components/angular-resource/angular-resource.js"></script>
<script src="/sproutassets/components/angular-cookies/angular-cookies.js"></script>
<script src="/sproutassets/components/angular-sanitize/angular-sanitize.js"></script>
<script src="/sproutassets/components/angular-ui/build/angular-ui.min.js"></script>
<script src="/sproutassets/components/angular-ui-bootstrap-bower/ui-bootstrap-tpls.js"></script>

<script src="/sproutassets/components/angular-strap/angular-strap.js"></script>
<script src="/sproutassets/components/angular-strap/bootstrap-datepicker.js"></script>

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
<script src="scripts/controllers/cohortManagerController.js"></script>
<script src="scripts/filters/filters.js"></script>
<script src="scripts/directives/forms.js"></script>
<script src="scripts/directives/preventDefault.js"></script>
<script src="scripts/directives/patientViewDirective.js"></script>
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
<script src="scripts/directives/custodialAgreementDirective.js"></script>
<script src="scripts/directives/enrollmentLetterDirective.js"></script>
<script src="scripts/directives/formDeliveryExpirationDate.js"></script>
<script src="scripts/services/formsService.js"></script>
<script src="scripts/services/practiceService.js"></script>
<script src="scripts/services/studyService.js"></script>
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

        sizeAppFrame();

        var app = jQuerySprout(location).attr('hash').toString();
        app = app.substring(app.indexOf("/") + 1);

        if (app.length == 0) {
            app = "appointments";
        } else if (app == "addauth") {
            app = "admin";
        }

        jQuerySprout(".sproutstudy-tab-home").addClass("active");

        jQuerySprout("#sproutstudy-tab-container").on('click', '.sproutstudy-tab-button', function(event) {
//            event.preventDefault();
            jQuerySprout(".sproutstudy-tab-li").removeClass("active");
            jQuerySprout(this).parent().addClass("active");

            var instance = jQuerySprout(this).attr("instance");

            jQuerySprout(".sproutstudy-content").hide();
            jQuerySprout(".sproutstudy-content-" + instance).show();
//            return false;
        });
        jQuerySprout("#sproutstudy-tab-container").on('click', '.sproutstudy-close-tab', function(event) {
            var instanceId = jQuerySprout(this).attr("instance");
            deleteTab(instanceId);
            return false;
        });
    });

    function addPaneContentForm(form, nonce) {
        var title = form.title;
        var instanceId = form.instanceId;

        jQuerySprout(".form-iframe-container").hide();

        jQuerySprout(".sproutstudy-content").hide();

        if (jQuerySprout(".sproutstudy-tab-" + instanceId).length > 0) {
            jQuerySprout(".sproutstudy-content-" + instanceId).show();
        } else {
            var content = '<div class="sproutstudy-content sproutstudy-content-form sproutstudy-content-' + instanceId + '" id="' + instanceId + '"><iframe id="iframe-' + instanceId + '" name="iframe-' + instanceId + '" src="/prompt/?instanceId=' + instanceId + '&nonce=' + nonce + '&debug=true&showThanks=false" class="appFrame sproutStudyFrame" /></div>';
            var tab = '<li class="sproutstudy-tab-li sproutstudy-tab-form sproutstudy-tab-' + instanceId + '" title="' + title + '" instance="' + instanceId + '"><a href="#/" class="sproutstudy-tab-button"  title="' + title + '" instance="' + instanceId + '">' + title + '<span class="sproutstudy-close-tab" instance="' + instanceId + '"><i class="icon-remove-sign"></i></span></a></li>';
            jQuerySprout("#sproutstudy-tab-container").append(tab);
            jQuerySprout("#sproutStudyFormContent").append(content);

            var instanceTab = jQuerySprout('.sproutstudy-tab-' + instanceId);
            instanceTab.data("form", form);

        }

        jQuerySprout(".sproutstudy-tab-li").removeClass("active");
        jQuerySprout(".sproutstudy-tab-" + instanceId).addClass("active");
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
                    jQuerySprout(".sproutstudy-content-form").hide();

                    var sourceTab = jQuerySprout(".sproutstudy-tab-" + instanceId);
                    var targetTab = jQuerySprout(".sproutstudy-tab-home");

                    jQuerySprout(".sproutstudy-content-" + instanceId).remove();

                    sourceTab.remove();
                    targetTab.addClass("active");

                    jQuerySprout(".sproutstudy-content-home").show();
                    jQuerySprout('#modal-wait').modal('hide');
                });
            } else {
                jQuerySprout(".sproutstudy-tab-li.active").removeClass("active");
                jQuerySprout(".sproutstudy-content-form").hide();

                var sourceTab = jQuerySprout(".sproutstudy-tab-" + instanceId);
                var targetTab = jQuerySprout(".sproutstudy-tab-home");

                jQuerySprout(".sproutstudy-content-" + instanceId).remove();

                sourceTab.remove();
                targetTab.addClass("active");

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

            jQuerySprout(".sproutstudy-content-" + instanceId).remove();

            sourceTab.remove();
            targetTab.addClass("active");

            var targetInstanceId = targetTab.attr("instance");
            jQuerySprout(".sproutstudy-content-" + targetInstanceId).show();
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

    function clearAllFormTabs() {
        jQuerySprout(".sproutstudy-tab-li.active").removeClass("active");
        var homeTab = jQuerySprout(".sproutstudy-tab-home");
        homeTab.addClass("active");
        jQuerySprout(".sproutstudy-content-form").remove();
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
        setTimeout(sizeAppFrame, 1000);
    });

    function sizeAppFrame() {
        var tNavBarHeight = $(".navbar-fixed-top").height();
        var footerHeight = $(".footer").height();
        var wHeight = $(window).height();
        var dHeight = $(document).height();
        var aHeight = tNavBarHeight + footerHeight + 80;
        $(".appFrame").height(wHeight - aHeight);
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

</body>
</html>
