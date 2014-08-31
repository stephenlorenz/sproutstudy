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
	<link type="text/css" rel="stylesheet" href="/sproutassets/stylesheets/lcs/css/lcs.css" />
	<link type="text/css" rel="stylesheet" href="/sproutassets/stylesheets/lcs/css/login.css" />
	<link type="text/css" rel="stylesheet" href="/sproutassets/stylesheets/bootstrap/css/responsive.css" />
	<!--[if IE]><link rel="stylesheet" type="text/css" href="/sproutassets/stylesheets/ie/ie.css"></link><![endif]-->
   	<!--[if lt IE 9]><link rel="stylesheet" type="text/css" href="/sproutassets/stylesheets/ie/ie8.css"></link><![endif]-->
   	<!--[if lt IE 7]><link rel="stylesheet" type="text/css" href="/sproutassets/stylesheets/ie/ie6.css"></link><![endif]-->
    
    
</head>
<body xmlns:ng="http://angularjs.org" id="ng-app" class="ng-app:sproutStudyApp" xmlns:forms="http://angularjs.org" ng-app="sproutStudyApp" ng-controller="globalController">

<!--[if lt IE 7]>
<p class="chromeframe">You are using an outdated browser. <a href="http://browsehappy.com/">Upgrade your browser
    today</a> or <a href="http://www.google.com/chromeframe/?redirect=true">install Google Chrome Frame</a> to better
    experience this site.</p>
<![endif]-->

<!-- Add your site or application content here -->

<script type="text/javascript">
    var sproutStudyResourceBase = "";
</script>

<div class="container container-sproutstudy" ng-cloak>

	<div class="navbar navbar-fixed-top">
		<div class="navbar-inner">
			<ul class="nav">
                <li class="sproutstudy-tab-li sproutstudy-tab-study"><a href="#/" class="sproutstudy-tab-button">Home</a></li>
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
<script src="scripts/directives/forms.js"></script>
<script src="scripts/directives/preventDefault.js"></script>
<script src="scripts/directives/patientViewDirective.js"></script>
<script src="scripts/models/localeModel.js"></script>
<script src="scripts/services/appointmentsService.js"></script>
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

        jQuerySprout(".study-tab-" + app).addClass("active");

        jQuerySprout(".study-tab-button").bind('click', function(event) {
//            event.preventDefault();
            jQuerySprout(".study-tab-li").removeClass("active");
            jQuerySprout(this).parent().addClass("active");

            var activeTab = jQuerySprout(this).attr("href").substring(2);

            jQuerySprout(".study-tab").hide();
            jQuerySprout(".study-tab-" + activeTab).show();
//            return false;
        });

        jQuerySprout(".container-study").on('click', ".enrollment-quick-link", function(event) {
            event.preventDefault();
            if (window.self !== window.top) {
//                console.log("href: " + jQuerySprout(this).attr("href"));
//                console.log("src: " + parent.jQuerySprout("#iframe-ENROLLMENT").attr("src"));
                parent.jQuerySprout("#iframe-ENROLLMENT").attr("src", jQuerySprout(this).attr("href"));
                parent.jQuerySprout(".nav-ENROLLMENT").trigger('click');
//                console.log("in a frame");
//            } else {
//                console.log("NOT in a frame");
            } else {
                window.open(jQuerySprout(this).attr("href"),'enrollmentModule');
            }
            return false;
        });
    });

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
        var aHeight = tNavBarHeight + footerHeight + 200;
        $(".appFrame").height(wHeight - aHeight);
    }
</script>
</script>

</body>
</html>
