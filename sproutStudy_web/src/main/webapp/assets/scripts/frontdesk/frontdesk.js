var frontDeskPing =	function() {
	$.getJSON(frontDeskApiUrl + '/ping?callback=?', {
		serverToken: frontDeskServerToken,
		clientToken: frontDeskClientToken
	}, 
	function(data){
		if (data.message != 'ACK') console.log("ERROR: Ping failed!");
	}
	);
}

setInterval(function(){frontDeskPing();}, 20000);

var frontDeskGetAppointments =	function($scope) {
	$scope.appointments = [];
	$.getJSON(frontDeskApiUrl + '/getAppointments?callback=?', {
		serverToken: frontDeskServerToken,
		clientToken: frontDeskClientToken
	}, 
	function(data){
		$scope.appointments = data.appointments;
		console.log("appointments.size: " + $scope.appointments.length);
	      $scope.loaded = true;
	}
	);
}


