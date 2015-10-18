'use strict';

angular.module('jobvacancyApp')
    .factory('Register', function ($resource) {
        return $resource('api/register', {}, {
        });
    });


