 'use strict';

angular.module('jobvacancyApp')
    .factory('notificationInterceptor', function ($q, AlertService) {
        return {
            response: function(response) {
                var alertKey = response.headers('X-jobvacancyApp-alert');
                if (angular.isString(alertKey)) {
                    AlertService.success(alertKey, { param : response.headers('X-jobvacancyApp-params')});
                }
                return response;
            },
        };
    });