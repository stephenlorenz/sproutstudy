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

    <script type="application/javascript">
        var sproutAdminInd = function() {
            return (typeof parent.insideSproutAdmin === "function");
        }
    </script>
    
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
    var sproutStudyCohortGlobal = undefined;
</script>

<div class="container container-sproutstudy" ng-controller="cohortController">
	<div class="navbar navbar-fixed-top" ng-cloak>
		<div class="navbar-inner">
			<ul class="nav sproutstudy-tab-container-reset" style="margin: 0px 0px 0px 0px;">
                <li class="sproutstudy-tab-reset" ><a href="/sproutstudy" class="sproutstudy-tab-button" instance="reset"><i class="icon-home"></i></a></li>
                <%--<li class="sproutstudy-tab-reset" ><a href="/sproutstudy" class="sproutstudy-tab-button" instance="reset"><img style="width: 20px; height: 20px; " src="/sproutstudy/images/sprout-study-60-2.png" alt="Sprout Study Logo" /></a></li>--%>
		    </ul>
			<ul class="nav" id="sproutstudy-tab-container">
                <li class="sproutstudy-tab-li sproutstudy-tab-home" instance="home" ng-show="member().click == undefined"><a ng-href="#/{{member().url}}" class="sproutstudy-tab-button" instance="home">{{member().fullName}}<span ng-show="member().id > 0"> ({{member().id}})</span></a></li>
                <li class="sproutstudy-tab-li sproutstudy-tab-home" instance="home" ng-show="member().click != undefined"><a href ng-click="member().click" class="sproutstudy-tab-button" instance="home">{{member().fullName}}<span ng-show="member().id > 0"> ({{member().id}})</span></a></li>
		    </ul>

            <ul class="nav pull-right">
                <li class="sprout-study-subapp-option" style="display: none;"><a>{{session().firstName}}</a></li>
                <li ng-show="cohort() != null && cohort().name.length > 0"><a><span id="sprout-study-iframe-cohort">{{cohort().name}}</span> Cohort</a></li>
                <li class="dropdown sproutstudy-tab-cohort">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <i class="fa fa-bars"></i>
                    </a>
                    <ul class="dropdown-menu" style="min-width: 150px;">
                        <li><a href="#/" ng-click="onClearForms();changeCohort()" class="sproutstudy-tab-button">Change Cohort</a></li>
                        <li><a ng-href="#/cohorts" ng-click="onClearForms()" class="sproutstudy-tab-button" ng-show="isCohortManager()">Cohort Admin</a></li>
                        <li><a ng-href="#/forms" ng-click="onClearForms()" class="sproutstudy-tab-button" ng-show="isManagerOfCohort() || isAdmin()  ">Form Admin</a></li>
                        <li><a ng-href="#/lists" ng-click="onClearForms()" class="sproutstudy-tab-button" ng-show="isManagerOfCohort() || isAdmin()  ">List Admin</a></li>
                        <li class="sprout-study-subapp-option"><a ng-href="#/feedback" class="sproutstudy-tab-button">Feedback</a></li<li><a id="btn_logout" href="logout">Logout</a></li>
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
<%--<script src="assets/components/angular/angular.js"></script>--%>
<%--<script src="assets/components/angular-route/angular-route.min.js"></script>--%>
<script src="/sproutassets/components/angular-resource/angular-resource.js"></script>
<script src="/sproutassets/components/angular-cookies/angular-cookies.js"></script>
<script src="/sproutassets/components/angular-sanitize/angular-sanitize.js"></script>
<script src="/sproutassets/components/angular-ui/build/angular-ui.min.js"></script>
<script src="/sproutassets/components/angular-ui-bootstrap-bower/ui-bootstrap-tpls.js"></script>
<script src="assets/scripts/jquery.contenteditable.js"></script>

<script src="/sproutassets/components/angular-strap/angular-strap.js"></script>
<script src="/sproutassets/components/angular-strap/bootstrap-datepicker.js"></script>
<script src="/sproutassets/scripts/log4javascript/log4javascript.js"></script>
<script type="text/javascript" src="assets/components/angular-ui-ace/ui-ace.min.js"></script>
<script type="text/javascript" src="assets/components/angular-bootstrap-contextmenu/contextMenu.js"></script>
<script type="text/javascript" src="assets/components/ng-websocket/ng-websocket.js"></script>

<script type="text/javascript">
    var log = log4javascript.getLogger();
    var ajaxAppender = new log4javascript.AjaxAppender("/sproutstudy/log4javascript");
    log.addAppender(ajaxAppender);

    log.info("SproutStudy Dashboard loaded.");
//    log.info("this is a debug statement, log4javascript!!!");
</script>

