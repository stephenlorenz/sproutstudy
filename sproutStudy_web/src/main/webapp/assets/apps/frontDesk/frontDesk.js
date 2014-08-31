/**
 * @author slorenz
 */

var formsView = "<table class=\"table table-bordered\">\
	<tr ng-repeat='form in forms | orderBy:\"title\":false' >\
		<td>{{form.id}}</td>\
		<td>{{form.title}}</td>\
		<td><button class=\"btn btn-mini\" ng-click='onPush(form.id)' >Push</button></td>\
	</tr>\
</table>";



var frontDeskConfig = function($routeProvider) {
	$routeProvider
		.when('/', {
			controller: 'formsController',
			template: formsView
		})
		.when('/push/:instanceId', {
			controller: 'pushController',
			templateUrl: formsView			
		})
	;
};
var frontDesk = angular.module('frontDesk', []).config(frontDeskConfig);
