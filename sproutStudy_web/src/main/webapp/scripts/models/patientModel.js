angular.module('sproutStudyApp')
    .service('patientModel', function() {
	this.getPatient = function() {
		return {
			mrn: '1232145',
			firstName: 'Stephen',
            middleName: 'Charles',
            lastName: 'Lorenz',
            address1: '10 Merrill St',
            address2: '',
            city: 'West Newbury',
            state: 'MA',
            zip: '01985',
            dob: '10/18/1972',
            sex: 'M',
            practice: 'Lurie Center',
            program: '',
            provider: '',
            userType: '',
            username: '',
            usernameVerify: '',
            email: '',
            emailVerify: '',
            optOut: 'false'

        };
	};
});
