angular.module('sproutStudyApp')
    .service('localeModel', function() {
	this.getLocales = function() {
		return [{
			code: 'en',
			name: 'English'
		}, {
			code: 'es',
			name: 'Spanish'
		}
		];
	};
});
