/* globals $ */
'use strict';

angular.module('jobvacancyApp')
    .directive('jobvacancyAppPagination', function() {
        return {
            templateUrl: 'scripts/components/form/pagination.html'
        };
    });
