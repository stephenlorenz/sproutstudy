'use strict';

angular.module('sproutStudyApp').factory('networkService', function ($http, $log, sproutStudyProperties) {

	var serialize = function(object) {
		var urlParams = "";
		for (var key in object) {
			if (object.hasOwnProperty(key)) {
				urlParams += urlParams.length == 0 ? key + "=" + escape(object[key]) : "&" + key + "=" + escape(object[key]); 
			}
		}
		return urlParams;
	};

	return {
		generateUrl: function (method, params) {

			var urlParam = "";

			if (params !== null && params !== undefined) {
				if ($.isArray(params)) {
					$.each(params, function(index, param) {
						urlParam += (urlParam.length == 0 ? "?" : "&") + serialize(param);
					});
				} else {
					urlParam += (urlParam.length == 0 ? "?" : "&") + serialize(params);
				}
			}
			var url = sproutStudyProperties.serverUrl + "/" + method + urlParam;
//			$log.log("networkService.generateUrl.url: " + url);
			return url;
		}
        , generateEnrollmentUrl: function (method, params) {

            var urlParam = "";

            if (params !== null && params !== undefined) {
                if ($.isArray(params)) {
                    $.each(params, function(index, param) {
                        urlParam += (urlParam.length == 0 ? "?" : "&") + serialize(param);
                    });
                } else {
                    urlParam += (urlParam.length == 0 ? "?" : "&") + serialize(params);
                }
            }
            var url = sproutStudyProperties.enrollmentUrl + "/" + method + urlParam;
//			$log.log("networkService.generateUrl.url: " + url);
            return url;
        }

    };



});
