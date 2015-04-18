'use strict';

angular.module('sproutStudyApp')
    .directive('narrative', function ($compile) {

    var getTemplate = function(scope) {

        //console.log("narrativeDirective.getTemplate()..." + scope.template);

        //var template = '<div contenteditable="true">This is a sample narrative for the signature test form.</div><br/><div><span contenteditable="true">Technician Partners UID: </span>{{model.signature.technician}}.</div><div><span contenteditable="true">Cardiologist Partners UID: </span>{{model.signature.cardiologist}}.</div><div><span contenteditable="true">Radiologist Partners UID: </span>{{model.signature.radiologist}}.</div><div ng-show="model.signature.test_field != \'\'"><br/><span contenteditable="true">Test Field: </span>{{model.signature.test_field}}.</div>';
        // FIXME add logic here to retrieve the template from the SproutTransformService
//        return template;
        return scope.template;
    }

    var linker = function(scope, element, attrs) {
        element.html(getTemplate(scope)).show();
        $compile(element.contents())(scope);
    }

    return {
        restrict: "E",
        link: linker,
        scope: {
            model:'=',
            template:'='
        }
    };
});
