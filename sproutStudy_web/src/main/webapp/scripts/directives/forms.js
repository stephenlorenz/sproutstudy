'use strict';

angular.module('sproutStudyApp')
  .directive('forms', function ($http, $compile) {
        return function($scope, element, attrs, compile) {
            element.bind("click", function() {
                var formDetail = element.next("tr.form-detail");
                if (!formDetail.hasClass("form-detail-dummy")) {
                    formDetail.toggle();
                }
            });

            $scope.$watch(attrs, function() {
                var params = eval("(" + attrs.forms + ')');
                var appointment = params.appointment;

                if (appointment.forms.length > 0) {
                    var html;
                    $http.get(frontOfficeResourceBase + 'views/formsView.html?clearCache=4').success(function(html) {
                        element.addClass("appointment");
                        var htmlCompiled = $compile(html)($scope);
                        element.after(htmlCompiled);
                    });
                } else {
                    html += '<tr class="form-detail form-detail-dummy" style="display: none;"></tr>';
                }
            });
        }
  });