<script src="assets/scripts/external/scrollTo/jquery.scrollTo.min.js"></script>
<script src="assets/scripts/external/splitter/js/splitter.js"></script>
<script src="assets/scripts/external/splitterJQuery/splitter.js"></script>
<script src="assets/scripts/external/handlebars/handlebars-v3.0.3.js"></script>
<script src="/sproutapi/assets/scripts/moment/moment.js" type="text/javascript"></script>

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
<script src="scripts/controllers/feedbackController.js"></script>
<script src="scripts/controllers/formManagerController.js"></script>
<script src="scripts/controllers/listManagerController.js"></script>
<script src="scripts/controllers/transformManagerController.js"></script>
<script src="scripts/controllers/cohortManagerController.js"></script>
<script src="scripts/filters/filters.js"></script>
<script src="scripts/directives/forms.js"></script>
<script src="scripts/directives/preventDefault.js"></script>
<script src="scripts/directives/patientViewDirective.js"></script>
<script src="scripts/directives/sproutNarrativeDirective.js"></script>
<script src="scripts/directives/focusOnMe.js"></script>
<script src="scripts/models/localeModel.js"></script>
<script src="scripts/services/appointmentsService.js"></script>
<script src="scripts/services/cohortService.js"></script>
<script src="scripts/services/sessionService.js"></script>
<script src="scripts/services/enrollmentService.js"></script>
<script src="scripts/services/providerService.js"></script>
<script src="scripts/services/patientService.js"></script>
<script src="scripts/services/settingsService.js"></script>
<script src="scripts/services/feedbackService.js"></script>
<script src="scripts/services/formManagerService.js"></script>
<script src="scripts/services/listManagerService.js"></script>
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

        if (sproutAdminInd()) {
            jQuerySprout(".sprout-study-subapp-option").hide();
        } else {
            jQuerySprout(".sprout-study-subapp-option").show();
        }

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
        jQuerySprout("#sproutStudyFormContent").on('click', '.sprout-study-narrative-content-print-button', function(event) {
            var instanceId = jQuerySprout(this).attr("instanceid");
            printNarrative(instanceId);
            return false;
        });

        Handlebars.registerHelper('getNode', function (sourceNode, queryKey, options) {

            var result;

            if (arguments.length < 2) {
                throw new Error("Handlerbars Helper 'getNode' needs 2 parameters");
            }

            if (sourceNode !== undefined && Array.isArray(sourceNode)) {
                for (var i = 0; i < sourceNode.length; i++) {
                    var key = Object.keys(sourceNode[i]);
                    if (key == queryKey) {
                        result = sourceNode[i][key];
                    }
                }
            }

            return options.fn(result);

        });

        Handlebars.registerHelper('compare', function (lvalue, operator, rvalue, options) {

            var operators, result;

            if (arguments.length < 3) {
                throw new Error("Handlerbars Helper 'compare' needs 3 parameters");
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

            if (!isNaN(rvalue)) {
                rvalue = parseInt(rvalue, 10);
            }

            result = operators[operator](lvalue, rvalue);

            if (result) {
                return options.fn(this);
            } else {
                return options.inverse(this);
            }

        });

        Handlebars.registerHelper('localeLong', function (formLocale, locale, options) {
            if (arguments.length !== 2) {
                throw new Error("Handlerbars Helper 'localeLong' needs 2 parameters");
            }

            if (formLocale == locale) {
                return options.fn(this);
            } else {
                return options.inverse(this);
            }
        });

        Handlebars.registerHelper('locale', function (locale, options) {

            if (arguments.length !== 2) {
                throw new Error("Handlerbars Helper 'locale' only accepts 1 parameter, the locale code (e.g. 'en' or 'es').");
            }

            if (options !== undefined && options.data !== undefined && options.data.root !== undefined && options.data.root.sprout !== undefined && options.data.root.sprout.locale !== undefined) {
                var formLocale = options.data.root.sprout.locale;

                var result = formLocale == locale;

                if (result) {
                    return options.fn(this);
                } else {
                    return options.inverse(this);
                }
            } else {
                return options.fn(this);
            }
        });
        Handlebars.registerHelper('en', function (options) {

            if (arguments.length !== 1) {
                throw new Error("Handlerbars Helper 'en' accepts no parameters.");
            }

            if (options !== undefined && options.data !== undefined && options.data.root !== undefined && options.data.root.sprout !== undefined && options.data.root.sprout.locale !== undefined) {
                var formLocale = options.data.root.sprout.locale;

                var result = formLocale == 'en';

                if (result) {
                    return options.fn(this);
                } else {
                    return options.inverse(this);
                }
            } else {
                return options.fn(this);
            }
        });
        Handlebars.registerHelper('es', function (options) {

            if (arguments.length !== 1) {
                throw new Error("Handlerbars Helper 'es' accepts no parameters.");
            }

            if (options !== undefined && options.data !== undefined && options.data.root !== undefined && options.data.root.sprout !== undefined && options.data.root.sprout.locale !== undefined) {
                var formLocale = options.data.root.sprout.locale;

                var result = formLocale == 'es';

                if (result) {
                    return options.fn(this);
                } else {
                    return options.inverse(this);
                }
            } else {
                return options.fn(this);
            }
        });

        var DateFormats = {
            short: "MM/DD/YYYY",
            weekday: "dddd, MMM D",
            medium: "MMM D",
            long: "dddd, MMM D, YYYY"
        };

        // Deprecated since version 0.8.0
        Handlebars.registerHelper("formatDate", function(datetime, format) {
            if (moment) {
                // can use other formats like 'lll' too
                format = DateFormats[format] || format;

                if (isNaN(datetime)) {
                    return datetime;
                }

                var datetimeTime = parseInt(datetime);

                return moment(datetimeTime).format(format);
            } else {
                return datetime;
            }
        });

        Handlebars.registerHelper("i18n", function(code, options) {


//            console.log("option: " + options);
//            console.dir(options);
//
//            console.log("messages: " + messages);
//            console.dir(messages);

            if (options.data.root && options.data.root.sprout && options.data.root.translations) {

                var formLocale = "en";

                if (options.data.root.sprout.locale) {
                    formLocale = options.data.root.sprout.locale;
                }

                console.log("i10n.code: " + code);
                console.log("i10n.formLocale: " + formLocale);

                if (formLocale && options.data.root.translations[code]) {
                    var translation = options.data.root.translations[code];
                    if (translation[formLocale]) {
                        var template = Handlebars.compile(translation[formLocale]);
                        return template(this);
                    }
                }

            }
        });

        Handlebars.registerHelper({
            eq: function (v1, v2) {
                return v1 === v2;
            },
            ne: function (v1, v2) {
                return v1 !== v2;
            },
            lt: function (v1, v2) {
                return v1 < v2;
            },
            gt: function (v1, v2) {
                return v1 > v2;
            },
            lte: function (v1, v2) {
                return v1 <= v2;
            },
            gte: function (v1, v2) {
                return v1 >= v2;
            },
            and: function () {
                var result = false;
                var foundTrue = false;
                var foundFalse = false;
                if (arguments) {
                    for (var i = 0; i < arguments.length; i++) {
                        if(typeof(arguments[i]) === "boolean"){
                            if (arguments[i] === true) {
                                foundTrue = true;
                            } else {
                                foundFalse = true;
                            }
                        }
                    }
                }
                return foundTrue && !foundFalse;
            },
            or: function () {
                var result = false;
                if (arguments) {
                    for (var i = 0; i < arguments.length; i++) {
                        if(typeof(arguments[i]) === "boolean"){
                            if (arguments[i] === true) result = true;
                        }
                    }
                }
                return result;
            }
        });



        Handlebars.registerHelper("xif", function (expression, options) {
            return Handlebars.helpers["x"].apply(this, [expression, options]) ? options.fn(this) : options.inverse(this);
        });

        Handlebars.registerHelper("x", function(expression, options) {
            var result;

            // you can change the context, or merge it with options.data, options.hash
            var context = this;

            // yup, i use 'with' here to expose the context's properties as block variables
            // you don't need to do {{x 'this.age + 2'}}
            // but you can also do {{x 'age + 2'}}
            // HOWEVER including an UNINITIALIZED var in a expression will return undefined as the result.
            with(context) {
                result = (function() {
                    try {
                        return eval(expression);
                    } catch (e) {
                        console.warn('•Expression: {{x \'' + expression + '\'}}\n•JS-Error: ', e, '\n•Context: ', context);
                    }
                }).call(context); // to make eval's lexical this=context
            }
            return result;
        });

        /*
         if you want access upper level scope, this one is slightly different
         the expression is the JOIN of all arguments
         usage: say context data looks like this:

         // data
         {name: 'Sam', age: '20', address: { city: 'yomomaz' } }

         // in template
         // notice how the expression wrap all the string with quotes, and even the variables
         // as they will become strings by the time they hit the helper
         // play with it, you will immediately see the errored expressions and figure it out

         {{#with address}}
         {{z '"hi " + "' ../this.name '" + " you live with " + "' city '"' }}
         {{/with}}
         */
        Handlebars.registerHelper("z", function () {
            var options = arguments[arguments.length - 1]
            delete arguments[arguments.length - 1];
            return Handlebars.helpers["x"].apply(this, [Array.prototype.slice.call(arguments, 0).join(''), options]);
        });

        Handlebars.registerHelper("zif", function () {
            var options = arguments[arguments.length - 1]
            delete arguments[arguments.length - 1];
            return Handlebars.helpers["x"].apply(this, [Array.prototype.slice.call(arguments, 0).join(''), options]) ? options.fn(this) : options.inverse(this);
        });



        /*
         More goodies since you're reading this gist.
         */

// say you have some utility object with helpful functions which you want to use inside of your handlebars templates

        util = {

            // a helper to safely access object properties, think ot as a lite xpath accessor
            // usage:
            // var greeting = util.prop( { a: { b: { c: { d: 'hi'} } } }, 'a.b.c.d');
            // greeting -> 'hi'

            // [IMPORTANT] THIS .prop function is REQUIRED if you want to use the handlebars helpers below,
            // if you decide to move it somewhere else, update the helpers below accordingly
            prop: function() {
                if (typeof props == 'string') {
                    props = props.split('.');
                }
                if (!props || !props.length) {
                    return obj;
                }
                if (!obj || !Object.prototype.hasOwnProperty.call(obj, props[0])) {
                    return null;
                } else {
                    var newObj = obj[props[0]];
                    props.shift();
                    return util.prop(newObj, props);
                }
            },

            // some more helpers .. just examples, none is required
            isNumber: function(n) {
                return !isNaN(parseFloat(n)) && isFinite(n);
            },
            daysInMonth: function (m, y) {
                y = y || (new Date).getFullYear();
                return /8|3|5|10/.test(m) ? 30 : m == 1 ? (!(y % 4) && y % 100) || !(y % 400) ? 29 : 28 : 31;
            },
            uppercaseFirstLetter: function (str) {
                str || (str = '');
                return str.charAt(0).toUpperCase() + str.slice(1);
            },
            hasNumber: function (n) {
                return !isNaN(parseFloat(n));
            },
            truncate: function (str, len) {
                if (typeof str != 'string') return str;
                len = util.isNumber(len) ? len : 20;
                return str.length <= len ? str : str.substr(0, len - 3) + '...';
            }
        };

// a helper to execute any util functions and get its return
// usage: {{u 'truncate' this.title 30}} to truncate the title
        Handlebars.registerHelper('u', function() {
            var key = '';
            var args = Array.prototype.slice.call(arguments, 0);

            if (args.length) {
                key = args[0];
                // delete the util[functionName] as the first element in the array
                args.shift();
                // delete the options arguments passed by handlebars, which is the last argument
                args.pop();
            }
            if (util.hasOwnProperty(key)) {
                // notice the reference to util here
                return typeof util[key] == 'function' ?
                    util[key].apply(util, args) :
                    util[key];
            } else {
                log.error('util.' + key + ' is not a function nor a property');
            }
        });

// a helper to execute any util function as an if helper,
// that util function should have a boolean return if you want to use this properly
// usage: {{uif 'isNumber' this.age}} {{this.age}} {{else}} this.dob {{/uif}}
        Handlebars.registerHelper('uif', function() {
            var options = arguments[arguments.length - 1];
            return Handlebars.helpers['u'].apply(this, arguments) ? options.fn(this) : options.inverse(this);
        });

// a helper to execute any global function or get global.property
// say you have some globally accessible metadata i.e
// window.meta = {account: {state: 'MA', foo: function() { .. }, isBar: function() {...} } }
// usage:
// {{g 'meta.account.state'}} to print the state

// or will execute a function
// {{g 'meta.account.foo'}} to print whatever foo returns
        Handlebars.registerHelper('g', function() {
            var path, value;
            if (arguments.length) {
                path = arguments[0];
                delete arguments[0];

                // delete the options arguments passed by handlebars
                delete arguments[arguments.length - 1];
            }

            // notice the util.prop is required here
            value = util.prop(window, path);
            if (typeof value != 'undefined' && value !== null) {
                return typeof value == 'function' ?
                    value.apply({}, arguments) :
                    value;
            } else {
                log.warn('window.' + path + ' is not a function nor a property');
            }
        });

// global if
// usage:
// {{gif 'meta.account.isBar'}} // to execute isBar() and behave based on its truthy or not return
// or just check if a property is truthy or not
// {{gif 'meta.account.state'}} State is valid ! {{/gif}}
        Handlebars.registerHelper('gif', function() {
            var options = arguments[arguments.length - 1];
            return Handlebars.helpers['g'].apply(this, arguments) ? options.fn(this) : options.inverse(this);
        });

// just an {{#each}} warpper to iterate over a global array,
// usage say you have: window.meta = { data: { countries: [ {name: 'US', code: 1}, {name: 'UK', code: '44'} ... ] } }
// {{geach 'meta.data.countries'}} {{this.code}} {{/geach}}

        Handlebars.registerHelper('geach', function(path, options) {
            var value = util.prop(window, arguments[0]);
            if (!_.isArray(value))
                value = [];
            return Handlebars.helpers['each'].apply(this, [value, options]);
        });

        $(document).on('click', '.dropdown-menu', function(e) {
            if ($(this).hasClass('keep-open-on-click')) { e.stopPropagation(); }
        });


    });

    function setActiveTabData(key, value) {
//        console.log("setActiveTabData");
        var activeTab = jQuerySprout(".sproutstudy-tab-li.active");
        if (activeTab !== undefined) activeTab.data(key, value);
    }

    function getActiveTabData(key) {
//        console.log("getActiveTabData");
        var activeTab = jQuerySprout(".sproutstudy-tab-li.active");
        if (activeTab !== undefined) return activeTab.data(key);
    }

    function saveNarrative(instanceId, callback) {
//        console.log("index.jsp.saveNarrative.instanceId: " + instanceId);
        if (angular.element(jQuerySprout("#studyControllerDiv")).scope() !== undefined) {
            angular.element(jQuerySprout("#studyControllerDiv")).scope().saveNarrative(instanceId, function(data) {
                callback(data);
            });
        }

    }

    function setPdfModalContent(content) {

        var iframePdfViewerContainer = document.getElementById("pdfViewContainer");

//        console.log("iframePdfViewerContainer: " + iframePdfViewerContainer);

        if (iframePdfViewerContainer != null) {
            iframePdfViewerContainer.src="data:application/pdf;base64,JVBERi0xLjQKJeLjz9MKMyAwIG9iago8PC9MZW5ndGggMTYzL0ZpbHRlci9GbGF0ZURlY29kZT4+c3RyZWFtCnicbc7BDoIwDAbg+56i3sbB0eFcNo5EiScT445cpowIwaDL9PkFgiaKSQ9N2v9r7yQzZCVBoQRTEoRlopga2jjnkAgwFaFHe721DvbWexvqp4tM02/iFOCS4Zig8D1IOJNviatR2rm27SD3rlz8IIJp8Q/pdbX+KILh9NLj1LhzSEcLCrqxwUFXQVb7cEmBYzyU1rqIZodwfmhryIG8AAhsPooKZW5kc3RyZWFtCmVuZG9iagoxIDAgb2JqCjw8L1BhcmVudCA0IDAgUi9Db250ZW50cyAzIDAgUi9UeXBlL1BhZ2UvUmVzb3VyY2VzPDwvRm9udDw8L0YxIDIgMCBSPj4+Pi9NZWRpYUJveFswIDAgNTk1IDg0Ml0+PgplbmRvYmoKOCAwIG9iago8PC9QYXJlbnQgNyAwIFIvRGVzdFsxIDAgUi9YWVogMjAgNzI0LjU4IDBdL1RpdGxlKFN1YmplY3Q6IEZyZWQgXChEYXRlIG9mIEJpcnRoOiAxMC8xMC8xOTk5XCkpPj4KZW5kb2JqCjcgMCBvYmoKPDwvUGFyZW50IDYgMCBSL0Rlc3RbMSAwIFIvWFlaIDIwIDc2MS4xMiAwXS9UaXRsZShIZWxsbyBGcmVkISkvQ291bnQgMS9MYXN0IDggMCBSL0ZpcnN0IDggMCBSPj4KZW5kb2JqCjYgMCBvYmoKPDwvUGFyZW50IDUgMCBSL0Rlc3RbMSAwIFIvWFlaIDIwIDgwNiAwXS9UaXRsZShTYW1wbGUgTmFycmF0aXZlKS9Db3VudCAyL0xhc3QgNyAwIFIvRmlyc3QgNyAwIFI+PgplbmRvYmoKNSAwIG9iago8PC9UeXBlL091dGxpbmVzL0NvdW50IDMvTGFzdCA2IDAgUi9GaXJzdCA2IDAgUj4+CmVuZG9iagoyIDAgb2JqCjw8L0Jhc2VGb250L0hlbHZldGljYS1Cb2xkL1R5cGUvRm9udC9FbmNvZGluZy9XaW5BbnNpRW5jb2RpbmcvU3VidHlwZS9UeXBlMT4+CmVuZG9iago0IDAgb2JqCjw8L1R5cGUvUGFnZXMvQ291bnQgMS9LaWRzWzEgMCBSXT4+CmVuZG9iago5IDAgb2JqCjw8L1R5cGUvQ2F0YWxvZy9PdXRsaW5lcyA1IDAgUi9QYWdlcyA0IDAgUj4+CmVuZG9iagoxMCAwIG9iago8PC9Qcm9kdWNlcihpVGV4dK4gNS41LjYgqTIwMDAtMjAxNSBpVGV4dCBHcm91cCBOViBcKEFHUEwtdmVyc2lvblwpKS9Nb2REYXRlKEQ6MjAxNjEyMjMxNjQ0MzMtMDUnMDAnKS9DcmVhdGlvbkRhdGUoRDoyMDE2MTIyMzE2NDQzMy0wNScwMCcpPj4KZW5kb2JqCnhyZWYKMCAxMQowMDAwMDAwMDAwIDY1NTM1IGYgCjAwMDAwMDAyNDUgMDAwMDAgbiAKMDAwMDAwMDc1OCAwMDAwMCBuIAowMDAwMDAwMDE1IDAwMDAwIG4gCjAwMDAwMDA4NTEgMDAwMDAgbiAKMDAwMDAwMDY5MyAwMDAwMCBuIAowMDAwMDAwNTgwIDAwMDAwIG4gCjAwMDAwMDA0NjkgMDAwMDAgbiAKMDAwMDAwMDM1NyAwMDAwMCBuIAowMDAwMDAwOTAyIDAwMDAwIG4gCjAwMDAwMDA5NjIgMDAwMDAgbiAKdHJhaWxlcgo8PC9Sb290IDkgMCBSL0lEIFs8MWExM2JkZGZlMzkxNWQwNmQ1OTJlZTc4NTUxNTc4NDc+PDFhMTNiZGRmZTM5MTVkMDZkNTkyZWU3ODU1MTU3ODQ3Pl0vSW5mbyAxMCAwIFIvU2l6ZSAxMT4+CiVpVGV4dC01LjUuNgpzdGFydHhyZWYKMTEyMAolJUVPRgo=";
        }


    }

    function removeActiveTabData(key) {
//        console.log("removeActiveTabData");
        var activeTab = jQuerySprout(".sproutstudy-tab-li.active");
        if (activeTab !== undefined) activeTab.removeData(key);
    }

    function addPaneContentForm(form, nonce) {
//        console.log("addPaneContentForm");
        var title = form.title;
        var instanceId = form.instanceId;

        jQuerySprout(".form-iframe-container").hide();

        jQuerySprout(".sproutstudy-content").hide();
        jQuerySprout(".sprout-study-form-narrative-split-frame").hide();

        if (jQuerySprout(".sproutstudy-tab-" + instanceId).length > 0) {
            jQuerySprout(".sprout-study-form-narrative-split-frame-" + instanceId).show();
        } else {

            var uneditable = form.uneditable;

            var content = '<div class="sprout-study-form-narrative-split-frame sprout-study-form-narrative-split-frame-' + instanceId + '"><div class="sproutstudy-content sproutstudy-split-frame-content sprout-study-drag-dimme sproutstudy-content-form sproutstudy-content-' + instanceId + '" id="' + instanceId + '"><iframe id="iframe-' + instanceId + '" name="iframe-' + instanceId + '" src="/prompt/?instanceId=' + instanceId + '&nonce=' + nonce + '&debug=true&showThanks=false&uneditable=' + uneditable + '" class="appFrame sproutStudyFrame" /></div><div class="sproutstudy-split-frame-content sproutstudy-split-frame-content-narrative sproutstudy-split-frame-content-narrative-' + instanceId + '"><legend>Narrative <span id="sprout-alert-div" class="alert alert-info alert-narrative-saved sprout-forms-saving-alert sprout-forms-saving-alert-portal-dummy hide" data-alert="alert" style="display: none;"><strong><i class="fa fa-save"></i> Narrative Auto-saved</strong></span><span class="label label-info sprout-narrative-save-success" style="margin-left: 5px; margin-top: -5px; display: none;"><i class="fa fa-save"></i> Narrative auto-saved.</span><button class="btn btn-danger sprout-study-narrative-content-save-button sprout-study-narrative-content-save-button-' + instanceId + '" style="display: none;" instanceId="' + instanceId + '"><i class="fa fa-save"></i> Save Narrative Changes</button><button class="btn btn-info btn-mini sprout-study-narrative-content-print-button sprout-study-narrative-content-print-button-' + instanceId + '" style="margin-left: 5px;" instanceId="' + instanceId + '"><i class="fa fa-print"></i> Print</button></legend><div class="sprout-study-narrative-content sprout-study-narrative-content-' + instanceId + '"></div><div class="sprout-study-template-content sprout-study-template-content-' + instanceId + '" style="display: none;" /><div class="sprout-study-template-scratch sprout-study-template-scratch-' + instanceId + '" style="display: none;"/></div></div>';
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

    function enableNarrative() {
//        console.log("enableNarrative");
        if (angular.element(jQuerySprout("#studyControllerDiv")).scope() !== undefined) {
            angular.element(jQuerySprout("#studyControllerDiv")).scope().onViewNarrative();
        }
    }

    function enableSplitNarrativeFrame(instanceId) {
//        console.log("enableSplitNarrativeFrame");
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
//        console.log("addTransformAdminContentForm");
        var title = form.title;
        var instanceId = form.instanceId;

        var uneditable = false;

        var content = '<div class="sprout-transform-content sprout-transform-content-form sprout-transform-content-' + instanceId + '" id="' + instanceId + '"><iframe id="iframe-' + instanceId + '" name="iframe-' + instanceId + '" src="/prompt/?instanceId=' + instanceId + '&nonce=' + nonce + '&debug=true&showThanks=false&uneditable=' + uneditable + '" class="appFrame sproutTransformFrame" /></div>';
        jQuerySprout("#sproutTransformFormContent").append(content);

    }

    function sproutFormsDoneCallback() {
//        console.log("sproutFormsDoneCallback");
        sproutFormsDoneInd = true;
    }

    function deleteTabNew(instanceId) {
//        console.log("deleteTab");
        var instanceId = jQuerySprout(".sproutstudy-tab-li.active").attr("instance");
        var form = jQuerySprout(".sproutstudy-tab-li.active").data("form");

        var destination = undefined;
        if (form) destination = form.destination;





        if (destination !== undefined && destination == 'HOME') {
//            console.log("deleteTab -- 1");
//            console.log("deletePaneContent 2");
//            if (!sproutFormsDoneInd) angular.element(jQuerySprout("#studyControllerDiv")).scope().onComposeMessage(form);
            var mutableInd = jQuerySprout("#iframe-" + instanceId).contents().find(".sprout-form-mutable-ind").val();

            if (mutableInd == 'true') {

//                console.log("deleteTab -- 4");

                jQuerySprout("#modal-wait-title").html("Saving and Closing form");
                jQuerySprout("#modal-wait-message").html("Saving and closing form....please wait...");
                jQuerySprout('#modal-wait').modal({
                    keyboard: false
                });

                jQuerySprout("#iframe-" + instanceId)[0].contentWindow.remoteSave(function (data) {
                    angular.element(jQuerySprout("#studyControllerDiv")).scope().enableSearch();
                });
            }

        } else {
//            console.log("deleteTab -- 2");
            if (instanceId != null && instanceId != 'home') {

//                console.log("deleteTab -- 3");

                var mutableInd = jQuerySprout("#iframe-" + instanceId).contents().find(".sprout-form-mutable-ind").val();

                if (mutableInd == 'true') {

//                    console.log("deleteTab -- 4");

                    jQuerySprout("#modal-wait-title").html("Saving and Closing form");
                    jQuerySprout("#modal-wait-message").html("Saving and closing form....please wait...");
                    jQuerySprout('#modal-wait').modal({
                        keyboard: false
                    });

                    jQuerySprout("#iframe-" + instanceId)[0].contentWindow.remoteSave(function(data) {
//                        jQuerySprout(".sproutstudy-tab-li.active").removeClass("active");
//                        jQuerySprout(".sprout-study-form-narrative-split-frame").hide();
//
//                        var sourceTab = jQuerySprout(".sproutstudy-tab-" + instanceId);
//                        var targetTab = jQuerySprout(".sproutstudy-tab-home");
//
//                        jQuerySprout(".sprout-study-form-narrative-split-frame-" + instanceId).remove();
//
//                        sourceTab.remove();
//                        targetTab.addClass("active");
//                        updateTransformButton(targetTab, true);
//
//                        jQuerySprout(".sproutstudy-content-home").show();
//                        jQuerySprout('#modal-wait').modal('hide');
                    });
                } else {
//                    console.log("deleteTab -- 5");
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
        }

        unlockForm(instanceId);

        angular.element(jQuerySprout("#studyControllerDiv")).scope().getAllForms();
        angular.element(jQuerySprout("#studyControllerDiv")).scope().$apply();

        sproutFormsDoneInd = false;
    }

    function deleteTab(instanceId) {
//        console.log("deleteTab");

        var destination = undefined;
        var form = undefined;
        var quickClose = false;


        var activeTab = jQuerySprout(".sproutstudy-tab-li.active");
        if (activeTab)

        if (instanceId === undefined || instanceId.length == 0) {
            quickClose = true;
            instanceId = jQuerySprout(".sproutstudy-tab-li.active").attr("instance");
            form = jQuerySprout(".sproutstudy-tab-li.active").data("form");
        } else {
            var instanceTab = jQuerySprout('.sproutstudy-tab-' + instanceId);
            if (instanceTab !== undefined) form = instanceTab.data("form");
        }

        if (form) destination = form.destination;

        var tabCount = jQuerySprout(".sproutstudy-tab-li").length;

//        console.log("deleteTab.tabCount: " + tabCount);
//        console.log("deleteTab.destination: " + destination);
//        console.log("deleteTab.tabCount: " + tabCount);

        if (destination !== undefined && destination == 'HOME' && tabCount == 3) {

//            console.log("deleteTab.position: 1");


//            console.log("deletePaneContent 2");
//            if (!sproutFormsDoneInd) angular.element(jQuerySprout("#studyControllerDiv")).scope().onComposeMessage(form);



            var mutableInd = jQuerySprout("#iframe-" + instanceId).contents().find(".sprout-form-mutable-ind").val();

            if (mutableInd == 'true') {
//                console.log("deleteTab.position: 2");
                jQuerySprout("#modal-wait-title").html("Closing form");
                jQuerySprout("#modal-wait-message").html("Closing form....please wait...");
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
                    angular.element(jQuerySprout("#studyControllerDiv")).scope().enableSearch();
                });
            } else {
//                console.log("deleteTab.position: 3");

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

                angular.element(jQuerySprout("#studyControllerDiv")).scope().enableSearch();
            }




        } else {
//            console.log("deleteTab.position: 4");

            if (instanceId != null && instanceId != 'home') {
//                console.log("deleteTab.position: 5");

                var mutableInd = jQuerySprout("#iframe-" + instanceId).contents().find(".sprout-form-mutable-ind").val();

                if (mutableInd == 'true') {
//                    console.log("deleteTab.position: 6");

                    jQuerySprout("#modal-wait-title").html("Closing form");
                    jQuerySprout("#modal-wait-message").html("Closing form....please wait...");
                    jQuerySprout('#modal-wait').modal({
                        keyboard: false
                    });

                    jQuerySprout("#iframe-" + instanceId)[0].contentWindow.remoteSave(function(data) {
                        jQuerySprout(".sproutstudy-tab-li.active").removeClass("active");
                        jQuerySprout(".sprout-study-form-narrative-split-frame").hide();

                        var sourceTab = jQuerySprout(".sproutstudy-tab-" + instanceId);
                        var targetTab = jQuerySprout(".sproutstudy-tab-home");

                        jQuerySprout(".sprout-study-form-narrative-split-frame-" + instanceId).remove();
                        if (activeTab) {
                            var activeInstanceId = activeTab.attr("instance");
                            if (activeInstanceId && activeInstanceId.length > 0) {
                                jQuerySprout(".sprout-study-form-narrative-split-frame-" + activeInstanceId).hide();
                            }
                        }

                        sourceTab.remove();
                        targetTab.addClass("active");
                        updateTransformButton(targetTab, true);

                        jQuerySprout(".sproutstudy-content-home").show();
                        jQuerySprout('#modal-wait').modal('hide');
                    });
                } else {
//                    console.log("deleteTab.position: 7");

                    jQuerySprout(".sproutstudy-tab-li.active").removeClass("active");
                    jQuerySprout("sprout-study-form-narrative-split-frame").hide();

                    var sourceTab = jQuerySprout(".sproutstudy-tab-" + instanceId);
                    var targetTab = jQuerySprout(".sproutstudy-tab-home");

                    jQuerySprout(".sprout-study-form-narrative-split-frame-" + instanceId).remove();
                    if (activeTab) {
                        var activeInstanceId = activeTab.attr("instance");
                        if (activeInstanceId && activeInstanceId.length > 0) {
                            jQuerySprout(".sprout-study-form-narrative-split-frame-" + activeInstanceId).hide();
                        }
                    }

                    sourceTab.remove();
                    targetTab.addClass("active");
                    updateTransformButton(targetTab, true);

                    jQuerySprout(".sproutstudy-content-home").show();
                    jQuerySprout('#modal-wait').modal('hide');
                }



            }
        }

//        console.log("deleteTab.position: 8");

        unlockForm(instanceId);

        angular.element(jQuerySprout("#studyControllerDiv")).scope().getAllForms();
        angular.element(jQuerySprout("#studyControllerDiv")).scope().$apply();

        sproutFormsDoneInd = false;
    }

    function getCohortSubjectSchema() {
        return angular.element(jQuerySprout("#studyControllerDiv")).scope().getCohortSubjectSchema();
    }

    function printNarrative(instanceId) {
//        console.log("printNarrative.instanceId: " + instanceId);
        $('#printFrame').contents().find('html').html($('.sprout-study-narrative-content-' + instanceId).html());
        $('#printFrame').get(0).contentWindow.print();
    }

    function sproutEnableReviewByInstanceId(instanceId) {
//        console.log("sproutEnableReviewByInstanceId.instanceId: " + instanceId);
    }

    function submittingCallback(action) {
        if (action === undefined) action = "Submitting";
//        console.log("submittingCallback....");
        jQuerySprout("#modal-wait-title").html(action + " Form");
        jQuerySprout("#modal-wait-message").html(action + " form....please wait...");
        jQuerySprout('#modal-wait').modal({
            keyboard: false,
            backdrop: 'static'
        });
    }

    function submittedCallback(location) {
//        console.log("submittedCallback.location: " + location);
//        jQuerySprout('#modal-wait').hide();
        jQuerySprout('#modal-wait').modal('hide');
    }

    function deletePaneContentNew(id) {
//        console.log("deletePaneContentNew")

        var instanceId = jQuerySprout(".sproutstudy-tab-li.active").attr("instance");
        var form = jQuerySprout(".sproutstudy-tab-li.active").data("form");

        var destination = undefined;
        if (form) destination = form.destination;

        if (!sproutFormsDoneInd) angular.element(jQuerySprout("#studyControllerDiv")).scope().onComposeMessage(form);
        angular.element(jQuerySprout("#studyControllerDiv")).scope().enableSearch();

        angular.element(jQuerySprout("#studyControllerDiv")).scope().getAllForms();

//        console.log("before apply");

        angular.element(jQuerySprout("#studyControllerDiv")).scope().$apply();

//        console.log("after apply");

        sproutFormsDoneInd = false;

    }

    function deletePaneContent(id) {
//        console.log("deletePaneContent: " + id);

        var instanceId = jQuerySprout(".sproutstudy-tab-li.active").attr("instance");
        var form = jQuerySprout(".sproutstudy-tab-li.active").data("form");

        var destination = undefined;
        if (form) destination = form.destination;

//        console.log("deletePaneContent 1");
//        console.log("deletePaneContent.instanceId: " + instanceId);
//        console.log("deletePaneContent.destination: " + destination);

        if (destination !== undefined && destination == 'HOME') {
//            console.log("deletePaneContent 2");
            if (!sproutFormsDoneInd) angular.element(jQuerySprout("#studyControllerDiv")).scope().onComposeMessage(form);
            angular.element(jQuerySprout("#studyControllerDiv")).scope().enableSearch();
        } else {
//            console.log("deletePaneContent 3");

            if (instanceId != null && instanceId != 'home') {
//                console.log("deletePaneContent 3.1");

                var sourceTab = jQuerySprout(".sproutstudy-tab-" + instanceId);
                var targetTab = jQuerySprout(".sproutstudy-tab-" + instanceId).prev();

//                console.log("deletePaneContent 3.2");

                var isIE = !!navigator.userAgent.match(/Trident./);
//                console.log("deletePaneContent.isIE: " + isIE);
                try {
//                    console.log("deletePaneContent 3.2.1");
                    if (isIE) {
                        jQuerySprout(".sprout-study-form-narrative-split-frame-" + instanceId).hide();
                        setTimeout(function () {
                            jQuerySprout(".sprout-study-form-narrative-split-frame-" + instanceId).remove();
                        }, 2000);
                    } else {
                        jQuerySprout(".sprout-study-form-narrative-split-frame-" + instanceId).remove();
                    }
//                    removeByClass("sprout-study-form-narrative-split-frame-" + instanceId);
//                    console.log("deletePaneContent 3.2.2");
                } catch (e) {
//                    console.log("deletePaneContent 3.2.3");

                    jQuerySprout(".sprout-study-form-narrative-split-frame-" + instanceId).hide();
//                    removeByClass("sprout-study-form-narrative-split-frame-" + instanceId);
//                    console.log("deletePaneContent 3.2.4");
                }

//                console.log("deletePaneContent 3.3");

                sourceTab.remove();

//                console.log("deletePaneContent 3.4");

                targetTab.addClass("active");

//                console.log("deletePaneContent 3.5");

                updateTransformButton(targetTab, true);

//                console.log("deletePaneContent 3.6");

                var targetInstanceId = targetTab.attr("instance");

//                console.log("deletePaneContent 3.7.targetInstanceId: " + targetInstanceId);

                jQuerySprout(".sprout-study-form-narrative-split-frame-" + targetInstanceId).show();
                jQuerySprout(".sproutstudy-content-" + targetInstanceId).show();

//                console.log("deletePaneContent 3.8");

//                if (targetInstanceId == 'home') jQuerySprout(".sproutstudy-content-" + targetInstanceId).show();

//                console.log("deletePaneContent 3.9");

                angular.element(jQuerySprout("#studyControllerDiv")).scope().getSubjectInbox();

//                console.log("deletePaneContent 3.10");

                if (!sproutFormsDoneInd) angular.element(jQuerySprout("#studyControllerDiv")).scope().onComposeMessage(form);

//                console.log("deletePaneContent 3.11");

            } else {
//                console.log("deletePaneContent 3.11.1");
                // demographic form was just submitted
                instanceId = jQuerySprout(".iframe-demographic-form-content").attr("instanceId");
                angular.element(jQuerySprout("#studyControllerDiv")).scope().setNewSubject(id, instanceId);
            }
//            console.log("deletePaneContent 3.12");

            unlockForm(instanceId);

//            console.log("deletePaneContent 3.13");

        }

        angular.element(jQuerySprout("#studyControllerDiv")).scope().getAllForms();
        angular.element(jQuerySprout("#studyControllerDiv")).scope().$apply();

        sproutFormsDoneInd = false;

//        console.log("deletePaneContent 3.14");


    }

    function unlockForm(instanceId) {
        angular.element(jQuerySprout("#studyControllerDiv")).scope().unlockForm(instanceId);
    }


    function stripNarrativeTextEditable(instanceId) {
        var scratch = jQuerySprout(".sprout-study-template-scratch-" + instanceId);

        scratch.html(jQuerySprout(".sprout-study-template-content-" + instanceId).html());

        scratch.find('[contenteditable]').contents().unwrap();
        scratch.find('handlebar').contents().unwrap();

        var scratchText = scratch.html();
        scratchText = scratchText.replace(/<!--<handlebar>/g, "").replace(/<\/handlebar>-->/g, "");

        return scratchText;
    }

    function stripNarrativeTextUneditable(instanceId) {
        if (jQuerySprout(".sprout-study-template-scratch-" + instanceId).length > 0) {
            var scratch = jQuerySprout(".sprout-study-template-scratch-" + instanceId);

            scratch.html(jQuerySprout(".sprout-study-narrative-content-" + instanceId).html());

            scratch.find('[contenteditable]').contents().unwrap();
            scratch.find('handlebar').contents().unwrap();

            var scratchText = scratch.html();
            scratchText = scratchText.replace(/<!--<handlebar>/g, "").replace(/<\/handlebar>-->/g, "");

            return scratchText;
        }
        return undefined;
    }


    function updateTransformButton(targetTab, apply) {
        var instanceId = targetTab.attr("instance");
        var template = targetTab.data("template");

        if (angular.element(jQuerySprout("#studyControllerDiv")).scope() !== undefined) {
            angular.element(jQuerySprout("#studyControllerDiv")).scope().showNarrativeButton(template !== undefined && template.key !== undefined && template !== null && template.key !== null, instanceId);
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
            if (formCallbackCatalog[instanceId]) {
                var callbackItem = formCallbackCatalog[instanceId];
                callbackItem.hideSproutControlButtons();
            }
        }
    }

    function clearHighlightsFromJSONModel() {
        jQuerySprout(".sprout-transform-model-highlight").removeClass("sprout-transform-model-highlight");
    }

    function highlightJSONModel(sproutJsonPath) {
        if (sproutJsonPath !== undefined) {
            keyParts = sproutJsonPath.split(".");
            var keyPathLong = "";
            for (var i=0;i<keyParts.length;i++) {
                var keyPart = keyParts[i];
                if (keyPart !== undefined && keyPart !== 'repeat' && keyPart !== 'composite') {
                    if (keyPathLong.length > 0) keyPathLong += "-";
                    keyPathLong += keyPart
                }
            }

            jQuerySprout("." + keyPathLong).addClass("sprout-transform-model-highlight");
            try {
                var sproutTransformModelContent = jQuerySprout("#sproutTransformModelContent");
                if (sproutTransformModelContent !== undefined) {
                    jQuerySprout(sproutTransformModelContent).scrollTo("." + keyPathLong);
                }
            } catch (e) {};
        }
    }

    function formSyncCallback(instanceId, loadingInd) {
        if (angular.element(jQuerySprout("#studyControllerDiv")).scope() !== undefined) {
//            console.log("formSyncCallback");
            setSproutTransformTemplate(null, instanceId);

            if (jQuerySprout(".sproutstudy-split-frame-content-narrative-" + instanceId).filter(":visible").length == 0 && !loadingInd) {
                if (formCallbackCatalog[instanceId]) {
                    var callbackItem = formCallbackCatalog[instanceId];
                    callbackItem.narrativeUpdate("");
                }
            }
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
//        console.log("clearAllFormTabs");
        jQuerySprout(".sproutstudy-tab-li.active").removeClass("active");
        var homeTab = jQuerySprout(".sproutstudy-tab-home");
        homeTab.addClass("active");
        updateTransformButton(homeTab);
            jQuerySprout(".sprout-study-form-narrative-split-frame").hide();
//            jQuerySprout(".sprout-study-form-narrative-split-frame").remove();
//        removeByClass("sprout-study-form-narrative-split-frame");
//        hideByClass("sprout-study-form-narrative-split-frame");

        removeByClass("sproutstudy-tab-form");
    }

    function removeByClass(className) {
        var x = document.getElementsByClassName(className);

        if (x && x.length > 0) {
//            console.log(className + ".length: " + x.length);

            var i;
            for (i = 0; i < x.length; i++) {
                var div = x[i];
                div.parentNode.removeChild(div);
            }
        }
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

    function focusOnLastRow(target) {
        $(target).focus();
    }
    function highlightRow(row) {
//        console.log("row.class.3: " + row.attr("class"));
        if (row !== undefined) row.effect('pulsate');
    }

    function sizeAppFrame() {
//        console.log("sizeAppFrame");
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
//        console.log("sizeTransformPane");

        var instanceId = jQuerySprout(".sproutstudy-tab-li.active").attr("instance");

//        console.log("sizeTransformPane.instanceId: " + instanceId);

        var parentPaneWidth = jQuerySprout(".sprout-study-form-narrative-split-frame-" + instanceId).width();
        var formPaneWidth = jQuerySprout(".sproutstudy-content-" + instanceId).width();
        var narrativePaneWidth = jQuerySprout(".sproutstudy-split-frame-content-narrative-" + instanceId).width();

        var narrativePaneWidthNew = parentPaneWidth - formPaneWidth;

        if (narrativePaneWidthNew != narrativePaneWidth) {
            jQuerySprout(".sproutstudy-split-frame-content-narrative-" + instanceId).width(narrativePaneWidthNew);
        }

        var modelToolbarHeight = $(".sprout-transform-model-toolbar").height();
        var narrativeToolbarHeight = $(".sprout-transform-narrative-toolbar").height();

        $(".sproutTransformFrame").height($(".sprout-transform-form-pane").height());
        $(".sprout-transform-template-editor").height($(".sprout-transform-template-pane").height());
        $("#sproutTransformNarrativeContent").height($(".sprout-transform-narrative-pane").height() - narrativeToolbarHeight - 40);
        $("#sproutTransformModelContent").height($(".sprout-transform-model-pane").height() - modelToolbarHeight - 50);
    }

    var formCallbackCatalog = {};

    function registerSproutFormsCallbackMethods(getSerializedArray, resetSignatures, paths, narrativeUpdate, hideSproutControlButtons, instanceId) {
//        console.log("sproutStudy.registerSproutFormsCallbackMethods.instanceId: " + instanceId);
        var callbackItem = {};
        if (formCallbackCatalog[instanceId]) {
            callbackItem = formCallbackCatalog[instanceId];
        }
        callbackItem["getSerializedArray"] = getSerializedArray;
        callbackItem["resetSignatures"] = resetSignatures;
        callbackItem["narrativeUpdate"] = narrativeUpdate;
        callbackItem["hideSproutControlButtons"] = hideSproutControlButtons;
        callbackItem["paths"] = paths;
        formCallbackCatalog[instanceId] = callbackItem;
    }

    function getNarrativeModelVerbose(instanceId) {
//        console.log("getNarrativeModelVerbose");
        if (instanceId == undefined || instanceId == null) {
            var activeTab = jQuerySprout(".sproutstudy-tab-li.active");
            instanceId = activeTab.attr("instance");
        }

        if (formCallbackCatalog[instanceId]) {
            var verbose = true;
            var callbackItem = formCallbackCatalog[instanceId];
            var narrativeModel = callbackItem.getSerializedArray(verbose);
            //console.log("model: " + JSON.stringify(narrativeModel, null, 4));
            return narrativeModel;
        }
    }

    function getNarrativeModel(instanceId) {
//        console.log("getNarrativeModel");
        if (instanceId == undefined || instanceId == null) {
            var activeTab = jQuerySprout(".sproutstudy-tab-li.active");
            instanceId = activeTab.attr("instance");
        }

        if (formCallbackCatalog[instanceId]) {
            var callbackItem = formCallbackCatalog[instanceId];
            var narrativeModel = callbackItem.getSerializedArray();
//            console.log(JSON.stringify(narrativeModel));
            var paths = callbackItem.paths();

            if (angular.element(jQuerySprout("#transformControllerDiv")).scope() !== undefined && paths !== undefined) {
                angular.element(jQuerySprout("#transformControllerDiv")).scope().setPaths(paths);
                angular.element(jQuerySprout("#transformControllerDiv")).scope().applyIfPossible();
            }

//            console.log("getNarrativeModel.model: " + JSON.stringify(narrativeModel, null, 4));
            return narrativeModel;
        }
    }



    function compileTemplate() {
//        console.log("compileTemplate");
        var source = angular.element(jQuerySprout("#transformControllerDiv")).scope().getTemplateFromEditor();
        var model = angular.element(jQuerySprout("#transformControllerDiv")).scope().getModel();

        if (typeof model != 'string') {
            json = JSON.stringify(model, undefined, 2);
//            console.log("compileTemplate.model: " + json);
//        } else {
//            console.log("compileTemplate.model: " + model);
        }

//        console.log("compileTemplate.template: " + source);

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
//        console.log("updateSproutTransformModelView");

//        console.log("updateSproutTransformModelView.model: " + model);
//
//        if (typeof model === 'string' || model instanceof String) {
//            var modelTmp = JSON.parse(model);
//            if (modelTmp && modelTmp.sprout) {
//                delete modelTmp.sprout['submissionDate'];
//                delete modelTmp.sprout['sprout%submissionDate'];
//                modelTmp.sprout['sprout%submissionDate'] = new Date().getTime();
//            }
//            model = JSON.stringify(modelTmp);
//        } else {
//            if (model && model.sprout) {
//                delete model.sprout['submissionDate'];
//                delete model.sprout['sprout%submissionDate'];
//                model.sprout['sprout%submissionDate'] = new Date().getTime();
//            }
//        }
        
        
        jQuerySprout("#sproutTransformModelContent").html(syntaxHighlight(model));
        jQuerySprout(".sprout-transform-key").off('dblclick');
        jQuerySprout(".sprout-transform-key").on('dblclick', function() {
            var sproutJsonPath = jQuerySprout(this).attr("sprout-json-path");
//            console.log("sproutJsonPath: " + sproutJsonPath);
            jQuerySprout("#handlebarjs-element-path").val("{{" + sproutJsonPath + "}}");
            jQuerySprout('#modal-model-element').modal({
                keyboard: true
            });
        });
    }

    function syntaxHighlightLegacy(json) {
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
    function syntaxHighlight(json) {
        if (typeof json != 'string') {
            json = JSON.stringify(json, undefined, 2);
        }
        if (json !== undefined) {
            json = json.replace(/&/g, '&amp;').replace(/</g, '&lt;').replace(/>/g, '&gt;');
            return json.replace(/("(\\u[a-zA-Z0-9]{4}|\\[^u]|[^\\"])*"(\s*:)?|\b(true|false|null)\b|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?)/g, function (match) {
                var cls = 'sprout-transform-model-type-number';
                var key = '';
                var sproutJsonPath = '';
                var value = match;
                if (/^"/.test(match)) {
                    if (/:$/.test(match)) {
                        cls = 'sprout-transform-model-type-key sprout-transform-key';
                        key = match.substring(1, match.length - 2).replace(/\%/g,'-');
                        sproutJsonPath = match.substring(1, match.length - 2).replace(/\%/g,'.');
                        var parts = match.split('%');
                        if (parts.length > 1) {
                            value = '"' + parts[parts.length - 1];
                        } else {
                            value = match;
                        }
                    } else {
                        cls = 'sprout-transform-model-type-string';
                    }
                } else if (/true|false/.test(match)) {
                    cls = 'sprout-transform-model-type-boolean';
                } else if (/null/.test(match)) {
                    cls = 'sprout-transform-model-type-null';
                }

                return '<span class="' + cls + ' sprout-transform-model-handle ' + key + '" sprout-json-path="' + sproutJsonPath + '">' + value + '</span>';
            });
        }
    }

    function makeNarrativeTextEditable(instanceId) {
        makeTextEditable(jQuerySprout(".sprout-study-template-content-" + instanceId));
    }

    function makeTextEditable(element) {
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
                    if ($.trim($(this).text()).length > 0) {
//						   console.log("text: " + $(this).text());
                        var guid = generateUUID();
                        $(this).wrap('<span class="' + guid + '" syncKey="' + guid + '" contenteditable="true" sproutNarrativeEditable="true" />');
                    }
                });

        var tagName = $(this).prop("tagName");

        if ((tagName === undefined || tagName !== 'HANDLEBAR') && $(this).find("handlebar").length > 0) {
            setTimeout(makeTextEditable($(this)), 5);
        }
    }

    function setSproutTransformTemplate(source, instanceId) {

//        console.log("setSproutTransformTemplate978.newSource: " + source);

        if (source === undefined || source === null) {
            source = $(".sprout-study-template-content-" + instanceId).html();
        } else {
//            source = source.replace( /\{\{(.*?)\}\}/g, "<!--<handlebar>{{$1}}</handlebar>-->");
            source = source.replace(/\{\{(\#.*?)\}\}/g, "<!--<handlebar>{{$1}}</handlebar>-->");
            source = source.replace(/\{\{(\/.*?)\}\}/g, "<!--<handlebar>{{$1}}</handlebar>-->");
            source = source.replace(/\{\{(?!else)([a-z].*?)\}\}/g, "<handlebar>{{$1}}</handlebar>");
            source = source.replace(/\{\{else\}\}/g, "<!--<handlebar>{{else}}</handlebar>-->");

            jQuerySprout(".sprout-study-template-content-" + instanceId).html(source);
//            console.log("setSproutTransformTemplate978.new template1: " + jQuerySprout(".sprout-study-template-content-" + instanceId).html());

//            console.log("978: setSproutTransformTemplate.1");
            if (typeof getActiveTabData !== 'undefined') {
                var activeForm = getActiveTabData().form;
//                console.log("978: setSproutTransformTemplate.activeForm: " + JSON.stringify(activeForm));
                if (activeForm && !activeForm.complete) {
//                    console.log("978: setSproutTransformTemplate.activeForm: making narrative text editable...");
                    makeNarrativeTextEditable(instanceId);
                }
            }
//            getActiveTabData().form.complete
//            if (editMode)


//            console.log("setSproutTransformTemplate978.new template2: " + jQuerySprout(".sprout-study-template-content-" + instanceId).html());
        }

//        console.log("before getNarrativeModel...");

//        console.log("setSproutTransformTemplate978.3: " + $(".sprout-study-template-content-" + instanceId).html());

        if ($(".sprout-study-template-content-" + instanceId).html() !== undefined) {


//            console.log("inside..... sprout-study-template-content-...");

            var model = getNarrativeModel(instanceId);

            var template = Handlebars.compile($(".sprout-study-template-content-" + instanceId).html());

//            console.log("setSproutTransformTemplate978.template: " + $(".sprout-study-template-content-" + instanceId).html());

            try {
                var narrative = template(model);

//            console.log("setSproutTransformTemplate.narrative: " + narrative);

                jQuerySprout("#sproutTransformNarrativeContent").html(narrative);
                jQuerySprout(".sprout-study-narrative-content-" + instanceId).html(narrative);


                if (formCallbackCatalog[instanceId]) {
                    var callbackItem = formCallbackCatalog[instanceId];
                    if (narrative !== undefined && narrative.length > 0) {
                        callbackItem.narrativeUpdate(escape(narrative));
                    }
                }

                jQuerySprout(".sprout-study-narrative-content-" + instanceId).off('focus blur keyup paste change', '[contenteditable]');

                jQuerySprout(".sprout-study-narrative-content-" + instanceId).on('focus', '[contenteditable]', function () {
                    var $this = $(this);
                    $this.data('before', $this.html());
                    return $this;
                }).on('blur keyup paste', '[contenteditable]', function () {
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
                }).on('change', '[contenteditable]', function () {
//                jQuerySprout(".sprout-study-narrative-content-save-button-" + instanceId).show();
//                    console.log("978: enable save narrative button...");

                    if (angular.element(jQuerySprout("#studyControllerDiv")).scope() !== undefined) {
                        angular.element(jQuerySprout("#studyControllerDiv")).scope().onHasNarrativeChanges(instanceId);
                    }
                });

                jQuerySprout(".sproutstudy-split-frame-content-narrative-" + instanceId).off('click', '.sprout-study-narrative-content-save-button').on('click', '.sprout-study-narrative-content-save-button', function (event) {
                    var instanceId = jQuerySprout(this).attr("instanceId");
                    if (angular.element(jQuerySprout("#studyControllerDiv")).scope() !== undefined) {
                        angular.element(jQuerySprout("#studyControllerDiv")).scope().onSyncNarrative(function (result, message) {
                            if (result) {
                                if (formCallbackCatalog[instanceId]) {
                                    var callbackItem = formCallbackCatalog[instanceId];
                                    callbackItem.resetSignatures();
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
    }

    function syncNarrativeTemplate(instanceId) {

//        console.log("765: syncNarrativeTemplate.before: " + $(".sprout-study-template-content-" + instanceId).html());

//        var narrativeParts = getNarrativeParts(instanceId);
//        if (narrativeParts.length > 0) {
//            $(".sprout-study-template-content-" + instanceId).find("[contenteditable]").each(function(index, element) {
//                if (jQuerySprout(this).find("[sproutnarrativeeditable='true']").length == 0) {
//                    $(this).html(narrativeParts[index]);
//                }
//            });
//        }

        $(".sprout-study-narrative-content-" + instanceId).find("[contenteditable]").each(function() {

//            console.log("765: syncNarrativeTemplate: " + $(this).html());


            $(".sprout-study-template-content-" + instanceId).find("." + $(this).attr("syncKey")).html($(this).html());
        });

//        console.log("syncNarrativeTemplate.after: " + $(".sprout-study-template-content-" + instanceId).html());

        return $(".sprout-study-template-content-" + instanceId).html();
    }

    function getNarrativeParts(instanceId) {
        var narrativeParts = [];
//        $(".sprout-study-narrative-content-" + instanceId).find("[sproutnarrativeeditable='true']").each(function(index, element) {
        $(".sprout-study-narrative-content-" + instanceId).find("[contenteditable]").each(function(index, element) {
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

    function displayErrorModal(title, message, callback) {
        jQuerySprout("#modal-error-title").html(title);
        jQuerySprout("#modal-error-message").html(message);
        jQuerySprout('#modal-error').modal({
            keyboard: false
        });
        sproutFormsDoneInd = true;
        deletePaneContent(1);
        if (callback !== undefined && typeof callback == 'function') callback();

    }

</script>

<script src="/sproutassets/scripts/idletimer/jquery.idletimer.js" type="text/javascript"></script>
<script src="/sproutassets/scripts/idletimer/jquery.idletimeout.js" type="text/javascript"></script>
<script type="text/javascript">
    var contextPath = "<%=request.getContextPath()%>";
    var timeoutSeconds = 3600;

    var hasActivity = false;

    function tickleIdleTimer() {
        hasActivity = true;
    }

    var handleUserEvent = function() {
        hasActivity = true;
    }

    setInterval(function () {
        if (hasActivity) {
            hasActivity = false;
            if (typeof parent.tickleIdleTimer === "function") {
                parent.tickleIdleTimer();
            } else {
                $(document).trigger("keydown");
            }
        }
    }, 500);

    jQuerySprout(document).on('mousemove', handleUserEvent);
    jQuerySprout(document).on('keydown', handleUserEvent);
    jQuerySprout(document).on('DOMMouseScroll', handleUserEvent);
    jQuerySprout(document).on('mousewheel', handleUserEvent);
    jQuerySprout(document).on('mousedown', handleUserEvent);

    var keepAlive = function(app) {
        $.idleTimer('keepAlive', {timeout: (timeoutSeconds * 1000)});
    }

    $(document).ready(function() {

        if (!sproutAdminInd()) {
            $.idleTimeout('#modal-timeout', '#idletimeout-resume', {
                idleAfter: timeoutSeconds,
//            pollingInterval: 60,
                pollingInterval: 30,
                failedRequests: 6,
                AJAXTimeout: 2000,
                keepAliveURL: '<%=request.getContextPath()%>/public/keepalive.jsp',
                serverResponseEquals: 'OK',
                onTimeout: function() {
                    $('#modal-timeout').modal('hide');
                    window.location = "<%=request.getContextPath()%>/logout";
                },
                onExpiration: function() {
                    alert("Your session has expired.  You will now be logged out.");
                    window.location = "<%=request.getContextPath()%>/logout";
                },
                onError: function() {
                    alert("SproutStudy has experienced an error and is no logger available.  You will now be logged out.");
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
        }

    });

</script>

<div class="modal modal-error hide fade in modal-200-600" id="modal-error" style="display: none;" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" options="modalSmallOpts" aria-hidden="true">
    <div class="modal-header">
        <h3 id="modal-error-title"></h3>
    </div>
    <div class="modal-body-short">
        <p>
        <h4 id="modal-error-message"></h4>
        </p>
    </div>
    <div class="modal-footer">
        <a href="#" data-dismiss="modal"  class="btn btn-danger">Close</a>
    </div>
</div>

<div class="modal modal-model hide fade in modal-200-600" id="modal-model-element" style="display: none;" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" options="modalSmallOpts" aria-hidden="true">
    <div class="modal-header">
        <h3>HandlebarJS Path</h3>
    </div>
    <div class="modal-body-short">
        <p>
            <textarea id="handlebarjs-element-path" style="width: 750px; height: 50px; font-family: Courier, monospace; font-size: 1.3em; font-weight: bold; color: blue; background-color: #efefef;"></textarea>
        </p>
    </div>
    <div class="modal-footer">
        <a href="#" data-dismiss="modal"  class="btn btn-primary">Close</a>
    </div>
</div>

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

<div id="sproutTransformTemplate" style="visibility: hidden; position:absolute;" />

<iframe id="printFrame" src="about:blank" height="0" width="0" style="border: 0px; font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif;" >
</iframe>

</body>
</html>
