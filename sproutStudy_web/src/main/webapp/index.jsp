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
    <link type="text/css" rel="stylesheet" href="/sproutassets/stylesheets/bootstrap/css/bootstrap.icon-large.min.css" />
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
	<div class="navbar navbar-fixed-top">
		<div class="navbar-inner">
			<ul class="nav" id="sproutstudy-tab-container">
                <li class="sproutstudy-tab-li sproutstudy-tab-home" instance="home"><a href="#/" class="sproutstudy-tab-button" instance="home">{{member().fullName}}<span ng-show="member().id > 0"> ({{member().id}})</span></a></li>
		    </ul>
            <ul class="nav" style="float: right; margin-right: 0px;" ng-show="cohort() != null">
                <li class="sproutstudy-tab-cohort"><a href="#/" ng-click="changeCohort()" class="sproutstudy-tab-button">{{cohort().name}} Cohort<i class="icon-list" style="margin-left: 10px;"></i></a></li>
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
<script src="scripts/controllers/adminController.js"></script>
<script src="scripts/controllers/globalController.js"></script>
<script src="scripts/controllers/cohortController.js"></script>
<script src="scripts/directives/forms.js"></script>
<script src="scripts/directives/preventDefault.js"></script>
<script src="scripts/directives/patientViewDirective.js"></script>
<script src="scripts/models/localeModel.js"></script>
<script src="scripts/services/appointmentsService.js"></script>
<script src="scripts/services/cohortService.js"></script>
<script src="scripts/services/enrollmentService.js"></script>
<script src="scripts/services/providerService.js"></script>
<script src="scripts/services/patientService.js"></script>
<script src="scripts/services/adminService.js"></script>
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
    });

    function addPaneContentOrig(title, instanceId, nonce) {
        jQuerySprout(".form-iframe-container").hide();

        jQuerySprout(".sproutstudy-content").hide();

        if (jQuerySprout(".sproutstudy-tab-" + instanceId).length > 0) {
            jQuerySprout(".sproutstudy-content-" + instanceId).show();
        } else {
            var content = '<div class="sproutstudy-content sproutstudy-content-form sproutstudy-content-' + instanceId + '" id="' + instanceId + '"><iframe id="iframe-' + instanceId + '" name="iframe-' + instanceId + '" src="/prompt/?instanceId=' + instanceId + '&nonce=' + nonce + '&debug=false" class="appFrame" /></div>';
            var tab = '<li class="sproutstudy-tab-li sproutstudy-tab-form sproutstudy-tab-' + instanceId + '" title="' + title + '" instance="' + instanceId + '"><a href="#/" class="sproutstudy-tab-button"  title="' + title + '" instance="' + instanceId + '">' + title + '</a></li>';
            jQuerySprout("#sproutstudy-tab-container").append(tab);
            jQuerySprout("#sproutStudyFormContent").append(content);
        }

        jQuerySprout(".sproutstudy-tab-li").removeClass("active");
        jQuerySprout(".sproutstudy-tab-" + instanceId).addClass("active");
    }

    function addPaneContentForm(form, nonce) {
        var title = form.title;
        var instanceId = form.instanceId;

        jQuerySprout(".form-iframe-container").hide();

        jQuerySprout(".sproutstudy-content").hide();

        if (jQuerySprout(".sproutstudy-tab-" + instanceId).length > 0) {
            jQuerySprout(".sproutstudy-content-" + instanceId).show();
        } else {
            var content = '<div class="sproutstudy-content sproutstudy-content-form sproutstudy-content-' + instanceId + '" id="' + instanceId + '"><iframe id="iframe-' + instanceId + '" name="iframe-' + instanceId + '" src="/prompt/?instanceId=' + instanceId + '&nonce=' + nonce + '&debug=false" class="appFrame" /></div>';
            var tab = '<li class="sproutstudy-tab-li sproutstudy-tab-form sproutstudy-tab-' + instanceId + '" title="' + title + '" instance="' + instanceId + '"><a href="#/" class="sproutstudy-tab-button"  title="' + title + '" instance="' + instanceId + '">' + title + '</a></li>';
            jQuerySprout("#sproutstudy-tab-container").append(tab);
            jQuerySprout("#sproutStudyFormContent").append(content);

            var instanceTab = jQuerySprout('.sproutstudy-tab-' + instanceId);
            instanceTab.data("form", form);

        }

        jQuerySprout(".sproutstudy-tab-li").removeClass("active");
        jQuerySprout(".sproutstudy-tab-" + instanceId).addClass("active");
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
            angular.element(jQuerySprout("#studyControllerDiv")).scope().onComposeMessage(form);
        } else {
            // demographic form was just submitted
            instanceId = jQuerySprout(".iframe-demographic-form-content").attr("instanceId");


            angular.element(jQuerySprout("#studyControllerDiv")).scope().setNewSubject(id, instanceId);
        }

        angular.element(jQuerySprout("#studyControllerDiv")).scope().getMutableForms();
        angular.element(jQuerySprout("#studyControllerDiv")).scope().$apply();
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
        var aHeight = tNavBarHeight + footerHeight + 15;
        $(".appFrame").height(wHeight - aHeight);
    }
</script>


    <div id="sproutStudyFormContent" />

</body>
</html>
