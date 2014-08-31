frontDesk.controller('formsController', 
function ($scope, $location, $routeParams, formsModel) {
	var forms = formsModel.getForms();
	$scope.forms = forms;
	console.log("forms.size: " + forms[0].title);
	
	$scope.onPush = function(instanceId) {
		$location.path('/push/' + instanceId);
	};
	
});
