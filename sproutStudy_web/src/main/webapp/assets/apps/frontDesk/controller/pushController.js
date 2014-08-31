frontDesk.controller('pushController', 
function ($scope, $location, $routeParams, formsModel) {
	var instanceId = $routeParams.id;
	
console.log("push instance id:" + instanceId);	
	
	$location.path('/');
});
